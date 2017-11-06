package com.atguigu.im.model.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.atguigu.im.model.dao.KeyInfoTable;

/**
 * Created by Administrator on 2016/9/23.
 */
public class DoorKeyDB extends SQLiteOpenHelper {
    // 构造
    public DoorKeyDB(Context context) {
        super(context, "mykey.db", null, 1);
    }

    // 数据库创建的时候调用
    @Override
    public void onCreate(SQLiteDatabase db) {
        // 创建数据库表的语句
        db.execSQL(KeyInfoTable.CREATE_TAB);
    }

    // 数据库更新的时候调用
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
