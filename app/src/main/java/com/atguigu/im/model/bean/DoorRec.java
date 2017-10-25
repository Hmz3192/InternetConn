package com.atguigu.im.model.bean;

/**
 * Created by ZJNU-Hmz on 2017/10/25.
 */

public class DoorRec {
//    单个门的记录
    String id;
    String openDate;
    String openKind;//开门类型，freekey 开门， 密码输入开门
    String openReason; // 外来人员需要开门理由


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOpenDate() {
        return openDate;
    }

    public void setOpenDate(String openDate) {
        this.openDate = openDate;
    }

    public String getOpenKind() {
        return openKind;
    }

    public void setOpenKind(String openKind) {
        this.openKind = openKind;
    }

    public String getOpenReason() {
        return openReason;
    }

    public void setOpenReason(String openReason) {
        this.openReason = openReason;
    }

    public DoorRec() {

    }

    public DoorRec(String id, String openDate, String openKind, String openReason) {

        this.id = id;
        this.openDate = openDate;
        this.openKind = openKind;
        this.openReason = openReason;
    }
}
