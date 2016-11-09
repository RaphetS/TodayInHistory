package org.raphets.todayinhistory.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;

public abstract class BaseFragment extends Fragment {
    protected View mRootView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mRootView=inflater.inflate(getLayoutId(),container,false);
        ButterKnife.bind(this,mRootView);
        initEvents();
        return mRootView;
    }

    protected abstract void initEvents();

    public abstract int getLayoutId() ;
}
