package com.atguigu.im.controller.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Display;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.atguigu.im.R;
import com.atguigu.im.controller.fragment.ChatFragment;
import com.atguigu.im.controller.fragment.ContactListFragment;
import com.atguigu.im.controller.fragment.KeyFragment;
import com.atguigu.im.model.Model;
import com.bumptech.glide.Glide;
import com.hyphenate.chat.EMClient;
import com.hyphenate.easeui.bean.UserDetail;
import com.hyphenate.exceptions.HyphenateException;
import com.squareup.picasso.Picasso;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;
import com.zjnu.thinkpad.myapplication.android.CaptureActivity;

import java.util.List;

// 主页面
public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private RadioGroup rg_main;
    private ChatFragment chatFragment;
    private ContactListFragment contactListFragment;
    private KeyFragment keyFragment;
    private Toolbar toolbar;
    private LinearLayout right;
    private NavigationView left;
    private boolean isDrawer = false;
    private TextView tv_sao, tv_add,username,textView;
    private static final String DECODED_CONTENT_KEY = "codedContent";
    private static final String DECODED_BITMAP_KEY = "codedBitmap";
    private DrawerLayout drawer;
    private Intent intent = null;
    private static final int REQUEST_CODE_SCAN = 0x0000;
    private UserDetail userBean;
    private ImageView icon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("我的钥匙包");
        setSupportActionBar(toolbar);
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        right = (LinearLayout) findViewById(R.id.right);
        left = (NavigationView) findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        initMydata();

