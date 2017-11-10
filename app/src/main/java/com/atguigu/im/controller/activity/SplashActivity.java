package com.atguigu.im.controller.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.TextView;

import com.atguigu.im.R;
import com.atguigu.im.model.Model;
import com.atguigu.im.model.bean.UserInfo;
import com.hyphenate.chat.EMClient;

// 欢迎页面
public class SplashActivity extends Activity {
    private MyCountDownTimer mc;

    private TextView tv_mis;

    // 判断进入主页面还是登录页面
    private void toMainOrLogin() {
//        new Thread(){
//            public void run(){
//
//            }
//        }.start();

        Model.getInstance().getGlobalThreadPool().execute(new Runnable() {
            @Override
            public void run() {
                // 判断当前账号是否已经登录过
                if(EMClient.getInstance().isLoggedInBefore()) {// 登录过

                    // 获取到当前登录用户的信息
                    UserInfo account = Model.getInstance().getUserAccountDao().getAccountByHxId(EMClient.getInstance().getCurrentUser());

                    if(account == null) {
                        // 跳转到登录页面
                        Intent intent = new Intent(SplashActivity.this, LoginAcitivity.class);
                        startActivity(intent);
                    }else {
                        // 登录成功后的方法
                        Model.getInstance().loginSuccess(account);

                        // 跳转到主页面
                        Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                        startActivity(intent);
                    }
                }else {// 没登录过
                    // 跳转到登录页面
                    Intent intent = new Intent(SplashActivity.this, LoginAcitivity.class);
                    startActivity(intent);
                }

                // 结束当前页面
                finish();
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_splash);
        tv_mis = findViewById(R.id.tv_mis);

        mc = new MyCountDownTimer(4000, 1000);
        mc.start();


        tv_mis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mc.cancel();;
                // 判断进入主页面还是登录页面
                toMainOrLogin();

            }
        });
    }


    class MyCountDownTimer extends CountDownTimer {
        public MyCountDownTimer(long millisInFuture, long countDownInterval){
            super(millisInFuture, countDownInterval);
        }
        public void onFinish(){
            toMainOrLogin();
        }
        public void onTick(long millisUntilFinished){
            tv_mis.setText("跳过("+millisUntilFinished/1000+"s)");
        }
    }

}
