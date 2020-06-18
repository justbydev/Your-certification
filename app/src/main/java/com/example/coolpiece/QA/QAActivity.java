package com.example.coolpiece.QA;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.coolpiece.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class QAActivity extends Fragment {
    Button questionbutton;
    RecyclerView questionlist;
    private QAlistAdapter qAlistAdapter;
    LinearLayoutManager layoutManager;
    ArrayList<QA> list=null;
    DatabaseReference databaseReference;
    String email;
    int first=0;
    int num;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.qa, container, false);

        questionbutton=(Button)v.findViewById(R.id.questionbutton);
        questionlist=(RecyclerView)v.findViewById(R.id.questionlist);

        questionlist.setHasFixedSize(true);
        layoutManager=new LinearLayoutManager(getContext());
        layoutManager.setReverseLayout(true);
        layoutManager.setStackFromEnd(true);
        questionlist.setLayoutManager(layoutManager);
        email= FirebaseAuth.getInstance().getCurrentUser().getEmail().toString();

        getquestionlist();

        questionbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(), Newqa.class);
                intent.putExtra("number", num);
                startActivity(intent);
            }
        });

        return v;
    }

    public void getquestionlist(){
        list=new ArrayList<>();
        databaseReference=FirebaseDatabase.getInstance().getReference("QA");
        databaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                String line=dataSnapshot.getValue().toString();
                String title=null;
                String content=null;
                String number=null;
                try {
                    JSONObject jsonObject=new JSONObject(line);
                    title=jsonObject.getString("title");
                    title=title.replace("$", " ");
                    title=title.replace("`", "\n");
                    content=jsonObject.getString("content");
                    content=content.replace("$", " ");
                    content=content.replace("`", "\n");
                    number=jsonObject.getString("number");
                    QA qa=new QA();
                    qa.setEmail(email);
                    qa.setTitle(title);
                    qa.setContent(content);
                    qa.setNumber(number);
                    list.add(qa);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                if(first==0){
                    qAlistAdapter=new QAlistAdapter(list);
                    questionlist.setAdapter(qAlistAdapter);
                    first=1;
                    num=qAlistAdapter.getItemCount();
                }
                else{
                    qAlistAdapter.notifyDataSetChanged();
                    num=qAlistAdapter.getItemCount();
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
}
