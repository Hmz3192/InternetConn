package com.atguigu.im.model.bean;

import java.io.Serializable;

/**
 * Created by ZJNU-Hmz on 2017/10/23.
 */

public class KeyMes implements Serializable {


    String id;
    String name;
    String state;
    String user; //yonghu
    String location;
    String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public KeyMes(String id, String name, String state, String user, String location, String password) {
        this.id = id;
        this.name = name;
        this.state = state;
        this.user = user;
        this.location = location;
        this.password = password;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public KeyMes() {

    }

}
