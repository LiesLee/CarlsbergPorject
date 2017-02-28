package com.carlsberg.app.bean.visit;

/**
 * Created by LiesLee on 17/2/28.
 */

public class Visit {
    /**
     * store_id : 8
     * store_name : 小红商店
     * area_title :
     * region_title :
     * flag_plan : 1
     * flag_plan_title : 内
     * task_id : 0
     * plan_id : 62
     * plan_asort : 2
     * task_status : 0
     * task_status_title : 未拜访
     */

    private String store_id;
    private String store_name;
    private String area_title;
    private String region_title;
    private int flag_plan;
    private String flag_plan_title;
    private int task_id;
    private int plan_id;
    private int plan_asort;
    private int task_status;
    private String task_status_title;

    public String getStore_id() {
        return store_id;
    }

    public void setStore_id(String store_id) {
        this.store_id = store_id;
    }

    public String getStore_name() {
        return store_name;
    }

    public void setStore_name(String store_name) {
        this.store_name = store_name;
    }

    public String getArea_title() {
        return area_title;
    }

    public void setArea_title(String area_title) {
        this.area_title = area_title;
    }

    public String getRegion_title() {
        return region_title;
    }

    public void setRegion_title(String region_title) {
        this.region_title = region_title;
    }

    public int getFlag_plan() {
        return flag_plan;
    }

    public void setFlag_plan(int flag_plan) {
        this.flag_plan = flag_plan;
    }

    public String getFlag_plan_title() {
        return flag_plan_title;
    }

    public void setFlag_plan_title(String flag_plan_title) {
        this.flag_plan_title = flag_plan_title;
    }

    public int getTask_id() {
        return task_id;
    }

    public void setTask_id(int task_id) {
        this.task_id = task_id;
    }

    public int getPlan_id() {
        return plan_id;
    }

    public void setPlan_id(int plan_id) {
        this.plan_id = plan_id;
    }

    public int getPlan_asort() {
        return plan_asort;
    }

    public void setPlan_asort(int plan_asort) {
        this.plan_asort = plan_asort;
    }

    public int getTask_status() {
        return task_status;
    }

    public void setTask_status(int task_status) {
        this.task_status = task_status;
    }

    public String getTask_status_title() {
        return task_status_title;
    }

    public void setTask_status_title(String task_status_title) {
        this.task_status_title = task_status_title;
    }
}
