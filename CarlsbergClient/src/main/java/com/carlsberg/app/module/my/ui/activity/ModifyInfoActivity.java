package com.carlsberg.app.module.my.ui.activity;

import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.carlsberg.app.R;
import com.carlsberg.app.application.CarlsbergAppcation;
import com.carlsberg.app.module.my.persenter.ModifyInfoPresenter;
import com.carlsberg.app.module.my.view.ModifyInfoView;
import com.carlsberg.app.utils.UIHelper;
import com.common.annotation.ActivityFragmentInject;
import com.common.base.presenter.BasePresenterImpl;
import com.common.base.ui.BaseActivity;
import com.common.base.ui.BaseView;

import butterknife.Bind;

import static com.carlsberg.app.R.id.et_new_password;
import static com.carlsberg.app.R.id.et_old_password;
import static com.carlsberg.app.R.id.tv_name;

/**
 * Created by LiesLee on 17/2/16.
 */
@ActivityFragmentInject(contentViewId = R.layout.act_modify_user_info, toolbarTitle = R.string.modify_user_info)
public class ModifyInfoActivity extends BaseActivity<ModifyInfoPresenter> implements ModifyInfoView {
    @Bind(R.id.tv_phone)
    TextView tv_phone;
    @Bind(R.id.tv_save)
    TextView tv_save;

    @Bind(R.id.et_name)
    EditText et_name;
    @Bind(R.id.et_email)
    EditText et_email;
    @Bind(R.id.et_address)
    EditText et_address;
    private String name;
    private String email;
    private String address;

    @Override
    protected void initView() {
        mPresenter = new ModifyInfoPresenter(this);
        tv_save.setOnClickListener(this);
    }

    @Override
    public void initData() {
        tv_phone.setText(CarlsbergAppcation.getInstance().getUser().getUser_info().getMobile());
        et_name.setText(CarlsbergAppcation.getInstance().getUser().getUser_info().getNick_name());
        et_email.setText(CarlsbergAppcation.getInstance().getUser().getUser_info().getEmail());
    }

    @Override
    protected void onClickBackFinishBefore() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_save :
                    if(check()){
                        mPresenter.changeUserInfo(name, email, address);
                    }
                break;

            default:
                break;
        }
    }


    boolean check() {
        name = et_name.getText().toString();
        email = et_email.getText().toString();
        address = et_address.getText().toString();
        if(TextUtils.isEmpty(name)){
            UIHelper.showShakeAnim(this, et_name, "名字不能为空");
            et_name.requestFocus();
            return false;
        }else if(TextUtils.isEmpty(email)){
            UIHelper.showShakeAnim(this, et_email, "邮箱不能为空");
            et_email.requestFocus();
            return false;
        }
        return true;
    }

    @Override
    public void modifySuccessed() {
        showShortToast("修改成功");
        finish();
    }
}
