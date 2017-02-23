package com.carlsberg.app.module.common.ui.activity;


import android.text.InputType;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.carlsberg.app.R;
import com.carlsberg.app.bean.common.User;
import com.carlsberg.app.module.my.persenter.LoginPersenter;
import com.carlsberg.app.module.my.view.LoginView;
import com.carlsberg.app.utils.UIHelper;
import com.common.annotation.ActivityFragmentInject;
import com.common.base.presenter.BasePresenterImpl;
import com.common.base.ui.BaseActivity;
import com.common.base.ui.BaseView;

import butterknife.Bind;

/**
 * Created by LiesLee on 16/11/23.
 */
@ActivityFragmentInject(contentViewId = R.layout.act_login)
public class LoginActivity extends BaseActivity<LoginPersenter> implements LoginView {

    @Bind(R.id.et_phone)
    EditText et_phone;
    @Bind(R.id.et_pass)
    EditText et_pass;

    @Bind(R.id.iv_is_show)
    ImageView iv_is_show;

    @Bind(R.id.tv_forgot)
    TextView tv_forgot;
    @Bind(R.id.tv_login)
    TextView tv_login;

    boolean isShow = false;
    private String phone;
    private String password;

    @Override
    protected void initView() {
        mPresenter = new LoginPersenter(this);
        iv_is_show.setOnClickListener(this);
        tv_forgot.setOnClickListener(this);
        tv_login.setOnClickListener(this);
    }

    @Override
    public void initData() {
        show(isShow);
    }

    @Override
    protected void onClickBackFinishBefore() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_is_show :
                isShow = !isShow;
                show(isShow);
                break;
            case R.id.tv_forgot :

                break;
            case R.id.tv_login :
                    if(check()){
                        mPresenter.login(phone, password);
                    }
                break;

            default:
                break;
        }
    }

    public void show(boolean isShow){
        if(isShow){
            iv_is_show.setSelected(true);
            et_pass.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
        }else{
            iv_is_show.setSelected(false);
            et_pass.setInputType(InputType.TYPE_CLASS_TEXT|InputType.TYPE_TEXT_VARIATION_PASSWORD);
        }
    }


    boolean check() {
        phone = et_phone.getText().toString();
        password = et_pass.getText().toString();
        if (TextUtils.isEmpty(phone)) {
            UIHelper.showShakeAnim(this, et_phone, "手机号码不能为空");
            et_phone.requestFocus();
            return false;
        } else if (!UIHelper.phoneNumberValid(phone)) {
            UIHelper.showShakeAnim(this, et_phone, "请输入正确手机号码");
            et_phone.requestFocus();
            return false;
        }else if (TextUtils.isEmpty(password)
                || et_pass.getText().toString().length() < 6 || et_pass.getText().toString().length() > 14) {
            UIHelper.showShakeAnim(this, et_pass, "请输入6到14位的密码");
            et_pass.requestFocus();
            return false;
        }
        return true;
    }

    @Override
    public void loginSucceed(User data) {

    }
}
