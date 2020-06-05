package com.example.coolpiece;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {
    EditText editTextEmail;
    EditText editTextPassword;
    Button buttonsignup;
    FirebaseAuth firebaseAuth;
    TextView loginfailmessage;
    TextView textViewsignin;
    ProgressDialog progressDialog;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        editTextEmail=(EditText)findViewById(R.id.editTextEmail);
        editTextPassword=(EditText)findViewById(R.id.editTextPassword);
        buttonsignup=(Button)findViewById(R.id.buttonSignup);
        loginfailmessage=(TextView)findViewById(R.id.loginfailmessage);
        textViewsignin=(TextView)findViewById(R.id.textViewSignin);

        firebaseAuth=FirebaseAuth.getInstance();

        progressDialog = new ProgressDialog(this);

        buttonsignup.setOnClickListener(buttononclicklistener);
        textViewsignin.setOnClickListener(buttononclicklistener);
    }

    private View.OnClickListener buttononclicklistener=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            int id=v.getId();
            switch(id){
                case R.id.buttonSignup:
                    userLogin();
                    return;
                case R.id.textViewSignin:
                    Intent intent=new Intent(LoginActivity.this, SignupActivity.class);
                    startActivity(intent);
                default:
                    return;
            }
        }
    };

    public void userLogin(){
        String email=editTextEmail.getText().toString();
        String password=editTextPassword.getText().toString();

        if(TextUtils.isEmpty(email)){
            Toast.makeText(this, "email을 입력해 주세요.", Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(password)){
            Toast.makeText(this, "password를 입력해 주세요.", Toast.LENGTH_SHORT).show();
            return;
        }
        progressDialog.setMessage("로그인중입니다. 잠시 기다려 주세요...");
        progressDialog.show();
        firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressDialog.dismiss();
                        if(task.isSuccessful()){
                            Intent intent=new Intent(LoginActivity.this, MainFragment.class);
                            finish();
                            startActivity(intent);
                        }
                        else{
                            loginfailmessage.setText("이메일/비밀번호를 확인해주세요.");
                        }
                    }
                });

    }
}
