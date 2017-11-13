package com.atguigu.im.controller.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.androidkun.PullToRefreshRecyclerView;
import com.androidkun.callback.PullToRefreshListener;
import com.atguigu.im.R;
import com.atguigu.im.homeadapter.ChannelBean;
import com.atguigu.im.homeadapter.ExpandableListViewAdapter;
import com.atguigu.im.homeadapter.GoodsListAdapter;
import com.atguigu.im.homeadapter.KTVListAdapter;
import com.atguigu.im.homeadapter.LikeAdapter;
import com.atguigu.im.homeadapter.SpaceItemDecoration;
import com.atguigu.im.model.Model;
import com.atguigu.im.utils.Constant;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.List;

public class ShopListActivity extends Activity implements View.OnClickListener {
    private LinearLayout llGoodsListHead;
    private ImageButton ibGoodsListBack;
    private ImageButton ibGoodsListHome;
    private TextView tvGoodsListSort;
    private LinearLayout llGoodsListPrice;
    private TextView tvGoodsListPrice;
    private ImageView ivGoodsListArrow;
    private TextView tvGoodsListSelect;
    private String position;
    private EditText etSearchDetail;
    /*筛选的额外窗*/
    private LinearLayout ll_select_root;
    private LinearLayout ll_price_root;
    private LinearLayout ll_theme_root;
    private LinearLayout ll_type_root;
    private ExpandableListView listView;


    /*筛选的返回*/
    private ImageButton ib_drawer_layout_back;
    private int click_count = 0;

    private RelativeLayout rl_select_price;
    private RelativeLayout rl_select_recommend_theme;
    private RelativeLayout rl_select_type;
    private Button btn_drawer_layout_cancel;
    private Button btn_drawer_layout_confirm;
    private RelativeLayout rl_price_30_50;
    private RelativeLayout rl_theme_note;
    private Button btn_drawer_type_confirm;
    private Button btn_drawer_type_cancel;

    private Button btn_drawer_theme_confirm;
    private Button btn_drawer_theme_cancel;

    private ArrayList<String> group;
    private ArrayList<List<String>> child;
    private ImageView iv_price_30_50, ivBack;
    private ImageView iv_theme_note;
    private DrawerLayout dl_left;
    private int price_statue = 1;
    private int them_statue = 1;
    private TextView ib_drawer_layout_confirm;

