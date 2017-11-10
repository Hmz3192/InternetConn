package com.atguigu.im.controller.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import com.atguigu.im.R;
import com.atguigu.im.utils.SpUtils;
import com.dk.view.patheffect.PathTextView;

public class AppActivity extends Activity {
    private Handler handler = new Handler(){
        public void handleMessage(Message msg){
            // 如果当前activity已经退出，那么我就不处理handler中的消息
            if(isFinishing()) {
                return;
            }
            // 跳转到主页面
            Intent intent = new Intent(AppActivity.this, SplashActivity.class);
            startActivity(intent);
            finish();
        }
    };
    private String time;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        time = 0 显示  time = 1 buxianshi
        time = SpUtils.getInstance().getString("time", "0");
//        if (time.equalsIgnoreCase("0")) {
            SpUtils.getInstance().save("time", "1");
            setContentView(R.layout.activity_app);
            PathTextView mPathTextView = (PathTextView) findViewById(R.id.path);
            mPathTextView.init("FreeKey");

            mPathTextView.setPaintType(PathTextView.Type.SINGLE);
            mPathTextView.setTextColor(R.color.black_deep);
            mPathTextView.setTextSize(88.0f);
            // 发送2s钟的延时消息
            handler.sendMessageDelayed(Message.obtain(), 5000);
            mPathTextView.setTextWeight(13);
            mPathTextView.setDuration(7000);
            mPathTextView.setShadow(10, 0, 0, R.color.black_deep);
//        } else {
//            Intent intent = new Intent(AppActivity.this, SplashActivity.class);
//            startActivity(intent);
//            finish();
//        }

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        // 销毁消息
        handler.removeCallbacksAndMessages(null);
    }

}
