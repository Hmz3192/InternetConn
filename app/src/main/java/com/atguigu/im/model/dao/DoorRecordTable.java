package com.atguigu.im.model.dao;

/**
 * Created by Administrator on 2016/9/23.
 */
public class DoorRecordTable {
    public static final String TAB_NAME = "tab_doorrecord";
    public static final String COL_ID = "Id";
    public static final String COL_USERID = "userId";
    public static final String COL_DOORID = "doorId";
    public static final String COL_TIME = "time";
    public static final String COL_KIND = "kind";
    public static final String COL_PASSWORD = "password";
    public static final String COL_RESON = "reson";


    public static final String CREATE_TAB = "create table "
            + TAB_NAME + " ("
            + COL_ID + " text primary key,"
            + COL_USERID + " text,"
            + COL_DOORID + " text,"
            + COL_TIME + " text,"
            + COL_KIND + " text,"
            + COL_PASSWORD + " text,"
            + COL_RESON + " text);";


//    create table ( hxid text primary key, name text, nick text, photo text);
}
