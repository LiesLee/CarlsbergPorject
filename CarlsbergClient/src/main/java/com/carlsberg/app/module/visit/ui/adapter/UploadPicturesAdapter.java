package com.carlsberg.app.module.visit.ui.adapter;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.carlsberg.app.R;
import com.carlsberg.app.bean.visit.PhotoListBean;
import com.carlsberg.app.utils.UIHelper;
import com.chad.library.adapter.base.BaseViewHolder;
import com.common.base.ui.BaseActivity;
import com.common.base.ui.BaseAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by LiesLee on 2017/2/19.
 */

public class UploadPicturesAdapter extends BaseAdapter<PhotoListBean> {


    public UploadPicturesAdapter(Context ctx, List<PhotoListBean> data, UIHelper.TakePictureSavePathCallBack callBack) {
        super(ctx, R.layout.item_store_pictures, data);

    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, PhotoListBean data) {
        baseViewHolder.setText(R.id.tv_photo_title, data.getPhoto_title());
        RecyclerView rv_imgs = baseViewHolder.getView(R.id.rv_imgs);
        GridLayoutManager mGridLayoutManager = new GridLayoutManager(mContext, 4);
        rv_imgs.setLayoutManager(mGridLayoutManager);
        rv_imgs.setAdapter(data.getImgsAdapter());

    }

}
