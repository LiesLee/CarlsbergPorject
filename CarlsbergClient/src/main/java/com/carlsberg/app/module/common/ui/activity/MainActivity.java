package com.carlsberg.app.module.common.ui.activity;


import android.app.Dialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.KeyEvent;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.carlsberg.app.R;
import com.carlsberg.app.application.CarlsbergAppcation;
import com.carlsberg.app.module.common.persenter.MainActivityPresenter;
import com.carlsberg.app.module.common.ui.adapter.MainFragmentAdapter;
import com.carlsberg.app.module.common.view.MainActivityView;
import com.carlsberg.app.module.home.ui.fragment.MainFragment;
import com.carlsberg.app.module.my.ui.fragment.MyFragment;
import com.carlsberg.app.module.visit.ui.fragment.VisitFragment;
import com.carlsberg.app.utils.DialogHelper;
import com.common.ShiHuiActivityManager;
import com.common.annotation.ActivityFragmentInject;
import com.common.base.presenter.BasePresenterImpl;
import com.common.base.ui.BaseActivity;
import com.common.base.ui.BaseFragment;
import com.common.base.ui.BaseView;
import com.views.NonSwipeableViewPager;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

@ActivityFragmentInject(contentViewId = R.layout.act_main, toolbarTitle = R.string.app_name)
public class MainActivity extends BaseActivity<MainActivityPresenter> implements MainActivityView {
    /**
     * 主页存放3个模块的ViewPager
     */
    @Bind(R.id.vp_main)
    NonSwipeableViewPager vp_main;

    @Bind(R.id.rg_main)
    RadioGroup rg_main;
    @Bind(R.id.rb_home)
    RadioButton rb_home;
    @Bind(R.id.rb_find)
    RadioButton rb_find;
    @Bind(R.id.rb_person)
    RadioButton rb_person;

    /**
     * 点击返回键退出时间记录
     */
    private long exitTime = 0;

    List<BaseFragment> fragments;
    private MainFragment mainFragment;
    private VisitFragment visitFragment;
    private MyFragment myFragment;
    private MainFragmentAdapter fragmentAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initView() {
        mPresenter = new MainActivityPresenter(this);

    }

    @Override
    public void initData() {
        mPresenter.quickLogin();
    }

    public void initHome(){
        fragments = new ArrayList<>();
        mainFragment = new MainFragment();
        visitFragment = new VisitFragment();
        myFragment = new MyFragment();
        fragments.add(mainFragment);
        fragments.add(visitFragment);
        fragments.add(myFragment);
        fragmentAdapter = new MainFragmentAdapter(getSupportFragmentManager(), fragments);
        vp_main.setAdapter(fragmentAdapter);
        vp_main.setOffscreenPageLimit(fragmentAdapter.getCount());

        //vp_main.setPageTransformer(true, new FadePageTransformer());

        rg_main.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i) {
                    case R.id.rb_home:
                        vp_main.setCurrentItem(0);
                        break;
                    case R.id.rb_find:
                        vp_main.setCurrentItem(1);
                        break;
                    case R.id.rb_person:
                        vp_main.setCurrentItem(2);
                        break;
                }
            }
        });

    }



    @Override
    protected void onClickBackFinishBefore() {

    }

    @Override
    public void onClick(View v) {

    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK
                && event.getAction() == KeyEvent.ACTION_DOWN) {
            if ((System.currentTimeMillis() - exitTime) > 2000) {
                showShortToast("再按一次退出应用");
                exitTime = System.currentTimeMillis();
            } else {
                ShiHuiActivityManager.getInstance().cleanActivity();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    public void showMore(){

        if(vp_main != null){
            if(rg_main != null){
                rg_main.check(R.id.rb_find);
            }

            if(visitFragment != null){
                visitFragment.currentPage(1);
            }
        }


    }

    @Override
    public void quickLoginSuccess() {
        initHome();
    }

    @Override
    public void quickLoginError() {
        DialogHelper.show2btnDialog(baseActivity, "登录状态刷新失败", "退出", "重新登录", true,
                new DialogHelper.DialogOnclickCallback() {
                    @Override
                    public void onButtonClick(Dialog dialog) {
                        ShiHuiActivityManager.getInstance().cleanActivity();
                    }
                }, new DialogHelper.DialogOnclickCallback() {
                    @Override
                    public void onButtonClick(Dialog dialog) {
                        CarlsbergAppcation.getInstance().setUser(null);
                        Intent intent = new Intent(baseActivity, LoginActivity.class);
                        intent.putExtra("isChangePass", true);
                        startActivity(intent);
                        finish();
                    }
                }).setCancelable(false);
    }


}
