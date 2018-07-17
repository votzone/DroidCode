package com.androidbook.services.httpget;

import android.app.Application;
import android.net.http.AndroidHttpClient;
import android.util.Log;

import org.apache.http.HttpVersion;
import org.apache.http.client.HttpClient;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.params.ConnManagerParams;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.protocol.HTTP;

/**
 * Created by chunleiyan on 2018/7/16.
 */

public class MyApplication extends Application {

    private HttpClient httpClient;

    @Override
    public void onCreate() {
        super.onCreate();

    }

    private static final String TAG = "ApplicationEx";

    private HttpClient createHttpClient2()
    {
        HttpParams params = new BasicHttpParams();
        HttpProtocolParams.setVersion(params, HttpVersion.HTTP_1_1);
        HttpProtocolParams.setContentCharset(params, HTTP.DEFAULT_CONTENT_CHARSET);
        HttpProtocolParams.setUseExpectContinue(params, true);

        ConnManagerParams.setTimeout(params, 1000);

        HttpConnectionParams.setConnectionTimeout(params, 5000);
        HttpConnectionParams.setSoTimeout(params, 10000);

        SchemeRegistry schReg = new SchemeRegistry();
        schReg.register(new Scheme("http",
                PlainSocketFactory.getSocketFactory(), 80));
        schReg.register(new Scheme("https",
                SSLSocketFactory.getSocketFactory(), 443));
        ClientConnectionManager conMgr = new
                ThreadSafeClientConnManager(params,schReg);

        return new DefaultHttpClient(conMgr, params);
    }

    private HttpClient createHttpClient(){
        AndroidHttpClient httpClient = AndroidHttpClient.newInstance("Mozilla/5.0 (Linux; U; Android 2.1; en-us; ADR6200 Build/ERD79) AppleWebKit/530.17 (KHTML, like Gecko) Version/ 4.0 Mobile Safari/530.17");

        ClientConnectionManager conMgr = httpClient.getConnectionManager();
        Log.v(TAG, "Connection manager is " + conMgr.getClass().getName());

        SchemeRegistry schReg = conMgr.getSchemeRegistry();
        for(String scheme : schReg.getSchemeNames()) {
            Log.v(TAG, "Scheme: " + scheme + ", port: " +
                    schReg.getScheme(scheme).getDefaultPort() +
                    ", factory: " +
                    schReg.getScheme(scheme).getSocketFactory().getClass().getName());
        }

        HttpParams params = httpClient.getParams();

        Log.v(TAG, "http.protocol.version: " + params.getParameter("http.protocol.version"));
        Log.v(TAG, "http.protocol.content-charset: " + params.getParameter("http.protocol.content-charset"));
        Log.v(TAG, "http.protocol.handle-redirects: " + params.getParameter("http.protocol.handle-redirects"));
        Log.v(TAG, "http.conn-manager.timeout: " + params.getParameter("http.conn-manager.timeout"));
        Log.v(TAG, "http.socket.timeout: " + params.getParameter("http.socket.timeout"));
        Log.v(TAG, "http.connection.timeout: " + params.getParameter("http.connection.timeout"));
        return new DefaultHttpClient(conMgr, params);
    }

    public HttpClient getHttpClient() {
        if(httpClient == null){
            httpClient = createHttpClient();
        }
        return httpClient;
    }
    @Override
    public void onLowMemory()
    {
        super.onLowMemory();
        shutdownHttpClient();
    }

    @Override
    public void onTerminate()
    {
        super.onTerminate();
        shutdownHttpClient();
    }

    private void shutdownHttpClient()
    {
        if(httpClient!=null && httpClient.getConnectionManager()!=null)
        {
            httpClient.getConnectionManager().shutdown();
            httpClient = null;
        }
    }

}
