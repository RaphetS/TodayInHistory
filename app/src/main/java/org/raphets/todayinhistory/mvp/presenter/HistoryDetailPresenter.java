package org.raphets.todayinhistory.mvp.presenter;

import org.raphets.todayinhistory.base.BasePresenter;
import org.raphets.todayinhistory.bean.Histroy;
import org.raphets.todayinhistory.bean.Picture;
import org.raphets.todayinhistory.http.HttpResponse;
import org.raphets.todayinhistory.mvp.contact.HistoryDetailContract;
import org.raphets.todayinhistory.mvp.model.HistoryDetailModel;

import java.util.List;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by RaphetS on 2016/10/16.
 */

public class HistoryDetailPresenter extends BasePresenter<HistoryDetailContract.Model, HistoryDetailContract.View> {
    public HistoryDetailPresenter(HistoryDetailContract.View view) {
        super(view);
        this.mModel = new HistoryDetailModel();
    }


    public void getHistoryData(String eId) {
        getmModel().getHistoryData(eId)
                    .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<HttpResponse<Histroy<Picture>>>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (mView!=null) {
                            getmView().onFailed("获取数据失败");
                        }
                    }

                    @Override
                    public void onNext(HttpResponse<Histroy<Picture>> httpResponse) {
                        if (mView != null) {
                            if (httpResponse.getError_code() == 0) {
                                List<Histroy<Picture>> histroyList = httpResponse.getResult();
                                if (histroyList.size() > 0) {
                                    mView.showData(histroyList.get(0));
                                } else {
                                    mView.onFailed("暂无数据");
                                }
                            } else {
                                mView.onFailed(httpResponse.getReason());
                            }


                        }
                    }
                });
    }

    @Override
    public void detachView() {
        mView = null;
    }
}
