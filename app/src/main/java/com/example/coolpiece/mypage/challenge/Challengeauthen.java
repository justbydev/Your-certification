package com.example.coolpiece.mypage.challenge;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.coolpiece.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class Challengeauthen extends AppCompatActivity {
    Button final_auth_button;
    ImageView certi_image;
    TextView picture_text;
    RadioButton board_upload_access;
    String certification;
    String day;
    String point;
    String attend;
    String startdate;
    ArrayList<String> day_check;
    int border;
    String title;
    DatabaseReference databaseReference;
    Challenge challenge;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.challenge_authen);

        final_auth_button=(Button)findViewById(R.id.final_auth_button);
        certi_image=(ImageView)findViewById(R.id.certi_image);
        picture_text=(TextView)findViewById(R.id.picture_text);
        board_upload_access=(RadioButton)findViewById(R.id.board_upload_access);

        Intent intent=getIntent();
        certification=intent.getStringExtra("certification");
        day=intent.getStringExtra("day");
        point=intent.getStringExtra("point");
        attend=intent.getStringExtra("attend");
        startdate=intent.getStringExtra("startdate");
        day_check=new ArrayList<>();
        day_check=intent.getStringArrayListExtra("day_check");
        try {
            border=getDay(startdate, day_check.size());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        final_auth_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                day_check.set(border, "ok");
                certification=certification.replace(" ", "-");
                title=certification+attend+day+point;
                databaseReference= FirebaseDatabase.getInstance().getReference("Challenge");
                String email= FirebaseAuth.getInstance().getCurrentUser().getEmail().toString();
                email=email.replace(".", "-");
                challenge=new Challenge();
                challenge.setCertification(certification);
                challenge.setDay(day);
                challenge.setStartdate(startdate);
                challenge.setPoint(point);
                challenge.setStartdate(startdate);
                challenge.setAttend(attend);
                challenge.setDay_check(day_check);
                databaseReference.child(email).child(title).setValue(challenge);
                Intent giveintent=new Intent(Challengeauthen.this, Challengedetail.class);
                giveintent.putExtra("certification", certification);
                giveintent.putExtra("day", day);
                giveintent.putExtra("startdate", startdate);
                giveintent.putExtra("attend", attend);
                giveintent.putExtra("point", point);
                giveintent.putStringArrayListExtra("day_check", day_check);
                startActivity(giveintent);
                finish();
            }
        });
    }

    public int getDay(String startdate, int about) throws ParseException {
        Date start;
        String today;
        int i=0;
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
        start=simpleDateFormat.parse(startdate);
        Calendar cal=Calendar.getInstance();
        SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
        Date time=new Date();
        today=format.format(time);
        for(i=0; i<about; i++){
            cal.setTime(start);
            cal.add(Calendar.DATE, i);
            if(today.equals(simpleDateFormat.format(cal.getTime()).toString())){
                break;
            }
        }
        return i;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent giveintent=new Intent(Challengeauthen.this, Challengedetail.class);
        giveintent.putExtra("certification", certification);
        giveintent.putExtra("day", day);
        giveintent.putExtra("startdate", startdate);
        giveintent.putExtra("attend", attend);
        giveintent.putExtra("point", point);
        giveintent.putStringArrayListExtra("day_check", day_check);
        startActivity(giveintent);
        finish();
    }
}
