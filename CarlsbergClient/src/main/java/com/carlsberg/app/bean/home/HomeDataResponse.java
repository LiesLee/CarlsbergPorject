package com.carlsberg.app.bean.home;

import java.util.List;

/**
 * Created by LiesLee on 17/2/27.
 */

public class HomeDataResponse {

    /**
     * user_info : {"user_id":"1","mobile":"15915866779","nick_name":"测试","email":"15915866779@qq.com","role_id":"1","role_name":"管理员","manage_id":"0","manage_nick_name":"","manage_role_name":"","login_num":"90","login_time":"2017-02-27 11:38:39"}
     * recent_plan : {"page":1,"count":10,"total_record":17,"total_page":2,"lists":[{"store_id":"8","store_name":"小红商店","area_title":"","region_title":"","flag_plan":1,"flag_plan_title":"内","task_id":0,"plan_id":62,"plan_asort":2,"task_status":0,"task_status_title":"未拜访"},{"store_id":"9","store_name":"111","area_title":"","region_title":"","flag_plan":1,"flag_plan_title":"内","task_id":0,"plan_id":63,"plan_asort":2,"task_status":0,"task_status_title":"未拜访"},{"store_id":"10","store_name":"王叔商店","area_title":"","region_title":"","flag_plan":1,"flag_plan_title":"内","task_id":0,"plan_id":64,"plan_asort":2,"task_status":0,"task_status_title":"未拜访"},{"store_id":"11","store_name":"1号店","area_title":"","region_title":"","flag_plan":1,"flag_plan_title":"内","task_id":0,"plan_id":65,"plan_asort":2,"task_status":0,"task_status_title":"未拜访"},{"store_id":"12","store_name":"2号店","area_title":"","region_title":"","flag_plan":1,"flag_plan_title":"内","task_id":0,"plan_id":66,"plan_asort":2,"task_status":0,"task_status_title":"未拜访"},{"store_id":"13","store_name":"3号店","area_title":"","region_title":"","flag_plan":1,"flag_plan_title":"内","task_id":0,"plan_id":67,"plan_asort":2,"task_status":0,"task_status_title":"未拜访"},{"store_id":"14","store_name":"A1","area_title":"","region_title":"","flag_plan":1,"flag_plan_title":"内","task_id":0,"plan_id":68,"plan_asort":2,"task_status":0,"task_status_title":"未拜访"},{"store_id":"15","store_name":"15号测试店","area_title":"","region_title":"","flag_plan":1,"flag_plan_title":"内","task_id":0,"plan_id":69,"plan_asort":2,"task_status":0,"task_status_title":"未拜访"},{"store_id":"16","store_name":"16号测试店","area_title":"","region_title":"","flag_plan":1,"flag_plan_title":"内","task_id":0,"plan_id":70,"plan_asort":2,"task_status":0,"task_status_title":"未拜访"},{"store_id":"18","store_name":"18号测试店","area_title":"广州市区","region_title":"天河区","flag_plan":1,"flag_plan_title":"内","task_id":0,"plan_id":72,"plan_asort":2,"task_status":0,"task_status_title":"未拜访"}]}
     */

    private UserInfo user_info;
    private RecentPlan recent_plan;

    public UserInfo getUser_info() {
        return user_info;
    }

    public void setUser_info(UserInfo user_info) {
        this.user_info = user_info;
    }

    public RecentPlan getRecent_plan() {
        return recent_plan;
    }

    public void setRecent_plan(RecentPlan recent_plan) {
        this.recent_plan = recent_plan;
    }

    public static class UserInfo {
        /**
         * user_id : 1
         * mobile : 15915866779
         * nick_name : 测试
         * email : 15915866779@qq.com
         * role_id : 1
         * role_name : 管理员
         * manage_id : 0
         * manage_nick_name :
         * manage_role_name :
         * login_num : 90
         * login_time : 2017-02-27 11:38:39
         */

        private String user_id;
        private String mobile;
        private String nick_name;
        private String email;
        private int role_id;
        private String role_name;
        private int manage_id;
        private String manage_nick_name;
        private String manage_role_name;
        private String login_num;
        private String login_time;

