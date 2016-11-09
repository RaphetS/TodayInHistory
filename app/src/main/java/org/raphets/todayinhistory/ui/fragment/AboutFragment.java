package org.raphets.todayinhistory.ui.fragment;


import android.widget.ImageView;

import org.raphets.todayinhistory.R;
import org.raphets.todayinhistory.base.BaseFragment;
import org.raphets.todayinhistory.http.ImageLoader;

import butterknife.BindView;

/**
 *   关于
 */
public class AboutFragment extends BaseFragment {
    @BindView(R.id.iv_pic)
    ImageView ivPic;

    private String TAG="TAG";

    @Override
    protected void initEvents() {
        ImageLoader.load(getActivity(),ivPic);
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_about;
    }




}
