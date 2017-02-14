package com.carlsberg.app.bean.common;

/**
 * Created by LiesLee on 16/10/17.
 */
public class User {

    /**
     * avatar : http://static.test.vip-time.cn:83/app/avatar.gif
     * hide_wallet : 0
     * merchant_id : 26
     * merchant_level : 0
     * merchant_type : 1
     * mobile : 138****8880
     * mobile_encode : OMSU7t8DrPmT1wsi1clogA036C7d2quWb7d3
     * nickname : 438
     * status : 1
     * store_id : 1
     */

    private Info info;
    /**
     * life_time : 1483608389
     * login_encode : MjZfOThhNWUzYTFkM2UxYzQ3ZWNiZmMwNGVmMDJlMWFhMjM=
     */

    private LoginEncode login_encode;
    /**
     * info : {"avatar":"http://static.test.vip-time.cn:83/app/avatar.gif","hide_wallet":"0","merchant_id":"26","merchant_level":"0","merchant_type":"1","mobile":"138****8880","mobile_encode":"OMSU7t8DrPmT1wsi1clogA036C7d2quWb7d3","nickname":"438","status":"1","store_id":"1"}
     * login_encode : {"life_time":1483608389,"login_encode":"MjZfOThhNWUzYTFkM2UxYzQ3ZWNiZmMwNGVmMDJlMWFhMjM="}
     * login_time : 2016-12-06 17:26:29
     */

    private String login_time;


    public Info getInfo() {
        return info;
    }

    public void setInfo(Info info) {
        this.info = info;
    }

    public LoginEncode getLogin_encode() {
        return login_encode;
    }

    public void setLogin_encode(LoginEncode login_encode) {
        this.login_encode = login_encode;
    }

    public String getLogin_time() {
        return login_time;
    }

    public void setLogin_time(String login_time) {
        this.login_time = login_time;
    }


    public static class Info {
        private String avatar;
        private int hide_wallet;
        private String merchant_id;
        private int merchant_level;
        private int merchant_type;
        private String mobile;
        private String mobile_encode;
        private String nickname;
        private String store_name;
        private int status;
        private String store_id;

        public String getStore_name() {
            return store_name;
        }

        public void setStore_name(String store_name) {
            this.store_name = store_name;
        }

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        public int getHide_wallet() {
            return hide_wallet;
        }

        public void setHide_wallet(int hide_wallet) {
            this.hide_wallet = hide_wallet;
        }

        public String getMerchant_id() {
            return merchant_id;
        }

        public void setMerchant_id(String merchant_id) {
            this.merchant_id = merchant_id;
        }

        public int getMerchant_level() {
            return merchant_level;
        }

        public void setMerchant_level(int merchant_level) {
            this.merchant_level = merchant_level;
        }

        public int getMerchant_type() {
            return merchant_type;
        }

        public void setMerchant_type(int merchant_type) {
            this.merchant_type = merchant_type;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getMobile_encode() {
            return mobile_encode;
        }

        public void setMobile_encode(String mobile_encode) {
            this.mobile_encode = mobile_encode;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getStore_id() {
            return store_id;
        }

        public void setStore_id(String store_id) {
            this.store_id = store_id;
        }
    }

    public static class LoginEncode {
        private long life_time;
        private String login_encode;

        public long getLife_time() {
            return life_time;
        }

        public void setLife_time(long life_time) {
            this.life_time = life_time;
        }

        public String getLogin_encode() {
            return login_encode;
        }

        public void setLogin_encode(String login_encode) {
            this.login_encode = login_encode;
        }
    }
}
