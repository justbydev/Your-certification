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

    TextView intro_button;
    TextView exam_button;
    TextView cutline_button;
    TextView enrollment_button;
    TextView association_button;
    TextView train_button;
    int intro_visib=0;
    int exam_visib=0;
    int cutline_visib=0;
    int enrol_visib=0;
    int asso_visib=0;
    int train_visib=0;
    int intro_height;
    int exam_height;
    int cutline_height;
    int enrol_height;
    int asso_height;
    int train_height;
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

        intro_button=findViewById(R.id.intro_button);
        exam_button=findViewById(R.id.exam_button);
        cutline_button=findViewById(R.id.cutline_button);
        enrollment_button=findViewById(R.id.enrollment_button);
        association_button=findViewById(R.id.association_button);
        train_button=findViewById(R.id.train_button);

        intro_button.setOnClickListener(buttononclicklistener);
        exam_button.setOnClickListener(buttononclicklistener);
        cutline_button.setOnClickListener(buttononclicklistener);
        enrollment_button.setOnClickListener(buttononclicklistener);
        association_button.setOnClickListener(buttononclicklistener);
        train_button.setOnClickListener(buttononclicklistener);
        back_button.setOnClickListener(buttononclicklistener);

        intro_button.setText("자격증 개요   "+"+");
        exam_button.setText("시험방식   "+"+");
        cutline_button.setText("합격기준   "+"+");
        enrollment_button.setText("등록기관   "+"+");
        association_button.setText("실시기관   "+"+");
        train_button.setText("훈련기관   "+"+");

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

        intro.measure(0, 0);
        intro_height=intro.getMeasuredHeight();
        intro.setVisibility(View.INVISIBLE);
        intro.setHeight(0);
        exam.measure(0, 0);
        exam_height=exam.getMeasuredHeight();
        exam.setVisibility(View.INVISIBLE);
        exam.setHeight(0);
        cut_line.measure(0, 0);
        cutline_height=cut_line.getMeasuredHeight();
        cut_line.setVisibility(View.INVISIBLE);
        cut_line.setHeight(0);
        enrollment.measure(0, 0);
        enrol_height=enrollment.getMeasuredHeight();
        enrollment.setVisibility(View.INVISIBLE);
        enrollment.setHeight(0);
        association.measure(0, 0);
        asso_height=association.getMeasuredHeight();
        association.setVisibility(View.INVISIBLE);
        association.setHeight(0);
        training_center.measure(0, 0);
        train_height=training_center.getMeasuredHeight();
        training_center.setVisibility(View.INVISIBLE);
        training_center.setHeight(0);

    }

    private View.OnClickListener buttononclicklistener=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            int id=v.getId();
            switch(id){
                case R.id.intro_button:
                    if(intro_visib==0){
                        intro.setHeight(intro_height*7);
                        intro.setVisibility(View.VISIBLE);
                        intro_visib=1;
                        intro_button.setText("자격증 개요   "+"-");
                    }
                    else{
                        intro.setHeight(0);
                        intro.setVisibility(View.INVISIBLE);
                        intro_visib=0;
                        intro_button.setText("자격증 개요   "+"+");
                    }
                    break;
                case R.id.exam_button:
                    if(exam_visib==0){
                        exam.setHeight(exam_height*2);
                        exam.setVisibility(View.VISIBLE);
                        exam_visib=1;
                        exam_button.setText("시험방식   "+"-");
                    }
                    else{
                        exam.setHeight(0);
                        exam.setVisibility(View.INVISIBLE);
                        exam_visib=0;
                        exam_button.setText("시험방식   "+"+");
                    }
                    break;
                case R.id.cutline_button:
                    if(cutline_visib==0){
                        cut_line.setHeight(cutline_height*2);
                        cut_line.setVisibility(View.VISIBLE);
                        cutline_visib=1;
                        cutline_button.setText("합격기준   "+"-");
                    }
                    else{
                        cut_line.setHeight(0);
                        cut_line.setVisibility(View.INVISIBLE);
                        cutline_visib=0;
                        cutline_button.setText("합격기준   "+"+");
                    }
                    break;
                case R.id.enrollment_button:
                    if(enrol_visib==0){
                        enrollment.setHeight(enrol_height*2);
                        enrollment.setVisibility(View.VISIBLE);
                        enrol_visib=1;
                        enrollment_button.setText("등록기관   "+"-");
                    }
                    else{
                        enrollment.setHeight(0);
                        enrollment.setVisibility(View.INVISIBLE);
                        enrol_visib=0;
                        enrollment_button.setText("등록기관   "+"+");
                    }
                    break;
                case R.id.association_button:
                    if(asso_visib==0){
                        association.setHeight(asso_height*2);
                        association.setVisibility(View.VISIBLE);
                        asso_visib=1;
                        association_button.setText("실시기관   "+"-");
                    }
                    else{
                        association.setHeight(0);
                        association.setVisibility(View.INVISIBLE);
                        asso_visib=0;
                        association_button.setText("실시기관   "+"+");
                    }
                    break;
                case R.id.train_button:
                    if(train_visib==0){
                        training_center.setHeight(train_height*3);
                        training_center.setVisibility(View.VISIBLE);
                        train_visib=1;
                        train_button.setText("훈련기관   "+"-");
                    }
                    else{
                        training_center.setHeight(0);
                        training_center.setVisibility(View.INVISIBLE);
                        train_visib=0;
                        train_button.setText("훈련기관   "+"+");
                    }
                    break;
                case R.id.back_button:
                    finish();
                    break;
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
