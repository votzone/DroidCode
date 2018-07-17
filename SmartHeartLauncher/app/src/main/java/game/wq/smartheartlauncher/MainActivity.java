package game.wq.smartheartlauncher;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import static android.content.pm.PackageManager.COMPONENT_ENABLED_STATE_DEFAULT;
import static android.content.pm.PackageManager.COMPONENT_ENABLED_STATE_ENABLED;

public class MainActivity extends AppCompatActivity {

    private final BroadcastReceiver homeReceiver = new BroadcastReceiver() {


        final String SYS_KEY = "reason"; // 标注下这里必须是这么一个字符串值

        final String SYS_HOME_KEY = "homekey";// 标注下这里必须是这么一个字符串值

        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (action.equals(Intent.ACTION_CLOSE_SYSTEM_DIALOGS)) {
                String reason = intent.getStringExtra(SYS_KEY);
                if (reason != null && reason.equals(SYS_HOME_KEY)) {
//                    Log.i("TT", "##################home键监听");
//                    Toast.makeText(MainActivity.this, "aaaa",
//                            Toast.LENGTH_SHORT).show();
//
//                    Intent intentw = new Intent(Intent.ACTION_MAIN);
//                    intentw.addCategory(Intent.CATEGORY_HOME);
//                    intentw.setClassName("android",
//                            "com.android.internal.app.ResolverActivity");
//                    startActivity(intentw);
                    Intent intentw = new Intent(Intent.ACTION_MAIN);
                    intentw.addCategory(Intent.CATEGORY_HOME);
                    intentw.setClassName("com.huawei.android.internal.app",
                            "com.huawei.android.internal.app.HwResolverActivity");
                    startActivity(intentw);
                    clearDefaultLauncher();


                }
            }
        }
    };


    TextView tvHello;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        IntentFilter homeFilter = new IntentFilter(
                Intent.ACTION_CLOSE_SYSTEM_DIALOGS);
        registerReceiver(homeReceiver, homeFilter);

        tvHello = findViewById(R.id.tv_txt_hello);
        StringBuilder sbuilder = new StringBuilder();
        String action = getIntent().getAction();
        if(action !=null){
            sbuilder.append("action == ").append(action);
        }else {
            sbuilder.append("action == null");
        }
        sbuilder.append("\nisTaskRoot == ").append(isTaskRoot());
        tvHello.setText(sbuilder.toString());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(homeReceiver);
    }

    public void onClick(View view){

        Intent intent = new Intent(this, MockHome.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setAction(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_LAUNCHER);//android.intent.category.LAUNCHER
        startActivity(intent);
        finish();

//        Intent intent = new Intent(Settings.ACTION_HOME_SETTINGS);
//        intent.setClassName("com.android.settings","com.android.settings.Settings$HomeSettingsActivity");
//        startActivity(intent);
//        clearDefaultLauncher();
//        Intent intentw = new Intent(Intent.ACTION_MAIN);
//        intentw.addCategory(Intent.CATEGORY_HOME);
//        intentw.setClassName("com.huawei.android.internal.app",
//                "com.huawei.android.internal.app.HwResolverActivity");
//        startActivity(intentw);
//        Intent  paramIntent = new Intent("android.intent.action.MAIN");
//        paramIntent.setComponent(new ComponentName("android", "com.android.internal.app.ResolverActivity"));
//        paramIntent.addCategory("android.intent.category.DEFAULT");
//        paramIntent.addCategory("android.intent.category.HOME");
//        startActivity(paramIntent);
    }

    public void clearDefaultLauncher() {
        PackageManager pm = getPackageManager();
        String pn = getPackageName();
        String hn = MockHome.class.getName();
        ComponentName mhCN = new ComponentName(pn, hn);
        Intent homeIntent = new Intent("android.intent.action.MAIN");
        homeIntent.addCategory("android.intent.category.HOME");
        homeIntent.addCategory("android.intent.category.DEFAULT");
        homeIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        pm.setComponentEnabledSetting(mhCN, COMPONENT_ENABLED_STATE_ENABLED, PackageManager.DONT_KILL_APP);
        startActivity(homeIntent);
        pm.setComponentEnabledSetting(mhCN, COMPONENT_ENABLED_STATE_DEFAULT, PackageManager.DONT_KILL_APP);
    }
}
