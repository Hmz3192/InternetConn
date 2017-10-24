package com.atguigu.im.controller.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.atguigu.im.R;
import com.atguigu.im.controller.activity.DetailActivity;
import com.atguigu.im.model.bean.KeyMes;

import java.util.List;

/**
 * Created by ZJNU-Hmz on 2017/10/23.
 */

public class KeyAdapter extends RecyclerView.Adapter {


    private final Context mcontext;
    private List<KeyMes> datas;
    private ViewHolder viewHolder;


    public KeyAdapter(Context mcontext, List<KeyMes> keyMes) {
        this.mcontext = mcontext;
        this.datas = keyMes;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new ViewHolder(View.inflate(mcontext, R.layout.key_list, null));

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        viewHolder = (ViewHolder) holder;
        viewHolder.tv_name.setText(datas.get(position).getName());
        viewHolder.tv_id.setText(datas.get(position).getId());
        viewHolder.LL_key.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mcontext, DetailActivity.class);
                intent.putExtra("mes",  datas.get(position));
                mcontext.startActivity(intent);
            }
        });

        viewHolder.LL_key.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {

                return false;
            }
        });

    }

    @Override
    public int getItemCount() {
        return datas.size();
    }



    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tv_id, tv_name;
        private LinearLayout LL_key;

        public ViewHolder(View itemView) {
            super(itemView);
            initView(itemView);
        }


        private void initView(View itemView) {
            LL_key = itemView.findViewById(R.id.LL_key);
            tv_id = itemView.findViewById(R.id.tv_id);
            tv_name = itemView.findViewById(R.id.tv_name);
        }
    }
}
