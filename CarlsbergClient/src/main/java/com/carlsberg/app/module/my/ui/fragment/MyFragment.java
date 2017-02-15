package com.carlsberg.app.module.my.ui.fragment;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.carlsberg.app.R;
import com.common.annotation.ActivityFragmentInject;
import com.common.base.presenter.BasePresenterImpl;
import com.common.base.ui.BaseFragment;
import com.common.base.ui.BaseView;

import butterknife.Bind;

/**
 * Created by LiesLee on 17/2/14.
 */
@ActivityFragmentInject(contentViewId = R.layout.fra_my)
public class MyFragment extends BaseFragment<BasePresenterImpl> implements BaseView {
    @Bind(R.id.tv_name)
    TextView tv_name;
    @Bind(R.id.tv_type)
    TextView tv_type;

    @Bind(R.id.ll_change_password)
    LinearLayout ll_change_password;

    @Bind(R.id.ll_about_us)
    LinearLayout ll_about_us;

    @Bind(R.id.ll_modify_info)
    LinearLayout ll_modify_info;

    @Override
    protected void initView(View fragmentRootView) {
        ll_change_password.setOnClickListener(this);
        ll_about_us.setOnClickListener(this);
    }

    @Override
    public void initData() {

    }

    @Override
    public void onClick(View view) {
        super.onClick(view);

        switch (view.getId()) {
            case R.id.ll_change_password :

                break;
            case R.id.ll_about_us :

                break;
            case R.id.ll_modify_info :

                break;

            default:
                break;
        }
    }
}
