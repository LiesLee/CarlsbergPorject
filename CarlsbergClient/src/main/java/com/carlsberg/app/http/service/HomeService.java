package com.carlsberg.app.http.service;

import com.carlsberg.app.bean.common.User;
import com.carlsberg.app.bean.home.HomeDataResponse;
import com.common.http.HttpResult;

import java.util.Map;

import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by LiesLee on 16/8/23.
 */
public interface HomeService {
    /**
     * home
     * @param params
     * @return
     */
    @FormUrlEncoded
    @POST("api.php")
    Observable<HttpResult<HomeDataResponse>> index(@FieldMap Map<String, Object> params);


}
