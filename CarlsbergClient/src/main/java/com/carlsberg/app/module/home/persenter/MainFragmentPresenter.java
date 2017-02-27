package com.carlsberg.app.module.home.persenter;

import com.carlsberg.app.application.CarlsbergAppcation;
import com.carlsberg.app.bean.common.User;
import com.carlsberg.app.bean.home.HomeDataResponse;
import com.carlsberg.app.common.Constant;
import com.carlsberg.app.http.protocol.CommonProtocol;
import com.carlsberg.app.http.protocol.HomeProtocol;
import com.carlsberg.app.module.common.view.MainActivityView;
import com.carlsberg.app.module.home.view.MainFragmentView;
import com.common.base.presenter.BasePresenterImpl;
import com.common.callback.RequestCallback;
import com.common.http.HttpSubscibe;

import rx.android.schedulers.AndroidSchedulers;

/**
 * Created by LiesLee on 17/2/27.
 */

public class MainFragmentPresenter extends BasePresenterImpl<MainFragmentView> {

    public MainFragmentPresenter(MainFragmentView view) {
        super(view);
    }

    public void loadHomeData(){
        HttpSubscibe.subscibe(CarlsbergAppcation.getInstance(), AndroidSchedulers.mainThread(),
                HomeProtocol.index(), null, mView, new RequestCallback<HomeDataResponse>() {
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
                    public void requestSuccess(HomeDataResponse data) {
                        if(data!=null){
                            mView.loadListDone(data);
                        }
                    }
                });
    }

}
