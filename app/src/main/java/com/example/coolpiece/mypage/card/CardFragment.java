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

public class CardFragment extends Fragment{

    public TextView certi_list;
    public TextView font_list;
    public TextView imoticon_list;

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.certificate_register, container, false);

        certi_list=(TextView)v.findViewById(R.id.certi_list);
        font_list=(TextView)v.findViewById(R.id.font_list);
        imoticon_list=(TextView)v.findViewById(R.id.imoticon_list);

        certi_list.setOnClickListener(buttononclicklistener);
        font_list.setOnClickListener(buttononclicklistener);
        imoticon_list.setOnClickListener(buttononclicklistener);


        return super.onCreateView(inflater, container, savedInstanceState);
    }
    private View.OnClickListener buttononclicklistener=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            int id=v.getId();
            switch(id) {
                case R.id.certi_list:
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
