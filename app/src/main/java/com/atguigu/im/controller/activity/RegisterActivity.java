package com.atguigu.im.controller.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.atguigu.im.R;

public class RegisterActivity extends Activity implements View.OnClickListener {
    private EditText etUsername;
    private EditText etPassword;
    private EditText etRepeatpassword;
    private EditText etSixpassword;
    private EditText idcard;
    private Button button;
    private EditText etPhone;
    private EditText validateCode;
    private Button getSec;
    private EditText etEmail;
    private Button bt_register;

    /**
     * Find the Views in the layout<br />
     * <br />
     * Auto-created on 2017-10-30 10:25:44 by Android Layout Finder
     * (http://www.buzzingandroid.com/tools/android-layout-finder)
     */
    private void findViews() {
        etUsername = (EditText)findViewById( R.id.et_username );
        etPassword = (EditText)findViewById( R.id.et_password );
        etRepeatpassword = (EditText)findViewById( R.id.et_repeatpassword );
        etSixpassword = (EditText)findViewById( R.id.et_sixpassword );
        idcard = (EditText)findViewById( R.id.idcard );
        button = (Button)findViewById( R.id.button );
        etPhone = (EditText)findViewById( R.id.et_phone );
        validateCode = (EditText)findViewById( R.id.validate_code );
        getSec = (Button)findViewById( R.id.get_sec );
        etEmail = (EditText)findViewById( R.id.et_email );
        bt_register = (Button)findViewById( R.id.bt_register );

        button.setOnClickListener( this );
        getSec.setOnClickListener( this );
        bt_register.setOnClickListener( this );
    }

    /**
     * Handle button click events<br />
     * <br />
     * Auto-created on 2017-10-30 10:25:44 by Android Layout Finder
     * (http://www.buzzingandroid.com/tools/android-layout-finder)
     */
    @Override
    public void onClick(View v) {
        if ( v == button ) {
            // Handle clicks for button
        } else if ( v == getSec ) {
            // Handle clicks for getSec
        } else if ( v == bt_register ) {
            // Handle clicks for btGo
        }
    }




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        findViews();

    }

}
