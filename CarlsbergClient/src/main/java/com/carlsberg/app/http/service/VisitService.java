package com.carlsberg.app.http.service;

import com.carlsberg.app.bean.home.HomeDataResponse;
import com.carlsberg.app.bean.visit.CollectViewResponse;
import com.carlsberg.app.bean.visit.VisitRespone;
import com.carlsberg.app.bean.visit.VisitStoreResponse;
import com.common.http.HttpResult;

import java.util.SortedMap;

import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by LiesLee on 17/2/28.
 */

public interface VisitService {
    /**
     * 拜访的商店列表
     * @param storePlan
     * @return
     */
    @FormUrlEncoded
    @POST("api.php")
    Observable<HttpResult<VisitRespone>> storePlan(@FieldMap SortedMap<String, Object> storePlan);
    /**
     * 拜访的商店列表
     * @param storePlan
     * @return
     */
    @FormUrlEncoded
    @POST("api.php")
    Observable<HttpResult<VisitStoreResponse>> storeView(@FieldMap SortedMap<String, Object> storePlan);
    /**
     *
     * @param
     * @return
     */
    @FormUrlEncoded
    @POST("api.php")
    Observable<HttpResult<CollectViewResponse>> collectView(@FieldMap SortedMap<String, Object> storePlan);
    /**
     * 打卡
     * @param storePlan
     * @return
     */
    @FormUrlEncoded
    @POST("api.php")
    Observable<HttpResult<String>> taskSign(@FieldMap SortedMap<String, Object> storePlan);
}
