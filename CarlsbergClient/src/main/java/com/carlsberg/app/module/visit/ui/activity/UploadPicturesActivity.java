package com.carlsberg.app.module.visit.ui.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.carlsberg.app.R;
import com.carlsberg.app.module.visit.ui.adapter.PublishDynamicImgsAdapter;
import com.carlsberg.app.module.visit.ui.adapter.StorePicturesAdapter;
import com.carlsberg.app.module.visit.ui.adapter.UploadPicturesAdapter;
import com.carlsberg.app.utils.UIHelper;
import com.common.annotation.ActivityFragmentInject;
import com.common.base.presenter.BasePresenterImpl;
import com.common.base.ui.BaseActivity;
import com.common.base.ui.BaseView;

import butterknife.Bind;

/**
 * Created by LiesLee on 17/2/20.
 */
@ActivityFragmentInject(contentViewId = R.layout.act_upload_pictures, toolbarTitle = R.string.app_name)
public class UploadPicturesActivity extends BaseActivity<BasePresenterImpl> implements BaseView,UIHelper.TakePictureSavePathCallBack {

    String path;
    PublishDynamicImgsAdapter imgsAdapter;

    @Bind(R.id.rv_list)
    RecyclerView rv_list;

    Handler handler;
    LinearLayoutManager mLinearLayoutManager;

    UploadPicturesAdapter uploadPicturesAdapter;
    private int deletePosition;

    @Override
    protected void initView() {
        mLinearLayoutManager = new LinearLayoutManager(baseActivity);
        rv_list.setLayoutManager(mLinearLayoutManager);
        uploadPicturesAdapter = new UploadPicturesAdapter(baseActivity, null, this);
        rv_list.setAdapter(uploadPicturesAdapter);
    }

    @Override
    public void initData() {

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
                imgsAdapter.addImgs(Uri.parse(path));
            }
        }
    }
}
