package com.carlsberg.app.module.visit.ui.adapter;

import android.content.Context;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.carlsberg.app.R;
import com.carlsberg.app.bean.common.Test;
import com.carlsberg.app.bean.visit.TaskCollect;
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

public class DataAddAdapter extends BaseAdapterForMultiItem<TaskCollect> {


    public DataAddAdapter(Context ctx, List<TaskCollect> data) {
        super(ctx, data);
        addItemType(1, R.layout.item_add_number);
        addItemType(2, R.layout.item_add_choose);
        addItemType(3, R.layout.item_add_text);
    }

    @Override
    protected void convert(final BaseViewHolder baseViewHolder, TaskCollect data) {
        baseViewHolder.setText(R.id.tv_name, data.getTitle()+"：");
        if(data.getItemType() == 1){

            AmountView mAmountView = baseViewHolder.getView(R.id.amount_view);
            mAmountView.setGoods_storage(data.getMax());//最大值
            int val = 0;

            if(!TextUtils.isEmpty(data.getVal())){
                val = Integer.parseInt(data.getVal());
            }

            if(val == 0){
                val = data.getMin();
                data.setVal(val + "");
            }

            mAmountView.setInitialValue(val);//初始值
            mAmountView.setOnAmountChangeListener(new AmountView.OnAmountChangeListener() {
                @Override
                public void onAmountChange(View view, int amount) {
                    TaskCollect j = getData().get(getFinalPositionOnList(baseViewHolder));
                    j.setVal(amount + "");
                }
            });
        }else if(data.getItemType() == 2){
            RadioGroup rg_performance = baseViewHolder.getView(R.id.rg_performance);
            if(rg_performance!=null){

                int val = 0;

                if(!TextUtils.isEmpty(data.getVal())){
                    val = Integer.parseInt(data.getVal());
                }else{
                    val = 1;
                    data.setVal(val+"");
                }

                switch (val) {
                    case 0 :
                        rg_performance.check(R.id.rb_bad);
                        break;
                    case 1 :
                        rg_performance.check(R.id.rb_soso);
                        break;
                    case 2 :
                        rg_performance.check(R.id.rb_good);
                        break;

                    default:
                        rg_performance.check(R.id.rb_soso);
                        break;
                }

                rg_performance.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup group, int checkedId) {
                        TaskCollect j = getData().get(getFinalPositionOnList(baseViewHolder));
                        switch (checkedId) {
                            case R.id.rb_bad :
                                j.setVal(0 + "");
                                break;

                            case R.id.rb_soso :
                                j.setVal(1 + "");
                                break;

                            case R.id.rb_good :
                                j.setVal(2 + "");
                                break;

                            default:

                                break;
                        }
                    }
                });
            }
        }else if(data.getItemType() == 3){
            EditText et_text = baseViewHolder.getView(R.id.et_text);
            if(et_text!=null){
                et_text.setFilters(new InputFilter[]{new InputFilter.LengthFilter(data.getMax())});
                String val = "";
                if(!TextUtils.isEmpty(data.getVal())){
                    val = data.getVal();
                }else{
                    data.setVal(val);
                }

                et_text.setText(val);

                et_text.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {

                    }

                    @Override
                    public void afterTextChanged(Editable s) {
                        TaskCollect j = getData().get(getFinalPositionOnList(baseViewHolder));
                        j.setVal(s.toString());
                    }
                });
            }
        }

    }
}
