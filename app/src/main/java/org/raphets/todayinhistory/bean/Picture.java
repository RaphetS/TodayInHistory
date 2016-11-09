package org.raphets.todayinhistory.bean;

/**
 *    图片
 */

public class Picture {
    private int id;
    private String pic_title;
    private String url;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPic_title() {
        return pic_title;
    }

    public void setPic_title(String pic_title) {
        this.pic_title = pic_title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
