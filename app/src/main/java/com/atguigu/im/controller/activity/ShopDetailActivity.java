package com.atguigu.im.controller.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.atguigu.im.R;
import com.atguigu.im.homeadapter.BuyAdapter;
import com.atguigu.im.homeadapter.ChannelBean;
import com.atguigu.im.homeadapter.CloseCommentAdapter;
import com.atguigu.im.homeadapter.CommentAdapter;
import com.atguigu.im.homeadapter.KTVBuyAdapter;
import com.atguigu.im.homeadapter.KtvCommentAdapter;
import com.atguigu.im.model.Model;
import com.atguigu.im.utils.Constant;
import com.bumptech.glide.Glide;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import static com.atguigu.im.R.id.iv_good_info_image;

public class ShopDetailActivity extends Activity implements View.OnClickListener {

    private String kind;
    private ChannelBean Beans;
    private ChannelBean.ResultBean.KTVBean ktvBean  = new ChannelBean.ResultBean.KTVBean();
    private ChannelBean.ResultBean.FOODBean  foodBean = new ChannelBean.ResultBean.FOODBean();
    private ChannelBean.ResultBean.CLOSEBean  closeBean = new ChannelBean.ResultBean.CLOSEBean();

    private ImageButton ibGoodInfoBack;
    private ImageButton ibGoodInfoMore;
    private ImageView ivGoodInfoImage;
    private TextView tvGoodInfoName;
    private RatingBar detailRatingBar;
    private TextView tvGoodInfoDesc;
    private TextView tvGoodLocation;
    private RecyclerView lvBuy;
    private RecyclerView lvComm;
    private String name;
    private TextView detail_price;
    private TextView detail_kind,tv_hint;
    private LinearLayout ll_root;
    private TextView tv_more_home;
    private LinearLayout ll_location;
    private LinearLayout ll_buy,ll_comm;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_detail);
        findViews();
        kind = getIntent().getStringExtra("kind");  //0shiwu  1.yifu  2.ktv
        name = getIntent().getStringExtra("name");
        GetDataFromNet();


