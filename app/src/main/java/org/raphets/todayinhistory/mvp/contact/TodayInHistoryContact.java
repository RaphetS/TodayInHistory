package org.raphets.todayinhistory.mvp.contact;

import org.raphets.todayinhistory.base.BasePresent;
import org.raphets.todayinhistory.bean.SimpleHistory;

import java.util.List;

/**
 * Created by RaphetS on 2016/10/15.
 */

public class TodayInHistoryContact {
    public interface View {
        void showProgressDialog();

        void dismissProgressDialog();

        void showContent(List<SimpleHistory> result);

        void showFail(String error);
    }

    public interface Present extends BasePresent{
        void getData(int month, int day);
        //void getCurrentDate();
    }
}
