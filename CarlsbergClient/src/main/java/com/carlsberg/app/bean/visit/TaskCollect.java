package com.carlsberg.app.bean.visit;

/**
 * Created by LiesLee on 17/2/28.
 */

public class TaskCollect {

    /**
     * title : 总共房间数量
     * type : input
     * val : 1
     * data : 1间
     */

    private String title;
    private String type;
    private String val;
    private String data;

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
}
