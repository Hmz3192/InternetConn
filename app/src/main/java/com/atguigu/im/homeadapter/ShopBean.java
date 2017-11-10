package com.atguigu.im.homeadapter;

/**
 * Created by ZJNU-Hmz on 2017/11/9.
 */

public class ShopBean {

    private String name;
    private String kind;
    private String location;
    private String length;
    private String number;
    private String url;

    public ShopBean(String name, String kind, String location, String length, String number, String url) {
        this.name = name;
        this.kind = kind;
        this.location = location;
        this.length = length;
        this.number = number;
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getLength() {
        return length;
    }

    public void setLength(String length) {
        this.length = length;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public ShopBean() {

    }

}
