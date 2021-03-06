package com.atguigu.im.model;

import android.content.Context;

import com.atguigu.im.model.bean.UserInfo;
import com.atguigu.im.model.dao.DoorKeyDao;
import com.atguigu.im.model.dao.DoorRecordDao;
import com.atguigu.im.model.dao.UserAccountDao;
import com.atguigu.im.model.dao.UserInfoDao;
import com.atguigu.im.model.db.DBManager;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Administrator on 2016/9/23.
 */
// 数据模型层全局类
public class Model {
    private Context mContext;
    private ExecutorService executors = Executors.newCachedThreadPool();

    // 创建对象
    private static Model model = new Model();
    private UserAccountDao userAccountDao;
    private UserInfoDao userInfoDao;
    private DoorKeyDao doorKeyDao;
    private DoorRecordDao doorRecordDao;
    private DBManager dbManager;
    private String GlobalUser;
    // 私有化构造
    private Model() {

    }

    // 获取单例对象
    public static Model getInstance(){

        return model;
    }

    // 初始化的方法
    public void init(Context context){
        mContext = context;

        // 创建用户账号数据库的操作类对象
        userAccountDao = new UserAccountDao(mContext);

        userInfoDao = new UserInfoDao(mContext);

        doorKeyDao = new DoorKeyDao(mContext);

        doorRecordDao = new DoorRecordDao(mContext);
        // 开启全局监听
        EventListener eventListener = new EventListener(mContext);
    }

    // 获取全局线程池对象
    public ExecutorService getGlobalThreadPool(){
        return executors;
    }

    // 用户登录成功后的处理方法
    public void loginSuccess(UserInfo account) {

        // 校验
        if(account == null) {
            return;
        }

        if(dbManager != null) {
            dbManager.close();
        }

        dbManager = new DBManager(mContext, account.getName());
        GlobalUser = account.getHxid();

    }


    public String getGlobalUser() {
        return GlobalUser;
    }
    public DBManager getDbManager(){
        return dbManager;
    }

    // 获取用户账号数据库的操作类对象
    public UserAccountDao getUserAccountDao(){
        return userAccountDao;
    }

    public UserInfoDao getUserInfoDao() {

        return userInfoDao;
    }

    public DoorKeyDao getDoorKeyDao() {

        return doorKeyDao;
    }

    public DoorRecordDao getDoorRecordDao() {

        return doorRecordDao;
    }
}
