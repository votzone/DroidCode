package com.wq.permissiongs.special;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import com.wq.permissiongs.R;

import static android.provider.Settings.ACTION_MANAGE_OVERLAY_PERMISSION;
import static android.provider.Settings.ACTION_MANAGE_WRITE_SETTINGS;
import static android.provider.Settings.canDrawOverlays;

public class SpecialPermissionActivity extends AppCompatActivity {

    public static final int request_code_alert_window = 0x74;
    public static final int request_code_write_settings = 0x75;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_special_permission);

    }

    private void doAlertWindow(){
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setTitle("DoAlertWindow").setMessage("Test")
                .setPositiveButton("OK", null).setNegativeButton("Cancel",null);
        AlertDialog alertDialog = builder.create();
        alertDialog.setCancelable(false);
        alertDialog.setCanceledOnTouchOutside(false);
        alertDialog.getWindow().setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
        alertDialog.show();
    }

    private void doNotAlertWindow(){
        Toast.makeText(this,"Do Not Alert Window",Toast.LENGTH_SHORT).show();
    }

    private void doWriteSettings(){
        Toast.makeText(this,"Do Write Settings",Toast.LENGTH_SHORT).show();
    }

    private void doNotWriteSettings(){
        Toast.makeText(this,"Do Not Write Settings",Toast.LENGTH_SHORT).show();
    }

    public void writeSettings(View view){
        if(Build.VERSION.SDK_INT >=23){
            if(Settings.System.canWrite(this)){
                doAlertWindow();
            }else {
                startActivityForResult(new Intent(ACTION_MANAGE_WRITE_SETTINGS, Uri.parse("package:"+getPackageName()))
                        , request_code_write_settings);
            }
        }
    }

    public void alertWindow(View view){
        if(Build.VERSION.SDK_INT >=23){
            if(Settings.canDrawOverlays(this)){
                doAlertWindow();
            }else {
                startActivityForResult(new Intent(ACTION_MANAGE_OVERLAY_PERMISSION, Uri.parse("package:"+getPackageName()))
                        , request_code_alert_window);
            }
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        switch (requestCode){
            case request_code_alert_window:
                if(Build.VERSION.SDK_INT >= 23) {
                    if (Settings.canDrawOverlays(this)) {
                        doAlertWindow();
                    }else {
                        doNotAlertWindow();
                    }
                }
                break;
            case request_code_write_settings:
                if(Build.VERSION.SDK_INT >= 23){
                    if(Settings.System.canWrite(this)){
                        doWriteSettings();
                    }else{
                        doNotWriteSettings();
                    }
                }
                break;

        }

        super.onActivityResult(requestCode, resultCode, data);
    }

    public static void launch(Context from){
        Intent i = new Intent(from, SpecialPermissionActivity.class);
        from.startActivity(i);
    }
}
