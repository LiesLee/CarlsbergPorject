package com.carlsberg.app.module.my.persenter;

import com.carlsberg.app.application.CarlsbergAppcation;
import com.carlsberg.app.bean.common.User;
import com.carlsberg.app.common.Constant;
import com.carlsberg.app.http.protocol.CommonProtocol;
import com.carlsberg.app.module.my.view.ModifyInfoView;
import com.common.base.presenter.BasePresenterImpl;
import com.common.callback.RequestCallback;
import com.common.http.HttpSubscibe;

import rx.android.schedulers.AndroidSchedulers;

/**
 * Created by LiesLee on 2017/3/2.
 */

public class ModifyInfoPresenter extends BasePresenterImpl<ModifyInfoView> {

    public ModifyInfoPresenter(ModifyInfoView view) {
        super(view);
    }

    public void changeUserInfo(String nick_name, String email, String address){
        HttpSubscibe.subscibe(CarlsbergAppcation.getInstance(), AndroidSchedulers.mainThread(),
                CommonProtocol.changeUserInfo(nick_name, email, address), null, mView, new RequestCallback<String>() {
                    @Override
                    public void beforeRequest() {
                        mView.showProgress(Constant.PROGRESS_TYPE_DIALOG);
                    }

                    @Override
                    public void requestError(int code, String msg) {
                        if(code == 0){
                            mView.hideProgress(Constant.PROGRESS_TYPE_DIALOG);
                            mView.toast("网络失败异常,请稍后再试");
                        }else{
                            mView.toast(msg);
                        }
                    }

                    @Override
                    public void requestComplete() {
                        mView.hideProgress(Constant.PROGRESS_TYPE_DIALOG);
                    }

                    @Override
                    public void requestSuccess(String data) {
                        if(data!=null){
                            mView.modifySuccessed();
                        }
                    }
                });
    }
}
