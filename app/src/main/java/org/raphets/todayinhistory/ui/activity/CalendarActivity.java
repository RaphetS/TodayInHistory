package org.raphets.todayinhistory.ui.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;

import org.greenrobot.eventbus.EventBus;
import org.raphets.todayinhistory.R;
import org.raphets.todayinhistory.base.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 日历界面
 * @author RaphetS
 */
public class CalendarActivity extends BaseActivity {
    @BindView(R.id.toolbar)
    Toolbar mToolBar;
    @BindView(R.id.calendarView)
    MaterialCalendarView mCalendarView;

    private CalendarDay mDate;

    @Override
    public int getLayoutId() {
        return R.layout.activity_calendar;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setToolBar();

        addListener();
    }

    private void addListener() {
        mCalendarView.setOnDateChangedListener(new OnDateSelectedListener() {
            @Override
            public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {
                mDate=date;
            }
        });
    }

    private void setToolBar() {
        mToolBar.setTitle("选择日期");
        setSupportActionBar(mToolBar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        mToolBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @OnClick(R.id.btn_ok)
    void onClcik(View view){
        EventBus.getDefault().post(mDate);
        finish();
    }
}
