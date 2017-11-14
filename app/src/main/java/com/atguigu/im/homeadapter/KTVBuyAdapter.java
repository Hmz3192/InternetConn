package com.atguigu.im.homeadapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.atguigu.im.R;
import com.atguigu.im.utils.Constant;
import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Created by ZJNU-Hmz on 2017/11/13.
 */

public class KTVBuyAdapter extends RecyclerView.Adapter{
    private final Context mcontext;
    private final List<ChannelBean.ResultBean.KTVBean.BuyBean> data;
    private ViewHolder viewHolder;

    public KTVBuyAdapter(Context mcontext, List<ChannelBean.ResultBean.KTVBean.BuyBean> foodBean) {
        this.mcontext = mcontext;
        this.data = foodBean;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(View.inflate(mcontext, R.layout.ktv_small_list, null));
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
        private ImageView ivSmallPic;
        private TextView ivSmallName;
        private TextView ivSmallPrice;
        private TextView ivSmallSelled;

        /**
         * Find the Views in the layout<br />
         * <br />
         * Auto-created on 2017-11-13 15:58:12 by Android Layout Finder
         * (http://www.buzzingandroid.com/tools/android-layout-finder)
         * @param itemView
         */
        private void findViews(View itemView) {
            ivSmallPic = (ImageView)itemView.findViewById( R.id.iv_small_pic );
            ivSmallName = (TextView)itemView.findViewById( R.id.iv_small_name );
            ivSmallPrice = (TextView)itemView.findViewById( R.id.iv_small_price );
            ivSmallSelled = (TextView)itemView.findViewById( R.id.iv_small_selled );

        }



        public ViewHolder(View itemView) {
            super(itemView);
            findViews(itemView);

        }

        public void setData(List<ChannelBean.ResultBean.KTVBean.BuyBean> data, int position) {
            ivSmallName.setText(data.get(position).getName());
            ivSmallPrice.setText("￥ "+String.valueOf(data.get(position).getPrice()));
            ivSmallSelled.setText(data.get(position).getSelled());
            Glide.with(mcontext).load(Constant.LOADURL + data.get(position).getUrl()).into(ivSmallPic);

        }
    }
}
