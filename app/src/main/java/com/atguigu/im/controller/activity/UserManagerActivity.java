package com.atguigu.im.controller.activity;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.atguigu.im.R;
import com.atguigu.im.model.Model;
import com.atguigu.im.model.bean.UserDetail;
import com.atguigu.im.utils.BitmapUtils;
import com.atguigu.im.utils.Constant;
import com.hyphenate.chat.EMClient;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

import static com.atguigu.im.R.id.tv_sex;
import static com.atguigu.im.R.id.tv_sig;

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
    private UserDetail userByHxId;

    /**
     * Find the Views in the layout<br />
     * <br />
     * Auto-created on 2017-10-28 19:26:42 by Android Layout Finder
     * (http://www.buzzingandroid.com/tools/android-layout-finder)
     */
    private void findViews() {
        title = (RelativeLayout)findViewById( R.id.title );
        la_back_user1 = (ImageView)findViewById( R.id.la_back_user1 );
        userHeadAvatar = (ImageView)findViewById( R.id.user_head_avatar1 );
        userHeadHeadphotoUpdate = (ImageView)findViewById( R.id.user_head_headphoto_update );
        userHxid = (TextView)findViewById( R.id.user_hxid );
        teHxid = (TextView)findViewById( R.id.te_hxid1 );
        rlNickname = (RelativeLayout)findViewById( R.id.rl_nickname );
        userNickname = (TextView)findViewById( R.id.user_nickname );
        teNick = (TextView)findViewById( R.id.te_nick );
        icRightNick = (ImageView)findViewById( R.id.ic_right_nick );
        rlSex = (RelativeLayout)findViewById( R.id.rl_sex );
        textView3 = (TextView)findViewById( R.id.textView3 );
        tvSex = (TextView)findViewById( tv_sex );
        icRightSex = (ImageView)findViewById( R.id.ic_right_sex );
        rlSig = (RelativeLayout)findViewById( R.id.rl_sig );
        textView4 = (TextView)findViewById( R.id.textView4 );
        tvSig = (TextView)findViewById( tv_sig );
        icRightSig = (ImageView)findViewById( R.id.ic_right_sig );


        la_back_user1.setOnClickListener(this);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_manager);
        findViews();
        userByHxId = Model.getInstance().getUserInfoDao().getUserByHxId(EMClient.getInstance().getCurrentUser());

        initDataView(userByHxId);

    }

    private void initDataView(UserDetail userByHxId) {
        Picasso.with(UserManagerActivity.this).load(Constant.LOADURL + userByHxId.getPicUrl()).transform(new Transformation() {
            @Override
            public Bitmap transform(Bitmap bitmap) {
                //先对图片进行压缩
//                Bitmap zoom = BitmapUtils.zoom(bitmap, DensityUtil.dip2px(mContext, 62), DensityUtil.dip2px(mContext, 62));
                Bitmap zoom = BitmapUtils.zoom(bitmap, 90, 90);
                //对请求回来的Bitmap进行圆形处理
                Bitmap ciceBitMap = BitmapUtils.circleBitmap(zoom);
                bitmap.recycle();//必须队更改之前的进行回收
                return ciceBitMap;
            }

            @Override
            public String key() {
                return "";
            }
        }).into(userHeadAvatar);

        teHxid.setText(userByHxId.getAccount());
        teNick.setText(userByHxId.getName());
        tvSex.setText(userByHxId.getIphone());
        tvSig.setText(userByHxId.getEmail());

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
