package org.raphets.todayinhistory.mvp.model;

import org.raphets.todayinhistory.bean.Histroy;
import org.raphets.todayinhistory.bean.Picture;
import org.raphets.todayinhistory.http.HttpResponse;
import org.raphets.todayinhistory.http.RetrofitHelper;
import org.raphets.todayinhistory.mvp.contact.HistoryDetailContract;

import rx.Observable;


/**
 * Author:   Tao.ZT.Zhang
 * Date:     2017/3/10.
 */

public class HistoryDetailModel implements HistoryDetailContract.Model {

    public HistoryDetailModel(){

    }
    @Override
    public Observable<HttpResponse<Histroy<Picture>>> getHistoryData(String eId) {
        return RetrofitHelper.getInstance().getHistoryDetail(eId);
    }
}