//

    }

    private void GetDataFromNet() {
                        Model.getInstance().getGlobalThreadPool().execute(new Runnable() {
                            @Override
                            public void run() {
                                String url = Constant.GETRECOMMEND;
                                OkHttpUtils
                                        .get()
                                        .url(url)
                                        .build()
                                        .execute(new StringCallback() {
                                            @Override
                                            public void onError(okhttp3.Call call, Exception e, int id) {

                                            }

                                            @Override
                                            public void onResponse(String response, int id) {
                                                Log.e("++++++++++", response.toString());
                                                Beans = JSON.parseObject(response, ChannelBean.class);
                                                ChoiceData(Beans, kind, name);
                                            }
                                        });
                            }
                        // 校

            });
    }

    private void ChoiceData(ChannelBean Beans, String kind, String name) {

        if (kind.equalsIgnoreCase("2")) {
            for (ChannelBean.ResultBean.KTVBean ktvBean1 : Beans.getResult().getKTV()) {
                if (name.equalsIgnoreCase(ktvBean1.getName())) {
                    ktvBean = ktvBean1;

                    Glide.with(this).load(Constant.LOADURL +ktvBean.getUrl()).into(ivGoodInfoImage);
                    tvGoodInfoName.setText(ktvBean.getName());
                    detailRatingBar.setRating(ktvBean.getRating());
                    detail_price.setText(ktvBean.getPrice());
                    tvGoodInfoDesc.setText(ktvBean.getIntroduce());
                    tvGoodLocation.setText(ktvBean.getLocation());

                    //购买的物品
                    KTVBuyAdapter buyAdapter = new KTVBuyAdapter(this, ktvBean.getBuy());
                    GridLayoutManager manager = new GridLayoutManager(this, 1);
                    lvBuy.setAdapter(buyAdapter);
                    /*设置布局管理者*/
                    lvBuy.setLayoutManager(manager);

//                    评论
                    KtvCommentAdapter commAdapter = new KtvCommentAdapter(this, ktvBean.getComment());
                    GridLayoutManager manager1 = new GridLayoutManager(this, 1);
                    lvComm.setAdapter(commAdapter);
                    /*设置布局管理者*/
                    lvComm.setLayoutManager(manager1);
                }
            }
        } else if (kind.equalsIgnoreCase("1")) {
            for (ChannelBean.ResultBean.CLOSEBean closeBean1 : Beans.getResult().getCLOSE()) {
                if (name.equalsIgnoreCase(closeBean1.getName())) {
                    closeBean = closeBean1;

                    ll_location.setVisibility(View.GONE);
                    ll_buy.setVisibility(View.GONE);

                    Glide.with(this).load(Constant.LOADURL +closeBean.getUrl()).into(ivGoodInfoImage);
                    tvGoodInfoName.setText(closeBean.getName());
                    detailRatingBar.setRating(closeBean.getRating());
                    detail_price.setText("$ "+closeBean.getPrice());
                    tvGoodInfoDesc.setText(closeBean.getIntroduce());
                    detail_kind.setText(closeBean.getKind());

                    CloseCommentAdapter commAdapter = new CloseCommentAdapter(this, closeBean.getComment());
                    GridLayoutManager manager1 = new GridLayoutManager(this, 1);
                    lvComm.setAdapter(commAdapter);
                    /*设置布局管理者*/
                    lvComm.setLayoutManager(manager1);
                }
            }
        } if (kind.equalsIgnoreCase("0")) {
            for (ChannelBean.ResultBean.FOODBean foodBean1 : Beans.getResult().getFOOD()) {
                if (name.equalsIgnoreCase(foodBean1.getName())) {
                    foodBean = foodBean1;
                    Glide.with(this).load(Constant.LOADURL +foodBean.getUrl()).into(ivGoodInfoImage);
                    tvGoodInfoName.setText(foodBean.getName());
                    detailRatingBar.setRating(foodBean.getRating());
                    detail_price.setText(foodBean.getPrice());
                    tvGoodInfoDesc.setText(foodBean.getIntroduce());
                    tvGoodLocation.setText(foodBean.getLocation());
                    detail_kind.setText(foodBean.getKind());

                    //购买的物品
                    BuyAdapter  buyAdapter = new BuyAdapter(this, foodBean.getBuy());
                    GridLayoutManager manager = new GridLayoutManager(this, 1);
                    lvBuy.setAdapter(buyAdapter);
                    /*设置布局管理者*/
                    lvBuy.setLayoutManager(manager);

//                    评论
                    CommentAdapter commAdapter = new CommentAdapter(this, foodBean.getComment());
                    GridLayoutManager manager1 = new GridLayoutManager(this, 1);
                    lvComm.setAdapter(commAdapter);
                    /*设置布局管理者*/
                    lvComm.setLayoutManager(manager1);
                }
            }
        }


    }



    /**
     * Find the Views in the layout<br />
     * <br />
     * Auto-created on 2017-11-12 16:10:42 by Android Layout Finder
     * (http://www.buzzingandroid.com/tools/android-layout-finder)
     */
    private void findViews() {
        tv_hint = findViewById(R.id.tv_hint);
        detail_kind = findViewById(R.id.detail_kind);
        detail_price = findViewById(R.id.detail_price);
        ibGoodInfoBack = (ImageButton) findViewById(R.id.ib_good_info_back);
        ll_location = findViewById(R.id.ll_location);
        ibGoodInfoMore = (ImageButton) findViewById(R.id.ib_good_info_more);
        ivGoodInfoImage = (ImageView) findViewById(iv_good_info_image);
        tvGoodInfoName = (TextView) findViewById(R.id.tv_good_info_name);
        detailRatingBar = (RatingBar) findViewById(R.id.detail_ratingBar);
        tvGoodInfoDesc = (TextView) findViewById(R.id.tv_good_info_desc);
        ll_comm = findViewById(R.id.ll_comm);
        ll_buy = findViewById(R.id.ll_buy);
        tvGoodLocation = (TextView) findViewById(R.id.tv_good_location);
        lvBuy = (RecyclerView) findViewById(R.id.lv_buy);
        lvComm = (RecyclerView) findViewById(R.id.lv_comm);
        ll_root = findViewById(R.id.ll_root);
        tv_more_home = findViewById(R.id.tv_more_home);
        tv_more_home.setOnClickListener(this);
        ibGoodInfoBack.setOnClickListener(this);
        ibGoodInfoMore.setOnClickListener(this);
    }

    /**
     * Handle button click events<br />
     * <br />
     * Auto-created on 2017-11-12 16:10:42 by Android Layout Finder
     * (http://www.buzzingandroid.com/tools/android-layout-finder)
     */
    @Override
    public void onClick(View v) {
        if (v == ibGoodInfoBack) {
            // Handle clicks for ibGoodInfoBack
            finish();

        } else if (v == ibGoodInfoMore) {
            // Handle clicks for ibGoodInfoMore
            if (ll_root.getVisibility() == View.VISIBLE) {
                ll_root.setVisibility(View.GONE);
            } else {
                ll_root.setVisibility(View.VISIBLE);
            }
        } else if (v == tv_more_home) {
            finish();
        }
    }


}
