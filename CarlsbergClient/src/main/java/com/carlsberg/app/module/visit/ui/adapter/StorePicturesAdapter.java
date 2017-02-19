package com.carlsberg.app.module.visit.ui.adapter;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.carlsberg.app.R;
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

public class StorePicturesAdapter extends BaseAdapter<String> {

    List<PublishDynamicImgsAdapter> picAdapterList = new ArrayList<>();

    public StorePicturesAdapter(Context ctx, List<String> data) {
        super(ctx, R.layout.item_store_pictures, Arrays.asList(new String[3]));

        PublishDynamicImgsAdapter a1 = new PublishDynamicImgsAdapter((BaseActivity) ctx);
        PublishDynamicImgsAdapter a2 = new PublishDynamicImgsAdapter((BaseActivity) ctx);
        PublishDynamicImgsAdapter a3 = new PublishDynamicImgsAdapter((BaseActivity) ctx);

        Collections.addAll(picAdapterList, a1, a2, a3);

    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, String s) {
        if(picAdapterList.size() > 0){
            RecyclerView rv_imgs = baseViewHolder.getView(R.id.rv_imgs);
            GridLayoutManager mGridLayoutManager = new GridLayoutManager(mContext, 4);
            rv_imgs.setLayoutManager(mGridLayoutManager);
            rv_imgs.setAdapter(picAdapterList.get(getFinalPositionOnList(baseViewHolder)));
        }
    }
}
