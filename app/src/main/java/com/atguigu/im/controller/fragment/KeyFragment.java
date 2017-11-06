package com.atguigu.im.controller.fragment;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSONArray;
import com.atguigu.im.R;
import com.atguigu.im.controller.activity.DetailActivity;
import com.atguigu.im.model.Model;
import com.atguigu.im.model.bean.KeyMes;
import com.atguigu.im.model.bean.UserDetail;
import com.atguigu.im.utils.Constant;
import com.atguigu.im.utils.PasswordView;
import com.hyphenate.chat.EMClient;
import com.kyleduo.switchbutton.SwitchButton;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.List;

/**
 * Created by ZJNU-Hmz on 2017/10/10.
 */

public class KeyFragment extends BaseFragment {
    private RecyclerView recy;

    private List<KeyMes> keyMesList;
    private KeyAdapter adapter;
    private int postion;
    private PasswordView passwordView;
    private KeyAdapter.ViewHolder viewHolder;
    private UserDetail userByHxId;
    private LinearLayout ll;
    @Override
    public View initView() {
        View view = View.inflate(mcontext, R.layout.fragment_key, null);
        recy = view.findViewById(R.id.recy);
        ll = view.findViewById(R.id.ll);
        userByHxId = Model.getInstance().getUserInfoDao().getUserByHxId(EMClient.getInstance().getCurrentUser());

        procssData();

        return view;
    }


    @Override
    public void initData() {
        super.initData();

    }

    private void procssData() {
        getDataFromNew();

    }

