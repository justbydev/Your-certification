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

import com.example.coolpiece.R;
import com.example.coolpiece.splash.dataclass.Guitar;
import com.example.coolpiece.splash.manageclass.ManageGuitarData;

import java.util.ArrayList;

public class Bigcategory_guitar extends AppCompatActivity {
    TextView bigcategory;
    TextView back_button;
    Spinner smallcategory;
    RecyclerView certificate_recycler;

    private Intent intent;
    String big;

    private GuitarAdapter guitarAdapter;
    private RecyclerView.LayoutManager layoutManager;
    ArrayList<Guitar> guitars=null;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bigcategory_guitar);

        back_button=(TextView)findViewById(R.id.back_button);
        certificate_recycler=(RecyclerView)findViewById(R.id.certificate_recycler);
        bigcategory=(TextView)findViewById(R.id.bigcategory);

        smallcategory=(Spinner)findViewById(R.id.small_category_spinner);
        intent=getIntent();
        big=intent.getStringExtra("CA");
        bigcategory.setText(big);

        guitars=new ArrayList<>();

        certificate_recycler.setHasFixedSize(true);
        layoutManager=new LinearLayoutManager(this);
        certificate_recycler.setLayoutManager(layoutManager);

        guitars=select_guitar(0);
        System.out.println(guitars.get(0).getName());
        System.out.println("=============================================");
        guitarAdapter=new GuitarAdapter(guitars, big);
        certificate_recycler.setAdapter(guitarAdapter);

        smallcategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                guitarAdapter.removeall(select_guitar(position));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    public ArrayList<Guitar> select_guitar(int index){
        return ManageGuitarData.getInstance().getGuitarArrayList(index);
    }
}
