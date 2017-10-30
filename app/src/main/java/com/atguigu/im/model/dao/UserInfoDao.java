package com.atguigu.im.model.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.atguigu.im.model.db.UserInfoDB;
import com.hyphenate.easeui.bean.UserDetail;

/**
 * Created by ZJNU-Hmz on 2017/10/30.
 */

public class UserInfoDao {

    private final UserInfoDB mHelper;

    public UserInfoDao(Context context) {
        mHelper = new UserInfoDB(context);
    }



    // 添加用户到数据库
    public void addAccount(UserDetail user) {
        // 获取数据库对象
        SQLiteDatabase db = mHelper.getReadableDatabase();

        // 执行添加操作
        ContentValues values = new ContentValues();
        values.put(UserInfoTable.COL_HXID, user.getAccount());
        values.put(UserInfoTable.COL_NAME, user.getName());
        values.put(UserInfoTable.COL_EMAIL, user.getEmail());
        values.put(UserInfoTable.COL_PHOTO, user.getPicUrl());
        values.put(UserInfoTable.COL_PASS, user.getSixPasswrod());
        values.put(UserInfoTable.COL_PHONE, user.getIphone());


        db.replace(UserInfoTable.TAB_NAME, null, values);
    }



    // 根据环信id获取所有用户信息
    public UserDetail getUserByHxId(String hxId) {
        // 获取数据库对象
        SQLiteDatabase db = mHelper.getReadableDatabase();

        // 执行查询语句
        String sql = "select * from " + UserInfoTable.TAB_NAME + " where " + UserInfoTable.COL_HXID + "=?";
        Cursor cursor = db.rawQuery(sql, new String[]{hxId});

        UserDetail userInfo = null;
        if(cursor.moveToNext()) {
            userInfo = new UserDetail();

            // 封装对象
            userInfo.setAccount(cursor.getString(cursor.getColumnIndex(UserInfoTable.COL_HXID)));
            userInfo.setName(cursor.getString(cursor.getColumnIndex(UserInfoTable.COL_NAME)));
            userInfo.setEmail(cursor.getString(cursor.getColumnIndex(UserInfoTable.COL_EMAIL)));
            userInfo.setPicUrl(cursor.getString(cursor.getColumnIndex(UserInfoTable.COL_PHOTO)));
            userInfo.setIphone(cursor.getString(cursor.getColumnIndex(UserInfoTable.COL_PHONE)));
            userInfo.setSixPasswrod(cursor.getString(cursor.getColumnIndex(UserInfoTable.COL_PASS)));
        }

        // 关闭资源
        cursor.close();

        // 返回数据
        return  userInfo;
    }
}
