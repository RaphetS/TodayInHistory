package org.raphets.todayinhistory.mvp.presenter;

import org.raphets.todayinhistory.base.BasePresenter;
import org.raphets.todayinhistory.bean.GirlBean;
import org.raphets.todayinhistory.http.GrilHttppResponse;
import org.raphets.todayinhistory.mvp.contact.GirlContract;
import org.raphets.todayinhistory.mvp.model.GirlModel;

import java.util.List;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by RaphetS on 2016/10/19.
 */

public class GirlPresenter extends BasePresenter<GirlContract.Modle, GirlContract.View> {

    public GirlPresenter(GirlContract.View view) {
        super(view);
        this.mModel = new GirlModel();
    }


    public void getGrilList(int pageIndex, int sizePerPage) {
            getmModel().getGirlList(pageIndex, sizePerPage)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<GrilHttppResponse<List<GirlBean>>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        if (mView!=null) {
                            getmView().showFail("获取数据失败");
                        }
                    }

                    @Override
                    public void onNext(GrilHttppResponse<List<GirlBean>> httppResponse) {
                        if (mView!=null) {
                            if (!httppResponse.getError()) {

                                if (httppResponse.getResults()!=null&&httppResponse.getResults().size()>0) {
                                    mView.showContent(httppResponse.getResults());
                                }else {
                                    mView.showFail("暂无数据");
                                }
                            }else {
                                mView.showFail("获取数据失败");
                            }
                        }
                    }
                });
    }


    public void getMoreGril(int pageIndex, int sizePerPage) {
       getmModel().getMoreGirl(pageIndex, sizePerPage)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<GrilHttppResponse<List<GirlBean>>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        mView.showFail(e.toString());
                    }

                    @Override
                    public void onNext(GrilHttppResponse<List<GirlBean>> httppResponse) {
                        if (mView!=null) {
                            getmView().showMoreGirl(httppResponse.getResults());
                        }
                    }
                });
    }

    @Override
    public void detachView() {
        mView=null;
    }
}
