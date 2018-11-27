package com.bwie.yinzhiyou20181123.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class NetUtils {
    private static NetUtils instance;
    private final Gson gson;
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
           // super.handleMessage(msg);
            //handler.sendEmptyMessageDelayed(0,2000);
            

        }
    };

    public void getResult2(final String surtur, final Class clazz, final CallBacK callBacK){
        new Thread(){
            @Override
            public void run() {
               // super.run();
                //getResult2(surtur,clazz,callBacK);
                //handler.sendEmptyMessageDelayed(0,2000);
                final Object o = getResult(surtur, clazz);
                handler.post(
                        new Runnable() {
                            @Override
                            public void run() {
                                callBacK.onSuccess(o);
                            }
                        }
                );
            }
        }.start();
    }
    private NetUtils(){
        gson = new Gson();
    }
    public static NetUtils getInstance(){
    if (instance==null){
        instance=new NetUtils();
    }
        return instance;
    }
    public boolean hasNetwork(Context context){
        ConnectivityManager cm= (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = cm.getActiveNetworkInfo();
        return activeNetworkInfo!=null&&activeNetworkInfo.isAvailable();
    }
    public interface CallBacK<E>{
        void onSuccess(E e);
    }
    public void getResult(final String surtur, final Class clazz, final CallBacK callBacK){
        new Thread(new Runnable() {
            @Override
            public void run() {
                final Object o = getResult(surtur,clazz);
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        callBacK.onSuccess(o);
                    }
                });
            }
        }).start();
        /*new AsyncTask<String,Void,Object>(){
            @Override
            protected Object doInBackground(String... strings) {
                return getResult(surtur,clazz);
            }

            @Override
            protected void onPostExecute(Object o) {
                callBacK.onSuccess(o);
            }
        }.execute(surtur);*/
    }
    public <E> E getResult(String surtur,Class clazz){

        return (E) gson.fromJson(getResult(surtur),clazz);
    }
    public String getResult(String surtur){
        String result="";
        try {
            URL url=new URL(surtur);
           HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
           urlConnection.setRequestMethod("GET");
           urlConnection.setReadTimeout(5000);
           urlConnection.setConnectTimeout(5000);
            int responseCode = urlConnection.getResponseCode();
            if (responseCode==200){
                result=Stream2(urlConnection.getInputStream());
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    private String Stream2(InputStream inputStream) throws IOException {
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        StringBuffer stringBuffer = new StringBuffer();
        for (String tem=bufferedReader.readLine();tem!=null;tem=bufferedReader.readLine()){
            stringBuffer.append(tem);
        }

        return stringBuffer.toString();
    }
}
