package com.example.coolpiece.QA;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.coolpiece.R;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class QAwithanswer extends AppCompatActivity {
    Button answerbutton;
    TextView anstitle;
    TextView anscontent;
    EditText answertext;
    Intent intent;
    String title;
    String content;
    String email;
    String number;
    String answer;
    TextView back_button;
    RecyclerView answer_recyclerview;
    DatabaseReference databaseReference;

    AnswerAdapter answerAdapter;
    LinearLayoutManager linearLayoutManager;
    ArrayList<String> answer_list=null;
    int first=0;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.qawithanswer);

        anstitle=(TextView)findViewById(R.id.anstitle);
        anscontent=(TextView)findViewById(R.id.anscontent);
        answerbutton=(Button)findViewById(R.id.answerbutton);
        answertext=(EditText)findViewById(R.id.answertext);
        answer_recyclerview=(RecyclerView)findViewById(R.id.answer_recyclerview);
        back_button=findViewById(R.id.back_button);

        intent=getIntent();
        title=intent.getStringExtra("title");
        content=intent.getStringExtra("content");
        email=intent.getStringExtra("email");
        number=intent.getStringExtra("number");


        anstitle.setText("제목: "+title);
        anscontent.setText(content);

        databaseReference=FirebaseDatabase.getInstance().getReference(number);

        answer_list=new ArrayList<>();
        answer_recyclerview.setHasFixedSize(true);
        linearLayoutManager=new LinearLayoutManager(this);
        linearLayoutManager.setReverseLayout(true);
        linearLayoutManager.setStackFromEnd(true);
        answer_recyclerview.setLayoutManager(linearLayoutManager);

        getanswerlistfromrealtime();

        answerbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                register_answer();
            }
        });
        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public void register_answer(){
        answer=answertext.getText().toString();
        if(TextUtils.isEmpty(answer)||answer==null||answer.length()==0){
            Toast.makeText(this, "답글을 입력해주세요", Toast.LENGTH_SHORT).show();
            return;
        }
        int strlen=answer.length();
        int blank=1;
        for(int i=0; i<strlen; i++){
            if(!Character.isWhitespace(answer.charAt(i))){
                blank=0;
                break;
            }
        }
        if(blank==1){
            Toast.makeText(this, "답글을 입력해주세요", Toast.LENGTH_SHORT).show();
            return;
        }

        databaseReference.push().setValue(answer);
        answertext.setText("");
    }

    public void getanswerlistfromrealtime(){
        databaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                String ans=dataSnapshot.getValue().toString();
                answer_list.add(ans);
                if(first==0){
                    answerAdapter=new AnswerAdapter(answer_list);
                    answer_recyclerview.setAdapter(answerAdapter);
                    first=1;
                }
                else{
                    answer_recyclerview.scrollToPosition(answerAdapter.getItemCount()-1);
                    answerAdapter.notifyDataSetChanged();
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
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
