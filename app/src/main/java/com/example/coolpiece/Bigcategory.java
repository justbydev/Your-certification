package com.example.coolpiece;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class Bigcategory extends AppCompatActivity {
    TextView bigcategory;
    Button A,B,C,D,E,F,G,H,I,J,K,L,M,N,O,P,Q,r,S,T;
    private Intent intent;
    String big;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bigcategory);

        bigcategory=(TextView)findViewById(R.id.bigcategory);
        intent=getIntent();
        big=intent.getStringExtra("CA");
        bigcategory.setText(big);
        A=(Button)findViewById(R.id.A);
        B=(Button)findViewById(R.id.B);
        C=(Button)findViewById(R.id.C);
        D=(Button)findViewById(R.id.D);
        E=(Button)findViewById(R.id.E);
        F=(Button)findViewById(R.id.F);
        G=(Button)findViewById(R.id.G);
        H=(Button)findViewById(R.id.H);
        I=(Button)findViewById(R.id.I);
        J=(Button)findViewById(R.id.J);
        K=(Button)findViewById(R.id.K);
        L=(Button)findViewById(R.id.L);
        M=(Button)findViewById(R.id.M);
        N=(Button)findViewById(R.id.N);
        O=(Button)findViewById(R.id.O);
        P=(Button)findViewById(R.id.P);
        Q=(Button)findViewById(R.id.Q);
        r=(Button)findViewById(R.id.R);
        S=(Button)findViewById(R.id.S);
        T=(Button)findViewById(R.id.T);

        A.setOnClickListener(buttonOnClickListener);
        B.setOnClickListener(buttonOnClickListener);
        C.setOnClickListener(buttonOnClickListener);
        D.setOnClickListener(buttonOnClickListener);
        E.setOnClickListener(buttonOnClickListener);
        F.setOnClickListener(buttonOnClickListener);
        G.setOnClickListener(buttonOnClickListener);
        H.setOnClickListener(buttonOnClickListener);
        I.setOnClickListener(buttonOnClickListener);
        J.setOnClickListener(buttonOnClickListener);
        K.setOnClickListener(buttonOnClickListener);
        L.setOnClickListener(buttonOnClickListener);
        M.setOnClickListener(buttonOnClickListener);
        N.setOnClickListener(buttonOnClickListener);
        O.setOnClickListener(buttonOnClickListener);
        P.setOnClickListener(buttonOnClickListener);
        Q.setOnClickListener(buttonOnClickListener);
        r.setOnClickListener(buttonOnClickListener);
        S.setOnClickListener(buttonOnClickListener);
        T.setOnClickListener(buttonOnClickListener);




    }
    private View.OnClickListener buttonOnClickListener=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            int id=v.getId();
            switch(id){
                case R.id.A:
                    intent=new Intent(Bigcategory.this, Smallcategory.class);
                    intent.putExtra("SCA", "건설");
                    intent.putExtra("BCA", big);
                    startActivity(intent);
                    return;
                case R.id.B:
                    intent=new Intent(Bigcategory.this, Smallcategory.class);
                    intent.putExtra("SCA", "경영.회계.사무");
                    intent.putExtra("BCA", big);
                    startActivity(intent);
                    return;
                case R.id.C:
                    intent=new Intent(Bigcategory.this, Smallcategory.class);
                    intent.putExtra("SCA", "광업자원");
                    intent.putExtra("BCA", big);
                    startActivity(intent);
                    return;
                case R.id.D:
                    intent=new Intent(Bigcategory.this, Smallcategory.class);
                    intent.putExtra("SCA", "기계");
                    intent.putExtra("BCA", big);
                    startActivity(intent);
                    return;
                case R.id.E:
                    intent=new Intent(Bigcategory.this, Smallcategory.class);
                    intent.putExtra("SCA", "농림어업");
                    intent.putExtra("BCA", big);
                    startActivity(intent);
                    return;
                case R.id.F:
                    intent=new Intent(Bigcategory.this, Smallcategory.class);
                    intent.putExtra("SCA", "문화.예술");
                    intent.putExtra("BCA", big);
                    startActivity(intent);
                    return;
                case R.id.G:
                    intent=new Intent(Bigcategory.this, Smallcategory.class);
                    intent.putExtra("SCA", "보건.의료");
                    intent.putExtra("BCA", big);
                    startActivity(intent);
                    return;
                case R.id.H:
                    intent=new Intent(Bigcategory.this, Smallcategory.class);
                    intent.putExtra("SCA", "사회복지.종교");
                    intent.putExtra("BCA", big);
                    startActivity(intent);
                    return;
                case R.id.I:
                    intent=new Intent(Bigcategory.this, Smallcategory.class);
                    intent.putExtra("SCA", "섬유.의복");
                    intent.putExtra("BCA", big);
                    startActivity(intent);
                    return;
                case R.id.J:
                    intent=new Intent(Bigcategory.this, Smallcategory.class);
                    intent.putExtra("SCA", "식품.가공");
                    intent.putExtra("BCA", big);
                    startActivity(intent);
                    return;
                case R.id.K:
                    intent=new Intent(Bigcategory.this, Smallcategory.class);
                    intent.putExtra("SCA", "안전관리");
                    intent.putExtra("BCA", big);
                    startActivity(intent);
                    return;
                case R.id.L:
                    intent=new Intent(Bigcategory.this, Smallcategory.class);
                    intent.putExtra("SCA", "영업.판매");
                    intent.putExtra("BCA", big);
                    startActivity(intent);
                    return;
                case R.id.M:
                    intent=new Intent(Bigcategory.this, Smallcategory.class);
                    intent.putExtra("SCA", "운전.운송");
                    intent.putExtra("BCA", big);
                    startActivity(intent);
                case R.id.N:
                    intent=new Intent(Bigcategory.this, Smallcategory.class);
                    intent.putExtra("SCA", "음식서비스");
                    intent.putExtra("BCA", big);
                    startActivity(intent);
                    return;
                case R.id.O:
                    intent=new Intent(Bigcategory.this, Smallcategory.class);
                    intent.putExtra("SCA", "이용.숙박.여행");
                    intent.putExtra("BCA", big);
                    startActivity(intent);
                    return;
                case R.id.P:
                    intent=new Intent(Bigcategory.this, Smallcategory.class);
                    intent.putExtra("SCA", "가구.공예");
                    intent.putExtra("BCA", big);
                    startActivity(intent);
                    return;
                case R.id.Q:
                    intent=new Intent(Bigcategory.this, Smallcategory.class);
                    intent.putExtra("SCA", "재료");
                    intent.putExtra("BCA", big);
                    startActivity(intent);
                    return;
                case R.id.R:
                    intent=new Intent(Bigcategory.this, Smallcategory.class);
                    intent.putExtra("SCA", "전기.전자");
                    intent.putExtra("BCA", big);
                    startActivity(intent);
                    return;
                case R.id.S:
                    intent=new Intent(Bigcategory.this, Smallcategory.class);
                    intent.putExtra("SCA", "정보통신");
                    intent.putExtra("BCA", big);
                    startActivity(intent);
                    return;
                case R.id.T:
                    intent=new Intent(Bigcategory.this, Smallcategory.class);
                    intent.putExtra("SCA", "화학.환경.에너지");
                    intent.putExtra("BCA", big);
                    startActivity(intent);
                    return;
                default:
                    return;
            }
        }
    };
}
