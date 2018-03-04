package com.atguigu.im.utils;

/**
 * Created by Administrator on 2016/9/24.
 */
// 全局的常量类
public class Constant {

    public static final String CONTACT_CHANGED = "contact_changed";// 发送联系人变化的广播
    public static final String CONTACT_INVITE_CHANGED = "contact_invite_changed";// 联系人邀请信息变化的广播
    public static final String GROUP_INVITE_CHANGED = "group_invite_changed";// 群邀请信息变化的广播
    public static final String GROUP_ID = "group_id";// 群id
    public static final String EXIT_GROUP = "exit_group";// 退群广播


    public static final String PREURL = "http://192.168.1.105:8112";


    public static final String GETONEINFO = PREURL + "/getPic";

    public static final String GETKETUSER = PREURL + "/getKeyByUser";

    public static final String GETDOORRECORD = PREURL + "/getDoorRecord";

    public static final String ADDKEY = PREURL + "/addKey";

    public static final String GETRECOMMEND = PREURL + "/new/json/KTV.json";

    public static final String LOADURL = PREURL;


}
