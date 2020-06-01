package com.example.coolpiece;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class CertificateDetail extends AppCompatActivity {
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
    String cat;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.certificate_detail);

        name=(TextView)findViewById(R.id.name);
        intro=(TextView)findViewById(R.id.intro);
        association=(TextView)findViewById(R.id.association);
        major=(TextView)findViewById(R.id.major);
        training_center=(TextView)findViewById(R.id.training_center);
        test_subject=(TextView)findViewById(R.id.test_subject);
        test_method=(TextView)findViewById(R.id.test_method);
        cut_line=(TextView)findViewById(R.id.cut_line);


        intent=getIntent();

        cat=intent.getStringExtra("cat");
        if(cat.equals("gisul")){
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
        else if(cat.equals("gineong")){
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

    }
}
