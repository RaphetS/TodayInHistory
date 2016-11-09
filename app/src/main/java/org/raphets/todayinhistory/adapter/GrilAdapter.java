package org.raphets.todayinhistory.adapter;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Scroller;

import org.raphets.todayinhistory.R;
import org.raphets.todayinhistory.base.BaseViewHolder;
import org.raphets.todayinhistory.bean.GrilBean;
import org.raphets.todayinhistory.http.ImageLoader;
import org.raphets.todayinhistory.utils.ScreenUtil;


import java.util.List;

/**
 * Created by RaphetS on 2016/10/19.
 */

public class GrilAdapter extends RecyclerView.Adapter {
    private List<GrilBean> mDatas;
    private Context mContext;
    private RecyclerView mRecyclerView;

    private final int item_type = 100;
    private final int load_type = 101;
    private OnItemClickListener mItemClickListener;
    private OnLoadMoreListener mOnLoadMoreListener;
    private boolean isLoading = false;
    private boolean isFirst=true;

    public GrilAdapter(Context mContext, List<GrilBean> mDatas, RecyclerView mRecyclerView) {
        this.mDatas = mDatas;
        this.mContext = mContext;
        this.mRecyclerView = mRecyclerView;
        initRcyclerView();
    }

    private void initRcyclerView() {
        GridLayoutManager mLayoutManager = (GridLayoutManager) mRecyclerView.getLayoutManager();
        mLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                if (position == mDatas.size()) {
                    return 2;
                } else {
                    return 1;
                }

            }
        });

        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                GridLayoutManager layoutManager = (GridLayoutManager) recyclerView.getLayoutManager();
                int lastVisibleItemPosition = layoutManager.findLastVisibleItemPosition();
                int itemCount = layoutManager.getItemCount();
                if (lastVisibleItemPosition > itemCount - 2 && !isLoading && dy > 0) {
                    isLoading = true;
                    if (mOnLoadMoreListener!=null) {
                        mOnLoadMoreListener.onLoadMore();
                    }
                }
            }
        });
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == load_type) {
            View loadView = LayoutInflater.from(mContext).inflate(R.layout.load_view, parent, false);
            LoadViewHolder holder = new LoadViewHolder(loadView);
            return holder;

        } else {
            View girlView = LayoutInflater.from(mContext).inflate(R.layout.item_gril, parent, false);
            BaseViewHolder holder = new BaseViewHolder(girlView);
            return holder;

        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {

        if (holder instanceof BaseViewHolder) {
            BaseViewHolder baseViewHolder = (BaseViewHolder) holder;
            ImageView imageView = baseViewHolder.getView(R.id.iv_gril);
            ViewGroup.LayoutParams layoutParams = imageView.getLayoutParams();
            layoutParams.height = ScreenUtil.getScreenHeight(mContext) / 3;
//            if ("57e319fd421aa95bc338986a".equals(mDatas.get(position).get_id())){

                ImageLoader.load(mContext, mDatas.get(position).getUrl(), imageView);
                baseViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        mItemClickListener.onItemClick(view, position);
                    }
                });


        }


    }


    @Override
    public int getItemCount() {
        return mDatas.size() + 1;
    }


    @Override
    public int getItemViewType(int position) {
        if (position == mDatas.size()) {
            return load_type;
        } else {
            return item_type;
        }
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mItemClickListener = listener;
    }

    public void setOnLoadMoreListener(OnLoadMoreListener listener) {
        this.mOnLoadMoreListener = listener;
    }

    public void loadCompleted() {
        isLoading = false;
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }


    public interface OnLoadMoreListener {
        void onLoadMore();
    }


    class LoadViewHolder extends RecyclerView.ViewHolder {
        public LoadViewHolder(View itemView) {
            super(itemView);
        }
    }

}
