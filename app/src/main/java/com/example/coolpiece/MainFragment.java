package com.example.coolpiece;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;

public class MainFragment extends AppCompatActivity {

    HomeActivity homeActivity=null;
    MypageActivity mypageActivity=null;
    TextView menu_home;
    TextView menu_mypage;
    TextView menu_log;
    FirebaseAuth firebaseAuth;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frag_main);


        menu_home=(TextView)findViewById(R.id.menu_home);
        menu_mypage=(TextView)findViewById(R.id.menu_mypage);
        menu_log=(TextView)findViewById(R.id.menu_log);

        menu_home.setOnClickListener(buttononclicklistener);
        menu_mypage.setOnClickListener(buttononclicklistener);
        menu_log.setOnClickListener(buttononclicklistener);

        firebaseAuth=FirebaseAuth.getInstance();
        if(firebaseAuth.getCurrentUser()!=null){
            menu_log.setText("로그아웃");
        }
        else{
            menu_log.setText("로그인");
        }

        homeActivity=new HomeActivity();

        getSupportFragmentManager().beginTransaction().add(R.id.container, homeActivity).commit();
    }

    private View.OnClickListener buttononclicklistener=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            int id=v.getId();
            switch(id){
                case R.id.menu_log:
                    Intent intent=new Intent(MainFragment.this, LoginActivity.class);
                    startActivity(intent);
                    return;
                case R.id.menu_home:
                    if(homeActivity==null){
                        homeActivity=new HomeActivity();
                        getSupportFragmentManager().beginTransaction().add(R.id.container, homeActivity).commit();
                    }
                    else{
                        getSupportFragmentManager().beginTransaction().show(homeActivity).commit();
                    }
                    if(mypageActivity!=null){
                        getSupportFragmentManager().beginTransaction().hide(mypageActivity).commit();
                    }
                    return;
                case R.id.menu_mypage:
                    if(firebaseAuth.getCurrentUser()!=null){
                        if(mypageActivity==null){
                            mypageActivity=new MypageActivity();
                            getSupportFragmentManager().beginTransaction().add(R.id.container, mypageActivity).commit();
                        }
                        else{
                            getSupportFragmentManager().beginTransaction().show(mypageActivity).commit();
                        }
                        if(homeActivity!=null){
                            getSupportFragmentManager().beginTransaction().hide(homeActivity).commit();
                        }
                    }
                    else{
                        AlertDialog.Builder builder= new AlertDialog.Builder(MainFragment.this);
                        builder.setTitle("로그인이 필요합니다.")
                                .setMessage("로그인 화면으로 이동하시겠습니까?")
                                .setPositiveButton("네", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        Intent intent=new Intent(MainFragment.this, LoginActivity.class);
                                        startActivity(intent);
                                    }
                                })
                                .setNegativeButton("아니요", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        if(homeActivity==null){
                                            homeActivity=new HomeActivity();
                                            getSupportFragmentManager().beginTransaction().add(R.id.container, homeActivity).commit();
                                        }
                                        else{
                                            getSupportFragmentManager().beginTransaction().show(homeActivity).commit();
                                        }
                                        if(mypageActivity!=null){
                                            getSupportFragmentManager().beginTransaction().hide(mypageActivity).commit();
                                        }
                                        dialog.cancel();
                                    }
                                });
                        AlertDialog alertDialog=builder.create();
                        alertDialog.show();
                    }

                    return;
                default:
                    return;
            }
        }
    };
}
