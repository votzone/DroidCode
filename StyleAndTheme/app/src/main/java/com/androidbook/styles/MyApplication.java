package com.androidbook.styles;

import android.app.Application;

import com.vot.wq.styleandtheme.R;

/**
 * Created by chunleiyan on 2018/7/14.
 */

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        setTheme(R.style.ErrorText_Danger_Link);
    }
}
