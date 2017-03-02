package com.carlsberg.app.bean.visit;

import android.text.TextUtils;

import com.chad.library.adapter.base.entity.MultiItemEntity;

/**
 * Created by LiesLee on 17/2/28.
 */

public class TaskCollect implements MultiItemEntity {

    /**
     * title : 总共房间数量
     * type : input
     * val : 1
     * data : 1间
     */

    private String data;

    private String title;
    private String id_name;
    private String type;
    private int min;
    private int max;
    private String val;


    /**
     * type=input：输入框
     * type=radio：单选框
     * ype=areatext：文本框
     * @return
     */
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

    public String getVal() {
        return val;
    }

    public void setVal(String val) {
        this.val = val;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getId_name() {
        return id_name;
    }

    public void setId_name(String id_name) {
        this.id_name = id_name;
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

}
