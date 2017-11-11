package com.atguigu.im.homeadapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.atguigu.im.R;
import com.atguigu.im.utils.Constant;
import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Created by ZJNU-Hmz on 2017/11/8.
 */

public class KTVListAdapter extends RecyclerView.Adapter {
    private final Context mcontext;
    private final List<ChannelBean.ResultBean.KTVBean> datas;
    private ViewHolder viewHolder;

    public KTVListAdapter(Context context, List<ChannelBean.ResultBean.KTVBean> shopBeens) {
        this.mcontext = context;
        this.datas = shopBeens;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewHolder view = new ViewHolder(View.inflate(mcontext, R.layout.ktv_list, null));
        return view;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        viewHolder = (ViewHolder) holder;
        viewHolder.tvKtvName.setText(datas.get(position).getName());
        viewHolder.tvKtvPrice.setText(datas.get(position).getPrice());
        viewHolder.tvKtvLong.setText(datas.get(position).getLength());
        viewHolder.tvKtvNumber.setText(datas.get(position).getNumber());
        viewHolder.ktvRatingBar.setRating(datas.get(position).getRating());
        Glide.with(mcontext).load(Constant.LOADURL +datas.get(position).getUrl()).into(viewHolder.iv_ktv);

    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvKtvName;
        private RatingBar ktvRatingBar;
        private TextView tvKtvPrice;
        private TextView tvKtvLong;
        private TextView tvKtvNumber;
        private ImageView iv_ktv;

        /**
         * Find the Views in the layout<br />
         * <br />
         * Auto-created on 2017-11-11 14:07:40 by Android Layout Finder
         * (http://www.buzzingandroid.com/tools/android-layout-finder)
         * @param itemView
         */
        private void findViews(View itemView) {
            tvKtvName = (TextView)itemView.findViewById( R.id.tv_ktv_name );
            ktvRatingBar = (RatingBar)itemView.findViewById( R.id.ktv_ratingBar );
            iv_ktv = itemView.findViewById(R.id.iv_ktv);
            tvKtvPrice = (TextView)itemView.findViewById( R.id.tv_ktv_price );
            tvKtvLong = (TextView)itemView.findViewById( R.id.tv_ktv_long );
            tvKtvNumber = (TextView)itemView.findViewById( R.id.tv_ktv_number );
        }

        public ViewHolder(View itemView) {
            super(itemView);
            findViews(itemView);
        }


    }
}
