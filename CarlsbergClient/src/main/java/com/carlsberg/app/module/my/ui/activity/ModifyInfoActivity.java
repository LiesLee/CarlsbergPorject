package com.carlsberg.app.module.my.ui.activity;

import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.carlsberg.app.R;
import com.carlsberg.app.utils.UIHelper;
import com.common.annotation.ActivityFragmentInject;
import com.common.base.presenter.BasePresenterImpl;
import com.common.base.ui.BaseActivity;
import com.common.base.ui.BaseView;

import butterknife.Bind;

import static com.carlsberg.app.R.id.et_new_password;
import static com.carlsberg.app.R.id.et_old_password;

/**
 * Created by LiesLee on 17/2/16.
 */
@ActivityFragmentInject(contentViewId = R.layout.act_modify_user_info, toolbarTitle = R.string.modify_user_info)
public class ModifyInfoActivity extends BaseActivity<BasePresenterImpl> implements BaseView {
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
    private String phone;

    @Override
    protected void initView() {
        tv_save.setOnClickListener(this);
    }

    @Override
    public void initData() {
        tv_phone.setText("12345678999");
    }

    @Override
    protected void onClickBackFinishBefore() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_save :
                    if(check()){

                    }
                break;

            default:
                break;
        }
    }


    boolean check() {
        phone = "12345678999";

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

}
