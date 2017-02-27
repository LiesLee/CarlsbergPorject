package com.carlsberg.app.module.common.persenter;

import com.carlsberg.app.application.CarlsbergAppcation;
import com.carlsberg.app.bean.common.User;
import com.carlsberg.app.common.Constant;
import com.carlsberg.app.http.protocol.CommonProtocol;
import com.carlsberg.app.module.common.view.MainActivityView;
import com.common.base.presenter.BasePresenterImpl;
import com.common.callback.RequestCallback;
import com.common.http.HttpSubscibe;

import rx.android.schedulers.AndroidSchedulers;

/**
 * Created by LiesLee on 17/2/27.
 */

public class MainActivityPresenter extends BasePresenterImpl<MainActivityView> {

    public MainActivityPresenter(MainActivityView view) {
        super(view);
    }

    public void quickLogin(){
        HttpSubscibe.subscibe(CarlsbergAppcation.getInstance(), AndroidSchedulers.mainThread(),
                CommonProtocol.quickLogin(), null, mView, new RequestCallback<User>() {
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
                        mView.quickLoginError();
                    }

                    @Override
                    public void requestComplete() {
                        mView.hideProgress(Constant.PROGRESS_TYPE_DIALOG);
                    }

                    @Override
                    public void requestSuccess(User data) {
                        if(data!=null){
                            CarlsbergAppcation.getInstance().setUser(data);
                            mView.quickLoginSuccess();
                        }
                    }
                });
    }
}
