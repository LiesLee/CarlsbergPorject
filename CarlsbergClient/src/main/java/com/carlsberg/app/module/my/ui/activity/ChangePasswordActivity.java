package com.carlsberg.app.module.my.ui.activity;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import com.carlsberg.app.R;
import com.carlsberg.app.application.CarlsbergAppcation;
import com.carlsberg.app.bean.common.User;
import com.carlsberg.app.module.common.ui.activity.LoginActivity;
import com.carlsberg.app.module.my.persenter.ChangePasswordPresenter;
import com.carlsberg.app.module.my.view.ModifyInfoView;
import com.carlsberg.app.utils.UIHelper;
import com.common.ShiHuiActivityManager;
import com.common.annotation.ActivityFragmentInject;
import com.common.base.presenter.BasePresenterImpl;
import com.common.base.ui.BaseActivity;
import com.common.base.ui.BaseView;

import butterknife.Bind;


@ActivityFragmentInject(contentViewId = R.layout.act_forget_pass, toolbarTitle = R.string.change_password)
public class ChangePasswordActivity extends BaseActivity<ChangePasswordPresenter> implements ModifyInfoView {

    @Bind(R.id.tv_send)
    TextView tv_send;
    @Bind(R.id.et_phone)
    EditText et_phone;
    @Bind(R.id.et_old_password)
    EditText et_old_password;
    @Bind(R.id.et_new_password)
    EditText et_new_password;
    @Bind(R.id.et_new_pass_confirm)
    EditText et_new_pass_confirm;
    private String phone;
    private String new_password_confirm;
    private String new_password;
    private String old_password;


    @Override
    protected void initView() {
        mPresenter = new ChangePasswordPresenter(this);
        et_new_pass_confirm.setOnKeyListener(new View.OnKeyListener() {

            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                // TODO Auto-generated method stub
                // 修改回车键功能
                if (keyCode == KeyEvent.KEYCODE_ENTER  && event.getAction() == KeyEvent.ACTION_DOWN ) {
                    // 先隐藏键盘
                    ((InputMethodManager) getSystemService(INPUT_METHOD_SERVICE))
                            .hideSoftInputFromWindow(baseActivity
                                            .getCurrentFocus().getWindowToken(),
                                    InputMethodManager.HIDE_NOT_ALWAYS);
                    //接下来在这里做你自己想要做的事，实现自己的业务。
                    if(check()){
                        //mPresenter.change(password, new_password);
                    }
                }
                return false;
            }
        });
        et_phone.setFocusable(false);
        et_phone.setEnabled(false);
        et_phone.setClickable(false);
        et_phone.setText(CarlsbergAppcation.getInstance().getUser().getUser_info().getMobile());
        tv_send.setOnClickListener(this);

    }

    @Override
    public void initData() {

    }

    @Override
    protected void onClickBackFinishBefore() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_send:
                if(check()){
                    mPresenter.changePassword(old_password, new_password, new_password_confirm);
                }
                break;
        }
    }






    boolean check() {
        old_password = et_old_password.getText().toString();
        new_password = et_new_password.getText().toString();
        new_password_confirm = et_new_pass_confirm.getText().toString();
        if(TextUtils.isEmpty(old_password)){
            UIHelper.showShakeAnim(this, et_old_password, "请输入您当前的登录密码");
            et_old_password.requestFocus();
            return false;
        }else if(TextUtils.isEmpty(new_password)){
            UIHelper.showShakeAnim(this, et_new_password, "请输入新的密码");
            et_new_password.requestFocus();
            return false;
        }else if (TextUtils.isEmpty(new_password_confirm)) {
            UIHelper.showShakeAnim(this, et_new_pass_confirm, "请输入再输一遍新密码确认");
            et_new_pass_confirm.requestFocus();
            return false;
        }
            return true;
    }


    /**
     * 根据传入的格式，获取对应格式化话的字符串
     *
     * @param context     上下文对象
     * @param formatResId 传入的格式resId
     * @param args        替换的操作列表
     * @return 格式化后的字符串
     */
    public static String getFormatString(Context context, int formatResId, Object... args) {
        String format = context.getString(formatResId);
        String result = String.format(format, args);
        return result;
    }


    @Override
    public void modifySuccessed() {
        showLongToast("修改成功，请重新登录！");
        CarlsbergAppcation.getInstance().setUser(null);
        ShiHuiActivityManager.getInstance().cleanActivity();
        startActivity(new Intent(baseActivity, LoginActivity.class));
    }
}
