package com.example.coolpiece;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainFragment extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    HomeActivity homeActivity=null;
    MypageActivity mypageActivity=null;
    LoginActivity loginActivity=null;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frag_main);

        bottomNavigationView=(BottomNavigationView)findViewById(R.id.bottom_navigation);

        homeActivity=new HomeActivity();
        getSupportFragmentManager().beginTransaction().add(R.id.container, homeActivity).commit();

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int select=item.getItemId();
                switch(select){
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
                        if(loginActivity!=null){
                            getSupportFragmentManager().beginTransaction().hide(loginActivity).commit();
                        }
                        return true;
                    case R.id.menu_mypage:
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
                        if(loginActivity!=null){
                            getSupportFragmentManager().beginTransaction().hide(loginActivity).commit();
                        }
                        return true;
                    case R.id.menu_log:
                        if(loginActivity==null){
                            loginActivity=new LoginActivity();
                            getSupportFragmentManager().beginTransaction().add(R.id.container, loginActivity).commit();
                        }
                        else{
                            getSupportFragmentManager().beginTransaction().show(loginActivity).commit();
                        }
                        if(homeActivity!=null){
                            getSupportFragmentManager().beginTransaction().hide(homeActivity).commit();
                        }
                        if(mypageActivity!=null){
                            getSupportFragmentManager().beginTransaction().hide(mypageActivity).commit();
                        }
                        return true;
                    default:
                        return true;
                }
            }
        });
    }
}
