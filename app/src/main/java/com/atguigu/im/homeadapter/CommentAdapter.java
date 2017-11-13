package com.atguigu.im.homeadapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.atguigu.im.R;
import com.atguigu.im.utils.Constant;
import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Created by ZJNU-Hmz on 2017/11/13.
 */

public class CommentAdapter extends RecyclerView.Adapter{
    private final Context mcontext;
    private final List<ChannelBean.ResultBean.FOODBean.CommentBeanX> data;

    private ViewHolder viewHolder;

    public CommentAdapter(Context mcontext, List<ChannelBean.ResultBean.FOODBean.CommentBeanX> commentBeanXes) {
        this.mcontext = mcontext;
        this.data = commentBeanXes;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(View.inflate(mcontext, R.layout.comment_small_list, null));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        viewHolder = (ViewHolder) holder;
        viewHolder.setData(data,position);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        private ImageButton ivPhoto;
        private TextView tvName;
        private TextView tvDate;
        private TextView tvComm;

        /**
         * Find the Views in the layout<br />
         * <br />
         * Auto-created on 2017-11-13 18:08:51 by Android Layout Finder
         * (http://www.buzzingandroid.com/tools/android-layout-finder)
         * @param itemView
         */
        private void findViews(View itemView) {
            ivPhoto = (ImageButton)itemView.findViewById( R.id.iv_photo );
            tvName = (TextView)itemView.findViewById( R.id.tv_name );
            tvDate = (TextView)itemView.findViewById( R.id.tv_date );
            tvComm = (TextView)itemView.findViewById( R.id.tv_comm );

        }



        public ViewHolder(View itemView) {
            super(itemView);
            findViews(itemView);

        }

        public void setData(List<ChannelBean.ResultBean.FOODBean.CommentBeanX> data, int position) {
            tvName.setText(data.get(position).getName());
            tvDate.setText(String.valueOf(data.get(position).getDate()));
            tvComm.setText(data.get(position).getNeirong());
            Glide.with(mcontext).load(Constant.LOADURL + data.get(position).getUrl()).into(ivPhoto);

        }
    }
}
