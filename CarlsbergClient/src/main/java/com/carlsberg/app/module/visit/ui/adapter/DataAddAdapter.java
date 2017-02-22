package com.carlsberg.app.module.visit.ui.adapter;

import android.content.Context;
import android.view.View;
import android.widget.Toast;

import com.carlsberg.app.R;
import com.carlsberg.app.bean.common.Test;
import com.chad.library.adapter.base.BaseViewHolder;
import com.common.base.ui.BaseAdapter;
import com.common.base.ui.BaseAdapterForMultiItem;
import com.socks.library.KLog;
import com.views.AmountView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by LiesLee on 17/2/22.
 */

public class DataAddAdapter extends BaseAdapterForMultiItem<Test> {


    public DataAddAdapter(Context ctx, List<Test> data) {
        super(ctx, data);
        addItemType(1, R.layout.item_add_number);
        addItemType(2, R.layout.item_add_choose);
        addItemType(3, R.layout.item_add_text);
    }

    @Override
    protected void convert(final BaseViewHolder baseViewHolder, Test i) {
        if(i.getItemType() == 1){
            AmountView mAmountView = baseViewHolder.getView(R.id.amount_view);
            mAmountView.setGoods_storage(10);
            mAmountView.setInitialValue(i.getAmount());
            mAmountView.setOnAmountChangeListener(new AmountView.OnAmountChangeListener() {
                @Override
                public void onAmountChange(View view, int amount) {
                    Test j = getData().get(getFinalPositionOnList(baseViewHolder));
                    j.setAmount(amount);
                }
            });
        }

    }
}