    private ExpandableListViewAdapter adapter;
    private LikeAdapter foodadapter;
    private GoodsListAdapter adapter1;
    private KTVListAdapter ktvadapter;
    private ChannelBean ktvBeans;
    private SwipeRefreshLayout swipe_refresh_widget;
    private GridLayoutManager manager;
    private int lastVisibleItem;
    private PullToRefreshRecyclerView pullToRefreshRV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_list);
        findViews();

        position = getIntent().getStringExtra("position");
        ll_select_root.setVisibility(View.VISIBLE);
        ib_drawer_layout_back.setVisibility(View.VISIBLE);


        /*筛选页面*/
        showSelectorLayout();
        getData();
    }

    private void getData() {
        Model.getInstance().getGlobalThreadPool().execute(new Runnable() {
            @Override
            public void run() {
                try {
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
                                            ktvBeans = JSON.parseObject(response, ChannelBean.class);
                                            inidData(position, ktvBeans);
                                        }
                                    });

                        }
                    });
                    // 校验
                } catch (Exception e) {

                }
            }
        });
    }

    private void inidData(String position, final ChannelBean ktvBeans) {
        if (position.equalsIgnoreCase("0")) {
            //shiwu
            foodadapter = new LikeAdapter(this, ktvBeans.getResult().getFOOD());
            manager = new GridLayoutManager(this, 1);
            pullToRefreshRV.setAdapter(foodadapter);
            /*设置布局管理者*/
            pullToRefreshRV.setLayoutManager(manager);


           foodadapter.setOnItemClickListener(new LikeAdapter.OnItemClickListener() {
               @Override
               public void setOnItemClickListener(ChannelBean.ResultBean.FOODBean data) {
                   Intent intent = new Intent(ShopListActivity.this, ShopDetailActivity.class);
                   intent.putExtra("kind", "0");
                   intent.putExtra("name", data.getName());
                   startActivity(intent);

//                   Toast.makeText(ShopListActivity.this, "" + data.getName(), Toast.LENGTH_SHORT).show();
               }
           });



        } else if (position.equalsIgnoreCase("1")) {
            //shangpin
            GridLayoutManager manager = new GridLayoutManager(ShopListActivity.this, 2);
            pullToRefreshRV.setLayoutManager(manager);
            pullToRefreshRV.addItemDecoration(new SpaceItemDecoration(10));
            adapter1 = new GoodsListAdapter(ShopListActivity.this, ktvBeans.getResult().getCLOSE());

            pullToRefreshRV.setAdapter(adapter1);
            adapter1.setOnItemClickListener(new GoodsListAdapter.OnItemClickListener() {
                @Override
                public void setOnItemClickListener(ChannelBean.ResultBean.CLOSEBean data) {
                    Intent intent = new Intent(ShopListActivity.this, ShopDetailActivity.class);
                    intent.putExtra("kind", "1");
                    intent.putExtra("name", data.getName());
                    startActivity(intent);
//                    Toast.makeText(ShopListActivity.this, "" + data.getName(), Toast.LENGTH_SHORT).show();


                }
            });



        } else if (position.equalsIgnoreCase("2")) {

            ktvadapter = new KTVListAdapter(this, ktvBeans.getResult().getKTV());
            GridLayoutManager manager = new GridLayoutManager(this, 1);
            pullToRefreshRV.setAdapter(ktvadapter);
            /*设置布局管理者*/
            pullToRefreshRV.setLayoutManager(manager);

            ktvadapter.setOnItemClickListener(new KTVListAdapter.OnItemClickListener() {
                @Override
                public void setOnItemClickListener(ChannelBean.ResultBean.KTVBean data) {
                    Intent intent = new Intent(ShopListActivity.this, ShopDetailActivity.class);
                    intent.putExtra("kind", "2");
                    intent.putExtra("name", data.getName());
                    startActivity(intent);
//                    Toast.makeText(ShopListActivity.this, "" + data.getName(), Toast.LENGTH_SHORT).show();

                }
            });
        }

        //是否开启下拉刷新功能
        pullToRefreshRV.setPullRefreshEnabled(true);
        //是否开启上拉加载功能
        pullToRefreshRV.setLoadingMoreEnabled(true);
        //设置是否显示上次刷新的时间
        pullToRefreshRV.displayLastRefreshTime(true);
        //设置刷新回调
        pullToRefreshRV.setPullToRefreshListener(new PullToRefreshListener() {
            @Override
            public void onRefresh() {
                pullToRefreshRV.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        pullToRefreshRV.setRefreshComplete();
                        //模拟没有数据的情况
//                        adapter.notifyDataSetChanged();
                    }
                }, 2000);
            }

            @Override
            public void onLoadMore() {
                pullToRefreshRV.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        pullToRefreshRV.setLoadMoreComplete();
                        //模拟加载数据的情况
//                        int size = data.size();
//                        for (int i = size; i < size + 4; i++) {
//                            data.add("" + i + i + i + i);
//                        }
//                        adapter.notifyDataSetChanged();
                    }
                }, 2000);
            }
        });
        //主动触发下拉刷新操作
        //pullToRefreshRV.onRefresh();
    }






    /**
     * Find the Views in the layout<br />
     * <br />
     * Auto-created on 2017-11-10 16:39:09 by Android Layout Finder
     * (http://www.buzzingandroid.com/tools/android-layout-finder)
     */
    private void findViews() {
        dl_left = (DrawerLayout) findViewById(R.id.dl_left);
        ivBack = (ImageView) findViewById(R.id.iv_back);
        etSearchDetail = (EditText) findViewById(R.id.et_search_detail);
        pullToRefreshRV = findViewById(R.id.pullToRefreshRV);
        ibGoodsListHome = (ImageButton) findViewById(R.id.ib_goods_list_home);
        tvGoodsListSort = (TextView) findViewById(R.id.tv_goods_list_sort);
        llGoodsListPrice = (LinearLayout) findViewById(R.id.ll_goods_list_price);
        tvGoodsListPrice = (TextView) findViewById(R.id.tv_goods_list_price);
        ivGoodsListArrow = (ImageView) findViewById(R.id.iv_goods_list_arrow);
        tvGoodsListSelect = (TextView) findViewById(R.id.tv_goods_list_select);
        tvGoodsListSelect.setOnClickListener(this);
        llGoodsListPrice.setOnClickListener(this);
        ibGoodsListHome.setOnClickListener(this);

        //筛选菜单
        ll_select_root = (LinearLayout) findViewById(R.id.ll_select_root);
        ll_price_root = (LinearLayout) findViewById(R.id.ll_price_root);
        ll_theme_root = (LinearLayout) findViewById(R.id.ll_theme_root);
        ll_type_root = (LinearLayout) findViewById(R.id.ll_type_root);
        ib_drawer_layout_confirm = findViewById(R.id.ib_drawer_layout_confirm);


        ib_drawer_layout_back = (ImageButton) findViewById(R.id.ib_drawer_layout_back);
        btn_drawer_layout_cancel = (Button) findViewById(R.id.btn_drawer_layout_cancel);
        btn_drawer_layout_confirm = (Button) findViewById(R.id.btn_drawer_layout_confirm);
        rl_price_30_50 = (RelativeLayout) findViewById(R.id.rl_price_30_50);
        rl_theme_note = (RelativeLayout) findViewById(R.id.rl_theme_note);
        btn_drawer_type_confirm = (Button) findViewById(R.id.btn_drawer_type_confirm);
        btn_drawer_type_cancel = (Button) findViewById(R.id.btn_drawer_type_cancel);
        btn_drawer_theme_confirm = (Button) findViewById(R.id.btn_drawer_theme_confirm);
        btn_drawer_theme_cancel = (Button) findViewById(R.id.btn_drawer_theme_cancel);

        dl_left = (DrawerLayout) findViewById(R.id.dl_left);
        rl_select_price = (RelativeLayout) findViewById(R.id.rl_select_price);
        rl_select_recommend_theme = (RelativeLayout) findViewById(R.id.rl_select_recommend_theme);
        rl_select_type = (RelativeLayout) findViewById(R.id.rl_select_type);
        listView = (ExpandableListView) findViewById(R.id.expandableListView);
        iv_price_30_50 = findViewById(R.id.iv_price_30_50);
        iv_theme_note = findViewById(R.id.iv_theme_note);

        ibGoodsListHome.setOnClickListener(this);
        llGoodsListPrice.setOnClickListener(this);
        tvGoodsListSort.setOnClickListener(this);
        tvGoodsListSelect.setOnClickListener(this);
        rl_select_price.setOnClickListener(this);
        rl_select_recommend_theme.setOnClickListener(this);
        rl_select_type.setOnClickListener(this);
        ib_drawer_layout_back.setOnClickListener(this);
        btn_drawer_layout_cancel.setOnClickListener(this);
        btn_drawer_layout_confirm.setOnClickListener(this);
        rl_price_30_50.setOnClickListener(this);
        rl_theme_note.setOnClickListener(this);
        btn_drawer_type_confirm.setOnClickListener(this);
        btn_drawer_type_cancel.setOnClickListener(this);
        btn_drawer_theme_confirm.setOnClickListener(this);
        btn_drawer_theme_cancel.setOnClickListener(this);
        ib_drawer_layout_confirm.setOnClickListener(this);



    }


    /**
     * Handle button click events<br />
     * <br />
     * Auto-created on 2017-11-10 16:39:09 by Android Layout Finder
     * (http://www.buzzingandroid.com/tools/android-layout-finder)
     */
    @Override
    public void onClick(View v) {
        if (v == ibGoodsListHome) {
            // Handle clicks for ibGoodsListHome
            finish();

        } else if (v == llGoodsListPrice) {
            //价格点击事件
            click_count++;
            //综合排序变为默认
            tvGoodsListSort.setTextColor(Color.parseColor("#333538"));
            //价格红
            tvGoodsListPrice.setTextColor(Color.parseColor("#ed4141"));
            if (click_count % 2 == 1) {
                // 箭头向下红
                ivGoodsListArrow.setBackgroundResource(R.drawable.new_price_sort_desc);
            } else {
                // 箭头向上红
                ivGoodsListArrow.setBackgroundResource(R.drawable.new_price_sort_asc);
            }
        } else if (v == tvGoodsListSelect) {
            //筛选的点击事件
            tvGoodsListSelect.setTextColor(Color.parseColor("#ed4141"));
            //打开画布
            dl_left.openDrawer(Gravity.RIGHT);
        } else if (v == tvGoodsListSort) {
            //综合排序点击事件
            click_count = 0;
            ivGoodsListArrow.setBackgroundResource(R.drawable.new_price_sort_normal);
            tvGoodsListPrice.setTextColor(Color.parseColor("#333538"));
            tvGoodsListSort.setTextColor(Color.parseColor("#ed4141"));
        } else if (v == rl_select_price) {
            //价格筛选的页面
            ll_price_root.setVisibility(View.VISIBLE);
            ib_drawer_layout_back.setVisibility(View.GONE);
            //价格页面
            showPriceLayout();
        } else if (v == rl_select_recommend_theme) {
            //主题筛选的页面
            ll_theme_root.setVisibility(View.VISIBLE);
            ib_drawer_layout_back.setVisibility(View.GONE);
            //主题页面
            showThemeLayout();
        } else if (v == rl_select_type) {
            ll_type_root.setVisibility(View.VISIBLE);
            ib_drawer_layout_back.setVisibility(View.GONE);
            //类别页面

            showTypeLayout();
        } else if (v == ib_drawer_layout_back) {
            /*筛选的返回*/
            dl_left.closeDrawers();
        } else if (v == ib_drawer_layout_confirm) {
            /*筛选的确定*/
            dl_left.closeDrawers();
        } else if (v == btn_drawer_layout_cancel) {
//            Toast.makeText(GoodsListActivity.this, "取消", Toast.LENGTH_SHORT).show();

            ll_select_root.setVisibility(View.VISIBLE);
            ib_drawer_layout_back.setVisibility(View.VISIBLE);
            showSelectorLayout();
        } else if (v == btn_drawer_layout_confirm) {
//            Toast.makeText(GoodsListActivity.this, "确认", Toast.LENGTH_SHORT).show();
            ll_select_root.setVisibility(View.VISIBLE);
            ib_drawer_layout_back.setVisibility(View.VISIBLE);
            showSelectorLayout();
        } else if (v == rl_price_30_50) {
//            点击价格
//            Toast.makeText(GoodsListActivity.this, "123123123", Toast.LENGTH_SHORT).show();
            if (price_statue == 1) {
                iv_theme_note.setVisibility(View.GONE);
                price_statue = 0;
            } else if (price_statue == 0) {
                iv_theme_note.setVisibility(View.VISIBLE);
                price_statue = 1;
            }
        } else if (v == rl_theme_note) {
            /*点击主题*/
//            Toast.makeText(GoodsListActivity.this, "123123123", Toast.LENGTH_SHORT).show();
            if (them_statue == 1) {
                iv_price_30_50.setVisibility(View.GONE);
                them_statue = 0;
            } else if (them_statue == 0) {
                iv_price_30_50.setVisibility(View.VISIBLE);
                them_statue = 1;
            }
        } else if (v == btn_drawer_type_confirm) {
//            类别中
//            Toast.makeText(GoodsListActivity.this, "确认", Toast.LENGTH_SHORT).show();
            ll_select_root.setVisibility(View.VISIBLE);
            ib_drawer_layout_back.setVisibility(View.VISIBLE);
            showSelectorLayout();
        } else if (v == btn_drawer_type_cancel) {
//            Toast.makeText(GoodsListActivity.this, "取消", Toast.LENGTH_SHORT).show();
            ll_select_root.setVisibility(View.VISIBLE);
            ib_drawer_layout_back.setVisibility(View.VISIBLE);
            showSelectorLayout();
        } else if (v == btn_drawer_theme_confirm) {
//            主题中
//            Toast.makeText(GoodsListActivity.this, "确认", Toast.LENGTH_SHORT).show();
            ll_select_root.setVisibility(View.VISIBLE);
            ib_drawer_layout_back.setVisibility(View.VISIBLE);
            showSelectorLayout();
        } else if (v == btn_drawer_theme_cancel) {
            ll_select_root.setVisibility(View.VISIBLE);
            ib_drawer_layout_back.setVisibility(View.VISIBLE);
            showSelectorLayout();
        }
    }


    //筛选页面
    private void showSelectorLayout() {
        ll_price_root.setVisibility(View.GONE);
        ll_theme_root.setVisibility(View.GONE);
        ll_type_root.setVisibility(View.GONE);
    }

    //价格页面
    private void showPriceLayout() {
        ll_select_root.setVisibility(View.GONE);
        ll_theme_root.setVisibility(View.GONE);
        ll_type_root.setVisibility(View.GONE);
    }

    //主题页面
    private void showThemeLayout() {
        ll_select_root.setVisibility(View.GONE);
        ll_price_root.setVisibility(View.GONE);
        ll_type_root.setVisibility(View.GONE);
    }

    //类别页面
    private void showTypeLayout() {
        ll_select_root.setVisibility(View.GONE);
        ll_price_root.setVisibility(View.GONE);
        ll_theme_root.setVisibility(View.GONE);

        //初始化ExpandableListView
        initExpandableListView();
        adapter = new ExpandableListViewAdapter(this, group, child);
        listView.setAdapter(adapter);
    }


    private void initExpandableListView() {
        group = new ArrayList<>();
        child = new ArrayList<>();
        //去掉默认箭头
        listView.setGroupIndicator(null);
        addInfo("全部", new String[]{"上衣", "下装"});
        addInfo("上衣", new String[]{"古风", "和风", "lolita", "日常"});
        addInfo("下装", new String[]{"日常", "泳衣", "汉风", "lolita", "创意T恤"});
        addInfo("外套", new String[]{"汉风", "古风", "lolita", "胖次", "南瓜裤", "日常"});

        // 这里是控制如果列表没有孩子菜单不展开的效果
        listView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent,
                                        View v, int groupPosition, long id) {
                if (child.get(groupPosition).isEmpty()) {// isEmpty没有
                    return true;
                } else {
                    return false;
                }
            }
        });
    }


    /**
     * 添加数据信息
     *
     * @param g
     * @param c
     */
    private void addInfo(String g, String[] c) {
        group.add(g);
        List<String> list = new ArrayList<String>();
        for (int i = 0; i < c.length; i++) {
            list.add(c[i]);
        }
        child.add(list);
    }

}
