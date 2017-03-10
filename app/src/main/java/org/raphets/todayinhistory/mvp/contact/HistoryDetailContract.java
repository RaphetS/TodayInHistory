package org.raphets.todayinhistory.mvp.contact;


import org.raphets.todayinhistory.base.IModel;
import org.raphets.todayinhistory.base.IView;
import org.raphets.todayinhistory.bean.Histroy;
import org.raphets.todayinhistory.bean.Picture;
import org.raphets.todayinhistory.http.HttpResponse;

import rx.Observable;


/**
 * Created by RaphetS on 2016/10/16.
 */

public class HistoryDetailContract {
    public interface View extends IView {
       void showData(Histroy<Picture> result);
        void onFailed(String message);
    }

    public interface Model extends IModel {
        Observable<HttpResponse<Histroy<Picture>>> getHistoryData(String eId);
    }
}
