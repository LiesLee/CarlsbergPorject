package com.carlsberg.app.http.async;

import android.accounts.NetworkErrorException;
import android.content.Context;
import android.text.TextUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.common.utils.FastJsonUtil;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.socks.library.KLog;
import com.views.util.ToastUtil;

import org.apache.http.conn.ConnectTimeoutException;

import java.net.SocketTimeoutException;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.client.HttpResponseException;

/**
 */
public abstract class ManagerResponseHandler<T> extends AsyncHttpResponseHandler {

    protected JsonResult<T>  jsonResult;
    protected Context context;
    private boolean isRequestSuc;

    public ManagerResponseHandler(Context context) {
        this.context = context;
    }

    @Override
    public void onSuccess(int i, Header[] headers, byte[] responseBody) {
        final String content = new String(responseBody);
        KLog.json(content);
        KLog.d("===", "onSuccess content:" + content);

        if (!TextUtils.isEmpty(content)) {
            //处理json解析
            jsonResult = JSON.parseObject(content, new TypeReference<JsonResult<T>>(){});
            if (jsonResult != null) {
                if(jsonResult.status == 201){
                    onFailure(new Throwable());
                }else{
                    onSuccess(jsonResult.status, jsonResult.msg, jsonResult.data);
                }
            }
        } else {
            onFailure(new Throwable());
        }

    }

    public abstract void onSuccess(int code, String msg, T data);

    public abstract void onFailure(Throwable throwable);

    @Override
    public void onFailure(int i, Header[] headers, byte[] bytes, Throwable throwable) {
        KLog.e("onFailure", "onfault code：" + i + "， content:" + throwable.getCause());
        onFailure(throwable);
        if (throwable instanceof NetworkErrorException) {
            ToastUtil.showShortToast(context, "网络错误");//是否提示toast
            networkError();
        } else if (throwable instanceof ConnectTimeoutException ||
                throwable instanceof SocketTimeoutException) {//连接/请求超时提示错误
            networkError();
        } else if ( throwable instanceof cz.msebera.android.httpclient.client.HttpResponseException) {//响应失败
            HttpResponseException exception = (HttpResponseException) throwable;
            KLog.e("error:" + exception.getMessage());
            ToastUtil.showShortToast(context, "网络异常");//是否提示toast
            networkError();
        } else {
            KLog.e("context=" + context);
            KLog.e("exception : " + throwable.getMessage());
            ToastUtil.showShortToast(context, "网络异常");//是否提示toast
            networkError();
        }


    }

    protected void networkError() {
    }
}