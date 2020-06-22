package com.example.coolpiece.mypage.authen;


import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.coolpiece.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class AddAuthenActivity extends AppCompatActivity implements View.OnClickListener{


    EditText certificate_name;
    EditText certificate_serial_num;
    EditText birthday;
    EditText certificate_date;
    EditText certificate_institution;
    Button buttonCertificate;
    FirebaseUser firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.certificate_register);
        certificate_name = (EditText)findViewById(R.id.certificate_name);
        certificate_serial_num = (EditText)findViewById(R.id.certificate_serial_num);
        birthday = (EditText)findViewById(R.id.birthday);
        certificate_date = (EditText)findViewById(R.id.certificate_date);
        certificate_institution = (EditText)findViewById(R.id.certificate_institution);
        buttonCertificate = (Button)findViewById(R.id.buttonCertificate);

        buttonCertificate.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        firebaseAuth = FirebaseAuth.getInstance().getCurrentUser();;
        String email=firebaseAuth.getEmail().toString();
        String name=certificate_name.getText().toString();
        String serial_num = certificate_serial_num.getText().toString();
        String birth=birthday.getText().toString();
        String date = birthday.getText().toString();
        String institution = certificate_institution.getText().toString();
        if(name==null ||email.equals("")|| name.equals("")|| serial_num.equals("")||birth.equals("")||date.equals("")|| institution.equals("")){
            Toast t = Toast.makeText(this,"빈 항목이 있습니다.", Toast.LENGTH_SHORT);
            t.show();
        }else{
            dbhelper Dbhelper = new dbhelper(this);
            SQLiteDatabase db = Dbhelper.getWritableDatabase();
            db.execSQL("insert into tb_authen(email,certificate_name,certificate_serial_num,birthday,certificate_date,certificate_institution) values (?,?,?,?,?,?)",
                    new String[]{email,name,serial_num,birth,date,institution});
            db.close();

            finish();
        }


    }
}
