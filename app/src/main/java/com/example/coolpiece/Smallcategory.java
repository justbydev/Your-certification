package com.example.coolpiece;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class Smallcategory extends AppCompatActivity {
    TextView smallcategory;
    RecyclerView certificate_recycler;
    private Intent intent;
    String small;
    String big;
    ArrayList<Gisul> gisuls=null;
    ArrayList<Gineongsa> gineongs=null;
    ArrayList<Gisa> gisas;

    private GisulAdapter gisulAdapter;
    private GineongAdapter gineongAdapter;
    private RecyclerView.LayoutManager layoutManager;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.smallcategory);

        smallcategory=(TextView)findViewById(R.id.smallcategory);
        certificate_recycler=(RecyclerView)findViewById(R.id.certificate_recycler);

        gisuls=new ArrayList<Gisul>();
        gineongs=new ArrayList<Gineongsa>();

        certificate_recycler.setHasFixedSize(true);
        layoutManager=new LinearLayoutManager(this);
        certificate_recycler.setLayoutManager(layoutManager);

        intent=getIntent();
        small=intent.getStringExtra("SCA");
        big=intent.getStringExtra("BCA");
        smallcategory.setText(big+'-'+small);

        if(big.equals("기술사")){
            //System.out.println("=======================here======================");
            gisuls=intent.getParcelableArrayListExtra("Smallcategory");
            gisulAdapter=new GisulAdapter(gisuls);
            certificate_recycler.setAdapter(gisulAdapter);
        }
        else if(big.equals("기능사")){
            gineongs= intent.getParcelableArrayListExtra("Smallcategory");
            gineongAdapter=new GineongAdapter(gineongs);
            certificate_recycler.setAdapter(gineongAdapter);
        }

    }


}
