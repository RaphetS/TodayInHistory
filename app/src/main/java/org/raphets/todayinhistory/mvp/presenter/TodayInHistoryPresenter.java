package org.raphets.todayinhistory.mvp.presenter;

import org.raphets.todayinhistory.base.BasePresenter;
import org.raphets.todayinhistory.bean.SimpleHistory;
import org.raphets.todayinhistory.http.HttpResponse;
import org.raphets.todayinhistory.mvp.contact.TodayInHistoryContract;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by RaphetS on 2016/10/15.
 */

public class TodayInHistoryPresenter extends BasePresenter<TodayInHistoryContract.Model, TodayInHistoryContract.View> {

    public TodayInHistoryPresenter(TodayInHistoryContract.Model model, TodayInHistoryContract.View view) {
        super(model,view);
    }


    public void getData(int month, int day) {
      getmModel().getData(month,day)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<HttpResponse<SimpleHistory>>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (mView!=null) {
                            getmView().showFail(e.toString());
                        }
                    }

                    @Override
                    public void onNext(HttpResponse<SimpleHistory> httpResponse) {
                        if (mView!=null){
                             if (httpResponse.getError_code()==0){
                                 getmView().showContent(httpResponse.getResult());
                             }else {
                                 getmView().showFail(httpResponse.getReason());
                             }
                        }
                    }
                });
    }

    @Override
    public void detachView() {
        mView=null;
    }
}
