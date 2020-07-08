package com.example.coolpiece.mypage.challenge;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.coolpiece.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;

import io.realm.Realm;
import io.realm.RealmResults;

public class ChallengeFragment extends Fragment {
    Button challenge_80;
    Button challenge_90;
    Button challenge_100;

    Intent intent;
    RecyclerView challenge_recyclerview;
    RecyclerView myauthenpic_recyclerview;
    ArrayList<Challenge> mylist=null;
    ArrayList<String> title=null;
    RecyclerView.LayoutManager layoutManager;
    MyChallengeAdapter myChallengeAdapter;
    DatabaseReference databaseReference;
    MyAuthenAdapter myAuthenAdapter;
    GridLayoutManager gridlayoutManager;

    ArrayList<MyPicture> myimages;
    int first=0;

    private Realm realm;
    private String me;
    int img_first=0;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.challenge, container, false);

        challenge_80=(Button)v.findViewById(R.id.challenge_80);
        challenge_90=(Button)v.findViewById(R.id.challenge_90);
        challenge_100=(Button)v.findViewById(R.id.challenge_100);
        challenge_recyclerview=(RecyclerView)v.findViewById(R.id.mychanllenge_recyclerview);
        myauthenpic_recyclerview=(RecyclerView)v.findViewById(R.id.myauthenpic_recyclerview);

        me= FirebaseAuth.getInstance().getCurrentUser().getEmail().toString();

        challenge_100.setOnClickListener(buttononclicklistener);
        challenge_90.setOnClickListener(buttononclicklistener);
        challenge_80.setOnClickListener(buttononclicklistener);

        challenge_recyclerview.setHasFixedSize(true);
        layoutManager=new LinearLayoutManager(getContext());
        challenge_recyclerview.setLayoutManager(layoutManager);
        mylist=new ArrayList<>();
        title=new ArrayList<>();
        String email= FirebaseAuth.getInstance().getCurrentUser().getEmail().toString();
        email=email.replace('.', '-');
        databaseReference= FirebaseDatabase.getInstance().getReference("Challenge").child(email);
        databaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                String line=dataSnapshot.getValue().toString();
                System.out.println("onchildadded");
                try {
                    JSONObject jsonObject=new JSONObject(line);
                    Challenge challenge=new Challenge();
                    String attend=jsonObject.getString("attend");
                    String certification=jsonObject.getString("certification");
                    certification=certification.replace("-", " ");
                    String day=jsonObject.getString("day");
                    String point=jsonObject.getString("point");
                    String startdate=jsonObject.getString("startdate");
                    JSONArray arrayList=jsonObject.getJSONArray("day_check");
                    ArrayList<String> day_check=new ArrayList<>();
                    int border=getDay(startdate, arrayList.length());
                    for(int i=0; i<arrayList.length(); i++){
                        day_check.add(arrayList.getString(i));
                        if(i<border){
                            if(day_check.get(i).equals("yet")){
                                day_check.set(i, "not");
                            }
                        }
                    }
                    challenge.setAttend(attend);
                    challenge.setCertification(certification);
                    challenge.setDay(day);
                    challenge.setPoint(point);
                    challenge.setStartdate(startdate);
                    challenge.setDay_check(day_check);
                    mylist.add(challenge);
                    certification=certification.replace(" ", "-");
                    title.add(certification+" 출석률 "+attend+"%");
                    challenge.setCertification(certification);
                    databaseReference.child(certification+attend+day+point).setValue(challenge);
                } catch (JSONException | ParseException e) {
                    e.printStackTrace();
                }
                if(first==0){
                    first=1;
                    myChallengeAdapter=new MyChallengeAdapter(mylist, title);
                    challenge_recyclerview.setAdapter(myChallengeAdapter);
                }
                else{
                    myChallengeAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                String line=dataSnapshot.getValue().toString();
                System.out.println("onchildchanged");
                try {
                    JSONObject jsonObject=new JSONObject(line);
                    Challenge challenge=new Challenge();
                    String attend=jsonObject.getString("attend");
                    String certification=jsonObject.getString("certification");
                    String day=jsonObject.getString("day");
                    String point=jsonObject.getString("point");
                    String startdate=jsonObject.getString("startdate");
                    JSONArray arrayList=jsonObject.getJSONArray("day_check");
                    ArrayList<String> day_check=new ArrayList<>();
                    for(int i=0; i<arrayList.length(); i++){
                        day_check.add(arrayList.getString(i));
                    }
                    challenge.setAttend(attend);
                    challenge.setCertification(certification);
                    challenge.setDay(day);
                    challenge.setPoint(point);
                    challenge.setStartdate(startdate);
                    challenge.setDay_check(day_check);
                    myChallengeAdapter.updatearray(certification, attend, challenge);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
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
        myimages=new ArrayList<>();
        myauthenpic_recyclerview.setHasFixedSize(true);
        gridlayoutManager=new GridLayoutManager(getContext(), 3);

        myauthenpic_recyclerview.setLayoutManager(gridlayoutManager);

        me=me.replace(".", "-");
        final String big="Images of "+me;
        DatabaseReference myimage=FirebaseDatabase.getInstance().getReference(big);
        myimage.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                String img=dataSnapshot.getValue().toString();
                MyPicture myPicture=new MyPicture();
                myPicture.setImg_name(img);
                myPicture.setOwner(me);
                myimages.add(0, myPicture);

                if(img_first==0){
                    img_first=1;
                    myAuthenAdapter=new MyAuthenAdapter(myimages);
                    myauthenpic_recyclerview.setAdapter(myAuthenAdapter);
                }
                else{
                    myAuthenAdapter.notifyDataSetChanged();
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

    public View.OnClickListener buttononclicklistener=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            int id=v.getId();
            switch(id){
                case R.id.challenge_80:
                    intent=new Intent(getContext(), NewChallenge.class);
                    intent.putExtra("name", "출석률 80% 도전");
                    intent.putExtra("attend", "80");
                    startActivity(intent);
                    return;
                case R.id.challenge_90:
                    intent=new Intent(getContext(), NewChallenge.class);
                    intent.putExtra("name", "출석률 90% 도전");
                    intent.putExtra("attend", "90");
                    startActivity(intent);
                    return;
                case R.id.challenge_100:
                    intent=new Intent(getContext(), NewChallenge.class);
                    intent.putExtra("name", "출석률 100% 도전");
                    intent.putExtra("attend", "100");
                    startActivity(intent);
                    return;
                default:
                    return;
            }
        }
    };

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
}
