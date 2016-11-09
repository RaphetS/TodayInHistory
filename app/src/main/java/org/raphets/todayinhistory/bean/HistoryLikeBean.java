package org.raphets.todayinhistory.bean;

import io.realm.RealmObject;

/**
 *  收藏数据
 */

public class HistoryLikeBean extends RealmObject {
    private String eId;
    private String title;
    private String img;
    private String date;

    public String geteId() {
        return eId;
    }

    public void seteId(String eId) {
        this.eId = eId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
