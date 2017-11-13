package com.atguigu.im.homeadapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.atguigu.im.R;
import com.atguigu.im.controller.activity.ShopListActivity;
import com.atguigu.im.utils.Constant;
import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Created by Hmz on 2017/7/28.
 */

public class GoodsListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private final List<ChannelBean.ResultBean.CLOSEBean> datas;
    private final ShopListActivity mcontext;
    private OnItemClickListener onItemClickListener;

    public GoodsListAdapter(ShopListActivity mContext, List<ChannelBean.ResultBean.CLOSEBean> page_data) {
        this.mcontext = mContext;
        this.datas = page_data;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(View.inflate(mcontext, R.layout.item_goods_list_adapter, null));

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        ViewHolder viewHolder = (ViewHolder) holder;
        viewHolder.setData(datas.get(position));
       /* viewHolder.ll_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mcontext, ShopDetailActivity.class);
                intent.putExtra("data", (Serializable) datas.get(position));
                intent.putExtra("kind", "1");
                mcontext.startActivity(intent);

            }
        });*/
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView iv_hot;
        private TextView tv_name;
        private TextView tv_price;
        private ChannelBean.ResultBean.CLOSEBean data;
        private LinearLayout ll_close;
        public ViewHolder(View itemView) {
            super(itemView);
            iv_hot = (ImageView) itemView.findViewById(R.id.iv_hot);
            tv_name = (TextView) itemView.findViewById(R.id.tv_name);
            tv_price = (TextView) itemView.findViewById(R.id.tv_price);
            ll_close = itemView.findViewById(R.id.ll_close);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onItemClickListener != null) {
                        onItemClickListener.setOnItemClickListener(data);
                    }
                }
            });
        }

        public void setData(ChannelBean.ResultBean.CLOSEBean data) {
            this.data = data;
            Glide.with(mcontext).load(Constant.LOADURL +data.getUrl()).into(iv_hot);
            tv_name.setText(data.getName());
            tv_price.setText("￥" + data.getPrice());

        }
    }
    public interface OnItemClickListener {
        void setOnItemClickListener(ChannelBean.ResultBean.CLOSEBean data);
    }
    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
}
