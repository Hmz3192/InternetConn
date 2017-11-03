package com.atguigu.im.model.dao;

/**
 * Created by ZJNU-Hmz on 2017/10/30.
 */

public class KeyInfoTable {

    public static final String TAB_NAME = "tab_key";
    public static final String COL_Id = "id";
    public static final String COL_HXID = "hxid";
    public static final String COL_DoorName = "doorName";
    public static final String COL_DoorLocation = "doorLocation";
    public static final String COL_DoorId = "doorId";
    public static final String COL_DoorTime = "doorTime";
    public static final String COL_DoorState = "doorState";
    public static final String COL_Kind = "doorKind";



    public static final String CREATE_TAB = "create table "
            + TAB_NAME + " ("
            + COL_Id + " Integer primary key,"
            + COL_HXID + " Integer,"
            + COL_DoorName + " text,"
            + COL_DoorLocation + " text,"
            + COL_DoorId + " text,"
            + COL_DoorTime + " text,"
            + COL_DoorState + " text,"
            + COL_Kind + " text);";
}
