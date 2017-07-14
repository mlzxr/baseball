package com.feigong.baseball.beans;

import java.util.List;

/**
 * Created by ruler on 17/7/12.
 */

public class ListImage {


    /**
     * img_urls : ["http://i2.letvimg.com/lc04_sms/201612/20/11/41/74a89fd8-b184-4de9-9075-e63db44c5a7e.jpg","http://i1.letvimg.com/lc04_sms/201612/20/11/41/17919ef5-0e60-4796-baa0-2d83abb35e78.jpg","http://i1.letvimg.com/lc04_sms/201612/20/11/42/1674799a-1af6-46d4-8f37-1a068b9225cf.jpg","http://i0.letvimg.com/lc04_sms/201612/20/11/42/306fbf15-e58f-4e0e-96fb-b8c7fa1fcd0d.jpg"]
     * select_index : 0
     */

    private int select_index;
    private List<String> img_urls;

    public int getSelect_index() {
        return select_index;
    }

    public void setSelect_index(int select_index) {
        this.select_index = select_index;
    }

    public List<String> getImg_urls() {
        return img_urls;
    }

    public void setImg_urls(List<String> img_urls) {
        this.img_urls = img_urls;
    }
}
