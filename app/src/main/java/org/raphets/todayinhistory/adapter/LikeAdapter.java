package org.raphets.todayinhistory.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;

import org.raphets.todayinhistory.R;
import org.raphets.todayinhistory.base.BaseAdapter;
import org.raphets.todayinhistory.base.BaseViewHolder;
import org.raphets.todayinhistory.bean.HistoryLikeBean;
import org.raphets.todayinhistory.http.ImageLoader;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by RaphetS on 2016/10/18.
 */

public class LikeAdapter extends BaseAdapter<HistoryLikeBean> {
    private List<HistoryLikeBean> mDatas;

    public LikeAdapter(Context mContext, List<HistoryLikeBean> mDatas, int mLayoutId) {
        super(mContext, mDatas, mLayoutId);
        this.mDatas=mDatas;
    }

    @Override
    protected void convert(Context context, BaseViewHolder holder, HistoryLikeBean bean) {
        holder.setText(R.id.tv_time, bean.getDate())
                .setText(R.id.tv_title, bean.getTitle());
        ImageView imageView=holder.getView(R.id.iv_pic);

        if (!TextUtils.isEmpty(bean.getImg())){
            imageView.setVisibility(View.VISIBLE);
            ImageLoader.load(context,bean.getImg(),imageView);
        }else {
          imageView.setVisibility(View.GONE);
        }
    }

    public List<HistoryLikeBean> getData(){
        return mDatas;
    }

    public void setData(List<HistoryLikeBean> data){
        this.mDatas=new ArrayList<>(data);
    }
}
