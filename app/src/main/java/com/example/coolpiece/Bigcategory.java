package com.example.coolpiece;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class Bigcategory extends AppCompatActivity {
    TextView bigcategory;
    Intent intent;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bigcategory);

        bigcategory=(TextView)findViewById(R.id.bigcategory);
        intent=getIntent();
        bigcategory.setText(intent.getStringExtra("CA"));
    }
}
