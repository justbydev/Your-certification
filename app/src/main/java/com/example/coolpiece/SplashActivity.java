package com.example.coolpiece;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class SplashActivity extends AppCompatActivity {
    /****for json data url from firebase storage****/
    String []url=new String[]{"https://firebasestorage.googleapis.com/v0/b/pusan-4628a.appspot.com/o/Data%2Fgisulsa.json?alt=media&token=07bd1f1b-4ba3-40fa-8a8a-605a4833076a",
            "https://firebasestorage.googleapis.com/v0/b/pusan-4628a.appspot.com/o/Data%2Fgineung.json?alt=media&token=7ff66a5c-f596-482c-a691-23f3a8b2eba0"};
    public static Context context;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context=this;
        new getData().execute(url);
    }


    private class getData extends AsyncTask<String, Void, String>{

        //execute's parameter url is the String array and it used in urls of doInBackground
        //in doInBackground parameter, its type is array
        @Override
        protected String doInBackground(String... urls) {
            String gisuljson=getjsonHtml(urls[0]);
            try {
                ManageGisulData.getInstance().startParsing(gisuljson);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            String gineongjson=getjsonHtml(urls[1]);
            try {
                ManageGineongData.getInstance().startParsing(gineongjson);
            } catch (JSONException | IOException e) {
                e.printStackTrace();
            }
            return "";
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            //have to write code Internet connection


            Intent intent=new Intent(getApplicationContext(), MainFragment.class);
            startActivity(intent);
            finish();
        }

        /****this is the method for getting json datas of each part by String****/
        private String getjsonHtml(String url){
            String jsonHtml="";
            HttpURLConnection con=null;
            InputStreamReader isr=null;
            BufferedReader br=null;
            try{
                /****get Url connection****/
                URL Url=new URL(url);
                con=(HttpURLConnection)Url.openConnection();

                /****get inputstream****/
                isr=new InputStreamReader(con.getInputStream(), "UTF-8");
                br=new BufferedReader(isr);

                String str=null;
                while((str=br.readLine())!=null){
                    //System.out.println(str);
                    jsonHtml+=str+"\n";
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return jsonHtml;
        }
    }




}
