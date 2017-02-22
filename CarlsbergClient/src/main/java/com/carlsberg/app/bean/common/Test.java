package com.carlsberg.app.bean.common;

import com.chad.library.adapter.base.entity.MultiItemEntity;

/**
 * Created by LiesLee on 17/2/22.
 */

public class Test implements MultiItemEntity {
    int amount;
    int type;

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    @Override
    public int getItemType() {
        return type;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
