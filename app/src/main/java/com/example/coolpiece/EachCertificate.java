package com.example.coolpiece;

import android.content.Context;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

public class EachCertificate extends AppCompatActivity {
    TextView name;
    TextView back_button;
    RecyclerView each_academy;
    Gisul gisul;
    Gineongsa gineongsa;
    Intent intent;
    RecyclerView.LayoutManager layoutManager;
    AcademyAdapter mAdapter;
    ArrayList<String> academy_name;
    ArrayList<String> academy_address;
    ArrayList<String> academy_phone;
    String cat;

    public static Context context;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.each_certificate);
        context=this;

        name=(TextView)findViewById(R.id.name);
        back_button=(TextView)findViewById(R.id.back_button);
        each_academy=(RecyclerView)findViewById(R.id.each_academy);

        back_button.setOnClickListener(buttononclicklistener);

        intent=getIntent();


        cat=intent.getStringExtra("cat");
        if(cat.equals("gisul")){
            gisul=new Gisul();
            gisul=intent.getParcelableExtra("academy");
            name.setText(gisul.getName());

            each_academy.setHasFixedSize(true);
            layoutManager=new LinearLayoutManager(this);
            each_academy.setLayoutManager(layoutManager);

            academy_name=new ArrayList<>();
            academy_name=gisul.getAcademy_name();
            academy_address=new ArrayList<>();
            academy_address=gisul.getAcademy_address();
            academy_phone=new ArrayList<>();
            academy_phone=gisul.getAcademy_phone();


            mAdapter=new AcademyAdapter(academy_name, academy_address, academy_phone);
            each_academy.setAdapter(mAdapter);
        }
        else if(cat.equals("gineong")){
            gineongsa=new Gineongsa();
            gineongsa=intent.getParcelableExtra("academy");
            name.setText(gineongsa.getName());

            each_academy.setHasFixedSize(true);
            layoutManager=new LinearLayoutManager(this);
            each_academy.setLayoutManager(layoutManager);

            academy_name=new ArrayList<>();
            academy_name=gineongsa.getAcademy_name();
            academy_address=new ArrayList<>();
            academy_address=gineongsa.getAcademy_address();
            academy_phone=new ArrayList<>();
            academy_phone=gineongsa.getAcademy_phone();


            mAdapter=new AcademyAdapter(academy_name, academy_address, academy_phone);
            each_academy.setAdapter(mAdapter);

        }


    }

    private View.OnClickListener buttononclicklistener=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            int id=v.getId();
            switch(id){
                case R.id.back_button:
                    finish();
                    return;
                default:
                    return;
            }
        }
    };

    @Override
    public void onBackPressed() {
        finish();
    }
}
