package com.atguigu.im.homeadapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import com.atguigu.im.R;
import com.atguigu.im.utils.Constant;
import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Created by ZJNU-Hmz on 2017/11/8.
 */

public class LikeAdapter extends RecyclerView.Adapter {
    private final Context mcontext;
    private final List<ChannelBean.ResultBean.FOODBean> datas;
    private OnItemClickListener onItemClickListener;

    private ViewHolder viewHolder;

    public LikeAdapter(Context context, List<ChannelBean.ResultBean.FOODBean> shopBeens) {
        this.mcontext = context;
        this.datas = shopBeens;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewHolder view = new ViewHolder(View.inflate(mcontext, R.layout.like_list, null));
        return view;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        viewHolder = (ViewHolder) holder;
        viewHolder.setData(datas.get(position));




    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvShopName;
        private TextView tvShopKind;
        private TextView tvShopLocation;
        private TextView tvShopLong;
        private TextView tvShopNumber;
        private RatingBar ratingBar;
        private ImageView like_iv;
        private LinearLayout shiwu_ll;
        private ChannelBean.ResultBean.FOODBean data;

        /**
         * Find the Views in the layout<br />
         * <br />
         * Auto-created on 2017-11-09 16:54:56 by Android Layout Finder
         * (http://www.buzzingandroid.com/tools/android-layout-finder)
         */
        private void findViews(View itemView) {
            ratingBar = itemView.findViewById(R.id.ratingBar);
            like_iv = itemView.findViewById(R.id.like_iv);
            tvShopName = (TextView)itemView.findViewById( R.id.tv_shop_name );
            tvShopKind = (TextView)itemView.findViewById( R.id.tv_shop_kind );
            tvShopLocation = (TextView)itemView.findViewById( R.id.tv_shop_location );
            tvShopLong = (TextView)itemView.findViewById( R.id.tv_shop_long );
            shiwu_ll = itemView.findViewById(R.id.shiwu_ll);
            tvShopNumber = (TextView)itemView.findViewById( R.id.tv_shop_number );
        }

        public ViewHolder(View itemView) {
            super(itemView);
            findViews(itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onItemClickListener != null) {
                        onItemClickListener.setOnItemClickListener(data);
                    }
                }
            });
        }

        public void setData(ChannelBean.ResultBean.FOODBean foodBean) {
            this.data = foodBean;
            tvShopName.setText(data.getName());
            tvShopKind.setText(data.getKind());
            tvShopLocation.setText(data.getLocation());
            tvShopLong.setText(data.getLength());
            tvShopNumber.setText(data.getNumber());
            ratingBar.setRating(data.getRating());
            Glide.with(mcontext).load(Constant.LOADURL +data.getUrl()).into(like_iv);

        }
    }

        public interface OnItemClickListener {
            void setOnItemClickListener(ChannelBean.ResultBean.FOODBean data);
        }
        public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
            this.onItemClickListener = onItemClickListener;
        }
}
