package com.example.coolpiece;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class EachCertificate extends AppCompatActivity {
    TextView name;
    RecyclerView each_academy;
    Gineongsa gineongsa;
    Intent intent;
    RecyclerView.LayoutManager layoutManager;
    AcademyAdapter mAdapter;
    ArrayList<String> academy_name;
    ArrayList<String> academy_address;
    ArrayList<String> academy_phone;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.each_certificate);

        name=(TextView)findViewById(R.id.name);
        each_academy=(RecyclerView)findViewById(R.id.each_academy);

        intent=getIntent();

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
