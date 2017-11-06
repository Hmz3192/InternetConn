package com.atguigu.im.controller.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.atguigu.im.R;
import com.atguigu.im.model.Model;
import com.atguigu.im.model.bean.DoorRec;
import com.atguigu.im.model.bean.KeyMes;

import java.util.List;

public class DoorOpenDetailActivity extends Activity implements View.OnClickListener {
    private ImageView back;
    private TextView kind;
    private TextView location;
    private EditText reason;
    private String id;
    private String doorId;
    private List<DoorRec> doorRecById;
    private KeyMes keyMesByDoorId;

    /**
     * Find the Views in the layout<br />
     * <br />
     * Auto-created on 2017-11-06 15:17:51 by Android Layout Finder
     * (http://www.buzzingandroid.com/tools/android-layout-finder)
     */
    private void findViews() {
        back = (ImageView) findViewById(R.id.back);
        kind = (TextView) findViewById(R.id.kind);
        location = (TextView) findViewById(R.id.location);
        reason = (EditText) findViewById(R.id.reason);
        back.setOnClickListener(this);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_door_open_detail);
        id = getIntent().getStringExtra("id");
        doorId = getIntent().getStringExtra("doorId");
        getDataFromSQL();
        findViews();
        initView();

    }

    private void initView() {
        if (doorRecById.get(0).getOpenKind().equalsIgnoreCase("1")) {
            kind.setText("密码开门");
        }else
            kind.setText("FreeKey开门");


        location.setText(keyMesByDoorId.getDoorLocation());
        reason.setText(doorRecById.get(0).getOpenReson());

    }

    private void getDataFromSQL() {
        doorRecById = Model.getInstance().getDoorRecordDao().getDoorRecById(id);
        keyMesByDoorId = Model.getInstance().getDoorKeyDao().getKeyMesByDoorId(doorId);

    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;

        }
    }
}
