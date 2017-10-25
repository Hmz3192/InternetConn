package com.atguigu.im.controller.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.atguigu.im.R;
import com.atguigu.im.model.bean.KeyMes;
import com.atguigu.im.utils.PasswordView;
import com.atguigu.im.utils.TimePickerDialog;

public class DetailActivity extends Activity implements View.OnClickListener,TimePickerDialog.TimePickerDialogInterface {
    private KeyMes keyMes;
    private TextView tv_location, tv_name, tv_user;
    private ImageView la_back_user;
    private PopupWindow mPopupWindow;
    private View popupView;
    private Button chang_pass, bt_seeRec;
    private LinearLayout ll_layout;
    private PasswordView passwordView;
    private int time = 1;
    private Button bt_genPass;
    private TimePickerDialog mTimePickerDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        popupView = getLayoutInflater().inflate(R.layout.layout_popupwindow, null);
        passwordView = popupView.findViewById(R.id.view_pass);
        keyMes = (KeyMes) getIntent().getSerializableExtra("mes");
        mTimePickerDialog = new TimePickerDialog(DetailActivity.this);
        initView();
        initData();
    }

    private void initData() {
        tv_name.setText(keyMes.getName());
        tv_location.setText(keyMes.getName());
        tv_user.setText(keyMes.getId());

        //密码输入完成
        passwordView.setOnFinishInput(new PasswordView.OnPasswordInputFinish() {
            @Override
            public void inputFinish() {
                if (time == 1) {
                    Toast.makeText(DetailActivity.this, "1" + passwordView.getStrPassword(), Toast.LENGTH_SHORT).show();
                } else if (time == 2) {
                    Toast.makeText(DetailActivity.this, "2" + passwordView.getStrPassword(), Toast.LENGTH_SHORT).show();
                } else if (time == 3) {
                    Toast.makeText(DetailActivity.this, "3" + passwordView.getStrPassword(), Toast.LENGTH_SHORT).show();

                }
                mPopupWindow.dismiss();
                WindowManager.LayoutParams params = getWindow().getAttributes();
                params.alpha = 1f;
                getWindow().setAttributes(params);

                time++;
                if (time == 4) {
                    time = 1;
                } else {
                    initWindows(time);
                }


            }
        });

        //取消按钮
        passwordView.getCancelImageView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(DetailActivity.this, "Biu Biu Biu", Toast.LENGTH_SHORT).show();
                for (int j = 5; j >= 0; j--) {
                    passwordView.getTvList()[j].setText("");
                }

                passwordView.setCurrentIndex(-1);

                mPopupWindow.dismiss();
                WindowManager.LayoutParams params = getWindow().getAttributes();
                params.alpha = 1f;
                getWindow().setAttributes(params);


            }
        });

        //忘记密码
        passwordView.getForgetTextView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(DetailActivity.this, "忘记密码", Toast.LENGTH_SHORT).show();
            }
        });


    }


    private void initView() {
        bt_genPass = findViewById(R.id.bt_genPass);
        tv_location = findViewById(R.id.tv_location);
        tv_user = findViewById(R.id.tv_user);
        tv_name = findViewById(R.id.tv_name);
        la_back_user = findViewById(R.id.la_back_user);
        chang_pass = findViewById(R.id.chang_pass);
        ll_layout = findViewById(R.id.ll_layout);
        bt_seeRec = findViewById(R.id.bt_seeRec);

        la_back_user.setOnClickListener(this);
        chang_pass.setOnClickListener(this);
        bt_seeRec.setOnClickListener(this);
        bt_genPass.setOnClickListener(this);

    }

    private void initWindows(int time) {
        mPopupWindow = new PopupWindow(popupView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
        mPopupWindow.setTouchable(true);
        mPopupWindow.setOutsideTouchable(true);
//        mPopupWindow.setBackgroundDrawable(new BitmapDrawable(getResources(), (Bitmap) null));
        mPopupWindow.setAnimationStyle(R.style.anim_menu_bottombar);


        /*阴影*/
        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.alpha = 0.7f;
        getWindow().setAttributes(params);


        mPopupWindow.getContentView().setFocusableInTouchMode(true);
        mPopupWindow.getContentView().setFocusable(true);
        mPopupWindow.showAtLocation(findViewById(R.id.ll_layout), Gravity.BOTTOM, 0, 0);

        if (time == 1) {
            passwordView.getTv_pasname().setText("请输入原有密码");
            passwordView.getCancelImageView().setVisibility(View.VISIBLE);
        } else if (time == 2) {
            passwordView.getTv_pasname().setText("请输入新密码");
        } else if (time == 3) {
            passwordView.getTv_pasname().setText("请再次输入密码");
        }
        mPopupWindow.getContentView().setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_MENU && event.getRepeatCount() == 0
                        && event.getAction() == KeyEvent.ACTION_DOWN) {
                    if (mPopupWindow != null && mPopupWindow.isShowing()) {
                        mPopupWindow.dismiss();
                        WindowManager.LayoutParams params = getWindow().getAttributes();
                        params.alpha = 1f;
                        getWindow().setAttributes(params);
                    }
                    return true;
                }
                return false;
            }
        });
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_MENU && event.getRepeatCount() == 0) {
            if (mPopupWindow != null && !mPopupWindow.isShowing()) {
                mPopupWindow.showAtLocation(findViewById(R.id.ll_layout), Gravity.BOTTOM, 0, 0);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.chang_pass:
                /*打开windows*/
                initWindows(time);
                break;

            case R.id.la_back_user:
                finish();
                break;
            case R.id.bt_seeRec:
                Intent intent = new Intent(DetailActivity.this, SeeRecordActivity.class);
                intent.putExtra("keyMes", keyMes);
                startActivity(intent);
                break;
            case R.id.bt_genPass:
                mTimePickerDialog.showTimePickerDialog();
                break;

        }

    }

    @Override
    public void positiveListener() {
        int hour = mTimePickerDialog.getHour();
        int minute = mTimePickerDialog.getMinute();
        Toast.makeText(this, ""+hour+":"+minute, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void negativeListener() {
    }
}
