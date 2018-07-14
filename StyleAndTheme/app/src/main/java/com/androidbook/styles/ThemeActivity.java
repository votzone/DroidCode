package com.androidbook.styles;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.vot.wq.styleandtheme.R;

public class ThemeActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setTheme(R.style.ErrorText_Danger);
        setContentView(R.layout.activity_theme);

    }


    public static void launch(Context from){
        Intent intent = new Intent(from, ThemeActivity.class);
        from.startActivity(intent);
    }
}
