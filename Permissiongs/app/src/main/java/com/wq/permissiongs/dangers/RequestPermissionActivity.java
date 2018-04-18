package com.wq.permissiongs.dangers;

import android.bluetooth.BluetoothClass;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.wq.permissiongs.Manifest;
import com.wq.permissiongs.R;


import java.util.ArrayList;

/**
 * https://developer.android.google.cn/training/permissions/index.html
 */
public class RequestPermissionActivity extends AppCompatActivity {

    private static final int key_request_permission_read_contacts = 0x19191;
    private static final int key_request_permission_write_contacts = 0x19192;
    private static final int key_request_permission_read_phone_state = 0x19193;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_permission);
    }


    private void shortToast(String msg){
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }


    public void requestReadContacts(View view){

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            if (requestSinglePermission(android.Manifest.permission.READ_CONTACTS, key_request_permission_read_contacts)){
                doReadContacts();
            }else {
                shortToast("requesting permission ");
            }
        }else {
            doReadContacts();
        }
    }

    private void doReadContacts(){
        shortToast("doReadContacts");
    }

    private void doNotReadContacts(){
        shortToast("doNotReadContacts");
    }


    public void requestWriteContacts(View view){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            if (requestSinglePermission(android.Manifest.permission.WRITE_CONTACTS, key_request_permission_write_contacts)){
                doWriteContacts();
            }else {
                shortToast("requesting permission ");
            }
        }else {
            doWriteContacts();
        }
    }


    private void doWriteContacts(){
        shortToast("doWriteContacts");
    }

    private void doNotWriteContacts(){
        shortToast("doNotWriteContacts");
    }


    public void requestPhoneState(View view){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            if (requestSinglePermission(android.Manifest.permission.READ_PHONE_STATE, key_request_permission_read_phone_state)){
                doPhoneState();
            }else {
                shortToast("requesting permission ");
            }
        }else {
            doPhoneState();
        }
    }


    private void doPhoneState(){
        shortToast("doPhoneState");
    }


    private void doNotPhoneState(){
        shortToast("doNotPhoneState");
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case key_request_permission_read_contacts:
                if(grantResults !=null && grantResults.length >0 &&grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    doReadContacts();
                }else {
                    doNotReadContacts();
                }
            break;
            case key_request_permission_write_contacts:
                if(grantResults !=null && grantResults.length >0 &&grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    doWriteContacts();
                }else {
                    doNotWriteContacts();
                }
                break;
            case key_request_permission_read_phone_state:
                if(grantResults !=null && grantResults.length >0 &&grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    doPhoneState();
                }else {
                    doNotPhoneState();
                }
                break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }

    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private boolean requestSinglePermission(final String permission, final int requestCode){

            if(checkSelfPermission(permission)!= PackageManager.PERMISSION_GRANTED){
                if (shouldShowRequestPermissionRationale(permission)) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(this);
                    builder.setMessage("TEST: need request Permission "+permission).
                            setOnDismissListener(new DialogInterface.OnDismissListener() {
                        @Override
                        public void onDismiss(DialogInterface dialog) {
                            requestPermissions(new String[]{permission}, requestCode);
                        }
                    }).show();

                }else {
                    requestPermissions(new String[]{permission}, requestCode);
                }
            } else {
                // permission already granted
                return true;
            }
            // permission not granted
            return false;

    }



}
