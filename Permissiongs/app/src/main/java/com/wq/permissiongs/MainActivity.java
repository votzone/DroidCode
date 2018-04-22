package com.wq.permissiongs;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.wq.permissiongs.customize.CustPermissionActivity;
import com.wq.permissiongs.dangers.RequestPermissionActivity;
import com.wq.permissiongs.fragment.FragmentActivity;
import com.wq.permissiongs.permissiongen.GenActivity;
import com.wq.permissiongs.special.SpecialPermissionActivity;

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

    public void requestPermessionInFragment(View view){
        Intent i = new Intent(this, FragmentActivity.class);
        startActivity(i);
    }

    public void specialPermission(View view){
        SpecialPermissionActivity.launch(this);
    }

    public void genPermission(View view){
        GenActivity.launch(this);
    }

    public void custPermission(View view){
        CustPermissionActivity.launch(this);
    }
}
