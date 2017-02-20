package com.carlsberg.app.bean.visit;

/**
 * Created by rrsh on 16/12/28.
 * 首页 店铺类别列表 区域实体
 */

public class RegionInfo {

    public RegionInfo() {
    }

    public RegionInfo(String name, String type) {
        this.name = name;
        this.type = type;
    }

    /**
     * region_id : 0
     * region_name : 附近
     */

    private String type;
    private String name;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
