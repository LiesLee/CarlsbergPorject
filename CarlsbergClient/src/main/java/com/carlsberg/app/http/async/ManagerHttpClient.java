package com.carlsberg.app.http.async;

import android.accounts.NetworkErrorException;
import android.content.Context;

import com.carlsberg.app.http.HttpConstants;
import com.common.utils.NetUtil;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.RequestParams;
import com.socks.library.KLog;

/**
 */
public class ManagerHttpClient {

    private static final int DEFAULT_SOCKET_TIMEOUT = 15 * 1000;// 30s请求超时
    private static Context sContext;
    private volatile static ManagerHttpClient sInstance;
    private AsyncHttpClient httpClient;

    private ManagerHttpClient() {
        httpClient = new AsyncHttpClient();
        httpClient.setTimeout(DEFAULT_SOCKET_TIMEOUT);
    }

    public static ManagerHttpClient getsInstance(Context context) {
        if (sInstance== null) {
            synchronized (ManagerHttpClient.class) {
                if (sInstance == null) {
                    sContext = context;
                    sInstance = new ManagerHttpClient();
                }
            }
        }
        return sInstance;
    }

    public void post(String url, RequestParams requestParams, ManagerResponseHandler responseHandler) {

        if (!NetUtil.isConnected(sContext)) {
            httpClient.cancelRequests(sContext, true);
            responseHandler.onFailure(0, null, null, new NetworkErrorException());
        } else {
            if (null == requestParams) {
                requestParams = new RequestParams();
            }
//            if (CustomerAppcation.getInstance().getUserInfo() != null) {
//                if(!requestParams.has("uid")){
//                    requestParams.add("uid", CustomerAppcation.getInstance().getUserInfo().getId());
//                }
//                if(!requestParams.has("userId")){
//                    requestParams.add("userId", CustomerAppcation.getInstance().getUserInfo().getId());
//                }
//                if(!requestParams.has("token")){
//                    requestParams.add("token", CustomerAppcation.getInstance().getUserInfo().getToken());
//                }
//
//            }
//            if(!requestParams.has("lng")){
//                requestParams.put("lng", SpUtil.readString(Constant.LNG));
//            }
//            if(!requestParams.has("lat")){
//                requestParams.put("lat", SpUtil.readString(Constant.LAT));
//            }
//            if(!requestParams.has("spNo")){
//                String szImei = Util.getDeviceId();
//                requestParams.add("spNo", szImei + "");  //设备唯一码
//            }

            String httpUrl = HttpConstants.getCustomerBaseUrl() + url;
            KLog.e("---", httpUrl + "?" + requestParams);
            httpClient.post(sContext, httpUrl, requestParams, responseHandler);
        }
    }

    public AsyncHttpClient getHttpClient() {
        return httpClient;
    }
}
