package com.androidbook.services.remote;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

public class RemotePrimService extends Service {

    private static final String TAG = "StockQuoteService";

    class PrimService extends IPrimService.Stub{

        @Override
        public double getQuote(String ticker) throws RemoteException {
            Log.v(TAG, "getQuote() called for " + ticker);
            return 20.0;
        }
    }

    public RemotePrimService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.v(TAG, "onCreate() called");
    }

    @Override
    public void onDestroy()
    {
        super.onDestroy();
        Log.v(TAG, "onDestroy() called");
    }

    @Override
    public IBinder onBind(Intent intent)
    {
        Log.v(TAG, "onBind() called");
        return new PrimService();
    }
}