    private void getDataFromNew() {
        Model.getInstance().getGlobalThreadPool().execute(new Runnable() {
            @Override
            public void run() {
                try {

                    Model.getInstance().getGlobalThreadPool().execute(new Runnable() {
                        @Override
                        public void run() {
                            String url = Constant.GETKETUSER;
                            OkHttpUtils
                                    .get()
                                    .url(url)
                                    .addParams("hxid", Model.getInstance().getGlobalUser())
                                    .build()
                                    .execute(new StringCallback() {


                                        @Override
                                        public void onError(okhttp3.Call call, Exception e, int id) {

                                        }

                                        @Override
                                        public void onResponse(String response, int id) {
                                            initAdapter(response);

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

    private void initAdapter(String response) {
        keyMesList = JSONArray.parseArray(response, KeyMes.class);
        if (keyMesList == null || keyMesList.size() == 0) {
            ll.setVisibility(View.VISIBLE);
            return;
        }

        Log.e("++++++++++++++++++3", keyMesList.toString());
        for (KeyMes keyMes : keyMesList) {
            // 保存自己信息到本地数据库
            Model.getInstance().getDoorKeyDao().addAccount(keyMes);
        }


        //设置适配器
        adapter = new KeyAdapter(mcontext, keyMesList);
        GridLayoutManager manager = new GridLayoutManager(mcontext, 1);
        recy.setAdapter(adapter);
                                            /*设置布局管理者*/
        recy.setLayoutManager(manager);
    }


    class KeyAdapter extends RecyclerView.Adapter {
        private PopupWindow mPopupWindow;
        private View popupView;
        private final Context mcontext;
        private List<KeyMes> datas;


        public KeyAdapter(Context mcontext, List<KeyMes> keyMes) {
            this.mcontext = mcontext;
            this.datas = keyMes;


        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            ViewHolder view = new ViewHolder(View.inflate(mcontext, R.layout.key_list, null));
            popupView = getActivity().getLayoutInflater().inflate(R.layout.layout_popupwindow, null);
            passwordView = popupView.findViewById(R.id.view_pass);
            return view;

        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
            viewHolder = (ViewHolder) holder;
            if (datas.get(position).getDoorKind().equalsIgnoreCase("1")) {
                viewHolder.door_iv.setImageDrawable(getResources().getDrawable(R.drawable.home_door));
            } else if (datas.get(position).getDoorKind().equalsIgnoreCase("2")) {
                viewHolder.door_iv.setImageDrawable(getResources().getDrawable(R.drawable.big_door));
            }else if (datas.get(position).getDoorKind().equalsIgnoreCase("3")) {
                viewHolder.door_iv.setImageDrawable(getResources().getDrawable(R.drawable.factory_door));
            }


            viewHolder.tv_name.setText(datas.get(position).getDoorLocation());
            viewHolder.tv_id.setText(datas.get(position).getDoorId());
            viewHolder.LL_key.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(mcontext, DetailActivity.class);
                    intent.putExtra("mes", datas.get(position));
                    mcontext.startActivity(intent);
                }
            });

            //开门
            viewHolder.sb_default.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
//                Toast.makeText(mcontext, b ? "开":"关", Toast.LENGTH_SHORT).show();
                    if (b) {
                        initWindows();
                    }
                }
            });
            //密码输入完成
            passwordView.setOnFinishInput(new PasswordView.OnPasswordInputFinish() {
                @Override
                public void inputFinish() {
                    mPopupWindow.dismiss();
                    WindowManager.LayoutParams params = getActivity().getWindow().getAttributes();
                    params.alpha = 1f;
                    getActivity().getWindow().setAttributes(params);
                    Toast.makeText(mcontext, passwordView.getStrPassword()+"=="+userByHxId.getPassword(), Toast.LENGTH_SHORT).show();
                    if (passwordView.getStrPassword().equalsIgnoreCase(userByHxId.getPassword())) {
                        Toast.makeText(mcontext, "开门成功", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(mcontext, "密码不对", Toast.LENGTH_SHORT).show();
//                        viewHolder.sb_default.setChecked(false);
                        viewHolder.sb_default.toggle();

                    }

                }
            });


            viewHolder.LL_key.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {

                    return false;
                }
            });

        }

        private void initWindows() {
            mPopupWindow = new PopupWindow(popupView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
            mPopupWindow.setTouchable(true);
            mPopupWindow.setOutsideTouchable(true);
//        mPopupWindow.setBackgroundDrawable(new BitmapDrawable(getResources(), (Bitmap) null));
            mPopupWindow.setAnimationStyle(R.style.anim_menu_bottombar);


        /*阴影*/
            WindowManager.LayoutParams params = getActivity().getWindow().getAttributes();
            params.alpha = 0.7f;
            getActivity().getWindow().setAttributes(params);


            mPopupWindow.getContentView().setFocusableInTouchMode(true);
            mPopupWindow.getContentView().setFocusable(true);
            mPopupWindow.showAtLocation(getView().findViewById(R.id.recy), Gravity.BOTTOM, 0, 0);

            mPopupWindow.getContentView().setOnKeyListener(new View.OnKeyListener() {
                @Override
                public boolean onKey(View v, int keyCode, KeyEvent event) {
                    if (keyCode == KeyEvent.KEYCODE_MENU && event.getRepeatCount() == 0
                            && event.getAction() == KeyEvent.ACTION_DOWN) {
                        if (mPopupWindow != null && mPopupWindow.isShowing()) {
                            mPopupWindow.dismiss();
                            WindowManager.LayoutParams params = getActivity().getWindow().getAttributes();
                            params.alpha = 1f;
                            getActivity().getWindow().setAttributes(params);
                        }
                        return true;
                    }
                    return false;
                }
            });
        }

        @Override
        public int getItemCount() {
            return datas.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder {
            private TextView tv_id, tv_name;
            private LinearLayout LL_key;
            private SwitchButton sb_default;
            private ImageView door_iv;
            public ViewHolder(View itemView) {
                super(itemView);
                initView(itemView);
            }


            private void initView(View itemView) {
                LL_key = itemView.findViewById(R.id.LL_key);
                door_iv = itemView.findViewById(R.id.door_iv);
                sb_default = itemView.findViewById(R.id.sb_default);
                tv_id = itemView.findViewById(R.id.tv_id);
                tv_name = itemView.findViewById(R.id.tv_name);
            }
        }


    }


}
