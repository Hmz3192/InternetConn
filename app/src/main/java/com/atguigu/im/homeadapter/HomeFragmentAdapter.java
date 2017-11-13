package com.atguigu.im.homeadapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.atguigu.im.R;
import com.atguigu.im.controller.activity.ShopListActivity;
import com.atguigu.im.utils.Constant;
import com.bumptech.glide.Glide;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerClickListener;
import com.youth.banner.listener.OnLoadImageListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


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
    private List<ChannelBean.ResultBean.KTVBean> ktvBean;
    private Animation mRefreshAnim;

    private static final String GOODSBEAN = "goodsbean";
    /*初始化布局*/
    private final LayoutInflater mLayoutInflater;
    private final Context mcontext;
    private final ChannelBean.ResultBean resultBean;
    /*当前默认类型*/
    private int currentType = BANNER;
    private RecommendViewHolder recommendViewHolder;
    private Handler handler = new Handler(){

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            stopAnim(recommendViewHolder);
            ktvBean = randomList(ktvBean);
            recommendViewHolder.setData(ktvBean);

        }
    };

    public static List<ChannelBean.ResultBean.KTVBean> randomList(List<ChannelBean.ResultBean.KTVBean> objects) {

        List<ChannelBean.ResultBean.KTVBean> objects1Temp = new ArrayList<>();
        int end = objects.size();
        int list = end;
        Random rad = new Random();

        for (int i = 0; i < list; i++) {
            int index = rad.nextInt(end);
            objects1Temp.add(objects.get(index));
            objects.remove(index);
            end--;
        }
        return objects1Temp;
    }

    public HomeFragmentAdapter(Context mcontext, ChannelBean.ResultBean resultBean) {
        this.mcontext = mcontext;
        this.resultBean = resultBean;
        this.ktvBean = resultBean.getKTV();
        mLayoutInflater = LayoutInflater.from(mcontext);
        mRefreshAnim = AnimationUtils.loadAnimation(mcontext, R.anim.anim_rotate_refresh);
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
            recommendViewHolder = new RecommendViewHolder(mcontext, mLayoutInflater.inflate(R.layout.layout_recommend, null));
            recommendViewHolder.ll_refresh.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    starAnim(recommendViewHolder);
                    handler.sendMessageDelayed(Message.obtain(), 2000);
                }
            });
            return recommendViewHolder;

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
            recommendViewHolder = (RecommendViewHolder) holder;
            recommendViewHolder.setData(ktvBean);

        }


    }

    public void stopAnim(RecommendViewHolder recommendViewHolder) {
        recommendViewHolder.textView5.setVisibility(View.VISIBLE);
        mRefreshAnim.reset();
        recommendViewHolder.iv_refresh.clearAnimation();
        recommendViewHolder.iv_refresh.setBackgroundResource(R.drawable.refresh);
    }

    private void starAnim(RecommendViewHolder recommendViewHolder) {
        recommendViewHolder.textView5.setVisibility(View.GONE);
        mRefreshAnim.reset();
        recommendViewHolder.iv_refresh.clearAnimation();
        recommendViewHolder.iv_refresh.setBackgroundResource(R.drawable.refresh);
        recommendViewHolder.iv_refresh.startAnimation(mRefreshAnim);
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
                    intent.putExtra("position", position+"");
                    mcontext.startActivity(intent);

                }

            });
        }

        public void setData(List<ChannelBean.ResultBean.ChannelInfoBean> data) {
            /*得到数据了*/
            /*设置gridview适配器*/
            adapter = new ChannelAdapter(mcontext, data);
            gcChannel.setAdapter(adapter);


        }
    }

    private class RecommendViewHolder extends RecyclerView.ViewHolder {
        private final Context context;
        private RecyclerView rec_like;
        private  RecommndAdapter adapter;
        private LinearLayout ll_refresh;
        private ImageView iv_refresh;
        private TextView textView5;
        public RecommendViewHolder(Context mcontext, View inflate) {
            super(inflate);
            this.context = mcontext;
            rec_like = inflate.findViewById(R.id.rec_like);
            ll_refresh = inflate.findViewById(R.id.ll_refresh);
            iv_refresh = inflate.findViewById(R.id.iv_refresh);
            textView5 = inflate.findViewById(R.id.textView5);
        }

        public void setData(List<ChannelBean.ResultBean.KTVBean> shopBeens) {

            adapter = new RecommndAdapter(context,shopBeens);
            GridLayoutManager manager = new GridLayoutManager(context, 1);
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

        public void setData(List<ChannelBean.ResultBean.BannerInfoBean> banner_info) {
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
                    Glide.with(mcontext).load(Uri.parse(Constant.LOADURL + url)).into(view);
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
