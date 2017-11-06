package com.atguigu.im.controller.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSONArray;
import com.atguigu.im.R;
import com.atguigu.im.controller.adapter.DoorRecordAdapter;
import com.atguigu.im.model.Model;
import com.atguigu.im.model.bean.DoorRec;
import com.atguigu.im.model.bean.KeyMes;
import com.atguigu.im.utils.Constant;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.List;


public class SeeRecordActivity extends Activity {
    private List<DoorRec> doorRecs;

    private DoorRecordAdapter adapter;
    private RelativeLayout title;
    private ImageView laBackUser;
    private TextView tvRecoId;
    private RecyclerView RcvDoorRec;
    private KeyMes keyMes;
    /**
     * Find the Views in the layout<br />
     * <br />
     * Auto-created on 2017-10-25 16:43:04 by Android Layout Finder
     * (http://www.buzzingandroid.com/tools/android-layout-finder)
     */
    private void findViews() {
        title = (RelativeLayout)findViewById( R.id.title );
        laBackUser = (ImageView)findViewById( R.id.la_back_user );
        tvRecoId = (TextView)findViewById( R.id.tv_reco_id );
        RcvDoorRec = (RecyclerView)findViewById( R.id.Rcv_doorRec );
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_see_record);
        Intent intent = getIntent();
        keyMes = (KeyMes) intent.getSerializableExtra("keyMes");
        findViews();
        initData();

    }

    private void setAdapter() {
        adapter = new DoorRecordAdapter(this,doorRecs);
        GridLayoutManager manager = new GridLayoutManager(this, 1);
        RcvDoorRec.setAdapter(adapter);
        /*设置布局管理者*/
        RcvDoorRec.setLayoutManager(manager);
    }

    private void initData() {
        tvRecoId.setText(keyMes.getDoorId());

        final String url = Constant.GETDOORRECORD;

        Model.getInstance().getGlobalThreadPool().execute(new Runnable() {
            @Override
            public void run() {
                OkHttpUtils
                        .get()
                        .url(url)
                        .addParams("doorId", keyMes.getDoorId())
                        .build()
                        .execute(new StringCallback() {
                            @Override
                            public void onError(okhttp3.Call call, Exception e, int id) {

                            }

                            @Override
                            public void onResponse(String response, int id) {
                                doorRecs = JSONArray.parseArray(response, DoorRec.class);
                                // 保存自己信息到本地数据库
//                                Model.getInstance().getUserInfoDao().addAccount(userBean);
                                for (DoorRec doorRec : doorRecs) {
                                    Model.getInstance().getDoorRecordDao().addRecord(doorRec);
                                }
                                setAdapter();
                            }
                        });
                // 校验
            }
        });



        laBackUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();

            }
        });
    }

}
