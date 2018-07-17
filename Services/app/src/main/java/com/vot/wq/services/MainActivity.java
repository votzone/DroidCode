package com.vot.wq.services;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.androidbook.services.httpget.BackLocalService;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private int counter = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClick(View view){
        if(view.getId() == R.id.start){
            Log.v(TAG, "Starting service... counter = " + counter);
            Intent intent = new Intent(MainActivity.this,
                    BackLocalService.class);
            intent.putExtra("counter", counter++);
            startService(intent);
        }else if(view.getId() == R.id.stop){
            stopService();
        }
    }

    private void stopService() {
        Log.v(TAG, "Stopping service...");
        if(stopService(new Intent(MainActivity.this,
                BackLocalService.class)))
            Log.v(TAG, "stopService was successful");
        else
            Log.v(TAG, "stopService was unsuccessful");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        stopService();
    }
}
