package org.raphets.todayinhistory;

import android.app.Application;

/**
 * Created by RaphetS on 2016/10/14.
 */

public class MyApp extends Application {
    private static MyApp instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance=this;

    }


    public static MyApp getInstance(){
        return instance;
    }
    
    
}