        public String getUser_id() {
            return user_id;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getNick_name() {
            return nick_name;
        }

        public void setNick_name(String nick_name) {
            this.nick_name = nick_name;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public int getRole_id() {
            return role_id;
        }

        public void setRole_id(int role_id) {
            this.role_id = role_id;
        }

        public String getRole_name() {
            return role_name;
        }

        public void setRole_name(String role_name) {
            this.role_name = role_name;
        }

        public int getManage_id() {
            return manage_id;
        }

        public void setManage_id(int manage_id) {
            this.manage_id = manage_id;
        }

        public String getManage_nick_name() {
            return manage_nick_name;
        }

        public void setManage_nick_name(String manage_nick_name) {
            this.manage_nick_name = manage_nick_name;
        }

        public String getManage_role_name() {
            return manage_role_name;
        }

        public void setManage_role_name(String manage_role_name) {
            this.manage_role_name = manage_role_name;
        }

        public String getLogin_num() {
            return login_num;
        }

        public void setLogin_num(String login_num) {
            this.login_num = login_num;
        }

        public String getLogin_time() {
            return login_time;
        }

        public void setLogin_time(String login_time) {
            this.login_time = login_time;
        }
    }

    public static class RecentPlan {
        /**
         * page : 1
         * count : 10
         * total_record : 17
         * total_page : 2
         * lists : [{"store_id":"8","store_name":"小红商店","area_title":"","region_title":"","flag_plan":1,"flag_plan_title":"内","task_id":0,"plan_id":62,"plan_asort":2,"task_status":0,"task_status_title":"未拜访"},{"store_id":"9","store_name":"111","area_title":"","region_title":"","flag_plan":1,"flag_plan_title":"内","task_id":0,"plan_id":63,"plan_asort":2,"task_status":0,"task_status_title":"未拜访"},{"store_id":"10","store_name":"王叔商店","area_title":"","region_title":"","flag_plan":1,"flag_plan_title":"内","task_id":0,"plan_id":64,"plan_asort":2,"task_status":0,"task_status_title":"未拜访"},{"store_id":"11","store_name":"1号店","area_title":"","region_title":"","flag_plan":1,"flag_plan_title":"内","task_id":0,"plan_id":65,"plan_asort":2,"task_status":0,"task_status_title":"未拜访"},{"store_id":"12","store_name":"2号店","area_title":"","region_title":"","flag_plan":1,"flag_plan_title":"内","task_id":0,"plan_id":66,"plan_asort":2,"task_status":0,"task_status_title":"未拜访"},{"store_id":"13","store_name":"3号店","area_title":"","region_title":"","flag_plan":1,"flag_plan_title":"内","task_id":0,"plan_id":67,"plan_asort":2,"task_status":0,"task_status_title":"未拜访"},{"store_id":"14","store_name":"A1","area_title":"","region_title":"","flag_plan":1,"flag_plan_title":"内","task_id":0,"plan_id":68,"plan_asort":2,"task_status":0,"task_status_title":"未拜访"},{"store_id":"15","store_name":"15号测试店","area_title":"","region_title":"","flag_plan":1,"flag_plan_title":"内","task_id":0,"plan_id":69,"plan_asort":2,"task_status":0,"task_status_title":"未拜访"},{"store_id":"16","store_name":"16号测试店","area_title":"","region_title":"","flag_plan":1,"flag_plan_title":"内","task_id":0,"plan_id":70,"plan_asort":2,"task_status":0,"task_status_title":"未拜访"},{"store_id":"18","store_name":"18号测试店","area_title":"广州市区","region_title":"天河区","flag_plan":1,"flag_plan_title":"内","task_id":0,"plan_id":72,"plan_asort":2,"task_status":0,"task_status_title":"未拜访"}]
         */

        private int page;
        private int count;
        private int total_record;
        private int total_page;
        private List<Visit> lists;

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

        public static class Visit {
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
    }
}
