package com.atguigu.im.model.bean;

/**
 * Created by ZJNU-Hmz on 2017/10/30.
 */

public class UserDetail {

    private Integer id;

    private String account;

    private String name;

    private String password;

    private String picUrl;

    private String sixPasswrod;

    private String iphone;

    private String email;

    private String cardPic;

    public String getCardPic() {
        return cardPic;
    }

    public void setCardPic(String cardPic) {
        this.cardPic = cardPic;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public String getSixPasswrod() {
        return sixPasswrod;
    }

    public void setSixPasswrod(String sixPasswrod) {
        this.sixPasswrod = sixPasswrod;
    }

    public String getIphone() {
        return iphone;
    }

    public void setIphone(String iphone) {
        this.iphone = iphone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public UserDetail() {

    }

    public UserDetail(Integer id, String account, String name, String password, String picUrl, String sixPasswrod, String iphone, String email) {

        this.id = id;
        this.account = account;
        this.name = name;
        this.password = password;
        this.picUrl = picUrl;
        this.sixPasswrod = sixPasswrod;
        this.iphone = iphone;
        this.email = email;
    }
}
