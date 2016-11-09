package org.raphets.todayinhistory.ui.activity;


import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import org.raphets.todayinhistory.R;
import org.raphets.todayinhistory.base.BaseActivity;
import org.raphets.todayinhistory.bean.HistoryLikeBean;
import org.raphets.todayinhistory.bean.Histroy;
import org.raphets.todayinhistory.bean.Picture;
import org.raphets.todayinhistory.common.Constants;
import org.raphets.todayinhistory.db.RealmHelper;
import org.raphets.todayinhistory.http.ImageLoader;
import org.raphets.todayinhistory.mvp.contact.HistoryDetailContact;
import org.raphets.todayinhistory.mvp.presenter.HistoryDetailPresenter;
import org.raphets.todayinhistory.utils.SnackBarUtil;

import butterknife.BindView;

/**
 * 历史上的今天-详情界面
 * @author RaphetS
 */

public class HistoryDetailActivity extends BaseActivity implements HistoryDetailContact.View {
    @BindView(R.id.collToolBar)
    CollapsingToolbarLayout mCollToolbar;
    @BindView(R.id.toolbar)
    Toolbar mToolBar;
    @BindView(R.id.tv_content)
    TextView tvContent;
    @BindView(R.id.iv_pic)
    ImageView ivPic;
    @BindView(R.id.fab_like)
    FloatingActionButton mFab;
    @BindView(R.id.rl_container)
    RelativeLayout mAdContainer;


    private String mEid;
    private HistoryDetailPresenter mPresent;
    private HistoryLikeBean mLikeBean;
    private String mDate;
    private RealmHelper mRealmHelper;
    private String TAG = "TAG";

    @Override
    public int getLayoutId() {
        return R.layout.activity_history_detail;

    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        init();

        setToolbar();

    }



    private void init() {
        mEid = getIntent().getStringExtra(Constants.EID);
        mDate = getIntent().getStringExtra(Constants.DATE);

        mLikeBean = new HistoryLikeBean();
        mRealmHelper = new RealmHelper(this);

        initFabLike();

        mPresent = new HistoryDetailPresenter(this);

        mPresent.getHistoryData(mEid);
    }

    private void initFabLike() {
        if (mRealmHelper.queryHistoryLike(mEid)) {
            mFab.setSelected(true);
        } else {
            mFab.setSelected(false);
        }
    }

    private void setToolbar() {
        mCollToolbar.setExpandedTitleColor(Color.WHITE);
        mCollToolbar.setCollapsedTitleTextColor(Color.WHITE);

        setToolbar(mToolBar, "");

    }

    @Override
    public void showData(Histroy<Picture> history) {
        mCollToolbar.setTitle(history.getTitle());
        tvContent.setText(history.getContent());
        if (Integer.parseInt(history.getPicNo()) > 0) {
            mLikeBean.setImg(history.getPicUrl().get(0).getUrl());
            ImageLoader.load(HistoryDetailActivity.this, history.getPicUrl().get(0).getUrl(), ivPic);
        } else {
            ImageLoader.load(HistoryDetailActivity.this, ivPic);
        }

        /**
         *  设置收藏数据
         */
        mLikeBean.seteId(history.getE_id());
        mLikeBean.setDate(mDate);
        mLikeBean.setTitle(history.getTitle());

    }

    @Override
    public void showFail(String msg) {
        SnackBarUtil.showLong(getWindow().getDecorView(), msg);
    }


    /**
     * 收藏
     */
    public void onLike(View view) {
        if (mFab.isSelected()) {
            mFab.setSelected(false);
            mRealmHelper.deleteHistoryLike(mEid);
        } else {
            mFab.setSelected(true);
            mRealmHelper.insertHistoryLike(mLikeBean);
        }
    }

    @Override
    public void onBackPressed() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            finishAfterTransition();
        } else {
            finish();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresent.detachView();
    }
}
