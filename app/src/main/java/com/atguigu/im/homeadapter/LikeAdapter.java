package com.atguigu.im.homeadapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.atguigu.im.R;

import java.util.List;

/**
 * Created by ZJNU-Hmz on 2017/11/8.
 */

public class LikeAdapter extends RecyclerView.Adapter {
    private final Context mcontext;
    private final List<ShopBean> datas;
    private ViewHolder viewHolder;

    public LikeAdapter(Context context, List<ShopBean> shopBeens) {
        this.mcontext = context;
        this.datas = shopBeens;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewHolder view = new ViewHolder(View.inflate(mcontext, R.layout.like_list, null));
        return view;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        viewHolder = (ViewHolder) holder;
        viewHolder.tvShopName.setText(datas.get(position).getName());
        viewHolder.tvShopKind.setText(datas.get(position).getKind());
        viewHolder.tvShopLocation.setText(datas.get(position).getLocation());
        viewHolder.tvShopLong.setText(datas.get(position).getLength());
        viewHolder.tvShopNumber.setText(datas.get(position).getNumber());

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

        /**
         * Find the Views in the layout<br />
         * <br />
         * Auto-created on 2017-11-09 16:54:56 by Android Layout Finder
         * (http://www.buzzingandroid.com/tools/android-layout-finder)
         */
        private void findViews(View itemView) {
            tvShopName = (TextView)itemView.findViewById( R.id.tv_shop_name );
            tvShopKind = (TextView)itemView.findViewById( R.id.tv_shop_kind );
            tvShopLocation = (TextView)itemView.findViewById( R.id.tv_shop_location );
            tvShopLong = (TextView)itemView.findViewById( R.id.tv_shop_long );
            tvShopNumber = (TextView)itemView.findViewById( R.id.tv_shop_number );
        }

        public ViewHolder(View itemView) {
            super(itemView);
            findViews(itemView);
        }


    }
}
