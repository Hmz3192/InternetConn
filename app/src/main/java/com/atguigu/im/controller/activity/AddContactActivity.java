package com.atguigu.im.controller.activity;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.atguigu.im.R;
import com.atguigu.im.model.Model;
import com.atguigu.im.utils.Constant;
import com.bumptech.glide.Glide;
import com.hyphenate.chat.EMClient;
import com.hyphenate.easeui.bean.UserDetail;
import com.hyphenate.exceptions.HyphenateException;
import com.squareup.picasso.Picasso;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import okhttp3.Call;

// 添加联系人页面
public class AddContactActivity extends Activity {
    private TextView tv_add_find;
    private EditText et_add_name;
    private RelativeLayout rl_add;
    private TextView tv_add_name;
    private Button bt_add_add;
    private UserDetail userBean = null;
    private ImageView iv_add_photo;
    private RelativeLayout rl_none;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_add_contact);

        // 初始化view
        initView();

        initListener();
    }

    private void initListener() {
        // 查找按钮的点击事件处理
        tv_add_find.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rl_none.setVisibility(View.GONE);
                rl_add.setVisibility(View.GONE);
                find();
            }
        });

        // 添加按钮的点击事件处理
        bt_add_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                add();
            }
        });
    }

    // 查找按钮的处理
    private void find() {
        // 获取输入的用户名称
        final String name = et_add_name.getText().toString();

        // 校验输入的名称
        if (TextUtils.isEmpty(name)) {
            Toast.makeText(AddContactActivity.this, "输入的用户名称不能为空", Toast.LENGTH_SHORT).show();
            return;
        }
        // 去服务器判断当前用户是否存在
        Model.getInstance().getGlobalThreadPool().execute(new Runnable() {
            @Override
            public void run() {
                // 去服务器判断当前查找的用户是否存在
                getDataFromMy(name);

                // 更新UI显示
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (userBean != null) {
                            rl_add.setVisibility(View.VISIBLE);
                            tv_add_name.setText(userBean.getName());
                            try {
                                Picasso.with(AddContactActivity.this)
                                        .load(userBean.getPicUrl())
//                                    .networkPolicy(NetworkPolicy.NO_CACHE)
//                                    .memoryPolicy(MemoryPolicy.NO_CACHE)//不加载缓存
                                        .into(iv_add_photo);
                            }catch (Exception e) {
                                //use default avatar
                                Glide.with(AddContactActivity.this)
                                        .load("")
                                        .placeholder(R.drawable.ease_default_avatar)
                                        .into(iv_add_photo);
                            }
                            userBean = null;
                        }else
                            rl_none.setVisibility(View.VISIBLE);

                    }
                });
            }
        });


    }

    private void getDataFromMy(String name) {
        final String url = Constant.GETONEINFO;

        OkHttpUtils
                .get()
                .url(url)
                .addParams("hxid", name)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Log.i("info", "成功获取数据：" + response);
                        userBean = JSON.parseObject(response, UserDetail.class);

                    }

                });
    }

    // 添加按钮处理
    private void add() {

        Model.getInstance().getGlobalThreadPool().execute(new Runnable() {
            @Override
            public void run() {
                // 去环信服务器添加好友
                try {
                    EMClient.getInstance().contactManager().addContact(userBean.getAccount(), "添加好友");

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(AddContactActivity.this, "发送添加好友邀请成功", Toast.LENGTH_SHORT).show();
                        }
                    });
                } catch (final HyphenateException e) {
                    e.printStackTrace();
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(AddContactActivity.this, "发送添加好友邀请失败" + e.toString(), Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });
    }

    private void initView() {
        tv_add_find = (TextView) findViewById(R.id.tv_add_find);
        rl_none = findViewById(R.id.rl_none);
        iv_add_photo = findViewById(R.id.iv_add_photo);
        et_add_name = (EditText) findViewById(R.id.et_add_name);
        rl_add = (RelativeLayout) findViewById(R.id.rl_add);
        tv_add_name = (TextView) findViewById(R.id.tv_add_name);
        bt_add_add = (Button) findViewById(R.id.bt_add_add);
    }
}
