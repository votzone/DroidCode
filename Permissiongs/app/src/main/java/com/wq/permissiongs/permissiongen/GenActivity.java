package com.wq.permissiongs.permissiongen;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.wq.permissiongs.R;

import kr.co.namee.permissiongen.PermissionFail;
import kr.co.namee.permissiongen.PermissionGen;
import kr.co.namee.permissiongen.PermissionSuccess;

public class GenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gen);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        PermissionGen.onRequestPermissionsResult(this, requestCode, permissions, grantResults);
    }


    public void requestPesmissions(View view){
        requestPermission();
    }

    private void requestPermission(){
        PermissionGen.with(this)
                .addRequestCode(100)
                .permissions(
                        Manifest.permission.READ_CONTACTS,
                        Manifest.permission.RECEIVE_SMS,
                        Manifest.permission.WRITE_CONTACTS)
                .request();

    }

    // 当且仅当所有权限都允许时才执行
    @PermissionSuccess(requestCode = 100)
    public void doSomething(){
        Toast.makeText(this, "Contact permission is granted", Toast.LENGTH_SHORT).show();
    }

    // otherwise
    @PermissionFail(requestCode = 100)
    public void doFailSomething(){
        Toast.makeText(this, "Contact permission is not granted", Toast.LENGTH_SHORT).show();
    }

    public static void launch(Context from){
        Intent i = new Intent(from, GenActivity.class);
        from.startActivity(i);
    }
}
