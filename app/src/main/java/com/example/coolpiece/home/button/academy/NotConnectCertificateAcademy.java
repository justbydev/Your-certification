package com.example.coolpiece.home.button.academy;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.coolpiece.R;
import com.example.coolpiece.splash.dataclass.Academy;
import com.example.coolpiece.splash.manageclass.ManageAcademyData;

import java.util.ArrayList;

public class NotConnectCertificateAcademy extends AppCompatActivity {
    TextView back_button;
    TextView academy_name;
    TextView academy_address;
    TextView academy_phone;
    Intent intent;
    String address=null;
    String name=null;
    String phone=null;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.not_connect_certificate_academy);

        back_button=(TextView)findViewById(R.id.back_button);

        academy_name=(TextView)findViewById(R.id.academy_name);
        academy_address=(TextView)findViewById(R.id.academy_address);
        academy_phone=(TextView)findViewById(R.id.academy_phone);

        back_button.setOnClickListener(buttononclicklistener);

        intent=getIntent();
        name=intent.getStringExtra("academy_name");
        academy_name.setText(name);
        ArrayList<Academy> total=new ArrayList<>();
        total= ManageAcademyData.getInstance().getAcademy_total();
        for(int i=0; i<total.size(); i++){
            if(name.equals(total.get(i).getAcademy_name())){
                address=total.get(i).getAcademy_address();
                phone=total.get(i).getAcademy_number();
                break;
            }
        }
        academy_address.setText(address);
        academy_phone.setText(phone);
    }

    private View.OnClickListener buttononclicklistener=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            int id=v.getId();
            switch(id){
                case R.id.back_button:
                    finish();
                    return;
                default:
                    return;
            }
        }
    };

    @Override
    public void onBackPressed() {
        finish();
    }
}
