package org.raphets.todayinhistory.ui.activity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;

import org.raphets.todayinhistory.R;
import org.raphets.todayinhistory.base.BaseActivity;
import org.raphets.todayinhistory.common.Constants;
import org.raphets.todayinhistory.http.ImageLoader;
import org.raphets.todayinhistory.utils.Tool;

import butterknife.BindView;
import uk.co.senab.photoview.PhotoViewAttacher;

/**
 * 妹子详情界面
 */
public class GrilDetailActivity extends BaseActivity {
    @BindView(R.id.toolbar)
    Toolbar mToolBar;
    @BindView(R.id.iv_gril)
    ImageView ivGril;
    @BindView(R.id.ll_container)
    LinearLayout mLLContainer;
    @BindView(R.id.rl_container)
    RelativeLayout mAdContainer;


    private String mImgUrl;
    private Bitmap mBitmap;
    private PhotoViewAttacher mAttacher;
    private String TAG = "TAG";

    @Override
    public int getLayoutId() {
        return R.layout.activity_gril_detail;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setToolbar(mToolBar, "妹纸");

        initData();

    }


    private void initData() {
        mImgUrl = getIntent().getStringExtra(Constants.URL_IMG);
        ImageLoader.load(this, mImgUrl, new SimpleTarget<Bitmap>() {
            @Override
            public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                mBitmap = resource;
                ivGril.setImageBitmap(mBitmap);
                mAttacher = new PhotoViewAttacher(ivGril);
            }

        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.gril_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_save:
                Tool.saveBitmapToFile(GrilDetailActivity.this, mImgUrl, mBitmap, mLLContainer, false);
                break;
            default:
                break;

        }

        return super.onOptionsItemSelected(item);
    }


}
