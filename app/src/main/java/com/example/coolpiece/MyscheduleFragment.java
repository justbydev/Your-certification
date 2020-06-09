package com.example.coolpiece;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class MyscheduleFragment extends Fragment {
    TextView todaytext;
    RecyclerView scheduler_recyclerview;
    ArrayList<String> schedulelist;
    ArrayList<String> dday;
    ArrayList<String> wantdate;
    RecyclerView.LayoutManager layoutManager;
    MyScheduleAdapter myScheduleAdapter;
    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;
    DatabaseReference databaseReference;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.myschedule, container, false);

        todaytext=v.findViewById(R.id.today_date);
        scheduler_recyclerview=v.findViewById(R.id.schedule_recyclerview);

        scheduler_recyclerview.setHasFixedSize(true);
        layoutManager=new LinearLayoutManager(getActivity());
        scheduler_recyclerview.setLayoutManager(layoutManager);

        long now=System.currentTimeMillis();
        Date mDate=new Date(now);
        SimpleDateFormat simpleDate=new SimpleDateFormat("yyyy-MM-dd");
        String today=simpleDate.format(mDate);
        todaytext.setText(today);

        getscheduledata();

        return v;
    }

    public void getscheduledata(){
        schedulelist=new ArrayList<>();
        wantdate=new ArrayList<>();
        dday=new ArrayList<>();
        firebaseUser=FirebaseAuth.getInstance().getCurrentUser();
        String temp=firebaseUser.getEmail().toString();
        String name=temp.replace('.', '-');
        databaseReference= FirebaseDatabase.getInstance().getReference("Schedule").child(name);
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot snapshot:dataSnapshot.getChildren()){
                    String line=snapshot.getValue().toString();
                    String date=null;
                    String text=null;
                    try {
                        JSONObject jsonObject=new JSONObject(line);
                        String tm=jsonObject.getString("date");
                        date=tm.replace('-', '.');
                        text=jsonObject.getString("text");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    schedulelist.add(text);
                    wantdate.add(date);
                    try {
                        dday.add(getDday(date));
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }
                myScheduleAdapter=new MyScheduleAdapter(schedulelist, dday, wantdate);
                scheduler_recyclerview.setAdapter(myScheduleAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public String getDday(String date) throws ParseException {
        final int ONE_DAY=24*60*60*1000;
        long now=System.currentTimeMillis()/ONE_DAY;
        Date mDate=new SimpleDateFormat("yyyy.MM.dd").parse(date);
        Calendar cal=Calendar.getInstance();
        cal.setTime(mDate);
        long want=cal.getTimeInMillis()/ONE_DAY;
        long result=want-now;
        String strFormat;
        if(result>0){
            strFormat="D-%d";
        }
        else if(result==0){
            strFormat="D-Day";
        }
        else{
            result=now-want;
            strFormat="D+%d";
        }
        String strCount=(String.format(strFormat, result));
        return strCount;
    }

}
