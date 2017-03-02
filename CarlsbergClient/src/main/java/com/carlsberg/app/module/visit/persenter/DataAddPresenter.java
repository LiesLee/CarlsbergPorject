package com.carlsberg.app.module.visit.persenter;

import com.carlsberg.app.application.CarlsbergAppcation;
import com.carlsberg.app.bean.visit.CollectViewResponse;
import com.carlsberg.app.bean.visit.TaskCollect;
import com.carlsberg.app.bean.visit.VisitStoreResponse;
import com.carlsberg.app.common.Constant;
import com.carlsberg.app.http.protocol.VisitProtocol;
import com.carlsberg.app.module.visit.view.DataAddView;
import com.common.base.presenter.BasePresenterImpl;
import com.common.callback.RequestCallback;
import com.common.http.HttpSubscibe;

import java.util.List;

import rx.android.schedulers.AndroidSchedulers;

/**
 * Created by LiesLee on 17/3/2.
 */

public class DataAddPresenter extends BasePresenterImpl<DataAddView> {

    public DataAddPresenter(DataAddView view) {
        super(view);
    }

    public void collectView(String store_id, String task_id){
        HttpSubscibe.subscibe(CarlsbergAppcation.getInstance(), AndroidSchedulers.mainThread(),
                VisitProtocol.collectView(store_id, task_id), null, mView, new RequestCallback<CollectViewResponse>() {
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
                    public void requestSuccess(CollectViewResponse data) {
                        mView.collectView(data);
                    }
                });
    }

    public void collectSave(String store_id, String task_id, List<TaskCollect> taskCollects, List<TaskCollect> taskScores){
        HttpSubscibe.subscibe(CarlsbergAppcation.getInstance(), AndroidSchedulers.mainThread(),
                VisitProtocol.collectSave(store_id, task_id, taskCollects, taskScores), null, mView, new RequestCallback<String>() {
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
                        mView.saveSuccessed();
                    }
                });
    }

}
