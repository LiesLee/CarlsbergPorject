package com.carlsberg.app.utils;


import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.telephony.TelephonyManager;

import com.carlsberg.app.application.CarlsbergAppcation;
import com.carlsberg.app.common.Constant;
import com.common.utils.StringUtils;
import com.common.utils.UUID;

/**
 * @project Util
 */
public class Util {

    public interface ShareImgCallback{
        void setImgDone(String file);
    }

    public static boolean checkSdCard() {
        if (Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED))
            return true;
        else
            return false;
    }

    /**
     * 获取设备号
     * 如过没有自动生成一个存到本地
     * @return
     */
    public static String getDeviceId(){
        String szImei = null;
        TelephonyManager telephonyMgr = (TelephonyManager) CarlsbergAppcation.getInstance().getSystemService(Context.TELEPHONY_SERVICE);
        if (ActivityCompat.checkSelfPermission(CarlsbergAppcation.getInstance(), Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
            szImei = SpUtil.readString(Constant.APP_DEVICEID_UUID);
            if (StringUtils.isEmpty(szImei)) {
                szImei = UUID.randomUUID().toString();
                SpUtil.writeString(Constant.APP_DEVICEID_UUID, szImei);
            }
        } else {
            szImei = telephonyMgr.getDeviceId();
        }

        if (StringUtils.isEmpty(szImei)) {
            szImei = SpUtil.readString(Constant.APP_DEVICEID_UUID);
            if (StringUtils.isEmpty(szImei)) {
                szImei = UUID.randomUUID().toString();
                SpUtil.writeString(Constant.APP_DEVICEID_UUID, szImei);
            }
        }
        return szImei;
    }

}
