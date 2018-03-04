package com.atguigu.im.model.bean;

/**
 * Created by ZJNU-Hmz on 2017/10/25.
 */

public class DoorRec {
    private Integer id;

    private Integer userId;

    private String doorId;

    private String openTime;

    private String openKind;

    private String openPassword;

    private String openReason;

    public String getTimeActive() {
        return timeActive;
    }

    public void setTimeActive(String timeActive) {
        this.timeActive = timeActive;
    }

    private String timeActive;


    public DoorRec() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getDoorId() {
        return doorId;
    }

    public void setDoorId(String doorId) {
        this.doorId = doorId == null ? null : doorId.trim();
    }

    public String getOpenTime() {
        return openTime;
    }

    public void setOpenTime(String openTime) {
        this.openTime = openTime == null ? null : openTime.trim();
    }

    public String getOpenKind() {
        return openKind;
    }

    public void setOpenKind(String openKind) {
        this.openKind = openKind == null ? null : openKind.trim();
    }

    public String getOpenPassword() {
        return openPassword;
    }

    public void setOpenPassword(String openPassword) {
        this.openPassword = openPassword == null ? null : openPassword.trim();
    }

    public String getOpenReason() {
        return openReason;
    }

    public void setOpenReason(String openReason) {
        this.openReason = openReason;
    }

    public DoorRec(Integer id, Integer userId, String doorId, String openTime, String openKind, String openPassword, String openReason, String timeActive) {

        this.id = id;
        this.userId = userId;
        this.doorId = doorId;
        this.openTime = openTime;
        this.openKind = openKind;
        this.openPassword = openPassword;
        this.openReason = openReason;
        this.timeActive = timeActive;
    }
}
