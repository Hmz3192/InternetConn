package com.atguigu.im.controller.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.atguigu.im.R;
import com.atguigu.im.homeadapter.ChannelBean;
import com.atguigu.im.homeadapter.LikeAdapter;
import com.atguigu.im.model.Model;
import com.atguigu.im.utils.Constant;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends Activity {
    private RecyclerView search_list;
    private EditText et_search_detail;
    private ImageView iv_back1;
    private String input;
    private ChannelBean ktvBeans;
    private LikeAdapter foodadapter;
    private GridLayoutManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        initView();
        initData();
        Intent intent = getIntent();
        input = intent.getStringExtra("input");
    }

    private void initData() {
        et_search_detail.setHint(input);
        getSearchData();
    }

    private void getSearchData() {
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
                                            ktvBeans = JSON.parseObject(response, ChannelBean.class);
                                            inidData(ktvBeans);
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

    private void inidData(ChannelBean ktvBeans) {
        List<ChannelBean.ResultBean.FOODBean> food = ktvBeans.getResult().getFOOD();
        List<ChannelBean.ResultBean.FOODBean> food2 = new ArrayList<>();
        if (input.equalsIgnoreCase("hb") || input.equalsIgnoreCase("hanbao")) {
            food2.add(food.get(1));
            food2.add(food.get(5));

        }
        if (input.equalsIgnoreCase("蛋糕") || input.equalsIgnoreCase("dangao")) {
            food2.add(food.get(0));
            food2.add(food.get(2));
            food2.add(food.get(4));
        }
        //shiwu
        foodadapter = new LikeAdapter(this, food2);

        manager = new GridLayoutManager(this, 1);
        search_list.setAdapter(foodadapter);
            /*设置布局管理者*/
        search_list.setLayoutManager(manager);


        foodadapter.setOnItemClickListener(new LikeAdapter.OnItemClickListener() {
            @Override
            public void setOnItemClickListener(ChannelBean.ResultBean.FOODBean data) {
                Intent intent = new Intent(SearchActivity.this, ShopDetailActivity.class);
                intent.putExtra("kind", "0");
                intent.putExtra("name", data.getName());
                startActivity(intent);

//                   Toast.makeText(ShopListActivity.this, "" + data.getName(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initView() {
        search_list = findViewById(R.id.search_list);
        et_search_detail = findViewById(R.id.et_search_detail);
        iv_back1 = findViewById(R.id.iv_back1);

        et_search_detail.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                InputMethodManager imm = (InputMethodManager) SearchActivity.this.getSystemService(INPUT_METHOD_SERVICE);
                if (i == KeyEvent.KEYCODE_ENTER && keyEvent.getAction() == KeyEvent.ACTION_DOWN) {
                    //隐藏软键盘
                    if (imm.isActive()) {
                        imm.hideSoftInputFromWindow(et_search_detail.getApplicationWindowToken(), 0);
                    }

//                    imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
                    if (TextUtils.isEmpty(et_search_detail.getText().toString())) {
                        Toast.makeText(SearchActivity.this, "请重新输入", Toast.LENGTH_SHORT).show();
                        return false;
                    }
                    onSearch(et_search_detail.getText().toString());

                }
                return false;
            }
        });
    }

    private void onSearch(String s) {
        Intent intent = new Intent(SearchActivity.this, SearchActivity.class);
        intent.putExtra("input", s);
        startActivity(intent);
        finish();
    }

}
