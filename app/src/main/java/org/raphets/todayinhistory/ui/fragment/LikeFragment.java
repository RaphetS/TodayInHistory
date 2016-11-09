package org.raphets.todayinhistory.ui.fragment;


import android.app.ActivityOptions;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import android.widget.TextView;

import org.raphets.todayinhistory.R;
import org.raphets.todayinhistory.adapter.LikeAdapter;
import org.raphets.todayinhistory.base.BaseAdapter;
import org.raphets.todayinhistory.base.BaseFragment;
import org.raphets.todayinhistory.bean.HistoryLikeBean;
import org.raphets.todayinhistory.common.Constants;
import org.raphets.todayinhistory.common.DefaultItemTouchHelpCallback;
import org.raphets.todayinhistory.common.LikeCallBack;
import org.raphets.todayinhistory.db.RealmHelper;
import org.raphets.todayinhistory.ui.activity.HistoryDetailActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 收藏
 */
public class LikeFragment extends BaseFragment {
    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;
    @BindView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout mSrl;
    @BindView(R.id.tv_empty)
    TextView tvEmpty;

    private RealmHelper mRealmHelper;
    private LikeAdapter mAdapter;
    private DefaultItemTouchHelpCallback mCallback;
    private List<HistoryLikeBean> mDatas=new ArrayList<>();

    @Override
    protected void initEvents() {
        mSrl.setColorSchemeColors(Color.RED,Color.BLUE,Color.GREEN);

        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(manager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        mRealmHelper = new RealmHelper(getActivity());
        mDatas = mRealmHelper.queryAllHistoryLike();
        mAdapter = new LikeAdapter(getActivity(), mDatas, R.layout.item_like);
        mRecyclerView.setAdapter(mAdapter);
        if (mAdapter.getItemCount()<=0){
            tvEmpty.setVisibility(View.VISIBLE);
        }
        mSrl.setRefreshing(false);

        setItemTouch();

        addListener();
    }


    @Override
    public void onResume() {
        super.onResume();
        if (!mSrl.isRefreshing()) {
            mSrl.setRefreshing(true);
        }
        List<HistoryLikeBean> datas=mRealmHelper.queryAllHistoryLike();
        mAdapter.updateData(datas);
        if (mAdapter.getData().size()==0){
            tvEmpty.setVisibility(View.VISIBLE);
        }else {
            tvEmpty.setVisibility(View.GONE);
        }

        mSrl.setRefreshing(false);
    }

    private void setItemTouch() {
        mCallback= new DefaultItemTouchHelpCallback(new DefaultItemTouchHelpCallback.OnItemTouchCallbackListener() {
            @Override
            public void onSwiped(int position) {
                // 滑动删除的时候，从数据库、数据源移除，并刷新UI
                mRealmHelper.deleteHistoryLike(mDatas.get(position).geteId());
                mDatas.remove(position);
                mAdapter.notifyItemRemoved(position);
                if (mAdapter.getItemCount()<=0){
                    tvEmpty.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public boolean onMove(int srcPosition, int targetPosition) {
                mAdapter.notifyItemMoved(srcPosition,targetPosition);
                return true;
            }
        });
        mCallback.setSwipeEnable(true);
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(mCallback);
        itemTouchHelper.attachToRecyclerView(mRecyclerView);
    }

    private void addListener() {
        mAdapter.setOnItemClickListener(new BaseAdapter.OnItemClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onItemClick(View view, int position) {
                Intent intent=new Intent(getActivity(),HistoryDetailActivity.class);
                intent.putExtra(Constants.EID,mDatas.get(position).geteId());
                intent.putExtra(Constants.DATE,mDatas.get(position).getDate());
                ActivityOptions options=ActivityOptions.makeSceneTransitionAnimation(getActivity(),view,"shareView");
                startActivity(intent,options.toBundle());
            }
        });

        mSrl.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
//                List<HistoryLikeBean> datas=mRealmHelper.queryAllHistoryLike();
//                List<HistoryLikeBean> oldDatas=mAdapter.getData();
//                DiffUtil.DiffResult diffResult= DiffUtil.calculateDiff(new LikeCallBack(oldDatas,datas));
//                mAdapter.setData(datas);
//                diffResult.dispatchUpdatesTo(mAdapter);
                List<HistoryLikeBean> datas=mRealmHelper.queryAllHistoryLike();
                mAdapter.updateData(datas);

                mSrl.setRefreshing(false);
                if (mAdapter.getItemCount()<=0){
                    tvEmpty.setVisibility(View.VISIBLE);
                }else {
                    tvEmpty.setVisibility(View.GONE);
                }

            }
        });
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_like;
    }
}
