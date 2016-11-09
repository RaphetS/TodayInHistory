package org.raphets.todayinhistory.mvp.presenter;

import org.raphets.todayinhistory.bean.GrilBean;
import org.raphets.todayinhistory.common.Constants;
import org.raphets.todayinhistory.http.GrilHttppResponse;
import org.raphets.todayinhistory.http.RetrofitHelper;
import org.raphets.todayinhistory.mvp.contact.GrilContact;

import java.util.List;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by RaphetS on 2016/10/19.
 */

public class GrilPresenter implements GrilContact.Present {
    private GrilContact.View mView;
    private int currentPage=1;
    public GrilPresenter(GrilContact.View mView) {
        this.mView = mView;
    }

    @Override
    public void getGrilList() {
        currentPage=1;
        RetrofitHelper.getInstance()
                .getGrilList(currentPage, Constants.NUM_PAGE)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<GrilHttppResponse<List<GrilBean>>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        if (mView!=null) {
                            mView.showFail("获取数据失败");
                        }
                    }

                    @Override
                    public void onNext(GrilHttppResponse<List<GrilBean>> httppResponse) {
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

    @Override
    public void getMoreGril() {
        currentPage++;
        RetrofitHelper.getInstance()
                .getGrilList(currentPage, Constants.NUM_PAGE)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<GrilHttppResponse<List<GrilBean>>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        mView.showFail(e.toString());
                    }

                    @Override
                    public void onNext(GrilHttppResponse<List<GrilBean>> httppResponse) {
                        if (mView!=null) {
                            mView.showMoreGril(httppResponse.getResults());
                        }
                    }
                });
    }

    @Override
    public void detachView() {
        mView=null;
    }
}
