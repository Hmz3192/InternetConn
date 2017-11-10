package com.atguigu.im.controller.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.atguigu.im.R;

public class ShopListActivity extends Activity implements View.OnClickListener{
    private DrawerLayout dlLeft;
    private ImageView ivBack;
    private EditText etSearchDetail;
    private ImageButton ibGoodsListHome;
    private TextView tvGoodsListSort;
    private LinearLayout llGoodsListPrice;
    private TextView tvGoodsListPrice;
    private ImageView ivGoodsListArrow;
    private TextView tvGoodsListSelect;
    private RecyclerView recyclerview;
    private String position;


    /**
     * Find the Views in the layout<br />
     * <br />
     * Auto-created on 2017-11-10 16:39:09 by Android Layout Finder
     * (http://www.buzzingandroid.com/tools/android-layout-finder)
     */
    private void findViews() {
        dlLeft = (DrawerLayout)findViewById( R.id.dl_left );
        ivBack = (ImageView)findViewById( R.id.iv_back );
        etSearchDetail = (EditText)findViewById( R.id.et_search_detail );
        ibGoodsListHome = (ImageButton)findViewById( R.id.ib_goods_list_home );
        tvGoodsListSort = (TextView)findViewById( R.id.tv_goods_list_sort );
        llGoodsListPrice = (LinearLayout)findViewById( R.id.ll_goods_list_price );
        tvGoodsListPrice = (TextView)findViewById( R.id.tv_goods_list_price );
        ivGoodsListArrow = (ImageView)findViewById( R.id.iv_goods_list_arrow );
        tvGoodsListSelect = (TextView)findViewById( R.id.tv_goods_list_select );
        recyclerview = (RecyclerView)findViewById( R.id.recyclerview );



        ibGoodsListHome.setOnClickListener( this );
    }

    /**
     * Handle button click events<br />
     * <br />
     * Auto-created on 2017-11-10 16:39:09 by Android Layout Finder
     * (http://www.buzzingandroid.com/tools/android-layout-finder)
     */
    @Override
    public void onClick(View v) {
        if ( v == ibGoodsListHome ) {
            // Handle clicks for ibGoodsListHome
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_list);
        findViews();

        position = getIntent().getStringExtra("position");



    }
}
