package com.atguigu.im.controller.fragment;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.atguigu.im.R;
import com.atguigu.im.controller.activity.DetailActivity;
import com.atguigu.im.model.bean.KeyMes;
import com.atguigu.im.utils.PasswordView;
import com.kyleduo.switchbutton.SwitchButton;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ZJNU-Hmz on 2017/10/10.
 */

public class KeyFragment extends BaseFragment {
    private RecyclerView recy;

    private List<KeyMes> keyMes;
    private KeyAdapter adapter;
    private int postion;
    private PasswordView passwordView;
    private KeyAdapter.ViewHolder viewHolder;

    @Override
    public View initView() {
        View view = View.inflate(mcontext, R.layout.fragment_key, null);
        recy = view.findViewById(R.id.recy);



        procssData();

        return view;
    }



    @Override
    public void initData() {
        super.initData();

    }

    private void procssData() {
        keyMes = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            keyMes.add(i, new KeyMes(i + "1x0001", "第一小区一单元一栋302门", "1","xiaoming","location",i+"123"));

        }
        //设置适配器
        adapter = new KeyAdapter(mcontext, keyMes);



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
            viewHolder.tv_name.setText(datas.get(position).getName());
            viewHolder.tv_id.setText(datas.get(position).getId());
            viewHolder.LL_key.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(mcontext, DetailActivity.class);
                    intent.putExtra("mes",  datas.get(position));
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

                    Toast.makeText(mcontext, "输入的密码是"+passwordView.getStrPassword()
                            +"设置的密码"+ datas.get(position).getPassword(), Toast.LENGTH_SHORT).show();
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
            public ViewHolder(View itemView) {
                super(itemView);
                initView(itemView);
            }


            private void initView(View itemView) {
                LL_key = itemView.findViewById(R.id.LL_key);
                sb_default = itemView.findViewById(R.id.sb_default);
                tv_id = itemView.findViewById(R.id.tv_id);
                tv_name = itemView.findViewById(R.id.tv_name);
            }
        }



    }






}
