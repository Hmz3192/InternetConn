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

public class RecommndAdapter extends RecyclerView.Adapter {
    private final Context mcontext;
    private final List<ChannelBean.ResultBean.KTVBean> datas;
    private ViewHolder viewHolder;
    private int a, b, c;

    public RecommndAdapter(Context context, List<ChannelBean.ResultBean.KTVBean> shopBeens) {

        this.mcontext = context;
        this.datas = shopBeens;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewHolder view = new ViewHolder(View.inflate(mcontext, R.layout.recommend_list, null));
        return view;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        viewHolder = (ViewHolder) holder;

            //设置ktvc
            viewHolder.tvRecName.setText(datas.get(position).getName());
            viewHolder.recRatingBar.setRating(datas.get(position).getRating());
            viewHolder.tvRecPrice.setText(datas.get(position).getPrice());
            viewHolder.tvRecNumber.setText(datas.get(position).getNumber());
            viewHolder.tvRecLong.setText(datas.get(position).getLength());
            Glide.with(mcontext).load(Constant.LOADURL +datas.get(position).getUrl()).into(viewHolder.ivRecUrl);



    }



    @Override
    public int getItemCount() {
        return 4;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView ivRecUrl;
        private TextView tvRecName;
        private RatingBar recRatingBar;
        private TextView tvRecPrice;
        private TextView tvRecLong;
        private TextView tvRecKind;
        private TextView tvRecNumber;

        /**
         * Find the Views in the layout<br />
         * <br />
         * Auto-created on 2017-11-11 16:56:14 by Android Layout Finder
         * (http://www.buzzingandroid.com/tools/android-layout-finder)
         * @param itemView
         */
        private void findViews(View itemView) {
            ivRecUrl = (ImageView) this.itemView.findViewById( R.id.iv_rec_url );
            tvRecName = (TextView) this.itemView.findViewById( R.id.tv_rec_name );
            recRatingBar = (RatingBar) this.itemView.findViewById( R.id.rec_ratingBar );
            tvRecPrice = (TextView) this.itemView.findViewById( R.id.tv_rec_price );
            tvRecLong = (TextView) this.itemView.findViewById( R.id.tv_rec_long );
            tvRecKind = (TextView) this.itemView.findViewById( R.id.tv_rec_kind );
            tvRecNumber = (TextView) this.itemView.findViewById( R.id.tv_rec_number );
        }


        public ViewHolder(View itemView) {
            super(itemView);
            findViews(itemView);
        }


    }
}
