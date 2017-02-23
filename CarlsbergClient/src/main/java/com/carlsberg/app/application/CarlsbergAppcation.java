package com.carlsberg.app.application;

import android.app.ActivityManager;
import android.content.Context;
import android.os.Handler;
import android.os.Process;
import android.support.multidex.MultiDexApplication;
import android.text.TextUtils;

import com.alibaba.fastjson.JSONObject;
import com.carlsberg.app.BuildConfig;
import com.carlsberg.app.bean.common.User;
import com.carlsberg.app.common.Constant;
import com.carlsberg.app.utils.SpUtil;
import com.common.utils.FastJsonUtil;
import com.karumi.dexter.Dexter;
import com.socks.library.KLog;

import java.util.List;

/**
 * Created by LiesLee on 16/8/22.
 */
public class CarlsbergAppcation extends MultiDexApplication {
    public static volatile CarlsbergAppcation instance;
    private Handler handler;
    private User user;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        handler = new Handler();
        KLog.init(BuildConfig.DEBUG);
        Dexter.initialize(this); //权限封装类
    }

    public static CarlsbergAppcation getInstance() {
        return instance;
    }


    public Handler getHandler() {
        return handler;
    }

    public void setHandler(Handler handler) {
        this.handler = handler;
    }



    /**
     * 是否登录过
     * @return
     */
    public boolean isLogin(){

        return (getUser() != null && getUser().getUser_info() != null);
    }

    public void setUser(User user) {
        if(user == null){
            this.user = null;
            SpUtil.remove(Constant.USER);
        }else{
            this.user = user;
            SpUtil.writeString(Constant.USER, FastJsonUtil.t2Json2(user));
        }

    }



    public User getUser(){
        if(user == null || user.getUser_info() ==null){
            String js = SpUtil.readString(Constant.USER);
            if(TextUtils.isEmpty(js)){
                return null;
            }else{
                user = JSONObject.parseObject(js, User.class);
                if(user!=null && user.getUser_info() != null){
                    return user;
                }else{
                    return null;
                }

            }
        }else{
            return user;
        }
    }

    private boolean shouldInit() {
        ActivityManager am = ((ActivityManager) getSystemService(Context.ACTIVITY_SERVICE));
        List<ActivityManager.RunningAppProcessInfo> processInfos = am.getRunningAppProcesses();
        String mainProcessName = getPackageName();
        int myPid = Process.myPid();
        for (ActivityManager.RunningAppProcessInfo info : processInfos) {
            if (info.pid == myPid && mainProcessName.equals(info.processName)) {
                return true;
            }
        }
        return false;
    }
}
