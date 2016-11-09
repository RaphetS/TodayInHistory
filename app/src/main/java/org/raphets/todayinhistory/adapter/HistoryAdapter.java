package org.raphets.todayinhistory.adapter;

import android.content.Context;

import org.raphets.todayinhistory.R;
import org.raphets.todayinhistory.base.BaseAdapter;
import org.raphets.todayinhistory.base.BaseViewHolder;
import org.raphets.todayinhistory.bean.SimpleHistory;

import java.util.List;

/**
 * Created by RaphetS on 2016/10/16.
 */

public class HistoryAdapter extends BaseAdapter<SimpleHistory> {

    public HistoryAdapter(Context mContext, List<SimpleHistory> mDatas, int mLayoutId) {
        super(mContext, mDatas, mLayoutId);
    }

    @Override
    protected void convert(Context mContext, BaseViewHolder holder, SimpleHistory t) {
        holder.setText(R.id.tv_time,t.getDate())
                .setText(R.id.tv_title,t.getTitle());
    }
}
