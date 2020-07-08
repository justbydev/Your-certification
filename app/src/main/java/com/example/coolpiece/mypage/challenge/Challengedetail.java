package com.example.coolpiece.mypage.challenge;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.coolpiece.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class Challengedetail extends AppCompatActivity {
    TextView challengetitle;
    TextView successday;
    TextView success_percent;
    RecyclerView check_recyclerview;
    Button authen_button;
    Intent intent;
    String certification;
    String day;
    String point;
    String attend;
    String startdate;
    ArrayList<String> day_check;
    int okday=0;
    int percentage=0;
    int today_check=0;
    int finish_flag=1;

    String title;
    DatabaseReference databaseReference;

    RecyclerView.LayoutManager layoutManager;
    ChallengeGridAdapter challengeGridAdapter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mychallenge_detail);

        challengetitle=(TextView)findViewById(R.id.challenge_title);
        successday=(TextView)findViewById(R.id.successday);
        success_percent=(TextView)findViewById(R.id.success_percent);
        check_recyclerview=(RecyclerView)findViewById(R.id.check_recyclerview);
        authen_button=(Button)findViewById(R.id.authen_button);

        intent=getIntent();
        certification=intent.getStringExtra("certification");
        day=intent.getStringExtra("day");
        point=intent.getStringExtra("point");
        attend=intent.getStringExtra("attend");
        startdate=intent.getStringExtra("startdate");
        day_check=new ArrayList<>();
        day_check=intent.getStringArrayListExtra("day_check");
        for(int i=0; i<day_check.size(); i++){
            if(day_check.get(i).equals("ok")){
                okday+=1;
            }
            if(day_check.get(i).equals("yet")){
                if(i==0){
                    finish_flag=0;
                    today_check=0;
                }
                else{
                    if(finish_flag==1){
                        if(day_check.get(i-1).equals("ok")){
                            today_check=1;
                        }
                        else{
                            today_check=0;
                        }
                        finish_flag=0;
                    }
                }

            }
        }
        if(finish_flag==1){
            authen_button.setText("도전 종료");
        }

        challengetitle.setText(certification+" 출석률 "+attend+"%");
        successday.setText("달성일: "+Integer.toString(okday)+"/"+day);
        System.out.println(day_check.size());
        System.out.println(okday/day_check.size());
        System.out.println((okday/day_check.size())*100);
        percentage=Math.round((okday*100)/day_check.size());
        success_percent.setText("달성률: "+Integer.toString(percentage)+"%");

        check_recyclerview.setHasFixedSize(true);
        layoutManager=new GridLayoutManager(this, 7);
        check_recyclerview.setLayoutManager(layoutManager);

        try {
            challengeGridAdapter=new ChallengeGridAdapter(day_check, startdate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        check_recyclerview.setAdapter(challengeGridAdapter);

        authen_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(finish_flag==1){
                    Toast.makeText(Challengedetail.this, "끝난 도전입니다", Toast.LENGTH_SHORT).show();
                }
                else if(finish_flag==0){
                    if(today_check==0){
                        Intent intent=new Intent(Challengedetail.this, Challengeauthen.class);
                        intent.putExtra("certification", certification);
                        intent.putExtra("day", day);
                        intent.putExtra("point", point);
                        intent.putExtra("attend", attend);
                        intent.putExtra("startdate", startdate);
                        intent.putStringArrayListExtra("day_check", day_check);
                        startActivity(intent);
                        finish();
                    }
                    else if(today_check==1){
                        Toast.makeText(Challengedetail.this, "오늘 인증이 완료되었습니다", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });

    }

    @Override
    protected void onRestart() {
        super.onRestart();
        System.out.println("onRestart");
    }
}
