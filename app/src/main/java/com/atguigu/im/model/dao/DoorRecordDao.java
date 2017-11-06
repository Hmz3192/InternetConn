package com.atguigu.im.model.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.atguigu.im.model.bean.DoorRec;
import com.atguigu.im.model.db.DoorRecordDB;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/9/23.
 */
// 用户账号数据库的操作类
public class DoorRecordDao {

    private final DoorRecordDB mHelper;

    public DoorRecordDao(Context context) {
        mHelper = new DoorRecordDB(context);
    }

    // 添加用户到数据库
    public void addRecord(DoorRec user) {
        // 获取数据库对象
        SQLiteDatabase db = mHelper.getReadableDatabase();


        // 执行添加操作
        ContentValues values = new ContentValues();
        values.put(DoorRecordTable.COL_ID, user.getId());
        values.put(DoorRecordTable.COL_DOORID, user.getDoorId());
        values.put(DoorRecordTable.COL_KIND, user.getOpenKind());
        values.put(DoorRecordTable.COL_PASSWORD, user.getOpenPassword());
        values.put(DoorRecordTable.COL_RESON, user.getOpenReson());
        values.put(DoorRecordTable.COL_TIME, user.getOpenTime());
        values.put(DoorRecordTable.COL_USERID, user.getUserId());

        db.replace(DoorRecordTable.TAB_NAME, null, values);
    }

    // 根据环信id获取所有用户信息
    public List<DoorRec> getDoorRecById(String id) {
        // 获取数据库对象
        SQLiteDatabase db = mHelper.getReadableDatabase();

        // 执行查询语句
        String sql = "select * from " + DoorRecordTable.TAB_NAME + " where " + DoorRecordTable.COL_ID + "=?" ;
        Cursor cursor = db.rawQuery(sql, new String[]{id});
        List<DoorRec> userInfos =    new ArrayList<>();
        while (cursor.moveToNext()) {
            DoorRec userInfo = new DoorRec();

            // 封装对象
            userInfo.setId(Integer.valueOf(cursor.getString(cursor.getColumnIndex(DoorRecordTable.COL_ID))));
            userInfo.setDoorId(cursor.getString(cursor.getColumnIndex(DoorRecordTable.COL_DOORID)));
            userInfo.setOpenKind(cursor.getString(cursor.getColumnIndex(DoorRecordTable.COL_KIND)));
            userInfo.setOpenPassword(cursor.getString(cursor.getColumnIndex(DoorRecordTable.COL_PASSWORD)));
            userInfo.setOpenReson(cursor.getString(cursor.getColumnIndex(DoorRecordTable.COL_RESON)));
            userInfo.setOpenTime(cursor.getString(cursor.getColumnIndex(DoorRecordTable.COL_TIME)));
            userInfo.setUserId(Integer.valueOf(cursor.getString(cursor.getColumnIndex(DoorRecordTable.COL_USERID))));

            userInfos.add(userInfo);

        }

        // 关闭资源
        cursor.close();

        // 返回数据
        return  userInfos;
    }
}
