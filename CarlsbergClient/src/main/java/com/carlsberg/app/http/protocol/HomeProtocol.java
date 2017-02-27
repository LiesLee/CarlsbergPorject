package com.carlsberg.app.http.protocol;

import com.carlsberg.app.application.CarlsbergAppcation;
import com.carlsberg.app.bean.common.User;
import com.carlsberg.app.bean.home.HomeDataResponse;
import com.carlsberg.app.http.manager.RetrofitManager;
import com.common.http.HostType;
import com.common.http.HttpResult;

import java.util.HashMap;
import java.util.Map;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by LiesLee on 17/2/27.
 */

public class HomeProtocol extends BaseProtocol {
    /**
     * quickLogin
     * @return
     */
    public static Observable<HttpResult<HomeDataResponse>> index(){
        Map<String, Object> params = new HashMap<>();
        params.put("user_id", CarlsbergAppcation.getInstance().getUser().getUser_info().getUser_id());
        return RetrofitManager.getInstance(HostType.USER_HOST).getHomeService()
                .index(createPatams(params, "appIndex"))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()) //返回结果处理线程
                .unsubscribeOn(Schedulers.io());
    }

}
