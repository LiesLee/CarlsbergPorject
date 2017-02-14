package com.carlsberg.app.module.home.ui.fragment;

import android.view.View;

import com.carlsberg.app.R;
import com.common.annotation.ActivityFragmentInject;
import com.common.base.presenter.BasePresenterImpl;
import com.common.base.ui.BaseFragment;
import com.common.base.ui.BaseView;

/**
 * Created by LiesLee on 17/2/14.
 */
@ActivityFragmentInject(contentViewId = R.layout.fra_main)
public class MainFragment extends BaseFragment<BasePresenterImpl> implements BaseView {
    @Override
    protected void initView(View fragmentRootView) {

    }

    @Override
    public void initData() {

    }
}
