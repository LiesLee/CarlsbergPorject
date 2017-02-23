package com.carlsberg.app.http.protocol;


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

}
