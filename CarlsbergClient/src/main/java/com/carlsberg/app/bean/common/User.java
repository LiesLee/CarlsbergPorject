package com.carlsberg.app.bean.common;

/**
 * Created by LiesLee on 16/10/17.
 */
public class User {

    /**
     * user_info : {"user_id":"1","mobile":"15915866779","nick_name":"测试","email":"15915866779@qq.com","role_id":"1","role_name":"管理员","manage_id":"0","manage_nick_name":"","manage_role_name":"","login_num":"73","login_time":"2017-02-24 14:53:30"}
     * login_encode : {"login_encode":"hfnMM3nPwPOG/Tu113LAcoS7aahWfHrd6EAB/sJ5dg8yT48CIdsUmOcYSv7E3wT7IYg+v7GutE3ySp0Yj1LFzQfap2ZnG1j2E/fGkIhRPZA=","exp_time":1490511426}
     */

    private UserInfo user_info;
    private LoginEncode login_encode;

    public UserInfo getUser_info() {
        return user_info;
    }

    public void setUser_info(UserInfo user_info) {
        this.user_info = user_info;
    }

    public LoginEncode getLogin_encode() {
        return login_encode;
    }

    public void setLogin_encode(LoginEncode login_encode) {
        this.login_encode = login_encode;
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
         * login_num : 73
         * login_time : 2017-02-24 14:53:30
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

    public static class LoginEncode {
        /**
         * login_encode : hfnMM3nPwPOG/Tu113LAcoS7aahWfHrd6EAB/sJ5dg8yT48CIdsUmOcYSv7E3wT7IYg+v7GutE3ySp0Yj1LFzQfap2ZnG1j2E/fGkIhRPZA=
         * exp_time : 1490511426
         */

        private String login_encode;
        private long exp_time;

        public String getLogin_encode() {
            return login_encode;
        }

        public void setLogin_encode(String login_encode) {
            this.login_encode = login_encode;
        }

        public long getExp_time() {
            return exp_time;
        }

        public void setExp_time(long exp_time) {
            this.exp_time = exp_time;
        }
    }
}
