package com.example.coolpiece.mycert;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MyCertActivity extends Fragment {
    RecyclerView mycert_recyclerview;
    LinearLayoutManager linearLayoutManager;
    MyCertAdapter myCertAdapter;
    String me;
    ArrayList<MyCert_Info> mList;
    int cert_first=0;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.mycert, container, false);

        mycert_recyclerview=(RecyclerView)v.findViewById(R.id.mycert_recyclerview);
        me= FirebaseAuth.getInstance().getCurrentUser().getEmail().toString();

        linearLayoutManager=new LinearLayoutManager(getContext());
        linearLayoutManager.setReverseLayout(true);
        linearLayoutManager.setStackFromEnd(true);
        mycert_recyclerview.setLayoutManager(linearLayoutManager);
        mList=new ArrayList<>();
        me=me.replace(".", "-");

        DatabaseReference mycert= FirebaseDatabase.getInstance().getReference("certificate").child(me);
        mycert.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                String img=dataSnapshot.getValue().toString();
                try {
                    JSONObject jsonObject=new JSONObject(img);
                    MyCert_Info my=new MyCert_Info();
                    my.setBirthday(jsonObject.getString("birthday").replace("`", " "));
                    System.out.println(jsonObject.getString("birthday").replace("`", " "));
                    my.setCertificate_date(jsonObject.getString("certificate_date").replace("`", " "));
                    System.out.println(jsonObject.getString("certificate_date").replace("`", " "));
                    my.setCertificate_institution(jsonObject.getString("certificate_institution").replace("`", " "));
                    System.out.println(jsonObject.getString("certificate_institution").replace("`", " "));
                    my.setCertificate_name(jsonObject.getString("certificate_name").replace("`", " "));
                    System.out.println(jsonObject.getString("certificate_name").replace("`", " "));
                    my.setCertificate_serial_num(jsonObject.getString("certificate_serial_num").replace("`", " "));
                    System.out.println(jsonObject.getString("certificate_serial_num").replace("`", " "));
                    my.setImg_name(jsonObject.getString("img_name").replace("`", " "));
                    System.out.println(jsonObject.getString("img_name").replace("`", " "));
                    mList.add(my);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                if(cert_first==0){
                    cert_first=1;
                    myCertAdapter=new MyCertAdapter(mList);
                    mycert_recyclerview.setAdapter(myCertAdapter);
                }
                else{
                    myCertAdapter.notifyDataSetChanged();
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


        return v;
    }
}
