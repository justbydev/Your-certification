package com.example.coolpiece;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class Smallcategory extends AppCompatActivity {
    TextView smallcategory;
    private Intent intent;
    String small;
    String big;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.smallcategory);

        smallcategory=(TextView)findViewById(R.id.smallcategory);

        intent=getIntent();
        small=intent.getStringExtra("SCA");
        big=intent.getStringExtra("BCA");
        smallcategory.setText(big+'-'+small);
    }
}
