package com.carlsberg.app.module.visit.persenter;

import com.carlsberg.app.application.CarlsbergAppcation;
import com.carlsberg.app.bean.visit.VisitStoreResponse;
import com.carlsberg.app.common.Constant;
import com.carlsberg.app.http.protocol.VisitProtocol;
import com.carlsberg.app.module.visit.view.UploadPicturesView;
import com.common.base.presenter.BasePresenterImpl;
import com.common.callback.RequestCallback;
import com.common.http.HttpSubscibe;

import rx.android.schedulers.AndroidSchedulers;

/**
 * Created by LiesLee on 17/3/1.
 */

public class UploadPicturesPresenter extends BasePresenterImpl<UploadPicturesView> {

    public UploadPicturesPresenter(UploadPicturesView view) {
        super(view);
    }

    public void loadPictures(String store_id, String task_id){
        HttpSubscibe.subscibe(CarlsbergAppcation.getInstance(), AndroidSchedulers.mainThread(),
                VisitProtocol.getPhoto(store_id, task_id), null, mView, new RequestCallback<VisitStoreResponse>() {
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
                    public void requestSuccess(VisitStoreResponse data) {
                        mView.loadPicturesDone(data);
                    }
                });
    }
}
