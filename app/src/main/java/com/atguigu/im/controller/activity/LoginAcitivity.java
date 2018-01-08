package com.atguigu.im.controller.activity;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.atguigu.im.R;
import com.atguigu.im.model.Model;
import com.atguigu.im.model.bean.UserInfo;
import com.hyphenate.EMCallBack;
import com.hyphenate.chat.EMClient;
import com.hyphenate.exceptions.HyphenateException;

// 登录页面
public class LoginAcitivity extends Activity implements View.OnClickListener {


    private TextView tvMore;
    private EditText etLoginName;
    private EditText etLoginPwd;
    private Button btGo;
    private Dialog mCameraDialog;
    private int choice = 1;



    private View.OnClickListener btnlistener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                // 注册
                case R.id.btn_register:
                    if (mCameraDialog != null) {
                        mCameraDialog.dismiss();
                    }

                    Intent intent = new Intent(LoginAcitivity.this, RegisterActivity.class);
                    startActivity(intent);
                    break;
//                忘记密码
                case R.id.btn_forgetPass:
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
    /**
     * Find the Views in the layout<br />
     * <br />
     * Auto-created on 2017-10-30 12:20:38 by Android Layout Finder
     * (http://www.buzzingandroid.com/tools/android-layout-finder)
     */
    private void findViews() {
        tvMore = (TextView)findViewById( R.id.tv_more );
        etLoginName = (EditText)findViewById( R.id.et_login_name );
        etLoginPwd = (EditText)findViewById( R.id.et_login_pwd );
        btGo = (Button)findViewById( R.id.bt_go );

        tvMore.setOnClickListener(this);
        btGo.setOnClickListener( this );
    }

    /**
     * Handle button click events<br />
     * <br />
     * Auto-created on 2017-10-30 12:20:38 by Android Layout Finder
     * (http://www.buzzingandroid.com/tools/android-layout-finder)
     */
    @Override
    public void onClick(View v) {
        if ( v == btGo ) {
            // Handle clicks for btGo
            login();
        } else if (v == tvMore) {
            showBottomDia();
        }
    }

    private void showBottomDia() {
        mCameraDialog = new Dialog(LoginAcitivity.this, R.style.my_dialog);
        LinearLayout root = (LinearLayout) LayoutInflater.from(LoginAcitivity.this).inflate(
                R.layout.bottom_dialog_login, null);
        root.findViewById(R.id.btn_register).setOnClickListener(btnlistener);
        root.findViewById(R.id.btn_forgetPass).setOnClickListener(btnlistener);
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
        final String loginName = etLoginName.getText().toString();
        final String loginPwd = etLoginPwd.getText().toString();

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
        final String registName = etLoginName.getText().toString();
        final String registPwd = etLoginPwd.getText().toString();
        
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
