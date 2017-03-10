package org.raphets.todayinhistory.mvp.model;

import org.raphets.todayinhistory.bean.SimpleHistory;
import org.raphets.todayinhistory.http.HttpResponse;
import org.raphets.todayinhistory.http.RetrofitHelper;
import org.raphets.todayinhistory.mvp.contact.TodayInHistoryContract;

import rx.Observable;

/**
 * Author:   Tao.ZT.Zhang
 * Date:     2017/3/10.
 */

public class TodayInHistoryModel implements TodayInHistoryContract.Model {
    @Override
    public Observable<HttpResponse<SimpleHistory>> getData(int month, int day) {
        return  RetrofitHelper.getInstance()
                .getHistoryList(month,day);
    }
}
