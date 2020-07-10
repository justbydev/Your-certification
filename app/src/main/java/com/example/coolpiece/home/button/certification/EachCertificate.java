package com.example.coolpiece.home.button.certification;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.coolpiece.R;
import com.example.coolpiece.home.button.academy.AcademyAdapter;
import com.example.coolpiece.home.button.academy.NotConnectAcademyAdapter;
import com.example.coolpiece.splash.dataclass.AcaCerti;
import com.example.coolpiece.splash.dataclass.Gineongsa;
import com.example.coolpiece.splash.dataclass.Gisa;
import com.example.coolpiece.splash.dataclass.Gisul;
import com.example.coolpiece.splash.dataclass.Guitar;
import com.example.coolpiece.splash.dataclass.Sanup;
import com.example.coolpiece.splash.manageclass.ManageAcaCertData;

import java.util.ArrayList;

public class EachCertificate extends AppCompatActivity {
    TextView name;
    TextView back_button;
    Button certificate_detail;
    RecyclerView each_academy;
    RecyclerView not_connect_each_academy;
    Gisul gisul;
    Gineongsa gineongsa;
    Gisa gisa;
    Sanup sanup;
    Guitar guitar;
    Intent intent;
    Intent passdetailintent;
    RecyclerView.LayoutManager layoutManager;
    RecyclerView.LayoutManager otherlayoutManager;
    AcademyAdapter mAdapter;
    NotConnectAcademyAdapter notConnectAcademyAdapter;
    ArrayList<String> academy_name;
    ArrayList<String> not_academy_name;
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

        back_button.setOnClickListener(buttononclicklistener);
        certificate_detail.setOnClickListener(buttononclicklistener);

        intent=getIntent();


