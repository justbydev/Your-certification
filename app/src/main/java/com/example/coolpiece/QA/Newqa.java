package com.example.coolpiece.QA;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.coolpiece.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class Newqa extends AppCompatActivity {
    EditText qatitle;
    EditText qacontent;
    Button qaregister;
    Button qacancel;
    String title;
    String content;
    String email;
    String number;
    int num;
    DatabaseReference databaseReference;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.newqa);

        qatitle=(EditText)findViewById(R.id.qatitle);
        qacontent=(EditText)findViewById(R.id.qacontent);
        qaregister=(Button)findViewById(R.id.qaregister);
        qacancel=(Button)findViewById(R.id.qacancel);

        Intent intent=getIntent();
        num=intent.getIntExtra("number", 0);


        qacancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        qaregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                register_newqa();
            }
        });
    }
    public void register_newqa(){
        email= FirebaseAuth.getInstance().getCurrentUser().getEmail().toString();
        title=qatitle.getText().toString();
        content=qacontent.getText().toString();
        if(TextUtils.isEmpty(title)){
            Toast.makeText(this, "제목을 입력해주세요", Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(content)){
            Toast.makeText(this, "내용을 입력해주세요", Toast.LENGTH_SHORT).show();
            return;
        }
        title=title.replace(" ", "$");
        title=title.replace("\n", "`");
        content=content.replace(" ", "$");
        content=content.replace("\n", "`");
        QA qa=new QA();
        qa.setTitle(title);
        qa.setContent(content);
        qa.setEmail(email);
        number=Integer.toString(num);
        qa.setNumber(number);
        databaseReference= FirebaseDatabase.getInstance().getReference("QA");
        databaseReference.push().setValue(qa);
        finish();
    }
}
