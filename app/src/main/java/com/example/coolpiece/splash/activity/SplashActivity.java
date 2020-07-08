package com.example.coolpiece.splash.activity;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.coolpiece.MainFragment;
import com.example.coolpiece.splash.manageclass.ManageGineongData;
import com.example.coolpiece.splash.manageclass.ManageGisaData;
import com.example.coolpiece.splash.manageclass.ManageGisulData;
import com.example.coolpiece.splash.manageclass.ManageGuitarData;
import com.example.coolpiece.splash.manageclass.ManageSanupData;

import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import io.realm.Realm;

public class SplashActivity extends AppCompatActivity {
    /****for json data url from firebase storage****/
    String []url=new String[]{"https://firebasestorage.googleapis.com/v0/b/pusan-4628a.appspot.com/o/Data%2Fgisulsa.json?alt=media&token=45abe4b7-470e-4761-a355-3fa486dff7fe",
            "https://firebasestorage.googleapis.com/v0/b/pusan-4628a.appspot.com/o/Data%2Fgineungs.json?alt=media&token=a6048df9-9b2a-4ddb-b907-c4e01ff75b9c",
    "https://firebasestorage.googleapis.com/v0/b/pusan-4628a.appspot.com/o/Data%2Fgisa.json?alt=media&token=e529244d-d5ce-4015-ac99-7417c366486d",
    "https://firebasestorage.googleapis.com/v0/b/pusan-4628a.appspot.com/o/Data%2Fsanupgisa.json?alt=media&token=e1e7ab93-421b-42da-972b-3c1dab0cd9ad",
    "https://firebasestorage.googleapis.com/v0/b/pusan-4628a.appspot.com/o/Data%2Fdata.json?alt=media&token=b6fcd08f-ff61-4917-b0cf-2fbc63f34918"};
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
            String gisajson=getjsonHtml(urls[2]);
            try {
                ManageGisaData.getInstance().startParsing(gisajson);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            String sanupjson=getjsonHtml(urls[3]);
            try {
                ManageSanupData.getInstance().startParsing(sanupjson);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            String guitarjson=getjsonHtml(urls[4]);
            try {
                ManageGuitarData.getInstance().startParsing(guitarjson);
            } catch (JSONException e) {
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
