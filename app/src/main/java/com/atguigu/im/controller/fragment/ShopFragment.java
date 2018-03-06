package com.atguigu.im.controller.fragment;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.atguigu.im.R;
import com.atguigu.im.controller.activity.SearchActivity;
import com.atguigu.im.homeadapter.ChannelBean;
import com.atguigu.im.homeadapter.HomeFragmentAdapter;
import com.atguigu.im.model.Model;
import com.atguigu.im.utils.Constant;
import com.atguigu.im.utils.SortUtil;
import com.atguigu.im.utils.SpUtils;
import com.hyphenate.easeui.ui.EaseBaiduMapActivity;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.List;

import static android.content.Context.INPUT_METHOD_SERVICE;
import static com.atguigu.im.utils.SpUtils.Location;

/**
 * Created by ZJNU-Hmz on 2017/10/10.
 */

public class ShopFragment extends BaseFragment {
    private static final int REQUEST_CODE_MAP = 111;

    private RecyclerView rvHome;
    private ChannelBean.ResultBean resultBean;
    private HomeFragmentAdapter adapter;
    private TextView txt_city;
    private EditText et_search;
    private String finalLocation = "重新获取";
    private LinearLayout ll_all;

    @Override
    public View initView() {
        View view = View.inflate(mcontext, R.layout.fragment_shop, null);
        rvHome = view.findViewById(R.id.rv_home);
        txt_city = view.findViewById(R.id.txt_city);
        et_search = view.findViewById(R.id.et_search);

        String string = SpUtils.getInstance().getString(Location, finalLocation);
        txt_city.setText(string);
        initListener();
        return view;
    }


    @Override
    public void initData() {
        super.initData();
        getDataFromNet();


    }

    private void getDataFromNet() {
        Model.getInstance().getGlobalThreadPool().execute(new Runnable() {
            @Override
            public void run() {
                try {
                    Model.getInstance().getGlobalThreadPool().execute(new Runnable() {
                        @Override
                        public void run() {
                            String url = Constant.GETRECOMMEND;
                            OkHttpUtils
                                    .get()
                                    .url(url)
                                    .build()
                                    .execute(new StringCallback() {
                                        @Override
                                        public void onError(okhttp3.Call call, Exception e, int id) {

                                        }

                                        @Override
                                        public void onResponse(String response, int id) {
                                            Log.e("++++++++++", response.toString());
                                            procssData(response);
                                        }
                                    });

                        }
                    });
                    // 校验
                } catch (Exception e) {

                }
            }
        });
    }

    private void procssData(String response) {
        ChannelBean resultBeanData = JSON.parseObject(response, ChannelBean.class);
        resultBean = resultBeanData.getResult();
        if (resultBean != null) {
            List<ChannelBean.ResultBean.KTVBean> ktv = resultBean.getKTV();
            //有数据
            SortUtil.SortList<ChannelBean.ResultBean.KTVBean> ktvBeanSortList = new SortUtil.SortList<>();
            ktvBeanSortList.Sort(ktv, "getLength", null);
            resultBean.setKTV(ktv);
            //设置适配器
            adapter = new HomeFragmentAdapter(mcontext, resultBean);
            rvHome.setAdapter(adapter);
            GridLayoutManager manager = new GridLayoutManager(mcontext, 1);

            /*设置布局管理者*/
            rvHome.setLayoutManager(manager);
        }
    }


    private void initListener() {
        et_search.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                InputMethodManager imm = (InputMethodManager) mcontext.getSystemService(INPUT_METHOD_SERVICE);
                if (i == KeyEvent.KEYCODE_ENTER && keyEvent.getAction() == KeyEvent.ACTION_DOWN) {
                    //隐藏软键盘
                    if (imm.isActive()) {
                        imm.hideSoftInputFromWindow(et_search.getApplicationWindowToken(), 0);
                    }

//                    imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
                    if (TextUtils.isEmpty(et_search.getText().toString())) {
                        Toast.makeText(mcontext, "请重新输入", Toast.LENGTH_SHORT).show();
                        return false;
                    }
                    onSearch(et_search.getText().toString());

                }
                return false;
            }
        });

        txt_city.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(mcontext, EaseBaiduMapActivity.class);
                intent1.putExtra("sure", "确定");
                startActivityForResult(intent1, REQUEST_CODE_MAP);
            }
        });
    }

    private void onSearch(String s) {
        Intent intent = new Intent(mcontext, SearchActivity.class);
        intent.putExtra("input", s);
        startActivity(intent);
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == Activity.RESULT_OK && requestCode == REQUEST_CODE_MAP) {
            double latitude = data.getDoubleExtra("latitude", 0);
            double longitude = data.getDoubleExtra("longitude", 0);
            String locationAddress = data.getStringExtra("address");
            if (locationAddress != null && !locationAddress.equals("")) {
//                sendLocationMessage(latitude, longitude, locationAddress);
                try {
                    String locationName = getLocationName(locationAddress);
                    finalLocation = locationName.substring(0, 2) + "-" + locationName.substring(2, 4);
                    txt_city.setText(finalLocation);
                } catch (Exception e) {
                    txt_city.setText(finalLocation);
                }

                SpUtils.getInstance().save(Location, finalLocation);


            } else {
                Toast.makeText(mcontext, R.string.unable_to_get_loaction, Toast.LENGTH_SHORT).show();
            }

        }

    }

    private String getLocationName(String locationAddress) {
        String sheng = "省";
        String shi = "市";

        int sheng_size = locationAddress.indexOf(sheng);
        int shi_size = locationAddress.indexOf(shi);


        String shengName = locationAddress.substring(sheng_size-2,sheng_size); //substr的值为hel
        String shiName = locationAddress.substring(shi_size-2,shi_size); //substr的值为hel

        String location = shengName + shiName;
        return location;
    }
}
