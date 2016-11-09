package org.raphets.todayinhistory.mvp.contact;

import org.raphets.todayinhistory.base.BasePresent;
import org.raphets.todayinhistory.base.BaseView;
import org.raphets.todayinhistory.bean.Histroy;
import org.raphets.todayinhistory.bean.Picture;

/**
 * Created by RaphetS on 2016/10/16.
 */

public class HistoryDetailContact {
    public interface View extends BaseView {
       void showData(Histroy<Picture> result);
    }

    public interface Present extends BasePresent {
        void getHistoryData(String eId);
    }
}
