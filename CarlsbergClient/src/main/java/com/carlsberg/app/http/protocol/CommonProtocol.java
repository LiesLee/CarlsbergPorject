package com.carlsberg.app.http.protocol;


import android.text.TextUtils;

import com.carlsberg.app.application.CarlsbergAppcation;
import com.carlsberg.app.bean.common.User;
import com.carlsberg.app.http.manager.RetrofitManager;
import com.common.http.HostType;
import com.common.http.HttpResult;
import com.common.utils.MD5Util;

import java.util.HashMap;
import java.util.Map;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by LiesLee on 16/8/23.
 */
public class CommonProtocol extends BaseProtocol{

    /**
     * login
     * @return
     */
    public static Observable<HttpResult<User>> login(String mobile, String password){
        Map<String, Object> params = new HashMap<>();
        params.put("login_mobile", mobile);
        params.put("login_password", MD5Util.MD5Encode(password, "utf-8"));
        return RetrofitManager.getInstance(HostType.USER_HOST).getCommonService()
                .login(createPatams(params, "login"))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()) //返回结果处理线程
                .unsubscribeOn(Schedulers.io());
    }
    /**
     * userInfo
     * @return
     */
    public static Observable<HttpResult<User>> userInfo(){
        Map<String, Object> params = new HashMap<>();
        params.put("user_id", CarlsbergAppcation.getInstance().getUser().getUser_info().getUser_id());
        return RetrofitManager.getInstance(HostType.USER_HOST).getCommonService()
                .login(createPatams(params, "userInfo"))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()) //返回结果处理线程
                .unsubscribeOn(Schedulers.io());
    }
    /**
     * userInfo
     * @return
     */
    public static Observable<HttpResult<String>> logout(){
        Map<String, Object> params = new HashMap<>();
        params.put("user_id", CarlsbergAppcation.getInstance().getUser().getUser_info().getUser_id());
        return RetrofitManager.getInstance(HostType.USER_HOST).getCommonService()
                .commonString(createPatams(params, "logout"))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()) //返回结果处理线程
                .unsubscribeOn(Schedulers.io());
    }
    /**
     * quickLogin
     * @return
     */
    public static Observable<HttpResult<User>> quickLogin(){
        Map<String, Object> params = new HashMap<>();
        params.put("login_encode", CarlsbergAppcation.getInstance().getUser().getLogin_encode().getLogin_encode());
        return RetrofitManager.getInstance(HostType.USER_HOST).getCommonService()
                .login(createPatams(params, "quickLogin"))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()) //返回结果处理线程
                .unsubscribeOn(Schedulers.io());
    }
    /**
     * changeUserInfo
     * @return
     */
    public static Observable<HttpResult<String>> changeUserInfo(String nick_name, String email, String address){
        Map<String, Object> params = new HashMap<>();
        params.put("user_id", CarlsbergAppcation.getInstance().getUser().getUser_info().getUser_id());
        params.put("nick_name", nick_name);
        params.put("email", email);
        if(!TextUtils.isEmpty(address)){
            params.put("address", address);
        }
        return RetrofitManager.getInstance(HostType.USER_HOST).getCommonService()
                .commonString(createPatams(params, "changeUserInfo"))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()) //返回结果处理线程
                .unsubscribeOn(Schedulers.io());
    }
    /**
     * changePassword
     * @return
     */
    public static Observable<HttpResult<String>> changePassword(String old_password, String new_password, String confirm_password){
        Map<String, Object> params = new HashMap<>();
        params.put("user_id", CarlsbergAppcation.getInstance().getUser().getUser_info().getUser_id());
        params.put("old_password", MD5Util.MD5Encode(old_password, "utf-8"));
        params.put("new_password", MD5Util.MD5Encode(new_password, "utf-8"));
        params.put("confirm_password", MD5Util.MD5Encode(confirm_password, "utf-8"));
        return RetrofitManager.getInstance(HostType.USER_HOST).getCommonService()
                .commonString(createPatams(params, "changePassword"))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()) //返回结果处理线程
                .unsubscribeOn(Schedulers.io());
    }


}
