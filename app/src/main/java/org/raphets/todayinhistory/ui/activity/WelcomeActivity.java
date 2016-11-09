package org.raphets.todayinhistory.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import org.raphets.todayinhistory.R;
import org.raphets.todayinhistory.utils.WeakHandler;

import butterknife.ButterKnife;

public class WelcomeActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        ButterKnife.bind(this);
        new WeakHandler().postDelayed(new Runnable() {
            @Override
            public void run() {
            startActivity(new Intent(WelcomeActivity.this,MainActivity.class));
                finish();
            }
        },1500);
    }

}


