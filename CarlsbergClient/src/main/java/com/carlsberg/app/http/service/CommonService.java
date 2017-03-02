package com.carlsberg.app.http.service;

import com.carlsberg.app.bean.common.User;
import com.common.http.HttpResult;

import java.util.Map;

import okhttp3.RequestBody;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PartMap;
import rx.Observable;

/**
 * Created by LiesLee on 16/8/23.
 */
public interface CommonService {
    /**
     * 刷新登录状态
     * @param params
     * @return
     */
    @FormUrlEncoded
    @POST("api.php")
    Observable<HttpResult<User>> login(@FieldMap Map<String, Object> params);
    /**
     * 刷新登录状态
     * @param params
     * @return
     */
    @FormUrlEncoded
    @POST("api.php")
    Observable<HttpResult<String>> commonString(@FieldMap Map<String, Object> params);


}
