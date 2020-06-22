package com.example.coolpiece.mypage.authen;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.coolpiece.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class AuthenFragment extends Fragment {
    @Nullable
    EditText certificate_name;
    EditText certificate_serial_num;
    EditText birthday;
    EditText certificate_date;
    EditText certificate_institution;

    FirebaseUser firebaseAuth;


    Button buttonCertificate;

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.certificate_register, container, false);
        certificate_name = (EditText)v.findViewById(R.id.certificate_name);
        certificate_serial_num = (EditText)v.findViewById(R.id.certificate_serial_num);
        birthday = (EditText)v.findViewById(R.id.birthday);
        certificate_date = (EditText)v.findViewById(R.id.certificate_date);
        certificate_institution = (EditText)v.findViewById(R.id.certificate_institution);

        buttonCertificate = (Button)v.findViewById(R.id.buttonCertificate);
        return v;

    }
    private View.OnClickListener buttononclicklistener=new View.OnClickListener() {
        @Override
        public void onClick(View v) {


        }


    };
}

