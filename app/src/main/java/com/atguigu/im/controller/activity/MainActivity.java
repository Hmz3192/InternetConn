package com.atguigu.im.controller.activity;

import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.wifi.WifiManager;
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
import android.util.Log;
import android.view.Display;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.atguigu.im.R;
import com.atguigu.im.bluetooth.BluetoothEventManager;
import com.atguigu.im.controller.fragment.ChatFragment;
import com.atguigu.im.controller.fragment.ContactListFragment;
import com.atguigu.im.controller.fragment.KeyFragment;
import com.atguigu.im.controller.fragment.ShopFragment;
import com.atguigu.im.model.Model;
import com.atguigu.im.model.bean.KeyMes;
import com.atguigu.im.model.bean.UserDetail;
import com.atguigu.im.utils.BitmapUtils;
import com.atguigu.im.utils.Constant;
import com.atguigu.im.wifihelper.WifiAdmin;
import com.atguigu.im.wifihelper.WifiApAdmin;
import com.bumptech.glide.Glide;
import com.hyphenate.chat.EMClient;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;
import com.zjnu.thinkpad.myapplication.android.CaptureActivity;

import java.text.SimpleDateFormat;
import java.util.Date;

// 主页面
public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private RadioGroup rg_main;
    private ChatFragment chatFragment;
    private ContactListFragment contactListFragment;
    private KeyFragment keyFragment;
    private ShopFragment shopFragment;
    private Toolbar toolbar;
    private LinearLayout right;
    private NavigationView left;
    private boolean isDrawer = false;
    private TextView tv_sao, tv_add, username, textView;
    private static final String DECODED_CONTENT_KEY = "codedContent";
    private static final String DECODED_BITMAP_KEY = "codedBitmap";
    private DrawerLayout drawer;
    private Intent intent = null;
    private static final int REQUEST_CODE_SCAN = 0x0000;
    private UserDetail userBean;
    private ImageView icon;
    private String user;
    private View menu;
    private BluetoothEventManager mBluetoothEventManager;
    private static final int REQUEST_CODE_BLUETOOTH_ON = 1313;
    private WifiAdmin mWifiAdmin;
    private WifiApAdmin wifiAp;
    private WifiManager mWifiManager;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        context = this;
        user = EMClient.getInstance().getCurrentUser();
        Log.e("++++++++++++++++++", user);
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

        initMydata(user);
        menu = (View) navigationView.getHeaderView(0);
