package com.example.coolpiece.mypage.authen;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.coolpiece.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.Console;
import java.util.HashMap;
import java.util.Map;

public class AuthenFragment extends Fragment {
    @Nullable
    EditText certificate_name;
    EditText certificate_serial_num;
    EditText birthday;
    EditText certificate_date;
    EditText certificate_institution;

    FirebaseUser firebaseAuth;
    final FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference ref = database.getReference("certificate/data");

    Button buttonCertificate;

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.certificate_register, container, false);
        certificate_name = (EditText)v.findViewById(R.id.certificate_name);
        certificate_serial_num = (EditText)v.findViewById(R.id.certificate_serial_num);
        birthday = (EditText)v.findViewById(R.id.birthday);
        certificate_date = (EditText)v.findViewById(R.id.certificate_date);
        certificate_institution = (EditText)v.findViewById(R.id.certificate_institution);

        buttonCertificate = (Button)v.findViewById(R.id.buttonCertificate);
        buttonCertificate.setOnClickListener(buttononclicklistener);
        return v;

    }
    private View.OnClickListener buttononclicklistener=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            int id=v.getId();
            if (v==buttonCertificate) {
                firebaseAuth = FirebaseAuth.getInstance().getCurrentUser();
                ;
                String email = firebaseAuth.getEmail().toString();
                String name = certificate_name.getText().toString();
                String serial_num = certificate_serial_num.getText().toString();
                String birth = birthday.getText().toString();
                String date = birthday.getText().toString();
                String institution = certificate_institution.getText().toString();
                if (name == null || email.equals("") || name.equals("") || serial_num.equals("") || birth.equals("") || date.equals("") || institution.equals("")) {
                    Toast t = Toast.makeText(getContext(), "빈 항목이 있습니다.", Toast.LENGTH_SHORT);
                    t.show();
                } else {
                    DatabaseReference usersRef = ref.child(email);
                    Authentificate_user new_user = new Authentificate_user(name, serial_num, birth, date, institution);
                    usersRef.child(email).setValue(new_user);
                    Toast t = Toast.makeText(getContext(), "인증완료.", Toast.LENGTH_SHORT);
                    t.show();

                }
            }

        }


    };
}

