package com.carlsberg.app.utils;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.carlsberg.app.R;
import com.gun0912.tedpicker.Config;
import com.gun0912.tedpicker.ImagePickerActivity;
import com.views.util.ToastUtil;

import java.util.ArrayList;

/**
 * Created by LiesLee on 16/9/29.
 */
public class UIHelper {

    public static final int INTENT_REQUEST_GET_IMAGES = 13;

    public static void showShakeAnim(Context context, View view, String toast) {
        Animation shake = AnimationUtils.loadAnimation(context, R.anim.shake_x);
        view.startAnimation(shake);
        view.requestFocus();
        if(!TextUtils.isEmpty(toast)){
            ToastUtil.showShortToast(context, toast);
        }

    }

    public static boolean phoneNumberValid(String number) {
        // 手机号固定在5-20范围内
        if (number.length() < 5 || number.length() > 20) {
            return false;
        }

        String match = "";
        if (number.length() != 11) {
            return false;
        } else {
            // match = "^[1]{1}[0-9]{2}[0-9]{8}$";
            match = "^(1[3456789])\\d{9}$";
        }

        // 正则匹配
        if (!"".equals(match)) {
            return number.matches(match);
        }
        return true;
    }


    /**
     * 打开选择图片及拍照页面
     * @param activity
     * @param selectionLimit 选择图片的最大数 0开始
     * @param image_uris  已经选择的图片
     */
    public static void getPictures(Activity activity, int selectionLimit, ArrayList<Uri> image_uris){
//        Config config = new Config();
//        config.setSelectionMin(1);
//        config.setSelectionLimit(selectionLimit);
//        config.setToolbarTitleRes(R.string.choice_image);
//
//        ImagePickerActivity.setConfig(config);
//
//        Intent intent = new Intent(activity, ImagePickerActivity.class);
//
//        if (image_uris != null) {
//            intent.putParcelableArrayListExtra(ImagePickerActivity.EXTRA_IMAGE_URIS, image_uris);
//        }
//        activity.startActivityForResult(intent, INTENT_REQUEST_GET_IMAGES);
    }



}
