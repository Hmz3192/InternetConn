package com.atguigu.im.model.bean;

import java.io.Serializable;

/**
 * Created by ZJNU-Hmz on 2017/10/23.
 */

public class KeyMes implements Serializable {


    String id;
    String name;
    String state;

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

    public KeyMes(String id, String name, String state) {

        this.id = id;
        this.name = name;
        this.state = state;
    }
}
