package com.example.coolpiece.home.button.certification;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.coolpiece.R;
import com.example.coolpiece.splash.dataclass.Guitar;

public class CertificateGuitarDetail extends AppCompatActivity {
    TextView back_button;
    TextView name;
    TextView intro;
    TextView exam;
    TextView cut_line;
    TextView enrollment;
    TextView association;
    TextView training_center;
    Intent intent;
    Guitar guitar;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.certificate_guitar_detail);

        back_button=findViewById(R.id.back_button);
        name=findViewById(R.id.name);
        intro=findViewById(R.id.intro);
        exam=findViewById(R.id.exam);
        cut_line=findViewById(R.id.cut_line);
        enrollment=findViewById(R.id.enrollment);
        association=findViewById(R.id.association);
        training_center=findViewById(R.id.training_center);

        back_button.setOnClickListener(buttononclicklistener);

        intent=getIntent();

        guitar=new Guitar();
        guitar=intent.getParcelableExtra("detail");

        name.setText(guitar.getName());
        intro.setText(guitar.getIntro());
        exam.setText(guitar.getExam());
        cut_line.setText(guitar.getCut_line());
        enrollment.setText(guitar.getEnrollment());
        association.setText(guitar.getAssociation());
        training_center.setText(guitar.getTraining_center());

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
