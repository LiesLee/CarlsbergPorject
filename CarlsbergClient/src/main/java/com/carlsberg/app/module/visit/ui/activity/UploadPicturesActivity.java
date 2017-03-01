package com.carlsberg.app.module.visit.ui.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.carlsberg.app.R;
import com.carlsberg.app.bean.visit.PhotoListBean;
import com.carlsberg.app.bean.visit.VisitStoreResponse;
import com.carlsberg.app.common.Constant;
import com.carlsberg.app.http.async.CompressImageNetUtils;
import com.carlsberg.app.module.visit.persenter.UploadPicturesPresenter;
import com.carlsberg.app.module.visit.ui.adapter.PublishDynamicImgsAdapter;
import com.carlsberg.app.module.visit.ui.adapter.StorePicturesAdapter;
import com.carlsberg.app.module.visit.ui.adapter.UploadPicturesAdapter;
import com.carlsberg.app.module.visit.view.UploadPicturesView;
import com.carlsberg.app.utils.UIHelper;
import com.common.annotation.ActivityFragmentInject;
import com.common.base.presenter.BasePresenterImpl;
import com.common.base.ui.BaseActivity;
import com.common.base.ui.BaseView;
import com.views.util.ToastUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * Created by LiesLee on 17/2/20.
 */
@ActivityFragmentInject(contentViewId = R.layout.act_upload_pictures, toolbarTitle = R.string.app_name)
public class UploadPicturesActivity extends BaseActivity<UploadPicturesPresenter> implements UploadPicturesView, UIHelper.TakePictureSavePathCallBack {

    String path;
    PublishDynamicImgsAdapter imgsAdapter;

    @Bind(R.id.rv_list)
    RecyclerView rv_list;

    Handler handler;
    LinearLayoutManager mLinearLayoutManager;

    UploadPicturesAdapter uploadPicturesAdapter;
    private int deletePosition;
    private String store_id;
    private String task_id;
    private String store_name;

    @Override
    protected void initView() {
        mPresenter = new UploadPicturesPresenter(this);
        store_id = getIntent().getStringExtra("store_id");
        task_id = getIntent().getStringExtra("task_id");
        store_name = getIntent().getStringExtra("store_name");
        if(TextUtils.isEmpty(store_id) || TextUtils.isEmpty(task_id)){
            finish();
            ToastUtil.showShortToast(baseActivity, "ID数据为空");
            return;
        }
        tv_title.setText(store_name);

        mLinearLayoutManager = new LinearLayoutManager(baseActivity);
        rv_list.setLayoutManager(mLinearLayoutManager);
        uploadPicturesAdapter = new UploadPicturesAdapter(baseActivity, null, this);
        rv_list.setAdapter(uploadPicturesAdapter);
    }

    @Override
    public void initData() {
        mPresenter.loadPictures(store_id, task_id);
    }

    @Override
    protected void onClickBackFinishBefore() {

    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void callbackPath(String path, PublishDynamicImgsAdapter mAdapter) {
        this.path = path;
        this.imgsAdapter = mAdapter;
    }

    @Override
    public void onImgDelete(int position, PublishDynamicImgsAdapter mAdapter) {
        this.deletePosition = position;
        this.imgsAdapter = mAdapter;
        mAdapter.notifyItemRemoved(deletePosition);
        mAdapter.getImgs().remove(deletePosition);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1024 && resultCode == RESULT_OK){
            if(data != null){
                Bundle bundle = data.getExtras();
                if(bundle!=null){
                    Bitmap imageBitmap = (Bitmap) bundle.get("data");
                    Log.e("DATA", "有bitmap");
                }
            }

            if(path != null){
                showProgress(Constant.PROGRESS_TYPE_DIALOG); //上传需要手动调起进度条
                ArrayList<String> arrayImage = new ArrayList<>();
                arrayImage.add(path);
                CompressImageNetUtils.getCompressImge(baseActivity, arrayImage, store_id, task_id, imgsAdapter.getPhoto_type()+"",
                        new CompressImageNetUtils.HandlerPicSingleCompressBack() {
                    @Override
                    public void callBack(boolean isSuccess, String imagePath) {
                        if(isSuccess){
                            mPresenter.loadPictures(store_id, task_id);
                        }else{
                            hideProgress(Constant.PROGRESS_TYPE_DIALOG);
                        }
                    }
                });
            }
        }
    }

    @Override
    public void loadPicturesDone(VisitStoreResponse data) {
        List<PhotoListBean> picList = data.getTask_photo();
        if(picList!=null && picList.size() > 0){
            for(int i = 0; i < picList.size() ; i++){
                PublishDynamicImgsAdapter imgsAdapter = new PublishDynamicImgsAdapter(baseActivity, this);
                imgsAdapter.setPhoto_type(picList.get(i).getPhoto_type());
                picList.get(i).setImgsAdapter(imgsAdapter);
            }
            uploadPicturesAdapter.setData(picList);
        }else {
            uploadPicturesAdapter.setData(null);
        }
    }
}
