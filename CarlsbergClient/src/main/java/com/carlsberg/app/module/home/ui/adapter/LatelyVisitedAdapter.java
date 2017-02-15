package com.carlsberg.app.module.home.ui.adapter;

import android.content.Context;
import android.graphics.Color;

import com.carlsberg.app.R;
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

    }

}
