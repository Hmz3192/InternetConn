package com.atguigu.im.controller.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.atguigu.im.R;

public class UserManagerActivity extends Activity implements View.OnClickListener{
    private RelativeLayout title;
    private ImageView la_back_user1;
    private ImageView userHeadAvatar;
    private ImageView userHeadHeadphotoUpdate;
    private TextView userHxid;
    private TextView teHxid;
    private RelativeLayout rlNickname;
    private TextView userNickname;
    private TextView teNick;
    private ImageView icRightNick;
    private RelativeLayout rlSex;
    private TextView textView3;
    private TextView tvSex;
    private ImageView icRightSex;
    private RelativeLayout rlSig;
    private TextView textView4;
    private TextView tvSig;
    private ImageView icRightSig;

    /**
     * Find the Views in the layout<br />
     * <br />
     * Auto-created on 2017-10-28 19:26:42 by Android Layout Finder
     * (http://www.buzzingandroid.com/tools/android-layout-finder)
     */
    private void findViews() {
        title = (RelativeLayout)findViewById( R.id.title );
        la_back_user1 = (ImageView)findViewById( R.id.la_back_user1 );
        userHeadAvatar = (ImageView)findViewById( R.id.user_head_avatar );
        userHeadHeadphotoUpdate = (ImageView)findViewById( R.id.user_head_headphoto_update );
        userHxid = (TextView)findViewById( R.id.user_hxid );
        teHxid = (TextView)findViewById( R.id.te_hxid );
        rlNickname = (RelativeLayout)findViewById( R.id.rl_nickname );
        userNickname = (TextView)findViewById( R.id.user_nickname );
        teNick = (TextView)findViewById( R.id.te_nick );
        icRightNick = (ImageView)findViewById( R.id.ic_right_nick );
        rlSex = (RelativeLayout)findViewById( R.id.rl_sex );
        textView3 = (TextView)findViewById( R.id.textView3 );
        tvSex = (TextView)findViewById( R.id.tv_sex );
        icRightSex = (ImageView)findViewById( R.id.ic_right_sex );
        rlSig = (RelativeLayout)findViewById( R.id.rl_sig );
        textView4 = (TextView)findViewById( R.id.textView4 );
        tvSig = (TextView)findViewById( R.id.tv_sig );
        icRightSig = (ImageView)findViewById( R.id.ic_right_sig );


        la_back_user1.setOnClickListener(this);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_manager);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.la_back_user1:
                finish();
                break;

        }
    }
}
