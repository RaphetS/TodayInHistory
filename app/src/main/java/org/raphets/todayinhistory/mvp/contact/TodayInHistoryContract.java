package org.raphets.todayinhistory.mvp.contact;

import org.raphets.todayinhistory.base.IModel;
import org.raphets.todayinhistory.base.IView;
import org.raphets.todayinhistory.bean.SimpleHistory;
import org.raphets.todayinhistory.http.HttpResponse;

import java.util.List;

import rx.Observable;

/**
 * Created by RaphetS on 2016/10/15.
 */

public class TodayInHistoryContract {
    public interface View extends IView {
        void showProgressDialog();

        void dismissProgressDialog();

        void showContent(List<SimpleHistory> result);

        void showFail(String error);
    }

    public interface Model extends IModel{
        Observable<HttpResponse<SimpleHistory>> getData(int month, int day);
        //void getCurrentDate();
    }
}
