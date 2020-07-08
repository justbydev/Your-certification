package com.example.coolpiece;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.coolpiece.QA.QAActivity;
import com.example.coolpiece.board.BoardActivity;
import com.example.coolpiece.login.LoginActivity;
import com.example.coolpiece.mypage.MypageActivity;
import com.google.firebase.auth.FirebaseAuth;

import org.w3c.dom.Text;

import io.realm.Realm;

public class MainFragment extends AppCompatActivity {

    HomeActivity homeActivity=null;
    MypageActivity mypageActivity=null;
    BoardActivity boardActivity=null;
    QAActivity qaActivity=null;
    TextView menu_home;
    TextView menu_mypage;
    TextView menu_board;
    TextView menu_qa;
    TextView logbutton;
    FirebaseAuth firebaseAuth;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frag_main);




        menu_home=(TextView)findViewById(R.id.menu_home);
        menu_mypage=(TextView)findViewById(R.id.menu_mypage);
        menu_board=(TextView)findViewById(R.id.menu_board);
        menu_qa=(TextView)findViewById(R.id.menu_QA);
        logbutton=(TextView)findViewById(R.id.logbutton);

        menu_home.setOnClickListener(buttononclicklistener);
        menu_mypage.setOnClickListener(buttononclicklistener);
        menu_board.setOnClickListener(buttononclicklistener);
        menu_qa.setOnClickListener(buttononclicklistener);
        logbutton.setOnClickListener(buttononclicklistener);

        firebaseAuth=FirebaseAuth.getInstance();
        if(firebaseAuth.getCurrentUser()!=null){
            logbutton.setText("로그아웃");
        }
        else{
            logbutton.setText("로그인");
        }

        homeActivity=new HomeActivity();

        getSupportFragmentManager().beginTransaction().add(R.id.container, homeActivity).commit();
    }

    private View.OnClickListener buttononclicklistener=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            int id=v.getId();
            switch(id){
                case R.id.logbutton:
                    if(logbutton.getText().equals("로그인")){
                        Intent intent=new Intent(MainFragment.this, LoginActivity.class);
                        startActivity(intent);
                    }
                    else if(logbutton.getText().equals("로그아웃")){
                        firebaseAuth.signOut();
                        Intent intent=new Intent(MainFragment.this, MainFragment.class);
                        finish();
                        startActivity(intent);
                    }
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
                    if(boardActivity!=null){
                        getSupportFragmentManager().beginTransaction().hide(boardActivity).commit();
                    }
                    if(qaActivity!=null){
                        getSupportFragmentManager().beginTransaction().hide(qaActivity).commit();
                    }
                    return;
                case R.id.menu_board:
                    if(boardActivity==null){
                        boardActivity=new BoardActivity();
                        getSupportFragmentManager().beginTransaction().add(R.id.container, boardActivity).commit();
                    }
                    else{
                        getSupportFragmentManager().beginTransaction().show(boardActivity).commit();
                    }
                    if(homeActivity!=null){
                        getSupportFragmentManager().beginTransaction().hide(homeActivity).commit();
                    }
                    if(mypageActivity!=null){
                        getSupportFragmentManager().beginTransaction().hide(mypageActivity).commit();
                    }
                    if(qaActivity!=null){
                        getSupportFragmentManager().beginTransaction().hide(qaActivity).commit();
                    }
                    return;
                case R.id.menu_QA:
                    if(firebaseAuth.getCurrentUser()!=null){
                        if(qaActivity==null){
                            qaActivity=new QAActivity();
                            getSupportFragmentManager().beginTransaction().add(R.id.container, qaActivity).commit();
                        }
                        else{
                            getSupportFragmentManager().beginTransaction().show(qaActivity).commit();
                        }
                        if(homeActivity!=null){
                            getSupportFragmentManager().beginTransaction().hide(homeActivity).commit();
                        }
                        if(mypageActivity!=null){
                            getSupportFragmentManager().beginTransaction().hide(mypageActivity).commit();
                        }
                        if(boardActivity!=null){
                            getSupportFragmentManager().beginTransaction().hide(boardActivity).commit();
                        }
                    }
                    else{
                        changetologinpage();
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
                        if(boardActivity!=null){
                            getSupportFragmentManager().beginTransaction().hide(boardActivity).commit();
                        }
                        if(qaActivity!=null){
                            getSupportFragmentManager().beginTransaction().hide(qaActivity).commit();
                        }
                    }
                    else{
                        changetologinpage();
                    }

                    return;
                default:
                    return;
            }
        }
    };
    public void changetologinpage(){
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
                        if(boardActivity!=null){
                            getSupportFragmentManager().beginTransaction().hide(boardActivity).commit();
                        }
                        if(qaActivity!=null){
                            getSupportFragmentManager().beginTransaction().hide(qaActivity).commit();
                        }
                        dialog.cancel();
                    }
                });
        AlertDialog alertDialog=builder.create();
        alertDialog.show();
    }
}
