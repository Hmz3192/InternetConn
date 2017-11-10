package com.atguigu.im.homeadapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;

import com.atguigu.im.R;
import com.atguigu.im.controller.activity.ShopListActivity;
import com.atguigu.im.model.bean.ResultBeanData;
import com.bumptech.glide.Glide;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerClickListener;
import com.youth.banner.listener.OnLoadImageListener;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Hmz on 2017/7/6.
 */

public class HomeFragmentAdapter extends RecyclerView.Adapter {
    /*广告条幅类型*/
    public static final int BANNER = 0;
    /*频道类型*/
    public static final int CHANNEL = 1;
    /*viewPage*/
    public static final int RECOMMEND = 2;


    private static final String GOODSBEAN = "goodsbean";
    /*初始化布局*/
    private final LayoutInflater mLayoutInflater;
    private final Context mcontext;
    /*数据载体*/
    private final ResultBeanData.ResultBean resultBean;
    /*当前默认类型*/
    private int currentType = BANNER;
    private List<ShopBean> shopBeens = new ArrayList<>();


    public HomeFragmentAdapter(Context mcontext, ResultBeanData.ResultBean resultBean) {
        this.mcontext = mcontext;
        this.resultBean = resultBean;
        mLayoutInflater = LayoutInflater.from(mcontext);
    }


    /*相当于getview 创建viewholder部分*/
    /*创建viewholder
    * parent：父类
    * viewType：当前类型*/
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == BANNER) {
            return new BannnerViewHolder(mcontext, mLayoutInflater.inflate(R.layout.banner_viewpager, null));
        } else if (viewType == CHANNEL) {
            return new ChannerViewHolder(mcontext, mLayoutInflater.inflate(R.layout.channel_item, null));
        } else if (viewType == RECOMMEND) {
            return new RecommendViewHolder(mcontext, mLayoutInflater.inflate(R.layout.layout_recommend, null));
        }

        return null;
    }

    /*相当于getview方法中的绑定数据模块*/
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (getItemViewType(position) == BANNER) {
            BannnerViewHolder bannnerViewHolder = (BannnerViewHolder) holder;
            bannnerViewHolder.setData(resultBean.getBanner_info());

        } else if (getItemViewType(position) == CHANNEL) {
            ChannerViewHolder channerViewHolder = (ChannerViewHolder) holder;
            channerViewHolder.setData(resultBean.getChannel_info());
        } else if (getItemViewType(position) == RECOMMEND) {
            RecommendViewHolder viewPageViewHolder = (RecommendViewHolder) holder;

            shopBeens.add(0,new ShopBean("传奇鸡米翅","小吃快餐","浙师大","523m","1003人消费过","url"));
            shopBeens.add(1,new ShopBean("传奇鸡米翅","小吃快餐","浙师大","523m","1003人消费过","url"));
            shopBeens.add(2,new ShopBean("传奇鸡米翅","小吃快餐","浙师大","523m","1003人消费过","url"));
            shopBeens.add(3,new ShopBean("传奇鸡米翅","小吃快餐","浙师大","523m","1003人消费过","url"));

            viewPageViewHolder.setData(shopBeens);

        }


    }

    class ChannerViewHolder extends RecyclerView.ViewHolder {
        private Context mcontext;
        private GridView gcChannel;
        private ChannelAdapter adapter;

        public ChannerViewHolder(final Context mcontext, View itemView) {
            super(itemView);
            this.mcontext = mcontext;
            gcChannel = itemView.findViewById(R.id.gv_channel);
            //设置item的点击事件
            gcChannel.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView parent, View view, int position, long id) {
//                    Toast.makeText(mcontext, "position" + position, Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(mcontext, ShopListActivity.class);
                    intent.putExtra("position", position);
                    mcontext.startActivity(intent);

                }

            });
        }

        public void setData(List<ResultBeanData.ResultBean.ChannelInfoBean> data) {
            /*得到数据了*/
            /*设置gridview适配器*/
            adapter = new ChannelAdapter(mcontext, data);
            gcChannel.setAdapter(adapter);


        }
    }

    private class RecommendViewHolder extends RecyclerView.ViewHolder {
        private final Context context;
        private RecyclerView rec_like;
        private LikeAdapter adapter;

        public RecommendViewHolder(Context mcontext, View inflate) {
            super(inflate);
            this.context = mcontext;
            rec_like = inflate.findViewById(R.id.rec_like);
        }

        public void setData(List<ShopBean> shopBeens) {
            adapter = new LikeAdapter(context,shopBeens);
            GridLayoutManager manager = new GridLayoutManager(mcontext, 1);
            rec_like.setAdapter(adapter);
            /*设置布局管理者*/
            rec_like.setLayoutManager(manager);
        }

    }

    class BannnerViewHolder extends RecyclerView.ViewHolder {
        private Context context;

        private Banner banner;

        public BannnerViewHolder(Context mcontext, View itemview) {
            super(itemview);
            this.context = mcontext;
            this.banner = (Banner) itemview.findViewById(R.id.banner);
        }

        public void setData(List<ResultBeanData.ResultBean.BannerInfoBean> banner_info) {
            /*设置banner的数据*/
            /*得到图片集合地址*/
            List<String> imagesUrl = new ArrayList<>();
            for (int i = 0; i < banner_info.size(); i++) {
                String imageUrl = banner_info.get(i).getImage();
                imagesUrl.add(imageUrl);
            }
            /*设置循环指示点*/
            banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
            /*设置动画效果*/
            banner.setBannerAnimation(Transformer.Accordion);
            /*加载回调图片*/
            banner.setImages(imagesUrl, new OnLoadImageListener() {
                @Override
                public void OnLoadImage(ImageView view, Object url) {
                    /*联网请求图片--Glide*/
                    Glide.with(mcontext).load(Uri.parse("http://10.7.90.214:8080" + url)).into(view);
                }
            });


            /*设置item的点击事件*/
            banner.setOnBannerClickListener(new OnBannerClickListener() {
                @Override
                public void OnBannerClick(int position) {
//                    Toast.makeText(mcontext, "postion== " + (position), Toast.LENGTH_LONG).show();
//                    startGoodsInfoActivity(goodsInfo);
                }
            });
        }


    }

    /*启动商品详情页面*/
//    private void startGoodsInfoActivity(GoodsInfo goodsInfo) {
//        Intent intent = new Intent(mcontext, GoodsInfoActivity.class);
//        intent.putExtra(GOODSBEAN, goodsInfo);
//        mcontext.startActivity(intent);
//
//    }


    /*得到不同类型*/
    @Override
    public int getItemViewType(int position) {
        switch (position) {
            case BANNER:
                currentType = BANNER;
                break;
            case CHANNEL:
                currentType = CHANNEL;
                break;
            case RECOMMEND:
                currentType = RECOMMEND;
                break;
        }
        return currentType;
    }

    /*总共有多少个item*/
    @Override
    public int getItemCount() {
        /*开发过程中从1-->2*/
        return 3;
    }


}
