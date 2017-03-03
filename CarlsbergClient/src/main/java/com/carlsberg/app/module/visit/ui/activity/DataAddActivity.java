package com.carlsberg.app.module.visit.ui.activity;

import android.app.Dialog;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.carlsberg.app.R;
import com.carlsberg.app.bean.visit.CollectViewResponse;
import com.carlsberg.app.module.visit.persenter.DataAddPresenter;
import com.carlsberg.app.module.visit.persenter.StoreVisitPresenter;
import com.carlsberg.app.module.visit.ui.adapter.DataAddPagerAdapter;
import com.carlsberg.app.module.visit.ui.fragment.DataAddShowFragment;
import com.carlsberg.app.module.visit.view.DataAddView;
import com.carlsberg.app.utils.DialogHelper;
import com.common.annotation.ActivityFragmentInject;
import com.common.base.presenter.BasePresenterImpl;
import com.common.base.ui.BaseActivity;
import com.common.base.ui.BaseView;
import com.views.util.ToastUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.Bind;

/**
 * Created by LiesLee on 17/2/21.
 */
@ActivityFragmentInject(contentViewId = R.layout.act_add_data, toolbarTitle = R.string.app_name)
public class DataAddActivity extends BaseActivity<DataAddPresenter> implements DataAddView {
    @Bind(R.id.tv_save)
    TextView tv_save;
    @Bind(R.id.tabs)
    TabLayout tabs;
    @Bind(R.id.view_pager)
    ViewPager view_pager;
    private String store_id;
    private String task_id;
    private String store_name;
    private DataAddPagerAdapter addPagerAdapter;

    @Override
    protected void initView() {
        mPresenter = new DataAddPresenter(this);
        store_id = getIntent().getStringExtra("store_id");
        task_id = getIntent().getStringExtra("task_id");
        store_name = getIntent().getStringExtra("store_name");
        if(TextUtils.isEmpty(store_id) || TextUtils.isEmpty(task_id)){
            finish();
            ToastUtil.showShortToast(baseActivity, "ID数据为空");
            return;
        }

        tv_title.setText(store_name);


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
        addPagerAdapter = new DataAddPagerAdapter(getSupportFragmentManager(), list);
        view_pager.setAdapter(addPagerAdapter);
        view_pager.setOffscreenPageLimit(addPagerAdapter.getCount());
        tabs.setupWithViewPager(view_pager);

        mPresenter.collectView(store_id, task_id);
    }

    @Override
    protected void onClickBackFinishBefore() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_save :

                DialogHelper.show2btnDialog(baseActivity, "你确定要保存吗？", "取消", "确定", false, null, new DialogHelper.DialogOnclickCallback() {
                    @Override
                    public void onButtonClick(Dialog dialog) {
                        if(addPagerAdapter!=null){
                            DataAddShowFragment f1 = addPagerAdapter.getmFragments().get(0);
                            DataAddShowFragment f2 = addPagerAdapter.getmFragments().get(1);

                            if(f1!=null && f2!=null){
                                mPresenter.collectSave(store_id, task_id, f1.getmAdapter().getData(), f2.getmAdapter().getData());
                            }
                        }
                    }
                });


                break;

            default:
                break;
        }
    }

    @Override
    public void collectView(CollectViewResponse data) {
        if(data != null && addPagerAdapter!=null){
            addPagerAdapter.getmFragments().get(0).refreshData(data.getTask_collect());
            addPagerAdapter.getmFragments().get(1).refreshData(data.getTask_score());
        }
    }

    @Override
    public void saveSuccessed() {
        showShortToast("保存成功");
        finish();
    }
}
