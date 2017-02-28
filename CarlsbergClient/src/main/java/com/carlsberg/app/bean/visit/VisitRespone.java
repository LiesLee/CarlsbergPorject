package com.carlsberg.app.bean.visit;

import java.util.List;

/**
 * Created by LiesLee on 17/2/28.
 */

public class VisitRespone {

    /**
     * page : 1
     * count : 10
     * total_record : 17
     * total_page : 2
     * lists : [{"store_id":"8","store_name":"小红商店","area_title":"","region_title":"","flag_plan":0,"flag_plan_title":"外","task_id":0,"plan_id":0,"plan_asort":0,"task_status":0,"task_status_title":"未拜访"},{"store_id":"9","store_name":"111","area_title":"","region_title":"","flag_plan":0,"flag_plan_title":"外","task_id":0,"plan_id":0,"plan_asort":0,"task_status":0,"task_status_title":"未拜访"},{"store_id":"10","store_name":"王叔商店","area_title":"","region_title":"","flag_plan":0,"flag_plan_title":"外","task_id":0,"plan_id":0,"plan_asort":0,"task_status":0,"task_status_title":"未拜访"},{"store_id":"11","store_name":"1号店","area_title":"","region_title":"","flag_plan":0,"flag_plan_title":"外","task_id":0,"plan_id":0,"plan_asort":0,"task_status":0,"task_status_title":"未拜访"},{"store_id":"12","store_name":"2号店","area_title":"","region_title":"","flag_plan":0,"flag_plan_title":"外","task_id":0,"plan_id":0,"plan_asort":0,"task_status":0,"task_status_title":"未拜访"},{"store_id":"13","store_name":"3号店","area_title":"","region_title":"","flag_plan":0,"flag_plan_title":"外","task_id":0,"plan_id":0,"plan_asort":0,"task_status":0,"task_status_title":"未拜访"},{"store_id":"14","store_name":"A1","area_title":"","region_title":"","flag_plan":0,"flag_plan_title":"外","task_id":0,"plan_id":0,"plan_asort":0,"task_status":0,"task_status_title":"未拜访"},{"store_id":"15","store_name":"15号测试店","area_title":"","region_title":"","flag_plan":0,"flag_plan_title":"外","task_id":0,"plan_id":0,"plan_asort":0,"task_status":0,"task_status_title":"未拜访"},{"store_id":"16","store_name":"16号测试店","area_title":"","region_title":"","flag_plan":0,"flag_plan_title":"外","task_id":0,"plan_id":0,"plan_asort":0,"task_status":0,"task_status_title":"未拜访"},{"store_id":"18","store_name":"18号测试店","area_title":"广州市区","region_title":"天河区","flag_plan":0,"flag_plan_title":"外","task_id":0,"plan_id":0,"plan_asort":0,"task_status":0,"task_status_title":"未拜访"}]
     * status_type : [{"status_type":0,"status_title":"全部"},{"status_type":1,"status_title":"未拜访"},{"status_type":2,"status_title":"拜访中"},{"status_type":3,"status_title":"己完成"},{"status_type":4,"status_title":"己审核"}]
     */

    private int page;
    private int count;
    private int total_record;
    private int total_page;
    private List<Visit> lists;
    private List<StatusType> status_type;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getTotal_record() {
        return total_record;
    }

    public void setTotal_record(int total_record) {
        this.total_record = total_record;
    }

    public int getTotal_page() {
        return total_page;
    }

    public void setTotal_page(int total_page) {
        this.total_page = total_page;
    }

    public List<Visit> getLists() {
        return lists;
    }

    public void setLists(List<Visit> lists) {
        this.lists = lists;
    }

    public List<StatusType> getStatus_type() {
        return status_type;
    }

    public void setStatus_type(List<StatusType> status_type) {
        this.status_type = status_type;
    }



    public static class StatusType {
        /**
         * status_type : 0
         * status_title : 全部
         */

        private int status_type;
        private String status_title;

        public int getStatus_type() {
            return status_type;
        }

        public void setStatus_type(int status_type) {
            this.status_type = status_type;
        }

        public String getStatus_title() {
            return status_title;
        }

        public void setStatus_title(String status_title) {
            this.status_title = status_title;
        }
    }
}
