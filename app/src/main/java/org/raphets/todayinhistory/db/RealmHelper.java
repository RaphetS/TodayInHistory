package org.raphets.todayinhistory.db;

import android.content.Context;

import org.raphets.todayinhistory.bean.HistoryLikeBean;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;

/**
 * Created by codeest on 16/8/16.
 */

public class RealmHelper {

    private static final String DB_NAME = "myRealm.realm";

    private Realm mRealm;

    public RealmHelper(Context mContext) {
        mRealm = Realm.getInstance(new RealmConfiguration.Builder(mContext)
                .deleteRealmIfMigrationNeeded()
                .name(DB_NAME)
                .build());
    }

    /**
     * 添加收藏
     */
    public void insertHistoryLike(HistoryLikeBean bean) {
        mRealm.beginTransaction();
        mRealm.copyToRealm(bean);
        mRealm.commitTransaction();
    }

    /**
     * 删除收藏
     */
    public void deleteHistoryLike(String eId) {
        HistoryLikeBean bean = mRealm.where(HistoryLikeBean.class).equalTo("eId", eId).findFirst();
        mRealm.beginTransaction();
        bean.deleteFromRealm();
        mRealm.commitTransaction();
    }

    /**
     * 查询所有收藏
     */
    public List<HistoryLikeBean> queryAllHistoryLike() {
        RealmResults<HistoryLikeBean> likeList = mRealm.where(HistoryLikeBean.class).findAll();
        return mRealm.copyFromRealm(likeList);
    }

    /**
     * 查询收藏
     */
    public boolean queryHistoryLike(String eId) {
        RealmResults<HistoryLikeBean> results = mRealm.where(HistoryLikeBean.class).equalTo("eId", eId).findAll();
        if (results.size() == 0) {
            return false;
        } else {
            return true;
        }
    }

}
