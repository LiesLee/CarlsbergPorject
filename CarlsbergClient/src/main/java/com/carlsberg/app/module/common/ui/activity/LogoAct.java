package com.carlsberg.app.module.common.ui.activity;

import android.Manifest;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.view.WindowManager;

import com.carlsberg.app.R;
import com.carlsberg.app.application.CarlsbergAppcation;
import com.common.annotation.ActivityFragmentInject;
import com.common.base.presenter.BasePresenterImpl;
import com.common.base.ui.BaseActivity;
import com.common.base.ui.BaseView;
import java.util.Timer;
import java.util.TimerTask;


/**
 * Created by CQY on 2016/7/5.
 */
@ActivityFragmentInject(contentViewId = R.layout.act_logo)
public class LogoAct extends BaseActivity<BasePresenterImpl> implements BaseView{

    @Override
    public void onCreate(Bundle savedInstanceState) {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);  // 隐藏状态栏
        super.onCreate(savedInstanceState);
    }

    @Override
    public void initData() {

    }


    @Override
    protected void initView() {
        Timer timer = new Timer();// 实例化Timer类
        timer.schedule(new TimerTask() {
            public void run() {
                if(CarlsbergAppcation.getInstance().isLogin()){
                    Intent intent = new Intent(baseActivity, MainActivity.class);
                    startActivity(intent);
                }else{
                    Intent intent = new Intent(baseActivity, LoginActivity.class);
                    startActivity(intent);
                }

                this.cancel();
            }
        }, 2000);// 这里百毫秒

    }

    @Override
    public void onClick(View v) {

    }


    @Override
    protected void onClickBackFinishBefore() {

    }

}
