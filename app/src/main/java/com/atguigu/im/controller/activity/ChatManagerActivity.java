package com.atguigu.im.controller.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.atguigu.im.R;
import com.hyphenate.easeui.widget.EaseSwitchButton;

public class ChatManagerActivity extends Activity implements View.OnClickListener {
    private ImageButton ibDrawerBack;
    private TextView tvIbDrawerLayoutTitle;
    private TextView ibDrawerLayoutConfirm;
    private RelativeLayout rlSwitchNotification;
    private EaseSwitchButton switchNotification;
    private TextView textview1;
    private RelativeLayout rlSwitchSound;
    private EaseSwitchButton switchSound;
    private TextView textview2;
    private RelativeLayout rlSwitchVibrate;
    private EaseSwitchButton switchVibrate;
    private RelativeLayout rlSwitchSpeaker;
    private EaseSwitchButton switchSpeaker;
    private LinearLayout llBlackList;
    private LinearLayout llDiagnose;
    private RelativeLayout rlSwitchChatroomOwnerLeave;
    private EaseSwitchButton switchOwnerLeave;

    /**
     * Find the Views in the layout<br />
     * <br />
     * Auto-created on 2017-10-28 19:20:04 by Android Layout Finder
     * (http://www.buzzingandroid.com/tools/android-layout-finder)
     */
    private void findViews() {
        ibDrawerBack = (ImageButton)findViewById( R.id.ib_drawer_back );
        tvIbDrawerLayoutTitle = (TextView)findViewById( R.id.tv_ib_drawer_layout_title );
        ibDrawerLayoutConfirm = (TextView)findViewById( R.id.ib_drawer_layout_confirm );
        rlSwitchNotification = (RelativeLayout)findViewById( R.id.rl_switch_notification );
        switchNotification = (EaseSwitchButton)findViewById( R.id.switch_notification );
        textview1 = (TextView)findViewById( R.id.textview1 );
        rlSwitchSound = (RelativeLayout)findViewById( R.id.rl_switch_sound );
        switchSound = (EaseSwitchButton)findViewById( R.id.switch_sound );
        textview2 = (TextView)findViewById( R.id.textview2 );
        rlSwitchVibrate = (RelativeLayout)findViewById( R.id.rl_switch_vibrate );
        switchVibrate = (EaseSwitchButton)findViewById( R.id.switch_vibrate );
        rlSwitchSpeaker = (RelativeLayout)findViewById( R.id.rl_switch_speaker );
        switchSpeaker = (EaseSwitchButton)findViewById( R.id.switch_speaker );
        llBlackList = (LinearLayout)findViewById( R.id.ll_black_list );
        llDiagnose = (LinearLayout)findViewById( R.id.ll_diagnose );
        rlSwitchChatroomOwnerLeave = (RelativeLayout)findViewById( R.id.rl_switch_chatroom_owner_leave );
        switchOwnerLeave = (EaseSwitchButton)findViewById( R.id.switch_owner_leave );

        ibDrawerBack.setOnClickListener( this );
        switchNotification.setOnClickListener( this );
        switchSound.setOnClickListener( this );
        switchVibrate.setOnClickListener( this );
        switchSpeaker.setOnClickListener( this );
        switchOwnerLeave.setOnClickListener( this );
    }

    /**
     * Handle button click events<br />
     * <br />
     * Auto-created on 2017-10-28 19:20:04 by Android Layout Finder
     * (http://www.buzzingandroid.com/tools/android-layout-finder)
     */
    @Override
    public void onClick(View v) {
        if ( v == ibDrawerBack ) {
            // Handle clicks for ibDrawerBack
            finish();
        } else if ( v == switchNotification ) {
            // Handle clicks for switchNotification
        } else if ( v == switchSound ) {
            // Handle clicks for switchSound
        } else if ( v == switchVibrate ) {
            // Handle clicks for switchVibrate
        } else if ( v == switchSpeaker ) {
            // Handle clicks for switchSpeaker
        } else if ( v == switchOwnerLeave ) {
            // Handle clicks for switchOwnerLeave
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_manager);
        findViews();
    }
}
