package com.carlsberg.app.http.async;

import android.accounts.NetworkErrorException;
import android.content.Context;
import android.text.TextUtils;

import com.common.utils.FastJsonUtil;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.socks.library.KLog;
import com.views.util.ToastUtil;

import org.apache.http.conn.ConnectTimeoutException;

import java.net.SocketTimeoutException;

import cz.msebera.android.httpclient.Header;

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
            jsonResult = FastJsonUtil.json2T(content, JsonResult.class);
            if (jsonResult != null) {
                if(jsonResult.status == 201){
                    onFailure(new Throwable());
//                    CustomerAppcation.getInstance().getHandler().post(new Runnable() {
//                        @Override
//                        public void run() {
//                            ToastUtil.showLongToast(context,"登录状态失效，请重新登录！");
//                            ShiHuiActivityManager.getInstance().cleanActivity();
//                            CustomerAppcation.getInstance().setUserInfo(null);
//                            SpUtil.remove(Constant.USER_JSON);
//                            //context.startActivity(new Intent(context, LoginActivity.class));
//                        }
//                    });

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
        } else {
            KLog.e("context=" + context);
            ToastUtil.showShortToast(context, "网络异常");//是否提示toast
            networkError();
        }
    }

    protected void networkError() {
    }
}