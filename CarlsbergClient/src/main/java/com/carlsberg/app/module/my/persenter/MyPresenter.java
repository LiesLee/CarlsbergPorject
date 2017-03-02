package com.carlsberg.app.module.my.persenter;

import com.carlsberg.app.application.CarlsbergAppcation;
import com.carlsberg.app.bean.common.User;
import com.carlsberg.app.common.Constant;
import com.carlsberg.app.http.protocol.CommonProtocol;
import com.carlsberg.app.module.my.view.LoginView;
import com.common.base.presenter.BasePresenterImpl;
import com.common.callback.RequestCallback;
import com.common.http.HttpSubscibe;

import rx.android.schedulers.AndroidSchedulers;

/**
 * Created by LiesLee on 2017/3/2.
 */

public class MyPresenter extends BasePresenterImpl<LoginView> {

    public MyPresenter(LoginView view) {
        super(view);
    }


    public void userInfo(){
        HttpSubscibe.subscibe(CarlsbergAppcation.getInstance(), AndroidSchedulers.mainThread(),
                CommonProtocol.userInfo(), null, mView, new RequestCallback<User>() {
                    @Override
                    public void beforeRequest() {
                        mView.showProgress(Constant.PROGRESS_TYPE_LIST);
                    }

                    @Override
                    public void requestError(int code, String msg) {
                        if(code == 0){
                            mView.hideProgress(Constant.PROGRESS_TYPE_LIST);
                            mView.toast("网络失败异常,请稍后再试");
                        }else{
                            mView.toast(msg);
                        }
                    }

                    @Override
                    public void requestComplete() {
                        mView.hideProgress(Constant.PROGRESS_TYPE_LIST);
                    }

                    @Override
                    public void requestSuccess(User data) {
                        if(data!=null){
                            mView.loginSucceed(data);
                        }
                    }
                });
    }

    public void logout(){
        HttpSubscibe.subscibe(CarlsbergAppcation.getInstance(), AndroidSchedulers.mainThread(),
                CommonProtocol.logout(), null, mView, new RequestCallback<String>() {
                    @Override
                    public void beforeRequest() {
                        mView.showProgress(Constant.PROGRESS_TYPE_LIST);
                    }

                    @Override
                    public void requestError(int code, String msg) {
                        if(code == 0){
                            mView.hideProgress(Constant.PROGRESS_TYPE_LIST);
                            mView.toast("网络失败异常,请稍后再试");
                        }else{
                            mView.toast(msg);
                        }
                    }

                    @Override
                    public void requestComplete() {
                        mView.hideProgress(Constant.PROGRESS_TYPE_LIST);
                    }

                    @Override
                    public void requestSuccess(String data) {
                        if(data!=null){
                            mView.loginSucceed(null);
                        }
                    }
                });
    }

}
