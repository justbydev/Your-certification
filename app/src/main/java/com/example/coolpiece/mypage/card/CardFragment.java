package com.example.coolpiece.mypage.card;

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
import com.example.coolpiece.mypage.schedule.MyScheduleAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class CardFragment extends Fragment{

    public TextView certi_list;
    public TextView font_list;
    public TextView imoticon_list;
    ArrayList<String> cardlist;
    RecyclerView card_recyclerview;
    RecyclerView.LayoutManager layoutManager;

    CardAdapter cardAdapter;
    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;
    DatabaseReference databaseReference;
    public boolean flag=false;
    private boolean first;

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.card, container, false);

        certi_list=(TextView)v.findViewById(R.id.certi_list);
        font_list=(TextView)v.findViewById(R.id.font_list);
        imoticon_list=(TextView)v.findViewById(R.id.imoticon_list);
        card_recyclerview=v.findViewById(R.id.certificate_list);
        card_recyclerview.setLayoutManager(layoutManager);

        firebaseUser=FirebaseAuth.getInstance().getCurrentUser();
        String temp=firebaseUser.getEmail().toString();
        String name=temp.replace('.', '-');
        databaseReference= FirebaseDatabase.getInstance().getReference("certificate/data").child(name);

        certi_list.setOnClickListener(buttononclicklistener);
        font_list.setOnClickListener(buttononclicklistener);
        imoticon_list.setOnClickListener(buttononclicklistener);
        cardlist = new ArrayList<>();


        return v;
    }
    public void updatecard(){
        databaseReference.addChildEventListener(new ChildEventListener() {//계속 추가되는 경우, 처음에는 전체를 읽고 그 후, 추가되는 데이터에 한해서만 읽음
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {//한번에 하나씩 읽게 됨, dataSnapshot에 하나의 데이터만이 들어있음
                String line=dataSnapshot.getValue().toString();
                String name=null;
                try {
                    JSONObject jsonObject=new JSONObject(line);
                    String tm=jsonObject.getString("certificate_name");

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                cardlist.add(name);
                if(first==false){
                    cardAdapter=new CardAdapter(cardlist);
                    card_recyclerview.setAdapter(cardAdapter);
                    first=true;
                }
                else{
                    cardAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    };
    private View.OnClickListener buttononclicklistener=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            int id=v.getId();
            switch(id) {
                case R.id.certi_list:
                    CardAdapter ca = new CardAdapter(cardlist);
                    //do
                    break;
                case R.id.font_list:
                    //do
                    break;
                case R.id.imoticon_list:
                    //do
                    break;
                default:break;
            }
        }


    };
}
