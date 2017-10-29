package com.atguigu.im.controller.activity;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.CardView;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.atguigu.im.R;
import com.atguigu.im.model.Model;
import com.atguigu.im.model.bean.UserInfo;
import com.hyphenate.EMCallBack;
import com.hyphenate.chat.EMClient;
import com.hyphenate.exceptions.HyphenateException;

// 登录页面
public class LoginAcitivity extends Activity implements View.OnClickListener {
    private EditText et_login_name;
    private EditText et_login_pwd;
    private CardView cv;
    private Button btGo;
    private FloatingActionButton fab;

    /**
     * Find the Views in the layout<br />
     * <br />
     * Auto-created on 2017-10-28 10:55:26 by Android Layout Finder
     * (http://www.buzzingandroid.com/tools/android-layout-finder)
     */
    private void findViews() {
        cv = (CardView)findViewById( R.id.cv );
        et_login_name = (EditText)findViewById( R.id.et_login_name );
        et_login_pwd = (EditText)findViewById( R.id.et_login_pwd );
        btGo = (Button)findViewById( R.id.bt_go );
        fab = (FloatingActionButton)findViewById( R.id.fab );

        btGo.setOnClickListener( this );
        fab.setOnClickListener( this );
    }

    /**
     * Handle button click events<br />
     * <br />
     * Auto-created on 2017-10-28 10:55:26 by Android Layout Finder
     * (http://www.buzzingandroid.com/tools/android-layout-finder)
     */
    @Override
    public void onClick(View v) {
        if ( v == btGo ) {
            // Handle clicks for btGo
            login();
        } else if ( v == fab ) {
            // Handle clicks for fab
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                getWindow().setExitTransition(null);
            }
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                getWindow().setEnterTransition(null);
            }

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                ActivityOptions options =
                        ActivityOptions.makeSceneTransitionAnimation(this, fab, fab.getTransitionName());
                startActivity(new Intent(this, RegisterActivity.class), options.toBundle());
            } else {
                startActivity(new Intent(this, RegisterActivity.class));
            }

        }
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login_acitivity);

        // 初始化控件
        findViews();
        // 初始化监听
        initListener();
    }

    private void initListener() {
        // 注册按钮的点击事件处理
//        bt_login_regist.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                regist();
//            }
//        });

        // 登录按钮的点击事件处理
//        bt_login_login.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                login();
//            }
//        });
    }

    // 登录按钮的页面逻辑处理
    private void login() {
        // 1 获取输入的用户名和密码
        final String loginName = et_login_name.getText().toString();
        final String loginPwd = et_login_pwd.getText().toString();

        // 2 校验输入的用户名和密码
        if(TextUtils.isEmpty(loginName) || TextUtils.isEmpty(loginPwd)) {
            Toast.makeText(LoginAcitivity.this, "输入的用户名或密码不能为空", Toast.LENGTH_SHORT).show();
            return;
        }

        // 3 登录逻辑处理
        Model.getInstance().getGlobalThreadPool().execute(new Runnable() {
            @Override
            public void run() {
                // 去环信服务器登录
                EMClient.getInstance().login(loginName, loginPwd, new EMCallBack() {
                    // 登录成功后的处理
                    @Override
                    public void onSuccess() {
                        // 对模型层数据的处理
                        Model.getInstance().loginSuccess(new UserInfo(loginName));

                        // 保存用户账号信息到本地数据库
                        Model.getInstance().getUserAccountDao().addAccount(new UserInfo(loginName));

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                // 提示登录成功
                                Toast.makeText(LoginAcitivity.this, "登录成功", Toast.LENGTH_SHORT).show();

                                // 跳转到主页面
                                Intent intent = new Intent(LoginAcitivity.this, MainActivity.class);

                                startActivity(intent);

                                finish();
                            }
                        });
                    }

                    // 登录失败的处理
                    @Override
                    public void onError(int i, final String s) {
                        // 提示登录失败
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(LoginAcitivity.this, "登录失败"+s, Toast.LENGTH_SHORT).show();
                            }
                        });
                    }

                    // 登录过程中的处理
                    @Override
                    public void onProgress(int i, String s) {

                    }
                });
            }
        });
    }

    // 注册的业务逻辑处理
    private void regist() {
        // 1 获取输入的用户名和密码
        final String registName = et_login_name.getText().toString();
        final String registPwd = et_login_pwd.getText().toString();
        
        // 2 校验输入的用户名和密码
        if(TextUtils.isEmpty(registName) || TextUtils.isEmpty(registPwd)) {
            Toast.makeText(LoginAcitivity.this, "输入的用户名或密码不能为空", Toast.LENGTH_SHORT).show();
            return;
        }

        // 3 去服务器注册账号
        Model.getInstance().getGlobalThreadPool().execute(new Runnable() {
            @Override
            public void run() {

                try {
                    // 去环信服务器注册账号
                    EMClient.getInstance().createAccount(registName,registPwd);

                    // 更新页面显示
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(LoginAcitivity.this, "注册成功", Toast.LENGTH_SHORT).show();
                        }
                    });
                } catch (final HyphenateException e) {
                    e.printStackTrace();
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(LoginAcitivity.this, "注册失败"+e.toString(), Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });
    }

}
