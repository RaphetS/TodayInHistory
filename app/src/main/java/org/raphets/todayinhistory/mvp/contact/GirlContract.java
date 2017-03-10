package org.raphets.todayinhistory.mvp.contact;

import org.raphets.todayinhistory.base.IModel;
import org.raphets.todayinhistory.base.IView;
import org.raphets.todayinhistory.bean.GirlBean;
import org.raphets.todayinhistory.http.GrilHttppResponse;

import java.util.List;

import rx.Observable;

/**
 * Created by RaphetS on 2016/10/19.
 */

public class GirlContract {
    public interface View extends IView {
        void showContent(List<GirlBean> data);

        void showMoreGirl(List<GirlBean> data);
        void showFail(String message);
    }

    public interface Modle extends IModel {
        Observable<GrilHttppResponse<List<GirlBean>>> getGirlList();

        Observable<GrilHttppResponse<List<GirlBean>>> getMoreGirl();
    }
}
