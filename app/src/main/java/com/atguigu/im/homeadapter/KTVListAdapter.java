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

public class KTVListAdapter extends RecyclerView.Adapter {
    private final Context mcontext;
    private final List<ChannelBean.ResultBean.KTVBean> datas;
    private ViewHolder viewHolder;
    private OnItemClickListener onItemClickListener;

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
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        viewHolder = (ViewHolder) holder;
        viewHolder.setData(datas.get(position));

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
        private LinearLayout ll_ktv;
        private ImageView iv_ktv;
        private ChannelBean.ResultBean.KTVBean data;

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
            ll_ktv = itemView.findViewById(R.id.ll_ktv);
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


        public void setData(ChannelBean.ResultBean.KTVBean ktvBean) {
            this.data = ktvBean;
            tvKtvName.setText(data.getName());
            tvKtvPrice.setText(data.getPrice());
            tvKtvLong.setText(data.getLength());
            tvKtvNumber.setText(data.getNumber());
            ktvRatingBar.setRating(data.getRating());
            Glide.with(mcontext).load(Constant.LOADURL +data.getUrl()).into(iv_ktv);
        }
    }

    public interface OnItemClickListener {
        void setOnItemClickListener(ChannelBean.ResultBean.KTVBean data);
    }
    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
}
