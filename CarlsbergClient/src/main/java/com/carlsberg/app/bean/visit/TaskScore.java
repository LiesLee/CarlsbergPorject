package com.carlsberg.app.bean.visit;

import android.text.TextUtils;

import com.chad.library.adapter.base.entity.MultiItemEntity;

/**
 * Created by LiesLee on 17/2/28.
 */

public class TaskScore implements MultiItemEntity {


    /**
     * title : 助销工具使用
     * type : input
     * data : 2分
     * val : 2
     */

    private String data;

    private String title;
    private String id_name;
    private String type;
    private int min;
    private int max;
    private int val;


    @Override
    public int getItemType() {
        if(TextUtils.isEmpty(type)) return 1;
        if("input".equals(type)){
            return 1;
        }else if("radio".equals(type)){
            return 2;
        }else if("areatext".equals(type)){
            return 3;
        }else{
            return 1;
        }
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public int getVal() {
        return val;
    }

    public void setVal(int val) {
        this.val = val;
    }

    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public String getId_name() {
        return id_name;
    }

    public void setId_name(String id_name) {
        this.id_name = id_name;
    }

}
