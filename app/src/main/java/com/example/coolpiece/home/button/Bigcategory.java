package com.example.coolpiece.home.button;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.coolpiece.splash.dataclass.Gisa;
import com.example.coolpiece.splash.dataclass.Sanup;
import com.example.coolpiece.splash.manageclass.ManageGineongData;
import com.example.coolpiece.splash.manageclass.ManageGisaData;
import com.example.coolpiece.splash.manageclass.ManageGisulData;
import com.example.coolpiece.R;
import com.example.coolpiece.splash.dataclass.Gineongsa;
import com.example.coolpiece.splash.dataclass.Gisul;
import com.example.coolpiece.splash.manageclass.ManageSanupData;

import java.util.ArrayList;

public class Bigcategory extends AppCompatActivity {
    TextView bigcategory;
    TextView back_button;
    Spinner smallcategory;
    RecyclerView certificate_recycler;
    private Intent intent;
    String big;
    String array1[] = new String[]{"건설", "경영.회계.사무", "광업자원", "기계", "농림어업",
    "문화.예술.디자인.방송", "보건.의료", "사회복지.종교", "섬유.의복", "식품.가공", "안전관리", "영업.판매",
    "운전.운송", "음식서비스", "이용.숙박.여행.오락.스포츠", "인쇄.목재.가구.공예", "재료", "전기.전자", "정보통신",
    "화학", "환경.에너지"};

    private GisulAdapter gisulAdapter;
    private GineongAdapter gineongAdapter;
    private GisaAdapter gisaAdapter;
    private SanupAdapter sanupAdapter;
    private RecyclerView.LayoutManager layoutManager;
    ArrayList<Gisul> gisuls=null;
    ArrayList<Gineongsa> gineongsas=null;
    ArrayList<Gisa> gisas=null;
    ArrayList<Sanup> sanups=null;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bigcategory);

        back_button=(TextView)findViewById(R.id.back_button);
        certificate_recycler=(RecyclerView)findViewById(R.id.certificate_recycler);
        bigcategory=(TextView)findViewById(R.id.bigcategory);

        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        smallcategory=(Spinner)findViewById(R.id.small_category_spinner);
        intent=getIntent();
        big=intent.getStringExtra("CA");

        bigcategory.setText(big);

        gisuls=new ArrayList<>();
        gineongsas=new ArrayList<>();
        gisas=new ArrayList<>();
        sanups=new ArrayList<>();

        /***for certification list recyclerview***/
        certificate_recycler.setHasFixedSize(true);
        layoutManager=new LinearLayoutManager(this);
        certificate_recycler.setLayoutManager(layoutManager);
        if(big.equals("기술사")){
            gisuls=select_gisul(0);
            gisulAdapter=new GisulAdapter(gisuls, big);
            certificate_recycler.setAdapter(gisulAdapter);
        }
        else if(big.equals("기능사")){
            gineongsas=select_gineong(0);
            gineongAdapter=new GineongAdapter(gineongsas, big);
            certificate_recycler.setAdapter(gineongAdapter);
        }
        else if(big.equals("기사")){
            gisas=select_gisa(0);
            gisaAdapter=new GisaAdapter(gisas, big);
            certificate_recycler.setAdapter(gisaAdapter);
        }
        else if(big.equals("산업기사")){
            sanups=select_sanup(0);
            sanupAdapter=new SanupAdapter(sanups, big);
            certificate_recycler.setAdapter(sanupAdapter);
        }


        smallcategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //전체 데이터가 모두 들어오면 position에 의 한 if 문은 사라질 것

                if(big.equals("기술사")){
                    gisulAdapter.removeall(select_gisul(position));
                }
                else if(big.equals("기능사")){
                    gineongAdapter.removeall(select_gineong(position));
                }
                else if(big.equals("기사")){
                    gisaAdapter.removeall(select_gisa(position));
                }
                else if(big.equals("산업기사")){
                    sanupAdapter.removeall(select_sanup(position));
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


    };
    public ArrayList<Gisul> select_gisul(int index){
        return ManageGisulData.getInstance().getGisulArrayList(index);
    }
    public ArrayList<Gineongsa> select_gineong(int index){
        return ManageGineongData.getInstance().getGineongArrayList(index);
    }
    public ArrayList<Gisa> select_gisa(int index){
        return ManageGisaData.getInstance().getGisaArrayList(index);
    }
    public ArrayList<Sanup> select_sanup(int index){
        return ManageSanupData.getInstance().getSanupArrayList(index);
    }
    @Override
    public void onBackPressed() {
        finish();
    }
}
