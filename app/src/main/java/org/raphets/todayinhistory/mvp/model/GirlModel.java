package org.raphets.todayinhistory.mvp.model;

import org.raphets.todayinhistory.bean.GirlBean;
import org.raphets.todayinhistory.common.Constants;
import org.raphets.todayinhistory.http.GrilHttppResponse;
import org.raphets.todayinhistory.http.RetrofitHelper;
import org.raphets.todayinhistory.mvp.contact.GirlContract;

import java.util.List;

import rx.Observable;

/**
 * Author:   Tao.ZT.Zhang
 * Date:     2017/3/10.
 */

public class GirlModel implements GirlContract.Modle {

    @Override
    public Observable<GrilHttppResponse<List<GirlBean>>> getGirlList(int pageIndex, int sizePerPage) {

        return RetrofitHelper.getInstance()
                .getGrilList(pageIndex,sizePerPage);
    }

    @Override
    public Observable<GrilHttppResponse<List<GirlBean>>> getMoreGirl(int pageIndex, int sizePerPage) {
        return RetrofitHelper.getInstance()
                .getGrilList(pageIndex, sizePerPage);
    }
}
