package org.raphets.todayinhistory.common;

import android.support.v7.util.DiffUtil;
import android.text.TextUtils;

import org.raphets.todayinhistory.bean.HistoryLikeBean;

import java.util.List;

/**
 * Created by RaphetS on 2016/10/22.
 */

public class LikeCallBack extends DiffUtil.Callback {
    private List<HistoryLikeBean> oldDatas, newDatas;

    public LikeCallBack(List<HistoryLikeBean> oldDatas, List<HistoryLikeBean> newDatas) {
        this.oldDatas = oldDatas;
        this.newDatas = newDatas;
    }

    @Override
    public int getOldListSize() {
        return oldDatas.size();
    }

    @Override
    public int getNewListSize() {
        return newDatas.size();
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        return oldDatas.get(oldItemPosition).geteId().equals(newDatas.get(newItemPosition).geteId());
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        boolean b1 = true, b2 = true;
        if (!TextUtils.isEmpty(oldDatas.get(oldItemPosition).getTitle())) {
            b1 = oldDatas.get(oldItemPosition).getTitle().equals(newDatas.get(newItemPosition).getTitle());
        }
        if (!TextUtils.isEmpty(oldDatas.get(oldItemPosition).getImg())) {
            b2 = oldDatas.get(oldItemPosition).getImg().equals(newDatas.get(newItemPosition).getImg());
        }
        return b1 && b2;
    }
}
