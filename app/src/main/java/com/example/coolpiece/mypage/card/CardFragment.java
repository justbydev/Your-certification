package com.example.coolpiece.mypage.card;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.coolpiece.R;
import com.example.coolpiece.mypage.authen.Authentificate_user;
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

    LinearLayout certi_view;
    public TextView certi_list;
    public TextView font_list;
    public TextView color_list;
    public TextView background_list;
    public TextView add_text;
    ArrayList<String> mList;
    LayoutInflater layoutInflater;

    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;
    DatabaseReference databaseReference;
    public boolean flag=false;
    private boolean first;
    static Context context;
    String mychoicecert=null;

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.card, container, false);

        context=getContext();
        certi_view=(LinearLayout) v.findViewById(R.id.certi_view);
        certi_list=(TextView)v.findViewById(R.id.certi_list);
        font_list=(TextView)v.findViewById(R.id.font_list);
        color_list=(TextView)v.findViewById(R.id.color_list);
        background_list=(TextView)v.findViewById(R.id.background_list);
        add_text=(TextView)v.findViewById(R.id.add_text);


        firebaseUser=FirebaseAuth.getInstance().getCurrentUser();
        String temp=firebaseUser.getEmail().toString();
        String name=temp.replace('.', '-');
        mList=new ArrayList<>();
        databaseReference= FirebaseDatabase.getInstance().getReference("certificate/data").child(name);
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot snapshot:dataSnapshot.getChildren()){
                    String json=snapshot.getValue().toString();
                    try {
                        JSONObject jsonObject=new JSONObject(json);
                        String certificate_name=jsonObject.getString("certificate_name");
                        mList.add(certificate_name);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        certi_list.setOnClickListener(buttononclicklistener);
        font_list.setOnClickListener(buttononclicklistener);
        color_list.setOnClickListener(buttononclicklistener);
        background_list.setOnClickListener(buttononclicklistener);
        add_text.setOnClickListener(buttononclicklistener);

        return v;
    }

    private View.OnClickListener buttononclicklistener=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            int id=v.getId();
            switch(id) {
                case R.id.certi_list:
                    AlertDialog.Builder builder=new AlertDialog.Builder(context);
                    builder.setTitle("자격증 목록");
                    final ArrayAdapter<String> adapter=new ArrayAdapter<>(
                            context, android.R.layout.select_dialog_singlechoice
                    );
                    for(int i=0; i<mList.size(); i++){
                        adapter.add(mList.get(i));
                    }

                    builder.setAdapter(adapter,
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    mychoicecert=adapter.getItem(which);
                                    final AlertDialog.Builder inBuilder=new AlertDialog.Builder(context);
                                    inBuilder.setMessage(mychoicecert+"입니다.");
                                    inBuilder.setTitle("당신이 선택한 것은 ");
                                    inBuilder.setPositiveButton("확인",
                                            new DialogInterface.OnClickListener() {
                                                @Override
                                                public void onClick(DialogInterface dialog, int which) {
                                                    dialog.dismiss();
                                                }
                                            });
                                    inBuilder.show();
                                }
                            });
                    builder.setNegativeButton("취소",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });

                    builder.show();
                    break;
                case R.id.font_list:
                    //do
                    break;
                case R.id.color_list:
                    break;
                case R.id.background_list:

                    break;
                case R.id.add_text:
                    AlertDialog.Builder edit=new AlertDialog.Builder(context);
                    edit.setMessage("새로운 텍스트를 추가하시겠습니까?");
                    edit.setNegativeButton("취소",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                    edit.setPositiveButton("확인",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    final EditText tx=new EditText(context);
                                    tx.setHint("새로운 글 추가");
                                    tx.setOnTouchListener(new View.OnTouchListener() {
                                        @Override
                                        public boolean onTouch(View v, MotionEvent event) {
                                            switch(event.getAction()){
                                                case MotionEvent.ACTION_DOWN:
                                                case MotionEvent.ACTION_MOVE:
                                                case MotionEvent.ACTION_UP:
                                                    tx.setX(event.getX());
                                                    tx.setX(event.getY());
                                                    break;
                                            }
                                            return true;
                                        }
                                    });
                                    certi_view.addView(tx);
                                    dialog.dismiss();
                                }
                            });
                    edit.show();
                    break;
                default:break;
            }
        }


    };
}
