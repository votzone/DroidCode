package com.wq.permissiongs.customize;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.wq.permissiongs.R;
import com.wq.permissiongs.special.SpecialPermissionActivity;

import kr.co.namee.permissiongen.PermissionGen;
import kr.co.namee.permissiongen.PermissionSuccess;

public class CustPermissionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cust_permission);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        PermissionGen.onRequestPermissionsResult(this, requestCode, permissions, grantResults);
    }

    public void openNormalActivity(View view){
        Intent intent = new Intent();
        // must full activity name with package name
        intent.setClassName("com.wq.hostcustpermission","com.wq.hostcustpermission.activity.NormalPermissionActivity");
        startActivity(intent);
    }

    @PermissionSuccess(requestCode = 100)
    private void doOpenDangerActivity(){
        Intent intent = new Intent();
        intent.setClassName("com.wq.hostcustpermission","com.wq.hostcustpermission.activity.DangerPermissionActivity");
        startActivity(intent);
    }

    public void openDangerActivity(View view){

        if(Build.VERSION.SDK_INT >=23 && checkSelfPermission("hostcustpermission.wq.com.DangerPermission") != PackageManager.PERMISSION_GRANTED){
            PermissionGen.needPermission(this,100, new String[]{"hostcustpermission.wq.com.DangerPermission"});
        }else {
            doOpenDangerActivity();
        }


    }

    public static void launch(Context from){
        Intent i = new Intent(from, CustPermissionActivity.class);
        from.startActivity(i);
    }
}
