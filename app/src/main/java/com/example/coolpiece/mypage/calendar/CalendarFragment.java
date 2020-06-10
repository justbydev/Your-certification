package com.example.coolpiece.mypage.calendar;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.coolpiece.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class CalendarFragment extends Fragment {
    CalendarView calendarView;
    TextView selected_date;
    ImageView add_schedule;
    RecyclerView selected_date_schedulelist;
    int select_year;
    int select_month;
    int select_day;
    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;
    DatabaseReference databaseReference;
    DatabaseReference scheduleReference;

    ArrayList<String> schedule;
    //ArrayList<String> tmp=null;
    RecyclerView.LayoutManager layoutManager;
    EachDateScheduleAdapter eachDateScheduleAdapter;
    int first=0;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.calender, container, false);

        calendarView=(CalendarView)v.findViewById(R.id.calender);
        selected_date=(TextView)v.findViewById(R.id.selected_date);
        add_schedule=(ImageView)v.findViewById(R.id.add_schedule);
        selected_date_schedulelist=(RecyclerView)v.findViewById(R.id.selected_date_schedulelist);

        selected_date_schedulelist.setHasFixedSize(true);
        layoutManager=new LinearLayoutManager(getActivity());
        selected_date_schedulelist.setLayoutManager(layoutManager);

        SimpleDateFormat sdf=new SimpleDateFormat("yyyy.M.d");
        String selectedDate=sdf.format(new Date(calendarView.getDate()));
        selected_date.setText(selectedDate);
        select_year= Calendar.getInstance().get(Calendar.YEAR);
        select_month=Calendar.getInstance().get(Calendar.MONTH)+1;
        select_day=Calendar.getInstance().get(Calendar.DAY_OF_MONTH);

        schedule=new ArrayList<String>();
        //tmp=new ArrayList<>();

        getschedulefromrealtime();




        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                selected_date.setText(Integer.toString(year)+'.'+Integer.toString(month+1)+'.'+Integer.toString(dayOfMonth));
                select_year=year;
                select_month=month+1;
                select_day=dayOfMonth;
                schedule.clear();
                getschedulefromrealtime();
                //eachDateScheduleAdapter.setchange(tmp);
            }
        });

        add_schedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                register_newschedule();

            }
        });

        return v;
    }
    public void register_newschedule(){
        final EditText input=new EditText(getActivity());
        AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
        builder.setTitle(Integer.toString(select_year)+'.'+Integer.toString(select_month)+'.'+Integer.toString(select_day))
                .setMessage("새 일정을 추가해주세요")
                .setView(input)
                .setPositiveButton("등록", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String temptext=input.getText().toString();
                        String text=temptext.replace(" ", "$");
                        firebaseUser=FirebaseAuth.getInstance().getCurrentUser();
                        String temp=firebaseUser.getEmail().toString();
                        String name=temp.replace('.', '-');
                        String date=Integer.toString(select_year)+'-'+Integer.toString(select_month)+'-'+Integer.toString(select_day);
                        databaseReference= FirebaseDatabase.getInstance().getReference("Calendar").child(name).child(date);
                        scheduleReference=FirebaseDatabase.getInstance().getReference("Schedule").child(name);
                        Schedule scheduleclass=new Schedule(date, text);
                        scheduleReference.push().setValue(scheduleclass);
                        databaseReference.push().setValue(text);
                        if(schedule.get(0).equals("일정이 없습니다.")){
                            schedule.clear();
                        }
                        schedule.add(temptext);
                        eachDateScheduleAdapter.notifyDataSetChanged();

                        dialog.cancel();

                    }
                })
                .setNegativeButton("취소", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
        AlertDialog alertDialog=builder.create();
        alertDialog.show();
    }
    public void getschedulefromrealtime(){
        firebaseUser=FirebaseAuth.getInstance().getCurrentUser();
        String temp=firebaseUser.getEmail().toString();
        String name=temp.replace('.', '-');
        String date=Integer.toString(select_year)+'-'+Integer.toString(select_month)+'-'+Integer.toString(select_day);
        databaseReference= FirebaseDatabase.getInstance().getReference("Calendar").child(name).child(date);
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {//전체 Realtime을 한번 읽을 때 사용
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                int count=0;
                for(DataSnapshot snapshot: dataSnapshot.getChildren()){//dataSnapshot에 한꺼번에 들어옴
                    String temptext=snapshot.getValue().toString();
                    String text=temptext.replace("$", " ");
                    schedule.add(text);
                    count=1;
                }
                if(first==0){
                    if(count==0){
                        schedule.add("일정이 없습니다.");
                    }
                    eachDateScheduleAdapter=new EachDateScheduleAdapter(schedule);
                    selected_date_schedulelist.setAdapter(eachDateScheduleAdapter);
                    first=1;
                }
                else{
                    if(count==0){
                        schedule.add("일정이 없습니다.");
                    }
                    eachDateScheduleAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
