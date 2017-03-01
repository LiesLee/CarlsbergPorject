package com.carlsberg.app.http.protocol;

import com.carlsberg.app.application.CarlsbergAppcation;
import com.carlsberg.app.bean.common.User;
import com.carlsberg.app.bean.visit.VisitRespone;
import com.carlsberg.app.bean.visit.VisitStoreResponse;
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
 * Created by LiesLee on 17/2/28.
 */

public class VisitProtocol extends BaseProtocol {
    /**
     * 拜访的列表
     * @param search_text
     * @param status_type
     * @param page
     * @return
     */
    public static Observable<HttpResult<VisitRespone>> storePlan(int showType, String search_text, int status_type, int page){
        Map<String, Object> params = new HashMap<>();
        params.put("search_text", search_text);
        params.put("user_id", CarlsbergAppcation.getInstance().getUser().getUser_info().getUser_id());
        params.put("status_type", status_type);
        params.put("page", page);
        return RetrofitManager.getInstance(HostType.USER_HOST).getVisitService()
                .storePlan(createPatams(params, showType == 0 ? "recentPlan" : "storePlan"))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()) //返回结果处理线程
                .unsubscribeOn(Schedulers.io());
    }
    /**
     * 拜访门店
     * @param store_id
     * @return
     */
    public static Observable<HttpResult<VisitStoreResponse>> storeView(String store_id, String task_id){
        Map<String, Object> params = new HashMap<>();
        params.put("store_id", store_id);
        params.put("task_id", task_id);
        params.put("user_id", CarlsbergAppcation.getInstance().getUser().getUser_info().getUser_id());
        return RetrofitManager.getInstance(HostType.USER_HOST).getVisitService()
                .storeView(createPatams(params, "storeView"))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()) //返回结果处理线程
                .unsubscribeOn(Schedulers.io());
    }
    /**
     * 打卡
     * @param store_id
     * @param task_id
     * @param sigin_type checkin ：到店签到  goaway ：离店签到
     * @return
     */
    public static Observable<HttpResult<String>> taskSign(String store_id, String task_id, String sigin_type){
        Map<String, Object> params = new HashMap<>();
        params.put("store_id", store_id);
        params.put("task_id", task_id);
        params.put("sigin_type", sigin_type);
        params.put("user_id", CarlsbergAppcation.getInstance().getUser().getUser_info().getUser_id());
        return RetrofitManager.getInstance(HostType.USER_HOST).getVisitService()
                .taskSign(createPatams(params, "taskSign"))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()) //返回结果处理线程
                .unsubscribeOn(Schedulers.io());
    }
}
