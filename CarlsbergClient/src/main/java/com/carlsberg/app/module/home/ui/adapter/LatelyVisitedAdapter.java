package com.carlsberg.app.module.home.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.View;

import com.carlsberg.app.R;
import com.carlsberg.app.bean.home.HomeDataResponse;
import com.carlsberg.app.bean.visit.Visit;
import com.carlsberg.app.module.visit.ui.activity.StoreVisitActivity;
import com.chad.library.adapter.base.BaseViewHolder;
import com.common.base.ui.BaseAdapter;

import java.util.Arrays;
import java.util.List;

/**
 * Created by rrsh on 16/10/13.
 */

public class LatelyVisitedAdapter extends BaseAdapter<Visit> {


    public LatelyVisitedAdapter(Context ctx, List<Visit> data) {
        super(ctx, R.layout.item_lately_visited, null);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, final Visit data) {
        baseViewHolder.setText(R.id.tv_name, data.getStore_name());
        baseViewHolder.setText(R.id.tv_address, data.getArea_title() + " - " + data.getRegion_title());
        baseViewHolder.setText(R.id.tv_type, data.getFlag_plan_title());
        baseViewHolder.setText(R.id.tv_status, data.getTask_status_title());
        if(data.getTask_status() == 0){
            baseViewHolder.setBackgroundColor(R.id.tv_type, R.color.blue);
        }else if(data.getTask_status() == 1){
            baseViewHolder.setBackgroundColor(R.id.tv_type, R.color.green);
        }else{
            baseViewHolder.setBackgroundColor(R.id.tv_type, R.color.red);
        }
        if (data.getFlag_plan() == 0) {
            baseViewHolder.setBackgroundColor(R.id.tv_type, R.color.black_30);
        } else {
            baseViewHolder.setBackgroundColor(R.id.tv_type, R.color.red);
        }
        baseViewHolder.getConvertView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, StoreVisitActivity.class);
                intent.putExtra("store_id", data.getStore_id());
                intent.putExtra("store_name", data.getStore_name());
                mContext.startActivity(intent);
            }
        });
    }

}
