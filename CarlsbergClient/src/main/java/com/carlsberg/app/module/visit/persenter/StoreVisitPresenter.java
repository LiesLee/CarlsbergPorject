package com.carlsberg.app.module.visit.persenter;

import com.carlsberg.app.application.CarlsbergAppcation;
import com.carlsberg.app.bean.visit.VisitRespone;
import com.carlsberg.app.bean.visit.VisitStoreResponse;
import com.carlsberg.app.common.Constant;
import com.carlsberg.app.http.protocol.VisitProtocol;
import com.carlsberg.app.module.visit.view.StoreVisitView;
import com.common.base.presenter.BasePresenterImpl;
import com.common.callback.RequestCallback;
import com.common.http.HttpSubscibe;

import rx.android.schedulers.AndroidSchedulers;

/**
 * Created by LiesLee on 17/2/28.
 */

public class StoreVisitPresenter extends BasePresenterImpl<StoreVisitView> {

    public StoreVisitPresenter(StoreVisitView view) {
        super(view);
    }

    public void storeView(String store_id, String task_id){
        HttpSubscibe.subscibe(CarlsbergAppcation.getInstance(), AndroidSchedulers.mainThread(),
                VisitProtocol.storeView(store_id, task_id), null, mView, new RequestCallback<VisitStoreResponse>() {
                    @Override
                    public void beforeRequest() {
                        mView.showProgress(Constant.PROGRESS_TYPE_LIST);
                    }

                    @Override
                    public void requestError(int code, String msg) {
                        if (code == 0) {
                            mView.hideProgress(Constant.PROGRESS_TYPE_LIST);
                            mView.toast("网络失败异常,请稍后再试");
                        } else if(code == 307){
                            mView.loadError_307(msg);
                        }else{
                            mView.toast(msg);
                        }
                    }

                    @Override
                    public void requestComplete() {
                        mView.hideProgress(Constant.PROGRESS_TYPE_LIST);
                    }

                    @Override
                    public void requestSuccess(VisitStoreResponse data) {
                        mView.loadDataDone(data);

                    }
                });
    }

    public void taskSign(String store_id, String task_id, String sigin_type){
        HttpSubscibe.subscibe(CarlsbergAppcation.getInstance(), AndroidSchedulers.mainThread(),
                VisitProtocol.taskSign(store_id, task_id, sigin_type), null, mView, new RequestCallback<String>() {
                    @Override
                    public void beforeRequest() {
                        mView.showProgress(Constant.PROGRESS_TYPE_DIALOG);
                    }

                    @Override
                    public void requestError(int code, String msg) {
                        if (code == 0) {
                            mView.hideProgress(Constant.PROGRESS_TYPE_DIALOG);
                            mView.toast("网络失败异常,请稍后再试");
                        } else {
                            mView.toast(msg);
                        }
                    }

                    @Override
                    public void requestComplete() {
                        mView.hideProgress(Constant.PROGRESS_TYPE_DIALOG);
                    }

                    @Override
                    public void requestSuccess(String data) {
                        mView.taskSignSuccessed();
                    }
                });
    }



}