        cat=intent.getStringExtra("cat");
        if(cat.equals("기술사")){
            gisul=new Gisul();
            gisul=intent.getParcelableExtra("certification");
            name.setText(gisul.getName());

            layoutManager=new LinearLayoutManager(this);
            each_academy.setLayoutManager(layoutManager);


            otherlayoutManager=new LinearLayoutManager(this);
            not_connect_each_academy.setLayoutManager(otherlayoutManager);

            academy_name=new ArrayList<>();
            not_academy_name=new ArrayList<>();
            ArrayList<AcaCerti> total=new ArrayList<>();
            total= ManageAcaCertData.getInstance().getAcacerti_total();
            String n=gisul.getName();
            for(int i=0; i<total.size(); i++){
                if(n.equals(total.get(i).getCert())){
                    if(total.get(i).getConnect()==1){
                        academy_name.add(total.get(i).getAcademy());
                    }
                    else{
                        not_academy_name.add(total.get(i).getAcademy());
                    }
                }
            }
            /*for(int i=0; i<gisul.getAcademy_name().size(); i++){
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
            }
            academy_name=new ArrayList<>();
            academy_name=gisul.getAcademy_name();
            academy_address=new ArrayList<>();
            academy_address=gisul.getAcademy_address();
            academy_phone=new ArrayList<>();
            academy_phone=gisul.getAcademy_phone();*/

            mAdapter=new AcademyAdapter(academy_name);
            each_academy.setAdapter(mAdapter);

            notConnectAcademyAdapter=new NotConnectAcademyAdapter(not_academy_name);
            not_connect_each_academy.setAdapter(notConnectAcademyAdapter);

            passdetailintent=new Intent(EachCertificate.this, CertificateDetail.class);
            passdetailintent.putExtra("detail", gisul);
            passdetailintent.putExtra("cat", cat);
        }
        else if(cat.equals("기능사")){
            gineongsa=new Gineongsa();
            gineongsa=intent.getParcelableExtra("certification");
            name.setText(gineongsa.getName());


            layoutManager=new LinearLayoutManager(this);
            each_academy.setLayoutManager(layoutManager);

            otherlayoutManager=new LinearLayoutManager(this);
            not_connect_each_academy.setLayoutManager(otherlayoutManager);

            academy_name=new ArrayList<>();
            not_academy_name=new ArrayList<>();
            /*for(int i=0; i<gineongsa.getAcademy_name().size(); i++){
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
            }*/
            ArrayList<AcaCerti> total=new ArrayList<>();
            total= ManageAcaCertData.getInstance().getAcacerti_total();
            String n=gineongsa.getName();
            for(int i=0; i<total.size(); i++){
                if(n.equals(total.get(i).getCert())){
                    if(total.get(i).getConnect()==1){
                        academy_name.add(total.get(i).getAcademy());
                    }
                    else{
                        not_academy_name.add(total.get(i).getAcademy());
                    }
                }
            }

            mAdapter=new AcademyAdapter(academy_name);
            each_academy.setAdapter(mAdapter);

            notConnectAcademyAdapter=new NotConnectAcademyAdapter(not_academy_name);
            not_connect_each_academy.setAdapter(notConnectAcademyAdapter);

            passdetailintent=new Intent(EachCertificate.this, CertificateDetail.class);
            passdetailintent.putExtra("detail", gineongsa);
            passdetailintent.putExtra("cat", cat);
        }
        else if(cat.equals("기사")){
            gisa=new Gisa();
            gisa=intent.getParcelableExtra("certification");
            name.setText(gisa.getName());

            layoutManager=new LinearLayoutManager(this);
            each_academy.setLayoutManager(layoutManager);

            otherlayoutManager=new LinearLayoutManager(this);
            not_connect_each_academy.setLayoutManager(otherlayoutManager);

            academy_name=new ArrayList<>();
            not_academy_name=new ArrayList<>();
            /*for(int i=0; i<gisa.getAcademy_name().size(); i++){
                if(gisa.getAcademy_connect().get(i)=="1"){
                    academy_name.add(gisa.getAcademy_name().get(i));
                    academy_address.add(gisa.getAcademy_address().get(i));
                    academy_phone.add(gisa.getAcademy_phone().get(i));
                }
                else{
                    not_academy_name.add(gisa.getAcademy_name().get(i));
                    not_academy_address.add(gisa.getAcademy_address().get(i));
                    not_academy_phone.add(gisa.getAcademy_phone().get(i));
                }
            }*/
            ArrayList<AcaCerti> total=new ArrayList<>();
            total= ManageAcaCertData.getInstance().getAcacerti_total();
            String n=gisa.getName();
            for(int i=0; i<total.size(); i++){
                if(n.equals(total.get(i).getCert())){
                    if(total.get(i).getConnect()==1){
                        academy_name.add(total.get(i).getAcademy());
                    }
                    else{
                        not_academy_name.add(total.get(i).getAcademy());
                    }
                }
            }

            mAdapter=new AcademyAdapter(academy_name);
            each_academy.setAdapter(mAdapter);

            notConnectAcademyAdapter=new NotConnectAcademyAdapter(not_academy_name);
            not_connect_each_academy.setAdapter(notConnectAcademyAdapter);

            passdetailintent=new Intent(EachCertificate.this, CertificateDetail.class);
            passdetailintent.putExtra("detail", gisa);
            passdetailintent.putExtra("cat", cat);
        }
        else if(cat.equals("산업기사")){
            sanup=new Sanup();
            sanup=intent.getParcelableExtra("certification");
            name.setText(sanup.getName());

            layoutManager=new LinearLayoutManager(this);
            each_academy.setLayoutManager(layoutManager);

            otherlayoutManager=new LinearLayoutManager(this);
            not_connect_each_academy.setLayoutManager(otherlayoutManager);

            academy_name=new ArrayList<>();
            not_academy_name=new ArrayList<>();
            /*for(int i=0; i<sanup.getAcademy_name().size(); i++){
                if(sanup.getAcademy_connect().get(i)=="1"){
                    academy_name.add(sanup.getAcademy_name().get(i));
                    academy_address.add(sanup.getAcademy_address().get(i));
                    academy_phone.add(sanup.getAcademy_phone().get(i));
                }
                else{
                    not_academy_name.add(sanup.getAcademy_name().get(i));
                    not_academy_address.add(sanup.getAcademy_address().get(i));
                    not_academy_phone.add(sanup.getAcademy_phone().get(i));
                }
            }*/
            ArrayList<AcaCerti> total=new ArrayList<>();
            total= ManageAcaCertData.getInstance().getAcacerti_total();
            String n=sanup.getName();
            for(int i=0; i<total.size(); i++){
                if(n.equals(total.get(i).getCert())){
                    if(total.get(i).getConnect()==1){
                        academy_name.add(total.get(i).getAcademy());
                    }
                    else{
                        not_academy_name.add(total.get(i).getAcademy());
                    }
                }
            }

            mAdapter=new AcademyAdapter(academy_name);
            each_academy.setAdapter(mAdapter);

            notConnectAcademyAdapter=new NotConnectAcademyAdapter(not_academy_name);
            not_connect_each_academy.setAdapter(notConnectAcademyAdapter);

            passdetailintent=new Intent(EachCertificate.this, CertificateDetail.class);
            passdetailintent.putExtra("detail", sanup);
            passdetailintent.putExtra("cat", cat);
        }
        else if(cat.equals("지도사/기타/취미")){
            guitar=new Guitar();
            guitar=intent.getParcelableExtra("certification");
            name.setText(guitar.getName());

            layoutManager=new LinearLayoutManager(this);
            each_academy.setLayoutManager(layoutManager);

            otherlayoutManager=new LinearLayoutManager(this);
            not_connect_each_academy.setLayoutManager(otherlayoutManager);

            academy_name=new ArrayList<>();
            not_academy_name=new ArrayList<>();
            /*for(int i=0; i<guitar.getAcademy_name().size(); i++){
                if(guitar.getAcademy_connect().get(i)=="1"){
                    academy_name.add(guitar.getAcademy_name().get(i));
                    academy_address.add(guitar.getAcademy_address().get(i));
                    academy_phone.add(guitar.getAcademy_phone().get(i));
                }
                else{
                    not_academy_name.add(guitar.getAcademy_name().get(i));
                    not_academy_address.add(guitar.getAcademy_address().get(i));
                    not_academy_phone.add(guitar.getAcademy_phone().get(i));
                }
            }*/
            ArrayList<AcaCerti> total=new ArrayList<>();
            total= ManageAcaCertData.getInstance().getAcacerti_total();
            String n=guitar.getName();
            for(int i=0; i<total.size(); i++){
                if(n.equals(total.get(i).getCert())){
                    if(total.get(i).getConnect()==1){
                        academy_name.add(total.get(i).getAcademy());
                    }
                    else{
                        not_academy_name.add(total.get(i).getAcademy());
                    }
                }
            }

            mAdapter=new AcademyAdapter(academy_name);
            each_academy.setAdapter(mAdapter);

            notConnectAcademyAdapter=new NotConnectAcademyAdapter(not_academy_name);
            not_connect_each_academy.setAdapter(notConnectAcademyAdapter);

            passdetailintent=new Intent(EachCertificate.this, CertificateGuitarDetail.class);
            passdetailintent.putExtra("detail", guitar);
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
