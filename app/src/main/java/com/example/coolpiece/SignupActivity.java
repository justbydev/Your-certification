package com.example.coolpiece;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
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
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignupActivity extends AppCompatActivity {
    EditText signupname;
    EditText signupemail;
    TextView emailerrorcheck;
    EditText signuppassword;
    TextView passworderrorcheck;
    EditText signuprepassword;
    TextView passwordequalcheck;
    Button buttonSignin;
    TextView tologin;
    int emailcheck=0;
    int passwordcheck=0;
    int passwordequal=0;
    private String name;
    private String email;
    private String password;
    private String repassword;
    String emailValidation="^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    String passwordValidation="^(?=.*[A-Za-z])(?=.*[0-9])(?=.*[$@$!%*#?&]).{8,}.$";

    FirebaseAuth firebaseAuth;
    ProgressDialog progressDialog;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);

        signupname=(EditText)findViewById(R.id.signupname);
        signupemail=(EditText)findViewById(R.id.signupemail);
        emailerrorcheck=(TextView)findViewById(R.id.emailerrorcheck);
        signuppassword=(EditText)findViewById(R.id.signuppassword);
        passworderrorcheck=(TextView)findViewById(R.id.passworderrorcheck);
        signuprepassword=(EditText)findViewById(R.id.signuprepassword);
        passwordequalcheck=(TextView)findViewById(R.id.passwordequalcheck);
        buttonSignin=(Button)findViewById(R.id.buttonSignin);
        tologin=(TextView)findViewById(R.id.tologin);

        buttonSignin.setOnClickListener(buttononclicklistener);
        tologin.setOnClickListener(buttononclicklistener);

        firebaseAuth=FirebaseAuth.getInstance();
        progressDialog=new ProgressDialog(this);


        signupemail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) { }
            @Override
            public void afterTextChanged(Editable s) {
                email=signupemail.getText().toString();
                if(email.matches(emailValidation)){
                    signupemail.setTextColor(Color.BLUE);
                    emailcheck=1;
                    emailerrorcheck.setText("제대로 된 이메일 형식입니다");
                }
                else{
                    signupemail.setTextColor(Color.BLACK);
                    emailcheck=0;
                    emailerrorcheck.setText("제대로 된 이메일 형식을 입력해주세요");
                }
            }
        });
        signuppassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) { }
            @Override
            public void afterTextChanged(Editable s) {
                password=signuppassword.getText().toString();
                if(password.matches(passwordValidation)){
                    passwordcheck=1;
                    passworderrorcheck.setText("올바른 비밀번호 형식입니다");
                }
                else{
                    passwordcheck=0;
                    passworderrorcheck.setText("영문자, 숫자, 특수문자 포함 9자리 이상으로 해주세요");
                }
            }
        });

        signuprepassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) { }
            @Override
            public void afterTextChanged(Editable s) {
                repassword=signuprepassword.getText().toString();
                if(password.equals(repassword)){
                    passwordequal=1;
                    passwordequalcheck.setText("비밀번호가 일치합니다");
                }
                else{
                    passwordequal=0;
                    passwordequalcheck.setText("비밀번호가 일치하지 않습니다");
                }
            }
        });
    }

    private View.OnClickListener buttononclicklistener=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            int id=v.getId();
            switch(id){
                case R.id.buttonSignin:
                    userSignin();
                    return;
                case R.id.tologin:
                    Intent intent=new Intent(SignupActivity.this, LoginActivity.class);
                    finish();
                    startActivity(intent);
                    return;
                default:
                    return;
            }
        }
    };
    private void userSignin(){
        name=signupname.getText().toString();
        if(TextUtils.isEmpty(name)){
            Toast.makeText(this, "이름을 입력해 주세요", Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(email)){
            Toast.makeText(this, "이메일을 입력해 주세요", Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(password)){
            Toast.makeText(this, "비밀번호를 입력해 주세요", Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(repassword)){
            Toast.makeText(this, "비밀번호를 재입력해 주세요", Toast.LENGTH_SHORT).show();
            return;
        }
        if(emailcheck==0){
            Toast.makeText(this, "제대로 된 이메일 형식을 입력해 주세요", Toast.LENGTH_SHORT).show();
            return;
        }
        else{
            if(passwordcheck==0){
                Toast.makeText(this, "제대로 된 비밀번호 형식을 입력해 주세요", Toast.LENGTH_SHORT).show();
                return;
            }
            else{
                if(passwordequal==0){
                    Toast.makeText(this, "일치하는 비밀번호를 입력해 주세요", Toast.LENGTH_SHORT).show();
                    return;
                }
                else{
                    progressDialog.setMessage("등록중입니다. 기다려 주세요...");
                    progressDialog.show();
                    firebaseAuth.createUserWithEmailAndPassword(email, password)
                            .addOnCompleteListener(SignupActivity.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if(task.isSuccessful()){
                                        Member member=new Member();
                                        member.setEmail(email);
                                        member.setName(name);
                                        DatabaseReference memberReference= FirebaseDatabase.getInstance().getReference("Member");
                                        memberReference.push().setValue(member);
                                        firebaseAuth.signOut();
                                        Intent intent=new Intent(SignupActivity.this, LoginActivity.class);
                                        finish();
                                        startActivity(intent);
                                    }
                                    else{
                                        try{
                                            throw task.getException();
                                        }catch(FirebaseAuthUserCollisionException e){
                                            Toast.makeText(SignupActivity.this, "이미 가입된 이메일입니다", Toast.LENGTH_SHORT).show();
                                        } catch (Exception e) {
                                            Toast.makeText(SignupActivity.this, "오류가 발생했습니다. 다시 시도해주세요", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                    progressDialog.dismiss();
                                }
                            });
                }
            }
        }
    }
}
