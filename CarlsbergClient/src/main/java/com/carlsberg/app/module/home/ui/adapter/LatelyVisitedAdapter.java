package com.carlsberg.app.module.home.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.View;

import com.carlsberg.app.R;
import com.carlsberg.app.module.visit.ui.activity.StoreVisitActivity;
import com.chad.library.adapter.base.BaseViewHolder;
import com.common.base.ui.BaseAdapter;

import java.util.Arrays;
import java.util.List;

/**
 * Created by rrsh on 16/10/13.
 */

public class LatelyVisitedAdapter extends BaseAdapter<String> {


    public LatelyVisitedAdapter(Context ctx, List<String> data) {
        super(ctx, R.layout.item_lately_visited, Arrays.asList(new String[10]));
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, String data) {
        baseViewHolder.getConvertView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mContext.startActivity(new Intent(mContext, StoreVisitActivity.class));
            }
        });
    }

}
