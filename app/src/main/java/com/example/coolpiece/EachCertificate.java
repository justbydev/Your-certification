package com.example.coolpiece;

import android.content.Context;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
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
    Button certificate_detail;
    Button certificate_calender;
    RecyclerView each_academy;
    RecyclerView not_connect_each_academy;
    Gisul gisul;
    Gineongsa gineongsa;
    Intent intent;
    Intent passdetailintent;
    RecyclerView.LayoutManager layoutManager;
    RecyclerView.LayoutManager otherlayoutManager;
    AcademyAdapter mAdapter;
    NotConnectAcademyAdapter notConnectAcademyAdapter;
    ArrayList<String> academy_name;
    ArrayList<String> academy_address;
    ArrayList<String> academy_phone;
    ArrayList<String> not_academy_name;
    ArrayList<String> not_academy_address;
    ArrayList<String> not_academy_phone;
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
        not_connect_each_academy=(RecyclerView)findViewById(R.id.not_connect_each_academy);
        certificate_detail=(Button)findViewById(R.id.certificate_detail);
        certificate_calender=(Button)findViewById(R.id.certificate_calender);

        back_button.setOnClickListener(buttononclicklistener);
        certificate_detail.setOnClickListener(buttononclicklistener);
        certificate_calender.setOnClickListener(buttononclicklistener);

        intent=getIntent();


        cat=intent.getStringExtra("cat");
        if(cat.equals("기술사")){
            gisul=new Gisul();
            gisul=intent.getParcelableExtra("certification");
            name.setText(gisul.getName());

            each_academy.setHasFixedSize(true);
            layoutManager=new LinearLayoutManager(this);
            each_academy.setLayoutManager(layoutManager);

            not_connect_each_academy.setHasFixedSize(true);
            otherlayoutManager=new LinearLayoutManager(this);
            not_connect_each_academy.setLayoutManager(otherlayoutManager);

            /*academy_name=new ArrayList<>();
            academy_address=new ArrayList<>();
            academy_phone=new ArrayList<>();
            not_academy_name=new ArrayList<>();
            not_academy_address=new ArrayList<>();
            not_academy_phone=new ArrayList<>();
            for(int i=0; i<gisul.getAcademy_name().size(); i++){
                if(gisul.getAcademy_connect().get(i)=="1"){
                    academy_name.add(gisul.getAcademy_name().get(i));
                    academy_address.add(gisul.getAcademy_address().get(i));
                    academy_phone.add(gisul.getAcademy_phone().get(i));
                }
                else{
                    not_academy_name.add(gisul.getAcademy_name().get(i));
                    not_academy_address.add(gisul.getAcademy_address().get(i));
                    not_academy_phone.add(gisul.getAcademy_phone().get(i));
                }
            }*/
            academy_name=new ArrayList<>();
            academy_name=gisul.getAcademy_name();
            academy_address=new ArrayList<>();
            academy_address=gisul.getAcademy_address();
            academy_phone=new ArrayList<>();
            academy_phone=gisul.getAcademy_phone();

            mAdapter=new AcademyAdapter(academy_name, academy_address, academy_phone);
            each_academy.setAdapter(mAdapter);

            notConnectAcademyAdapter=new NotConnectAcademyAdapter(not_academy_name, not_academy_address, not_academy_phone);
            not_connect_each_academy.setAdapter(notConnectAcademyAdapter);

            passdetailintent=new Intent(EachCertificate.this, CertificateDetail.class);
            passdetailintent.putExtra("detail", gisul);
            passdetailintent.putExtra("cat", cat);
        }
        else if(cat.equals("기능사")){
            gineongsa=new Gineongsa();
            gineongsa=intent.getParcelableExtra("certification");
            name.setText(gineongsa.getName());

            each_academy.setHasFixedSize(true);
            layoutManager=new LinearLayoutManager(this);
            each_academy.setLayoutManager(layoutManager);

            not_connect_each_academy.setHasFixedSize(true);
            otherlayoutManager=new LinearLayoutManager(this);
            not_connect_each_academy.setLayoutManager(otherlayoutManager);

            academy_name=new ArrayList<>();
            academy_address=new ArrayList<>();
            academy_phone=new ArrayList<>();
            not_academy_name=new ArrayList<>();
            not_academy_address=new ArrayList<>();
            not_academy_phone=new ArrayList<>();
            for(int i=0; i<gineongsa.getAcademy_name().size(); i++){
                if(gineongsa.getAcademy_connect().get(i).equals("yes")){
                    academy_name.add(gineongsa.getAcademy_name().get(i));
                    academy_address.add(gineongsa.getAcademy_address().get(i));
                    academy_phone.add(gineongsa.getAcademy_phone().get(i));
                }
                else{
                    not_academy_name.add(gineongsa.getAcademy_name().get(i));
                    not_academy_address.add(gineongsa.getAcademy_address().get(i));
                    not_academy_phone.add(gineongsa.getAcademy_phone().get(i));
                }
            }


            mAdapter=new AcademyAdapter(academy_name, academy_address, academy_phone);
            each_academy.setAdapter(mAdapter);

            notConnectAcademyAdapter=new NotConnectAcademyAdapter(not_academy_name, not_academy_address, not_academy_phone);
            not_connect_each_academy.setAdapter(notConnectAcademyAdapter);

            passdetailintent=new Intent(EachCertificate.this, CertificateDetail.class);
            passdetailintent.putExtra("detail", gineongsa);
            passdetailintent.putExtra("cat", cat);
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
                case R.id.certificate_detail:
                    startActivity(passdetailintent);
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