//      初始化左边的上面三个
        icon = (ImageView) menu.findViewById(R.id.imageView_email);
        username = (TextView) menu.findViewById(R.id.username_email);
        textView = (TextView) menu.findViewById(R.id.textView_email);
        initView();

        initData();

        initListener();

        //开启蓝牙
        startBlueTooth();
       /* //开启wifi
        mWifiAdmin = new WifiAdmin(MainActivity.this) {
            @Override
            public void myUnregisterReceiver(BroadcastReceiver receiver) {
                unregisterReceiver(receiver);
            }

            @Override
            public Intent myRegisterReceiver(BroadcastReceiver receiver, IntentFilter filter) {
                registerReceiver(receiver, filter);
                return null;
            }

            @Override
            public void onNotifyWifiConnected() {
            }

            @Override
            public void onNotifyWifiConnectFailed() {
            }
        };
        mWifiAdmin.openWifi();
        mWifiAdmin.addNetwork(mWifiAdmin.createWifiInfo("HotSpotRobin", "123456789",
                WifiAdmin.TYPE_WPA));
        right.setOnTouchListener(new View.OnTouchListener()
        {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (isDrawer) {
                    return left.dispatchTouchEvent(motionEvent);
                } else {
                    return false;
                }
            }
        });*/


    }

    // 打开WIFI
    public void openWifi() {
        if (mWifiAdmin != null) {
            mWifiAdmin.openWifi();
            return;
        }
        if (mWifiManager == null) {
            mWifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
        }
        if (!mWifiManager.isWifiEnabled()) {
            mWifiManager.setWifiEnabled(true);
        }
    }

    private void startBlueTooth() {
        BluetoothAdapter mBluetoothAdapter = BluetoothAdapter
                .getDefaultAdapter();
        if (mBluetoothAdapter == null) {
            Toast.makeText(this, "本机没有找到蓝牙硬件或驱动！", Toast.LENGTH_SHORT).show();
            finish();
        }
        // 如果本地蓝牙没有开启，则开启
        if (!mBluetoothAdapter.isEnabled()) {
            // 我们通过startActivityForResult()方法发起的Intent将会在onActivityResult()回调方法中获取用户的选择，比如用户单击了Yes开启，
            // 那么将会收到RESULT_OK的结果，
            // 如果RESULT_CANCELED则代表用户不愿意开启蓝牙
            Intent mIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(mIntent, 1);
            // 用enable()方法来开启，无需询问用户(实惠无声息的开启蓝牙设备),这时就需要用到android.permission.BLUETOOTH_ADMIN权限。
            // mBluetoothAdapter.enable();
            // mBluetoothAdapter.disable();//关闭蓝牙
        }
    }

    private void intiThree() {
        UserDetail userByHxId = Model.getInstance().getUserInfoDao().getUserByHxId(user);
        username.setText(userByHxId.getName());
        textView.setText(userByHxId.getEmail());
        try {

            Picasso.with(MainActivity.this).load(Constant.LOADURL + userByHxId.getPicUrl()).transform(new Transformation() {
                @Override
                public Bitmap transform(Bitmap bitmap) {
                    //先对图片进行压缩
//                Bitmap zoom = BitmapUtils.zoom(bitmap, DensityUtil.dip2px(mContext, 62), DensityUtil.dip2px(mContext, 62));
                    Bitmap zoom = BitmapUtils.zoom(bitmap, 90, 90);
                    //对请求回来的Bitmap进行圆形处理
                    Bitmap ciceBitMap = BitmapUtils.circleBitmap(zoom);
                    bitmap.recycle();//必须队更改之前的进行回收
                    return ciceBitMap;
                }

                @Override
                public String key() {
                    return "";
                }
            }).into(icon);

        } catch (Exception e) {
            //use default avatar
            Glide.with(MainActivity.this)
                    .load("")
                    .placeholder(R.drawable.ease_default_avatar)
                    .into(icon);
        }
    }

    private void initMydata(final String user) {
        final String url = Constant.GETONEINFO;

        Model.getInstance().getGlobalThreadPool().execute(new Runnable() {
            @Override
            public void run() {
                OkHttpUtils
                        .get()
                        .url(url)
                        .addParams("hxid", user)
                        .build()
                        .execute(new StringCallback() {
                            @Override
                            public void onError(okhttp3.Call call, Exception e, int id) {

                            }

                            @Override
                            public void onResponse(String response, int id) {
                                userBean = JSON.parseObject(response, UserDetail.class);
                                Log.e("Saving --userBean:---", userBean.toString());
                                // 保存自己信息到本地数据库
                                Model.getInstance().getUserInfoDao().addAccount(userBean);
                                intiThree();
                            }
                        });
                // 校验
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

                    case R.id.rb_main_shop:
                        fragment = shopFragment;
                        toolbar.setTitle("发现");
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
//                Toast.makeText(MainActivity.this, "解码结果： \n" + content, Toast.LENGTH_SHORT).show();
                Date day=new Date();
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//                Log.e("contentTag", content);
//                KeyMes parse = (KeyMes) JSON.parse(content);
                final KeyMes keyMes = JSON.parseObject(content, KeyMes.class);
                keyMes.setAddTime(df.format(day));
                keyMes.setUserId(Integer.valueOf(EMClient.getInstance().getCurrentUser()));
//                Log.e("-----------",keyMes.getDimension() + " | \n" + keyMes.getDoorKind() + " | \n" + keyMes.getDoorLocation() + " | \n");

                /*{
                    "doorName": "Hb-Wh-Ja-100305",
                        "doorLocation": "地球中国湖北省武汉市江岸区201号301门",
                        "doorKind": 3,
                        "longitude": 2233.4514,
                        "dimension": 53456.2356
                }*/
                Model.getInstance().getGlobalThreadPool().execute(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            String url = Constant.ADDKEY;
                            OkHttpUtils
                                    .post()
                                    .url(url)
                                    .addParams("userId", String.valueOf(keyMes.getUserId()))
                                    .addParams("doorName", keyMes.getDoorName())
                                    .addParams("doorLocation", keyMes.getDoorLocation())
                                    .addParams("addTime", keyMes.getAddTime())
                                    .addParams("doorKind", keyMes.getDoorKind())
                                    .addParams("longitude", keyMes.getLongitude())
                                    .addParams("dimension", keyMes.getDimension())
                                    .build()
                                    .execute(new StringCallback() {
                                        @Override
                                        public void onError(okhttp3.Call call, Exception e, int id) {

                                        }

                                        @Override
                                        public void onResponse(String response, int id) {
                                            initAdapter(response,keyMes);
                                        }
                                    });
                        } catch (Exception e) {

                        }
                    }
                });
            }
        }

        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                Toast.makeText(this, "蓝牙已经开启", Toast.LENGTH_SHORT).show();
            } else if (resultCode == RESULT_CANCELED) {
                Toast.makeText(this, "不允许蓝牙开启", Toast.LENGTH_SHORT).show();
                finish();
            }
        }
    }

    private void initAdapter(String response, KeyMes keyMes) {
        Model.getInstance().getDoorKeyDao().addAccount(keyMes);
        refresh();
    }

    private void refresh() {
        Intent intent = new Intent(MainActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
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
        shopFragment = new ShopFragment();

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

            intent = new Intent(this, KeysManagerActivity.class);

        }


//        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
//        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


}
