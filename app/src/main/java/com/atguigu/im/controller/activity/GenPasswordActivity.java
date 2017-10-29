package com.atguigu.im.controller.activity;

import android.app.Activity;
import android.app.Dialog;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.telephony.SmsManager;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.atguigu.im.R;

import java.util.Random;

public class GenPasswordActivity extends Activity implements View.OnClickListener {
    private RelativeLayout title;
    private ImageView laBackUser;
    private TextView tvDoorName;
    private EditText editText,et_user,et_time;
    private Button btGen;
    private Dialog dialog;
    private View inflate;
    //剪切板管理工具类
    private ClipboardManager mClipboardManager;
    //剪切板Data对象
    private ClipData mClipData;
    private String sixpass;
    private Dialog mCameraDialog;
    private View.OnClickListener btnlistener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btn_mes: // 发送短信
                    showMes();
                    if (mCameraDialog != null) {
                        mCameraDialog.dismiss();
                    }
                    break;
                // 打开发送社区
                case R.id.btn_shequ:
                    if (mCameraDialog != null) {
                        mCameraDialog.dismiss();
                    }
                    break;
//                发送微信
                case R.id.btn_weixin:
                    if (mCameraDialog != null) {
                        mCameraDialog.dismiss();
                    }
                    break;
                // 取消
                case R.id.btn_cancel:
                    if (mCameraDialog != null) {
                        mCameraDialog.dismiss();
                    }
                    break;
            }
        }
    };

    private void showMes() {
        LayoutInflater layoutInflater = LayoutInflater.from(this); // 创建视图容器并设置上下文
        final View view = layoutInflater.inflate(R.layout.mes_dialog, null); // 获取list_item布局文件的视图
        final EditText et_phone = view.findViewById(R.id.et_phone);
        new AlertDialog.Builder(this).setTitle("      ")
                .setView(view)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // TODO Auto-generated method stub
                        Toast.makeText(GenPasswordActivity.this, ""+et_phone.getText().toString(), Toast.LENGTH_SHORT).show();
                        send(et_phone.getText().toString(),"您的开门密码为"+sixpass+",请小心保管！！！       ——FreeKey智慧社区");
                    }
                })
                .setNegativeButton("取消", null)
                .show();
    }

    private void send(String s, String message){
        try {
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(s, null, message, null, null);
//            Toast.makeText(getApplicationContext(), "SMS sent.",
//                    Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(),
                    "发送失败，请重新再试！",
                    Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }

    }
    /**
     * Find the Views in the layout<br />
     * <br />
     * Auto-created on 2017-10-28 13:40:03 by Android Layout Finder
     * (http://www.buzzingandroid.com/tools/android-layout-finder)
     */
    private void findViews() {
        title = (RelativeLayout) findViewById(R.id.title);
        et_user = findViewById(R.id.et_user);
        laBackUser = (ImageView) findViewById(R.id.la_back_user);
        tvDoorName = (TextView) findViewById(R.id.tv_door_name);
        et_time = findViewById(R.id.et_time);
        editText = (EditText) findViewById(R.id.editText);
        btGen = (Button) findViewById(R.id.bt_gen);

        btGen.setOnClickListener(this);
        laBackUser.setOnClickListener(this);
    }

    /**
     * Handle button click events<br />
     * <br />
     * Auto-created on 2017-10-28 13:40:03 by Android Layout Finder
     * (http://www.buzzingandroid.com/tools/android-layout-finder)
     */
    @Override
    public void onClick(View v) {
        if (v == btGen) {
            if (!TextUtils.isEmpty(et_user.getText())||!TextUtils.isEmpty(et_time.getText())||!TextUtils.isEmpty(editText.getText())) {
                sixpass = genSixPassword();
                // TODO Auto-generated method stub
                LayoutInflater layoutInflater = LayoutInflater.from(this); // 创建视图容器并设置上下文
                final View view = layoutInflater.inflate(R.layout.dialog, null); // 获取list_item布局文件的视图
                TextView tvpass = view.findViewById(R.id.tv_pass);
                tvpass.setText(sixpass);
                new AlertDialog.Builder(this).setTitle("密码出现一次，请谨慎保管：")
                        .setView(view)
                        .setPositiveButton("复制", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // TODO Auto-generated method stub

                                //创建一个新的文本clip对象
                                mClipData = ClipData.newPlainText("Simple test", sixpass);
                                //把clip对象放在剪贴板中
                                mClipboardManager.setPrimaryClip(mClipData);
                                Toast.makeText(getApplicationContext(), "文本已经复制成功！",
                                        Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setNeutralButton("转发", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                showBottomDia();
                            }
                        })
                        .show();
            }
            Toast.makeText(this, "生成密码前请填写信息！", Toast.LENGTH_SHORT).show();



        } else if (v == laBackUser) {
            finish();
        }
    }

    private void showBottomDia() {
        mCameraDialog = new Dialog(GenPasswordActivity.this, R.style.my_dialog);
        LinearLayout root = (LinearLayout) LayoutInflater.from(GenPasswordActivity.this).inflate(
                R.layout.bottom_dialog, null);
        root.findViewById(R.id.btn_mes).setOnClickListener(btnlistener);
        root.findViewById(R.id.btn_shequ).setOnClickListener(btnlistener);
        root.findViewById(R.id.btn_weixin).setOnClickListener(btnlistener);
        root.findViewById(R.id.btn_cancel).setOnClickListener(btnlistener);
        mCameraDialog.setContentView(root);
        Window dialogWindow = mCameraDialog.getWindow();
        dialogWindow.setGravity(Gravity.BOTTOM);
        dialogWindow.setWindowAnimations(R.style.dialogstyle); // 添加动画
        WindowManager.LayoutParams lp = dialogWindow.getAttributes(); // 获取对话框当前的参数值
        lp.x = 0; // 新位置X坐标
        lp.y = -20; // 新位置Y坐标
        lp.width = (int) getResources().getDisplayMetrics().widthPixels; // 宽度
//      lp.height = WindowManager.LayoutParams.WRAP_CONTENT; // 高度
//      lp.alpha = 9f; // 透明度
        root.measure(0, 0);
        lp.height = root.getMeasuredHeight();
        lp.alpha = 9f; // 透明度
        dialogWindow.setAttributes(lp);
        mCameraDialog.show();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gen_password);
        mClipboardManager = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
        findViews();
    }


    public String genSixPassword() {
        String code="";
        Random rand=new Random();//生成随机数
        for(int a=0;a<6;a++){
            code+=rand.nextInt(10);//生成6位验证码
        }
        return code;
    }

}
