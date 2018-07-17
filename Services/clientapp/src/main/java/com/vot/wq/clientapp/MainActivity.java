package com.vot.wq.clientapp;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.androidbook.services.remote.IAidlService;
import com.androidbook.services.remote.Person;

public class MainActivity extends AppCompatActivity {

    protected static final String TAG = "StockQuoteClient";
    private IAidlService stockService = null;

    private Button bindBtn;
    private Button callBtn;
    private Button unbindBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        bindBtn = (Button)findViewById(R.id.bindBtn);
        bindBtn.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClassName("com.vot.wq.services","com.androidbook.services.remote.RemotePrimService");
                bindService(intent, serConn, Context.BIND_AUTO_CREATE);
                bindBtn.setEnabled(false);
                callBtn.setEnabled(true);
                unbindBtn.setEnabled(true);
            }});

        callBtn = (Button)findViewById(R.id.callBtn);
        callBtn.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                callService();
            }});
        callBtn.setEnabled(false);

        unbindBtn = (Button)findViewById(R.id.unbindBtn);
        unbindBtn.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                unbindService(serConn);
                bindBtn.setEnabled(true);
                callBtn.setEnabled(false);
                unbindBtn.setEnabled(false);
            }});
        unbindBtn.setEnabled(false);
    }


    private void callService() {
        try {
            double val = stockService.getQuote("SYH");
//            Toast.makeText(MainActivity.this, "Value from service is "+val,
//                    Toast.LENGTH_SHORT).show();

            Person person = new Person();
            person.setAge(47);
            person.setName("Dave");
            String response = stockService.get2Quote("SYH", person);
            Toast.makeText(MainActivity.this, "Value from service is "+response,
                    Toast.LENGTH_SHORT).show();

        } catch (RemoteException ee) {
            Log.e("MainActivity", ee.getMessage(), ee);
        }
    }

    private ServiceConnection serConn = new ServiceConnection() {

        @Override
        public void onServiceConnected(ComponentName name, IBinder service)
        {
            Log.v(TAG, "onServiceConnected() called");
            stockService = IAidlService.Stub.asInterface(service);
            callService();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.v(TAG, "onServiceDisconnected() called");
            stockService = null;
        }
    };
}
