package com.example.coolpiece.home.button.certification;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.coolpiece.R;
import com.example.coolpiece.splash.dataclass.Gineongsa;
import com.example.coolpiece.splash.dataclass.Gisa;
import com.example.coolpiece.splash.dataclass.Gisul;
import com.example.coolpiece.splash.dataclass.Sanup;

import org.w3c.dom.Text;

public class CertificateDetail extends AppCompatActivity {
    TextView back_button;
    TextView name;
    TextView intro;
    TextView association;
    TextView major;
    TextView training_center;
    TextView test_subject;
    TextView test_method;
    TextView cut_line;
    Intent intent;
    Gineongsa gineongsa;
    Gisul gisul;
    Gisa gisa;
    Sanup sanup;
    String cat;

    TextView intro_button;
    TextView association_button;
    TextView major_button;
    TextView train_button;
    TextView test_button;
    TextView method_button;
    TextView cutline_button;
    int intro_visib=0;
    int asso_visib=0;
    int major_visib=0;
    int train_visib=0;
    int test_visib=0;
    int method_visib=0;
    int cutline_visib=0;
    int intro_height;
    int asso_height;
    int major_height;
    int train_height;
    int test_height;
    int method_height;
    int cutline_height;

    int test;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.certificate_detail);

        back_button=(TextView)findViewById(R.id.back_button);
        name=(TextView)findViewById(R.id.name);
        intro=(TextView)findViewById(R.id.intro);
        association=(TextView)findViewById(R.id.association);
        major=(TextView)findViewById(R.id.major);
        training_center=(TextView)findViewById(R.id.training_center);
        test_subject=(TextView)findViewById(R.id.test_subject);
        test_method=(TextView)findViewById(R.id.test_method);
        cut_line=(TextView)findViewById(R.id.cut_line);

        intro_button=(TextView)findViewById(R.id.intro_button);
        association_button=(TextView)findViewById(R.id.association_button);
        major_button=(TextView)findViewById(R.id.major_button);
        train_button=(TextView)findViewById(R.id.train_button);
        test_button=(TextView)findViewById(R.id.test_button);
        method_button=(TextView)findViewById(R.id.method_button);
        cutline_button=(TextView)findViewById(R.id.cutline_button);


        intro_button.setOnClickListener(buttononclicklistener);
        association_button.setOnClickListener(buttononclicklistener);
        major_button.setOnClickListener(buttononclicklistener);
        train_button.setOnClickListener(buttononclicklistener);
        test_button.setOnClickListener(buttononclicklistener);
        method_button.setOnClickListener(buttononclicklistener);
        cutline_button.setOnClickListener(buttononclicklistener);
        back_button.setOnClickListener(buttononclicklistener);

        intro_button.setText("자격증 개요   "+"+");
        association_button.setText("자격증 실시기관   "+"+");
        major_button.setText("자격증 관련학과   "+"+");
        train_button.setText("자격증 훈련기관   "+"+");
        test_button.setText("시험과목   "+"+");
        method_button.setText("검정방법   "+"+");
        cutline_button.setText("합격기준   "+"+");

        intent=getIntent();

        cat=intent.getStringExtra("cat");
        if(cat.equals("기술사")){
            gisul=new Gisul();
            gisul=intent.getParcelableExtra("detail");
            name.setText(gisul.getName());
            intro.setText(gisul.getIntro());
            association.setText(gisul.getAssociation());
            major.setText(gisul.getMajor());
            training_center.setText(gisul.getTraining_center());
            test_subject.setText(gisul.getTest_subject());
            test_method.setText(gisul.getTest_method());
            cut_line.setText(gisul.getCut_line());
        }
        else if(cat.equals("기능사")){
            gineongsa=new Gineongsa();
            gineongsa=intent.getParcelableExtra("detail");
            name.setText(gineongsa.getName());
            intro.setText(gineongsa.getIntro());
            association.setText(gineongsa.getAssociation());
            major.setText(gineongsa.getMajor());
            training_center.setText(gineongsa.getTraining_center());
            test_subject.setText(gineongsa.getTest_subject());
            test_method.setText(gineongsa.getTest_method());
            cut_line.setText(gineongsa.getCut_line());
        }
        else if(cat.equals("기사")){
            gisa=new Gisa();
            gisa=intent.getParcelableExtra("detail");
            name.setText(gisa.getName());
            intro.setText(gisa.getIntro());
            association.setText(gisa.getAssociation());
            major.setText(gisa.getMajor());
            training_center.setText(gisa.getTraining_center());
            test_subject.setText(gisa.getTest_subject());
            test_method.setText(gisa.getTest_method());
            cut_line.setText(gisa.getCut_line());
        }
        else if(cat.equals("산업기사")){
            sanup=new Sanup();
            sanup=intent.getParcelableExtra("detail");
            name.setText(sanup.getName());
            intro.setText(sanup.getIntro());
            association.setText(sanup.getAssociation());
            major.setText(sanup.getMajor());
            training_center.setText(sanup.getTraining_center());
            test_subject.setText(sanup.getTest_subject());
            test_method.setText(sanup.getTest_method());
            cut_line.setText(sanup.getCut_line());
        }


        test=intro.getHeight();
        intro.measure(0, 0);
        intro_height=intro.getMeasuredHeight();
        intro.setVisibility(View.INVISIBLE);
        intro.setHeight(0);
        association.measure(0, 0);
        asso_height=association.getMeasuredHeight();
        association.setVisibility(View.VISIBLE);
        association.setHeight(0);
        major.measure(0, 0);
        major_height=major.getMeasuredHeight();
        major.setVisibility(View.INVISIBLE);
        major.setHeight(0);
        training_center.measure(0, 0);
        train_height=training_center.getMeasuredHeight();
        training_center.setVisibility(View.INVISIBLE);
        training_center.setHeight(0);
        test_subject.measure(0, 0);
        test_height=test_subject.getMeasuredHeight();
        test_subject.setVisibility(View.INVISIBLE);
        test_subject.setHeight(0);
        test_method.measure(0, 0);
        method_height=test_method.getMeasuredHeight();
        test_method.setVisibility(View.INVISIBLE);
        test_method.setHeight(0);
        cut_line.measure(0, 0);
        cutline_height=cut_line.getMeasuredHeight();
        cut_line.setVisibility(View.INVISIBLE);
        cut_line.setHeight(0);





    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);

    }

    private View.OnClickListener buttononclicklistener=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            int id=v.getId();
            switch(id){
                case R.id.intro_button:
                    if(intro_visib==0){
                        intro.setHeight(intro_height*11);
                        intro.setVisibility(View.VISIBLE);
                        intro_visib=1;
                        intro_button.setText("자격증 개요   "+"-");
                    }
                    else{
                        intro.setHeight(0);
                        intro.setVisibility(View.GONE);
                        intro_visib=0;
                        intro_button.setText("자격증 개요   "+"+");
                    }
                    break;
                case R.id.association_button:
                    if(asso_visib==0){
                        association.setHeight(asso_height*2);
                        association.setVisibility(View.VISIBLE);
                        asso_visib=1;
                        association_button.setText("자격증 실시기관   "+"-");
                    }
                    else{
                        association.setHeight(0);
                        association.setVisibility(View.INVISIBLE);
                        asso_visib=0;
                        association_button.setText("자격증 실시기관   "+"+");
                    }
                    break;
                case R.id.major_button:
                    if(major_visib==0){
                        major.setHeight(major_height*3);
                        major.setVisibility(View.VISIBLE);
                        major_visib=1;
                        major_button.setText("자격증 관련학과   "+"-");
                    }
                    else{
                        major.setHeight(0);
                        major.setVisibility(View.INVISIBLE);
                        major_visib=0;
                        major_button.setText("자격증 관련학과   "+"+");
                    }
                    break;
                case R.id.train_button:
                    if(train_visib==0){
                        training_center.setHeight(train_height*2);
                        training_center.setVisibility(View.VISIBLE);
                        train_visib=1;
                        train_button.setText("자격증 훈련기관   "+"-");
                    }
                    else{
                        training_center.setHeight(0);
                        training_center.setVisibility(View.INVISIBLE);
                        train_visib=0;
                        train_button.setText("자격증 훈련기관   "+"+");
                    }
                    break;
                case R.id.test_button:
                    if(test_visib==0){
                        test_subject.setHeight(test_height*4);
                        test_subject.setVisibility(View.VISIBLE);
                        test_visib=1;
                        test_button.setText("시험과목   "+"-");
                    }
                    else{
                        test_subject.setHeight(0);
                        test_subject.setVisibility(View.INVISIBLE);
                        test_visib=0;
                        test_button.setText("시험과목   "+"+");
                    }
                    break;
                case R.id.method_button:
                    if(method_visib==0){
                        test_method.setHeight(method_height*4);
                        test_method.setVisibility(View.VISIBLE);
                        method_visib=1;
                        method_button.setText("검정방법   "+"-");
                    }
                    else{
                        test_method.setHeight(0);
                        test_method.setVisibility(View.INVISIBLE);
                        method_visib=0;
                        method_button.setText("검정방법   "+"+");
                    }
                    break;
                case R.id.cutline_button:
                    if(cutline_visib==0){
                        cut_line.setHeight(cutline_height*5);
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
