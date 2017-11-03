package com.atguigu.im.model.bean;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by ZJNU-Hmz on 2017/10/23.
 */

public class KeyMes implements Serializable {


    private Integer id;

    private Integer userId;

    private String doorName;

    private String doorLocation;

    private String doorId;

    private Date addTime;

    private String doorState;

    private String doorKind;

    public KeyMes(Integer id, Integer userId, String doorName, String doorLocation, String doorId, Date addTime, String doorState, String doorKind) {
        this.id = id;
        this.userId = userId;
        this.doorName = doorName;
        this.doorLocation = doorLocation;
        this.doorId = doorId;
        this.addTime = addTime;
        this.doorState = doorState;
        this.doorKind = doorKind;
    }

    public KeyMes() {
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

    public String getDoorName() {
        return doorName;
    }

    public void setDoorName(String doorName) {
        this.doorName = doorName == null ? null : doorName.trim();
    }

    public String getDoorLocation() {
        return doorLocation;
    }

    public void setDoorLocation(String doorLocation) {
        this.doorLocation = doorLocation == null ? null : doorLocation.trim();
    }

    public String getDoorId() {
        return doorId;
    }

    public void setDoorId(String doorId) {
        this.doorId = doorId == null ? null : doorId.trim();
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    public String getDoorState() {
        return doorState;
    }

    public void setDoorState(String doorState) {
        this.doorState = doorState == null ? null : doorState.trim();
    }

    public String getDoorKind() {
        return doorKind;
    }

    public void setDoorKind(String doorKind) {
        this.doorKind = doorKind == null ? null : doorKind.trim();
    }
}
