package com.androidbook.services.httpget;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import com.vot.wq.services.R;

public class HttpDemo extends Activity implements Runnable {
    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        httpClient = ((MyApplication)this.getApplication()).getHttpClient();
        new Thread(this).start();
    }

    private HttpClient httpClient;
    public void run()
    {
        try {
            HttpGet request = new HttpGet("http://www.baidu.com/");
            HttpParams params = request.getParams();
            HttpConnectionParams.setSoTimeout(params, 60000);   // 1 minute
            request.setParams(params);
            Log.v("connection timeout", String.valueOf(HttpConnectionParams.getConnectionTimeout(params)));
            Log.v("socket timeout", String.valueOf(HttpConnectionParams.getSoTimeout(params)));

            HttpResponse response = httpClient.execute(request);

            String page= EntityUtils.toString(response.getEntity());
            System.out.println(page);
        } catch (IOException e) {
            // covers:
            //      ClientProtocolException
            //      ConnectTimeoutException
            //      ConnectionPoolTimeoutException
            //      SocketTimeoutException
            e.printStackTrace();
        }
    }

    public void run2() {
        BufferedReader in = null;
        try {
            HttpClient client = new DefaultHttpClient();
            HttpPost request = new HttpPost(
                    "http://www.androidbook.com/akc/display");
            List<NameValuePair> postParameters = new ArrayList<>();
            postParameters.add(new BasicNameValuePair("url", "DisplayNoteIMPURL"));
            postParameters.add(new BasicNameValuePair("reportId", "4788"));
            postParameters.add(new BasicNameValuePair("ownerUserId", "android"));
            postParameters.add(new BasicNameValuePair("aspire_output_format", "embedded-xml"));

            UrlEncodedFormEntity formEntity = new UrlEncodedFormEntity(postParameters);

            request.setEntity(formEntity);
            HttpResponse response = client.execute(request);
            in = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

            StringBuffer sb = new StringBuffer("");
            String line = "";
            String NL = System.getProperty("line.separator");
            while ((line = in.readLine()) != null) {
                sb.append(line + NL);
            }
            in.close();

            String result = sb.toString();
            System.out.println(result);
        } catch (Exception e) {
            // Do something about exceptions
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void run1() {
        BufferedReader in = null;
        try {
            HttpClient client = new DefaultHttpClient();
            HttpGet request = new HttpGet("https://www.baidu.com/");

            HttpResponse response = client.execute(request);
            in = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

            StringBuffer sb = new StringBuffer("");
            String line = "";
            String NL = System.getProperty("line.separator");
            while ((line = in.readLine()) != null) {
                sb.append(line + NL);
            }
            in.close();

            String page = sb.toString();
            System.out.println(page);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}