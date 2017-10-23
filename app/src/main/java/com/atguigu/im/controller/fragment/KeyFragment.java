package com.atguigu.im.controller.fragment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.Toast;

import com.atguigu.im.R;
import com.atguigu.im.controller.adapter.KeyAdapter;
import com.atguigu.im.model.bean.KeyMes;
import com.github.clans.fab.FloatingActionButton;
import com.zjnu.thinkpad.myapplication.android.CaptureActivity;

import java.util.ArrayList;
import java.util.List;

import static android.app.Activity.RESULT_OK;

/**
 * Created by ZJNU-Hmz on 2017/10/10.
 */

public class KeyFragment extends BaseFragment {
    private TextView tv_sao;
    private RecyclerView recy;
    private static final int REQUEST_CODE_SCAN = 0x0000;
    private static final String DECODED_CONTENT_KEY = "codedContent";
    private static final String DECODED_BITMAP_KEY = "codedBitmap";
    private List<KeyMes> keyMes;
    private KeyAdapter adapter;
    private FloatingActionButton add, get;
    private int postion;
    @Override
    public View initView() {
        View view = View.inflate(mcontext, R.layout.fragment_key, null);
        tv_sao = view.findViewById(R.id.tv_sao);
        recy = view.findViewById(R.id.recy);
        add = view.findViewById(R.id.menu_add);
        get = view.findViewById(R.id.menu_get);
        procssData();

        return view;
    }

    @Override
    public void initData() {
        super.initData();
        tv_sao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(),
                        CaptureActivity.class);
                startActivityForResult(intent, REQUEST_CODE_SCAN);
            }
        });

        recy.setOnCreateContextMenuListener(new View.OnCreateContextMenuListener() {
            @Override
            public void onCreateContextMenu(ContextMenu contextMenu, View view, ContextMenu.ContextMenuInfo contextMenuInfo) {
                contextMenu.add(0, ContextMenu.FIRST+1, 0, "删除");
                contextMenu.add(1, ContextMenu.FIRST+1, 0, "删除");

            }
        });

    }

    private void procssData() {
        keyMes = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            keyMes.add(i, new KeyMes(i + "1x0001", "第一小区一单元一栋302门", "1"));

        }


        //设置适配器
        adapter = new KeyAdapter(mcontext, keyMes);



        GridLayoutManager manager = new GridLayoutManager(mcontext, 1);
        recy.setAdapter(adapter);
        /*设置布局管理者*/
        recy.setLayoutManager(manager);

        // 绑定listview和contextmenu
        registerForContextMenu(recy);

        recy.setOnCreateContextMenuListener(new View.OnCreateContextMenuListener() {
            @Override
            public void onCreateContextMenu(ContextMenu contextMenu, View view, ContextMenu.ContextMenuInfo contextMenuInfo) {
                contextMenu.add(0, ContextMenu.FIRST+1, 0, "编辑");
                contextMenu.add(1, ContextMenu.FIRST+2, 0, "删除");

            }
        });
    }


    @Override
    public boolean onContextItemSelected(MenuItem item) {
        if (item.getItemId() == ContextMenu.FIRST + 1) {
            AdapterView.AdapterContextMenuInfo menuInfo = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

            //编辑
            Toast.makeText(mcontext, "bianji" + menuInfo.position, Toast.LENGTH_SHORT).show();
        }
        if (item.getItemId() == ContextMenu.FIRST + 2) {
            AdapterView.AdapterContextMenuInfo menuInfo = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

            //删除
            Toast.makeText(mcontext, "删除" + menuInfo.position, Toast.LENGTH_SHORT).show();

        }
        return super.onContextItemSelected(item);

    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE_SCAN && resultCode == RESULT_OK) {
            if (data != null) {

                String content = data.getStringExtra(DECODED_CONTENT_KEY);
                Bitmap bitmap = data.getParcelableExtra(DECODED_BITMAP_KEY);
                Toast.makeText(mcontext, "解码结果： \n" + content, Toast.LENGTH_SHORT).show();

            }
        }
    }
}
