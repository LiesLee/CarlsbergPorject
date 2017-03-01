package com.carlsberg.app.bean.visit;

import com.carlsberg.app.module.visit.ui.adapter.DynamicImgsAdapter;
import com.carlsberg.app.module.visit.ui.adapter.PublishDynamicImgsAdapter;

import java.util.List;

/**
 * Created by LiesLee on 17/2/28.
 */

public class PhotoListBean {

    /**
     * photo_type : 1
     * photo_title : 拍摄门头照
     * lists : [{"image_id":"7","image_url":"http://app.zm.cn/uploads/20160517/14634184803.jpg"},{"image_id":"8","image_url":"http://app.zm.cn/uploads/20160517/14634184804.jpg"},{"image_id":"9","image_url":"http://app.zm.cn/uploads/20160517/14634184800.jpg"}]
     */

    private int photo_type;
    private String photo_title;
    private List<Image> lists;
    private PublishDynamicImgsAdapter imgsAdapter;
    private DynamicImgsAdapter imgsAdapter_2;

    public DynamicImgsAdapter getImgsAdapter_2() {
        return imgsAdapter_2;
    }

    public void setImgsAdapter_2(DynamicImgsAdapter imgsAdapter_2) {
        this.imgsAdapter_2 = imgsAdapter_2;
    }

    public PublishDynamicImgsAdapter getImgsAdapter() {
        return imgsAdapter;
    }

    public void setImgsAdapter(PublishDynamicImgsAdapter imgsAdapter) {
        this.imgsAdapter = imgsAdapter;
    }

    public int getPhoto_type() {
        return photo_type;
    }

    public void setPhoto_type(int photo_type) {
        this.photo_type = photo_type;
    }

    public String getPhoto_title() {
        return photo_title;
    }

    public void setPhoto_title(String photo_title) {
        this.photo_title = photo_title;
    }

    public List<Image> getLists() {
        return lists;
    }

    public void setLists(List<Image> lists) {
        this.lists = lists;
    }

    public static class Image {
        /**
         * image_id : 7
         * image_url : http://app.zm.cn/uploads/20160517/14634184803.jpg
         */

        private String image_id;
        private String image_url;

        public String getImage_id() {
            return image_id;
        }

        public void setImage_id(String image_id) {
            this.image_id = image_id;
        }

        public String getImage_url() {
            return image_url;
        }

        public void setImage_url(String image_url) {
            this.image_url = image_url;
        }
    }
}
