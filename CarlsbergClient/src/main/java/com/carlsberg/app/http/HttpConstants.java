package com.carlsberg.app.http;

import android.text.TextUtils;

import com.carlsberg.app.BuildConfig;
import com.carlsberg.app.R;
import com.carlsberg.app.application.CarlsbergAppcation;
import com.carlsberg.app.utils.SpUtil;
import com.common.http.HostType;

public class HttpConstants {
    //http://static.test.vip-time.cn:83/app/test/a.xlsx

    /**
     * 返回成功
     */
    public static final int REQUEST_SUCESS = 200;
    /**
     * 登录状态失效,需要重新登录
     */
    public static final int LOGIN_STATUS_DISABLED = 202;
    /**
     * TOKEN状态失效,需要重新登录
     */
    public static final int TOKEN_STATUS_DISABLED = 201;
    /**
     * 签名认证错误
     */
    public static final int SIGNKEY_ERROR = 203;
    /**
     * 接口不存在
     */
    public static final int URL_NOT_FOUND = 204;
    public static final int LOGIN_PASSWORD_ERROR = 205;
    public static final int PAY_PASSWORD_ERROR = 206;

    public static String getHost(int hostType) {
        if (HostType.USER_HOST == hostType) {
            return getCustomerBaseUrl();
        } else {
            return "";
        }
    }


    /**
     * 用户端域名
     *
     * @return
     */
    public static String getCustomerBaseUrl() {
        return BuildConfig.DEBUG ?
                "http://test.jsb-app.com/" :
                "https://www.jsb-app.com/";
    }

    public static String getSignKey() {
        return BuildConfig.DEBUG
                ? CarlsbergAppcation.getInstance().getResources().getString(R.string.sign_key)
                : CarlsbergAppcation.getInstance().getResources().getString(R.string.sign_key_release);
    }

   }

