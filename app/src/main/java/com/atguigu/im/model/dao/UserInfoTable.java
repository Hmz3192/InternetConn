package com.atguigu.im.model.dao;

/**
 * Created by ZJNU-Hmz on 2017/10/30.
 */

public class UserInfoTable {

    public static final String TAB_NAME = "tab_my";
    public static final String COL_NAME = "name";
    public static final String COL_HXID = "hxid";
    public static final String COL_PHOTO = "photo";
    public static final String COL_PASS = "sixpassword";
    public static final String COL_EMAIL = "email";
    public static final String COL_PHONE = "phone";


    public static final String CREATE_TAB = "create table "
            + TAB_NAME + " ("
            + COL_HXID + " text primary key,"
            + COL_NAME + " text,"
            + COL_PASS + " text,"
            + COL_EMAIL + " text,"
            + COL_PHONE + " text,"
            + COL_PHOTO + " text);";
}
