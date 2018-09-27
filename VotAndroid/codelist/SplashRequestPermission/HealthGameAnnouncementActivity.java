package com.google.transform;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;

import com.unity3d.player.UnityPlayerNativeActivity;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;


/**
 * 使用splash 方式申请所有权限
 *
 * <manifest>
        <activity android:name="com.google.transform.HealthGameAnnouncementActivity"
        android:screenOrientation="landscape">
             <intent-filter>
             <action android:name="android.intent.action.MAIN" />
             <category android:name="android.intent.category.LAUNCHER" />
             </intent-filter>
         </activity>
 * </manifest>
 *
 * 注：android:screenOrientation="landscape" 直接指定启动时为横屏，在代码中指定会走两次oncreate
 */
public class HealthGameAnnouncementActivity extends Activity {

    private int permissionReqCount = 0;

    private static final int code_next_activity = 110;
    Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(msg.what == code_next_activity){
                lastOk = true;
                toNextActivity();
            }
        }
    };

    private boolean permissionOk = false;
    private boolean lastOk = false;
    public void toNextActivity(){
        if(permissionOk && lastOk) {
            Intent intent = new Intent(this, UnityPlayerNativeActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.setAction(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_LAUNCHER);
            startActivity(intent);
            finish();
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        if(this.getResources().getConfiguration().orientation
                == Configuration.ORIENTATION_PORTRAIT){
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
            // todo finish 当切换方向时，会重新生成activity，原来的activity直接finish。
            // finish();
        }
        initView();
        if(isGrantExternalRW(this)){
            Log.e("unity","permissiong Ok");
            permissionOk = true;

        }
        mHandler.sendEmptyMessageDelayed(code_next_activity, 5*1000);

    }

    private void initView(){
        InputStream inputStream;
        Bitmap bitmap = null;
        try {
            // todo assets 目录下启动页
            inputStream = getAssets().open("health_game_announcement.png");
            if(inputStream!=null){
                bitmap = BitmapFactory.decodeStream(inputStream);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        FrameLayout frameLayout = new FrameLayout(this);
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT,
                FrameLayout.LayoutParams.MATCH_PARENT);
        ImageView imageView = new ImageView(this);
        if(bitmap !=null) {
            imageView.setImageBitmap(bitmap);
        }

        imageView.setLayoutParams(layoutParams);
        frameLayout.addView(imageView);

        setContentView(frameLayout);

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions,  int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode==1){
            ArrayList<String> denyPermissions = new ArrayList<>();

            for (int i = 0; i < permissions.length; i++) {
                String permission = permissions[i];
                Log.e("unity",permission+" -  2");
                int grant = grantResults[i];
                if (grant != PackageManager.PERMISSION_GRANTED){
                    denyPermissions.add(permission);
                }
            }

            if(permissions ==null || permissions.length <=0){
                // permissions 可能为空
                return;
            }

            if (denyPermissions.size() > 0){
                String[] denyPermissionStr = new String[denyPermissions.size()];
                for (int i = 0; i < denyPermissions.size(); i++) {
                    denyPermissionStr[i] = denyPermissions.get(i);
                }
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    permissionReqCount ++;
                    if (permissionReqCount < 4){
                        requestPermissions(denyPermissionStr,1);
                    }else {
                        for (String deny :denyPermissions) {
                            Log.e("unity", deny);
                        }
                        Toast.makeText(this, "应用缺少必要的权限！请点击\"权限\"，打开所需要的权限。", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                        intent.setData(Uri.parse("package:" + getPackageName()));
                        startActivity(intent);
                        finish();
                    }
                }
            }else{
                Log.e("unity","permissiong Ok 2");
                permissionOk = true;
                toNextActivity();
            }
        }
    }

    /***
     * 获取manifest文件中声明的所有权限
     * @return
     */
    public String[] myPermissions(){
        PackageManager pm = getPackageManager();
        PackageInfo pi;

        try {
            pi = pm.getPackageInfo(getPackageName(), PackageManager.GET_PERMISSIONS);
            String[] permissions = pi.requestedPermissions;
            return permissions;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return new String[]{};
        }
    }

    /**
     * 判断是否拥有权限
     * @param activity
     * @return
     */
    public boolean isGrantExternalRW(Activity activity) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M ){
            return true;
        }


        String[] myPermissions = myPermissions();
        if (myPermissions.length==0){
            return  true;
        }
        ArrayList<String> denyPermissions = new ArrayList<>();
        for (String myPermission :
                myPermissions) {
            if (activity.checkSelfPermission(
                    myPermission) != PackageManager.PERMISSION_GRANTED){
                if("android.permission.SYSTEM_ALERT_WINDOW".equals(myPermission)||
                        "android.permission.MOUNT_UNMOUNT_FILESYSTEMS".equals(myPermission)||
                        "android.permission.android.permission.READ_PHONE_STATE".equals(myPermission)||
                        "vivo.game.permission.OPEN_JUMP_INTENTS".equals(myPermission)||
                        "com.bbk.account.permission.READ_ACCOUNTINFO".equals(myPermission)||
                        "android.permission.REQUEST_INSTALL_PACKAGES".equals(myPermission)){

                }else {
                    denyPermissions.add(myPermission);
                }

            }
        }

        if (denyPermissions.size()==0){
//            Toast.makeText(activity, "0", Toast.LENGTH_SHORT).show();
            return  true;
        }

        if (Build.VERSION.SDK_INT >= 26&&denyPermissions.size()>0){
                String[] deneypermissions = new String[denyPermissions.size()];
                for (int i = 0; i < denyPermissions.size(); i++) {
                    deneypermissions[i] = denyPermissions.get(i);
                }
                activity.requestPermissions(deneypermissions, 1);
                return false;
        }

//        boolean flagNotStoragePermission = activity.checkSelfPermission(
//                Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED;
//        boolean flagNotPhoneStatePermissiont =activity.checkSelfPermission(
//                Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED;
//
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M
//                && flagNotStoragePermission && flagNotPhoneStatePermissiont) {//&& Build.VERSION.SDK_INT < 26
//
//            activity.requestPermissions(new String[]{
//                    Manifest.permission.READ_EXTERNAL_STORAGE,
//                    Manifest.permission.WRITE_EXTERNAL_STORAGE,
//                    Manifest.permission.READ_PHONE_STATE
//            }, 1);
//
//            return false;
//        }



        return true;
    }
}
