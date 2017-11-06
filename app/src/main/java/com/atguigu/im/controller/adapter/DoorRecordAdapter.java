package com.atguigu.im.controller.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.atguigu.im.R;
import com.atguigu.im.controller.activity.DoorOpenDetailActivity;
import com.atguigu.im.model.bean.DoorRec;

import java.util.List;

/**
 * Created by ZJNU-Hmz on 2017/10/25.
 */

public class DoorRecordAdapter extends RecyclerView.Adapter{

    private final Context mcontext;
    private final List<DoorRec> datas;

    public DoorRecordAdapter(Context context, List<DoorRec> doorRecs) {
        this.mcontext = context;
        this.datas = doorRecs;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(View.inflate(mcontext, R.layout.record_door, null));

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
       ViewHolder viewHolder = (ViewHolder) holder;
        viewHolder.tv_rec_date.setText(datas.get(position).getOpenTime());
        if (datas.get(position).getOpenKind().equalsIgnoreCase("1")) {
            viewHolder.tv_rec_kind.setText("密码开门");
        }else
            viewHolder.tv_rec_kind.setText("FreeKey开门");

        viewHolder.ll_rec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Toast.makeText(mcontext, "_" + position, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(mcontext, DoorOpenDetailActivity.class);
                intent.putExtra("id",String.valueOf(datas.get(position).getId()));
                intent.putExtra("doorId", datas.get(position).getDoorId());
                mcontext.startActivity(intent);
            }
        });
        }

    @Override
    public int getItemCount() {
        return datas.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tv_rec_date, tv_rec_kind;
        private LinearLayout ll_rec;
        public ViewHolder(View itemView) {
            super(itemView);
            initView(itemView);
        }


        private void initView(View itemView) {
            ll_rec = itemView.findViewById(R.id.ll_rec);
            tv_rec_date = itemView.findViewById(R.id.tv_rec_date);
            tv_rec_kind = itemView.findViewById(R.id.tv_rec_kind);
        }
    }

}
