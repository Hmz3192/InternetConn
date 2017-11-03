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

import com.atguigu.im.R;
import com.atguigu.im.controller.adapter.DoorRecordAdapter;
import com.atguigu.im.model.bean.DoorRec;
import com.atguigu.im.model.bean.KeyMes;

import java.util.ArrayList;
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
        setAdapter();
    }

    private void setAdapter() {
        adapter = new DoorRecordAdapter(this,doorRecs);
        GridLayoutManager manager = new GridLayoutManager(this, 1);
        RcvDoorRec.setAdapter(adapter);
        /*设置布局管理者*/
        RcvDoorRec.setLayoutManager(manager);
    }

    private void initData() {
        doorRecs = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            doorRecs.add(i,new DoorRec(i+"--0x111","2017.08.09 14:23:01","freekey解锁","freekey解锁1111111111111111111111111111111111111111"));
        }
        tvRecoId.setText(keyMes.getDoorId());

        laBackUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();

            }
        });
    }

}
