package com.carlsberg.app.bean.visit;

import java.util.List;

/**
 * Created by LiesLee on 17/2/28.
 */

public class VisitStoreResponse {

    /**
     * store_task : {"store_id":"18","store_name":"18号测试店","store_addr":"评细地址评细地址评细地址a","store_type":"2","store_nature":"3","area_title":"广州市区","region_title":"天河区","type_title":"混场","nature_title":"普通洒吧","flag_plan":0,"flag_plan_title":"外","user_id":"1","plan_id":0,"task_id":0,"checkin_date":"-","goaway_date":"-","task_status":0,"task_status_title":"未拜访"}
     * task_button : [{"button_type":"checkin","button_title":"入店签到","is_open":1},{"button_type":"collect","button_title":"现场数据","is_open":0},{"button_type":"photo","button_title":"拍照上传","is_open":0},{"button_type":"goaway","button_title":"离店签到","is_open":0}]
     * task_photo : []
     * task_collect : []
     * task_score : []
     */

    private StoreTask store_task;
    private List<TaskButton> task_button;
    private List<PhotoListBean> task_photo;
    private List<TaskCollect> task_collect;
    private List<TaskScore> task_score;

    public StoreTask getStore_task() {
        return store_task;
    }

    public void setStore_task(StoreTask store_task) {
        this.store_task = store_task;
    }

    public List<TaskButton> getTask_button() {
        return task_button;
    }

    public void setTask_button(List<TaskButton> task_button) {
        this.task_button = task_button;
    }

    public List<PhotoListBean> getTask_photo() {
        return task_photo;
    }

    public void setTask_photo(List<PhotoListBean> task_photo) {
        this.task_photo = task_photo;
    }

    public List<TaskCollect> getTask_collect() {
        return task_collect;
    }

    public void setTask_collect(List<TaskCollect> task_collect) {
        this.task_collect = task_collect;
    }

    public List<TaskScore> getTask_score() {
        return task_score;
    }

    public void setTask_score(List<TaskScore> task_score) {
        this.task_score = task_score;
    }

    public static class StoreTask {
        /**
         * store_id : 18
         * store_name : 18号测试店
         * store_addr : 评细地址评细地址评细地址a
         * store_type : 2
         * store_nature : 3
         * area_title : 广州市区
         * region_title : 天河区
         * type_title : 混场
         * nature_title : 普通洒吧
         * flag_plan : 0
         * flag_plan_title : 外
         * user_id : 1
         * plan_id : 0
         * task_id : 0
         * checkin_date : -
         * goaway_date : -
         * task_status : 0
         * task_status_title : 未拜访
         */

        private String store_id;
        private String store_name;
        private String store_addr;
        private String store_type;
        private String store_nature;
        private String area_title;
        private String region_title;
        private String type_title;
        private String nature_title;
        private int flag_plan;
        private String flag_plan_title;
        private String user_id;
        private int plan_id;
        private int task_id;
        private String checkin_date;
        private String goaway_date;
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

        public String getStore_addr() {
            return store_addr;
        }

        public void setStore_addr(String store_addr) {
            this.store_addr = store_addr;
        }

        public String getStore_type() {
            return store_type;
        }

        public void setStore_type(String store_type) {
            this.store_type = store_type;
        }

        public String getStore_nature() {
            return store_nature;
        }

        public void setStore_nature(String store_nature) {
            this.store_nature = store_nature;
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

        public String getType_title() {
            return type_title;
        }

        public void setType_title(String type_title) {
            this.type_title = type_title;
        }

        public String getNature_title() {
            return nature_title;
        }

        public void setNature_title(String nature_title) {
            this.nature_title = nature_title;
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

        public String getUser_id() {
            return user_id;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
        }

        public int getPlan_id() {
            return plan_id;
        }

        public void setPlan_id(int plan_id) {
            this.plan_id = plan_id;
        }

        public int getTask_id() {
            return task_id;
        }

        public void setTask_id(int task_id) {
            this.task_id = task_id;
        }

        public String getCheckin_date() {
            return checkin_date;
        }

        public void setCheckin_date(String checkin_date) {
            this.checkin_date = checkin_date;
        }

        public String getGoaway_date() {
            return goaway_date;
        }

        public void setGoaway_date(String goaway_date) {
            this.goaway_date = goaway_date;
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

    public static class TaskButton {
        /**
         * button_type : checkin
         * button_title : 入店签到
         * is_open : 1
         */

        private String button_type;
        private String button_title;
        private int is_open;

        public String getButton_type() {
            return button_type;
        }

        public void setButton_type(String button_type) {
            this.button_type = button_type;
        }

        public String getButton_title() {
            return button_title;
        }

        public void setButton_title(String button_title) {
            this.button_title = button_title;
        }

        public int getIs_open() {
            return is_open;
        }

        public void setIs_open(int is_open) {
            this.is_open = is_open;
        }
    }
}
