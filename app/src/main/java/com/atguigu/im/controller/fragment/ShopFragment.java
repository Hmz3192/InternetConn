package com.atguigu.im.controller.fragment;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.alibaba.fastjson.JSON;
import com.atguigu.im.R;
import com.atguigu.im.homeadapter.HomeFragmentAdapter;
import com.atguigu.im.model.bean.ResultBeanData;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import okhttp3.Call;

/**
 * Created by ZJNU-Hmz on 2017/10/10.
 */

public class ShopFragment extends BaseFragment {
    private RecyclerView rvHome;
    private EditText tv_search_home;
    private ResultBeanData.ResultBean resultBean;
    private HomeFragmentAdapter adapter;


    @Override
    public View initView() {
        View view = View.inflate(mcontext, R.layout.fragment_shop, null);
        rvHome = view.findViewById(R.id.rv_home);
//        tv_search_home = view.findViewById(R.id.tv_search_home);

        initListener();

        return view;
    }


    @Override
    public void initData() {
        super.initData();
        getDataFromNet();


    }

    private void getDataFromNet() {
        String url = "http://101.201.234.133:8080/Andro/json/HOME_URL.json";
  /*   String url = "http://www.csdn.net/";*/
  /*String url = "http://101.201.234.133:8080/index.jsp";*/
        OkHttpUtils
                .get()
                .url(url)
                .build()
                .execute(new StringCallback() {
                    /*当请求失败的时候*/
                    @Override
                    public void onError(Call call, Exception e, int id) {
                    }

                    /*当联网成功回调数据，respond请求成功的数据回调*/
                    @Override
                    public void onResponse(String response, int id) {
                        Log.e("error", "请求成功==" + response);
                        //解析数据
                        procssData(response);
                    }


                });
    }

    private void procssData(String response) {
        ResultBeanData resultBeanData = JSON.parseObject(response, ResultBeanData.class);
        resultBean = resultBeanData.getResult();
        if (resultBean != null) {
            //有数据
            //设置适配器
            adapter = new HomeFragmentAdapter(mcontext, resultBean);
            rvHome.setAdapter(adapter);
            GridLayoutManager manager = new GridLayoutManager(mcontext, 1);

            /*设置布局管理者*/
            rvHome.setLayoutManager(manager);
        }
    }


    private void initListener() { //置顶的监听
        /*tv_search_home.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                InputMethodManager imm  = (InputMethodManager) mcontext.getSystemService(INPUT_METHOD_SERVICE);
                if (i == KeyEvent.KEYCODE_ENTER && keyEvent.getAction() == KeyEvent.ACTION_DOWN) {
                    //隐藏软键盘
                    if (imm.isActive()) {
                        imm.hideSoftInputFromWindow(tv_search_home.getApplicationWindowToken(), 0);
                    }

//                    imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
                    if (TextUtils.isEmpty(tv_search_home.getText().toString())) {
                        Toast.makeText(mcontext, "请重新输入", Toast.LENGTH_SHORT).show();
                        return false;
                    }
//                    onSearch(tv_search_home.getText().toString());

                }
                return false;
            }
        })*/;


    }
}
