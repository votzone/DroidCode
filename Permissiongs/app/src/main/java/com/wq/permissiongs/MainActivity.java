package com.wq.permissiongs;

import android.content.Intent;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.wq.permissiongs.dangers.RequestPermissionActivity;

/**
 * https://developer.android.google.cn/guide/topics/security/permissions.html
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void requestPermission(View view){
        Intent i = new Intent(this, RequestPermissionActivity.class);
        startActivity(i);
    }
}
