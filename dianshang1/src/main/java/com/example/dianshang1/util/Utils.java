package com.example.dianshang1.util;

import android.os.AsyncTask;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import javax.security.auth.callback.Callback;

public class Utils {
    private static Utils instance;
    private final Gson gson;

    private Utils(){
        gson = new Gson();
    }
    public static Utils getInstance(){
    if (instance==null){
        instance=new Utils();
    }
        return instance;
    }
    public <E> E getResult(String strUrl, Class calzz){

        return (E) gson.fromJson(getResult(strUrl),calzz);
    }
   public static interface CallCack<E>{
        void onSuccess(E e);
    }
    public void getResult(final String strUrl, final Class calzz, final CallCack callcack){
        new AsyncTask<String,Void,Object>(){
            @Override
            protected Object doInBackground(String... strings) {
                return getResult(strUrl,calzz);
            }

            @Override
            protected void onPostExecute(Object o) {
                callcack.onSuccess(o);
            }
        }.execute(strUrl);
    }
    public String getResult(String strUrl){
        String result="";
        try {
            URL url=new URL(strUrl);
          HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
          urlConnection.setRequestMethod("GET");
          urlConnection.setConnectTimeout(5000);
          urlConnection.setReadTimeout(5000);
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
