package com.wq.hostcustpermission;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.wq.hostcustpermission.activity.DangerPermissionActivity;
import com.wq.hostcustpermission.activity.NormalPermissionActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void openNormalActivity(View view){
//        Intent intent = new Intent(this, NormalPermissionActivity.class);
        Intent intent = new Intent();
        // must full activity name with package name
        intent.setClassName("com.wq.hostcustpermission","com.wq.hostcustpermission.activity.NormalPermissionActivity");
        startActivity(intent);
    }

    public void openDangerActivity(View view){
//        Intent intent = new Intent(this, DangerPermissionActivity.class);
        Intent intent = new Intent();
        intent.setClassName("com.wq.hostcustpermission","com.wq.hostcustpermission.activity.DangerPermissionActivity");
        startActivity(intent);
    }
}