//      初始化左边的上面三个
        icon = navigationView.findViewById(R.id.imageView);
        username = navigationView.findViewById(R.id.username);
        textView = navigationView.findViewById(R.id.textView);
        initView();

        initData();

        initListener();

        intiThree();
        right.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (isDrawer) {
                    return left.dispatchTouchEvent(motionEvent);
                } else {
                    return false;
                }
            }
        });



    }

    private void intiThree() {
        UserDetail userByHxId = Model.getInstance().getUserInfoDao().getUserByHxId(EMClient.getInstance().getCurrentUser());
        username.setText(userByHxId.getName());
        textView.setText(userByHxId.getEmail());
        try {
//                int avatarResId = Integer.parseInt(user.getAvatar());
                   /* Glide.with(context)
                            .load(userBean.getPhoto())
                            .diskCacheStrategy( DiskCacheStrategy.NONE )//禁用磁盘缓存
                            .skipMemoryCache( true )//跳过内存缓存
                            .into(imageView);*/
            Picasso.with(MainActivity.this)
                    .load(userByHxId.getPicUrl())
//                            .networkPolicy(NetworkPolicy.NO_CACHE)
//                            .memoryPolicy(MemoryPolicy.NO_CACHE)//不加载缓存
                    .into(icon);
        } catch (Exception e) {
            //use default avatar
            Glide.with(MainActivity.this)
                    .load("")
                    .placeholder(R.drawable.ease_default_avatar)
                    .into(icon);
        }
    }

    private void initMydata() {
        //        开一个线程从环信服务器获取所有好友
        Model.getInstance().getGlobalThreadPool().execute(new Runnable() {
            @Override
            public void run() {
                try {
                    // 获取到所有的好友的环信id
                    List<String> hxids = EMClient.getInstance().contactManager().getAllContactsFromServer();
                    hxids.add(hxids.size() + 1, EMClient.getInstance().getCurrentUser());
                    // 校验
                    if (hxids != null && hxids.size() >= 0) {
                        for (final String hxid : hxids) {
                            //根据hxid获取的所有用户
                            Model.getInstance().getGlobalThreadPool().execute(new Runnable() {
                                @Override
                                public void run() {
                                    String url = "http://192.168.1.104:8080/IntelCd/getPic";
                                    OkHttpUtils
                                            .get()
                                            .url(url)
                                            .addParams("hxid", hxid)
                                            .build()
                                            .execute(new StringCallback() {


                                                @Override
                                                public void onError(okhttp3.Call call, Exception e, int id) {

                                                }

                                                @Override
                                                public void onResponse(String response, int id) {
                                                    userBean = JSON.parseObject(response, UserDetail.class);
                                                    // 保存好友信息到本地数据库
                                                    Model.getInstance().getUserInfoDao().addAccount(userBean);
                                                }
                                            });
                                }
                            });

                        }
                    }
                } catch (HyphenateException e) {
                    e.printStackTrace();
                }
            }
        });
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    private void initListener() {
        // 添加按钮的点击事件处理
        tv_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddContactActivity.class);
                startActivity(intent);
            }
        });

        tv_sao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, CaptureActivity.class);
                startActivityForResult(intent, REQUEST_CODE_SCAN);
            }
        });

        //RadioGroup的选择事件
        rg_main.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                Fragment fragment = null;

                switch (checkedId) {

                    case R.id.rb_main_one:
                        fragment = keyFragment;
                        toolbar.setTitle("我的钥匙包");
                        tv_add.setVisibility(View.GONE);
                        break;

                    // 会话列表页面
                    case R.id.rb_main_chat:
                        fragment = chatFragment;
                        toolbar.setTitle("会话");
                        tv_add.setVisibility(View.GONE);
                        break;

                    // 联系人列表页面
                    case R.id.rb_main_contact:
                        fragment = contactListFragment;
                        toolbar.setTitle("联系人");
                        tv_add.setVisibility(View.VISIBLE);

                        break;
                }

                // 实现fragment切换的方法
                switchFragment(fragment);
            }
        });

        // 默认选择会话列表页面
        rg_main.check(R.id.rb_main_one);


        drawer.setDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                isDrawer = true;
                //获取屏幕的宽高
                WindowManager manager = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
                Display display = manager.getDefaultDisplay();
                //设置右面的布局位置  根据左面菜单的right作为右面布局的left   左面的right+屏幕的宽度（或者right的宽度这里是相等的）为右面布局的right
                right.layout(left.getRight(), 0, left.getRight() + display.getWidth(), display.getHeight());
            }

            @Override
            public void onDrawerOpened(View drawerView) {
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                isDrawer = false;
                if (intent != null) {
                    startActivity(intent);
                    intent = null;
                }

            }

            @Override
            public void onDrawerStateChanged(int newState) {
            }
        });
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE_SCAN && resultCode == RESULT_OK) {
            if (data != null) {

                String content = data.getStringExtra(DECODED_CONTENT_KEY);
                Bitmap bitmap = data.getParcelableExtra(DECODED_BITMAP_KEY);
                Toast.makeText(MainActivity.this, "解码结果： \n" + content, Toast.LENGTH_SHORT).show();

            }
        }
    }


    // 实现fragment切换的方法
    private void switchFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.fl_main, fragment).commit();
    }

    private void initData() {
        // 创建三个fragment对象
        chatFragment = new ChatFragment();
        contactListFragment = new ContactListFragment();
        keyFragment = new KeyFragment();


    }


    private void initView() {
        tv_sao = (TextView) findViewById(R.id.tv_sao);
        rg_main = (RadioGroup) findViewById(R.id.rg_main);
        tv_add = (TextView) findViewById(R.id.tv_add);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawers();
        }

        int id = item.getItemId();
        //了解社区
        if (id == R.id.nav_camera) {

//            账号管理
        } else if (id == R.id.nav_gallery) {

            intent = new Intent(this, UserManagerActivity.class);


//            聊天管理
        } else if (id == R.id.nav_slideshow) {
            //           Toast.makeText(this, "nav_slideshow", Toast.LENGTH_SHORT).show();
            intent = new Intent(this, ChatManagerActivity.class);


//          钥匙包设置
        } else if (id == R.id.nav_manage) {


        }


//        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
//        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


}
