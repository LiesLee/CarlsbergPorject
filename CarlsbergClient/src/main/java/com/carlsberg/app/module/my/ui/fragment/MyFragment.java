package com.carlsberg.app.module.my.ui.fragment;

import android.content.Intent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.carlsberg.app.R;
import com.carlsberg.app.application.CarlsbergAppcation;
import com.carlsberg.app.bean.common.User;
import com.carlsberg.app.common.Constant;
import com.carlsberg.app.http.HttpConstants;
import com.carlsberg.app.module.common.ui.activity.LoginActivity;
import com.carlsberg.app.module.common.ui.activity.WebViewActivity;
import com.carlsberg.app.module.my.ui.activity.ChangePasswordActivity;
import com.carlsberg.app.module.my.ui.activity.ModifyInfoActivity;
import com.carlsberg.app.module.my.persenter.MyPresenter;
import com.carlsberg.app.module.my.view.LoginView;
import com.common.ShiHuiActivityManager;
import com.common.annotation.ActivityFragmentInject;
import com.common.base.ui.BaseFragment;
import com.views.util.RefreshUtil;

import butterknife.Bind;
import in.srain.cube.views.ptr.PtrClassicFrameLayout;
import in.srain.cube.views.ptr.PtrFrameLayout;

/**
 * Created by LiesLee on 17/2/14.
 */
@ActivityFragmentInject(contentViewId = R.layout.fra_my)
public class MyFragment extends BaseFragment<MyPresenter> implements LoginView {

    @Bind(R.id.pcfl_pull_to_refresh)
    PtrClassicFrameLayout pcfl_pull_to_refresh;

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
        mPresenter = new MyPresenter(this);
        //initialize Refresh layout
        RefreshUtil.init_material_pull(baseActivity, pcfl_pull_to_refresh, new RefreshUtil.PtrRefreshListener() {
            @Override
            public void OnRefresh(final PtrFrameLayout frame) {
                mPresenter.userInfo();
            }
        });

        ll_change_password.setOnClickListener(this);
        ll_about_us.setOnClickListener(this);
        tv_exit.setOnClickListener(this);
        ll_modify_info.setOnClickListener(this);
    }

    @Override
    public void initData() {
        tv_name.setText(CarlsbergAppcation.getInstance().getUser().getUser_info().getNick_name());
        tv_type.setText(CarlsbergAppcation.getInstance().getUser().getUser_info().getRole_name());
    }

    @Override
    public void onResume() {
        super.onResume();
        if(mPresenter != null && pcfl_pull_to_refresh != null){
            RefreshUtil.autoRefresh(pcfl_pull_to_refresh);
        }
    }


    @Override
    public void hideProgress(int type) {
        super.hideProgress(type);
        if (type == Constant.PROGRESS_TYPE_LIST) {
            pcfl_pull_to_refresh.refreshComplete();
        }
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
                Intent intentWeb = new Intent(baseActivity, WebViewActivity.class);
                intentWeb.putExtra("url", HttpConstants.getCustomerBaseUrl() + "h5.php?c=about");
                startActivity(intentWeb);
                break;
            case R.id.ll_modify_info :
                Intent intentU = new Intent(baseActivity, ModifyInfoActivity.class);
                startActivityForResult(intentU, 999);
                break;
            case R.id.tv_exit :
                mPresenter.logout();
                break;

            default:
                break;
        }
    }

    @Override
    public void loginSucceed(User data) {
        if(data == null){
            CarlsbergAppcation.getInstance().setUser(null);
            ShiHuiActivityManager.getInstance().cleanActivity();
            startActivity(new Intent(baseActivity, LoginActivity.class));
        }else{
            tv_name.setText(data.getUser_info().getNick_name());
            tv_type.setText(data.getUser_info().getRole_name());
        }

    }
}
