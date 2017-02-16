package com.carlsberg.app.module.my.ui.fragment;

import android.content.Intent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.carlsberg.app.R;
import com.carlsberg.app.module.common.ui.activity.LoginActivity;
import com.carlsberg.app.module.home.ui.activity.ChangePasswordActivity;
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
    @Bind(R.id.tv_exit)
    TextView tv_exit;

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
        tv_exit.setOnClickListener(this);
    }

    @Override
    public void initData() {

    }

    @Override
    public void onClick(View view) {
        super.onClick(view);

        switch (view.getId()) {
            case R.id.ll_change_password:
                Intent intent = new Intent(baseActivity, ChangePasswordActivity.class);
                startActivity(intent);
                break;
            case R.id.ll_about_us :

                break;
            case R.id.ll_modify_info :

                break;
            case R.id.tv_exit :
                Intent intentE = new Intent(baseActivity, LoginActivity.class);
                startActivity(intentE);
                break;

            default:
                break;
        }
    }
}
