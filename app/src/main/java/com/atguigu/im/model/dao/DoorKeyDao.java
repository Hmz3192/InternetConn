package com.atguigu.im.model.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.atguigu.im.model.bean.KeyMes;
import com.atguigu.im.model.db.DoorKeyDB;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by ZJNU-Hmz on 2017/10/30.
 */

public class DoorKeyDao {

    private final DoorKeyDB mHelper;

    public DoorKeyDao(Context context) {
        mHelper = new DoorKeyDB(context);
    }



    // 添加用户到数据库
    public void addAccount(KeyMes user) {
        // 获取数据库对象
        SQLiteDatabase db = mHelper.getReadableDatabase();

        // 执行添加操作
        ContentValues values = new ContentValues();
        values.put(KeyInfoTable.COL_HXID, user.getUserId());
        values.put(KeyInfoTable.COL_DoorId, user.getDoorId());
        values.put(KeyInfoTable.COL_DoorLocation, user.getDoorLocation());
        values.put(KeyInfoTable.COL_DoorName, user.getDoorName());
        values.put(KeyInfoTable.COL_DoorState, user.getDoorState());
        values.put(KeyInfoTable.COL_DoorTime, String.valueOf(user.getAddTime()));
        values.put(KeyInfoTable.COL_Id, user.getId());
        values.put(KeyInfoTable.COL_Kind, user.getDoorKind());


        db.replace(KeyInfoTable.TAB_NAME, null, values);
    }



    // 根据环信id获取所有钥匙信息
    public List<KeyMes> getUserByHxId(String hxId) {
        // 获取数据库对象
        SQLiteDatabase db = mHelper.getReadableDatabase();

        // 执行查询语句
        String sql = "select * from " + KeyInfoTable.TAB_NAME + " where " + KeyInfoTable.COL_HXID + "=?";
        Cursor cursor = db.rawQuery(sql, new String[]{hxId});

        List<KeyMes> userInfos =    new ArrayList<>();
        while (cursor.moveToNext()) {
            KeyMes userInfo = new KeyMes();

            // 封装对象
            userInfo.setUserId(Integer.valueOf(cursor.getString(cursor.getColumnIndex(KeyInfoTable.COL_HXID))));
            userInfo.setAddTime(new Date(cursor.getString(cursor.getColumnIndex(KeyInfoTable.COL_DoorTime))));
            userInfo.setDoorId(cursor.getString(cursor.getColumnIndex(KeyInfoTable.COL_DoorId)));
            userInfo.setDoorKind(cursor.getString(cursor.getColumnIndex(KeyInfoTable.COL_Kind)));
            userInfo.setDoorLocation(cursor.getString(cursor.getColumnIndex(KeyInfoTable.COL_DoorLocation)));
            userInfo.setDoorName(cursor.getString(cursor.getColumnIndex(KeyInfoTable.COL_DoorName)));
            userInfo.setDoorState(cursor.getString(cursor.getColumnIndex(KeyInfoTable.COL_DoorState)));
            userInfo.setId(Integer.valueOf(cursor.getString(cursor.getColumnIndex(KeyInfoTable.COL_Id))));

            userInfos.add(userInfo);

        }

        // 关闭资源
        cursor.close();

        // 返回数据
        return  userInfos;
    }



}
