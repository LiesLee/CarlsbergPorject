package com.carlsberg.app.module.visit.ui.activity;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.TextView;

import com.carlsberg.app.R;
import com.carlsberg.app.module.visit.ui.adapter.DataAddPagerAdapter;
import com.carlsberg.app.module.visit.ui.fragment.DataAddShowFragment;
import com.common.annotation.ActivityFragmentInject;
import com.common.base.presenter.BasePresenterImpl;
import com.common.base.ui.BaseActivity;
import com.common.base.ui.BaseView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.Bind;

/**
 * Created by LiesLee on 17/2/21.
 */
@ActivityFragmentInject(contentViewId = R.layout.act_add_data, toolbarTitle = R.string.app_name)
public class DataAddActivity extends BaseActivity<BasePresenterImpl> implements BaseView {
    @Bind(R.id.tv_save)
    TextView tv_save;
    @Bind(R.id.tabs)
    TabLayout tabs;
    @Bind(R.id.view_pager)
    ViewPager view_pager;

    @Override
    protected void initView() {
        tv_save.setOnClickListener(this);
    }

    @Override
    public void initData() {
        DataAddShowFragment f1 = new DataAddShowFragment();
        f1.setTitle("户口上座率情况");
        DataAddShowFragment f2 = new DataAddShowFragment();
        f2.setTitle("促销员表现评分");
        List<DataAddShowFragment> list = new ArrayList<>();
        Collections.addAll(list, f1,f2);
        DataAddPagerAdapter addPagerAdapter = new DataAddPagerAdapter(getSupportFragmentManager(), list);
        view_pager.setAdapter(addPagerAdapter);
        tabs.setupWithViewPager(view_pager);
    }

    @Override
    protected void onClickBackFinishBefore() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_save :

                break;

            default:
                break;
        }
    }
}
