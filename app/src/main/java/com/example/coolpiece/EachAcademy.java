package com.example.coolpiece;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class EachAcademy extends AppCompatActivity {
    TextView academy_name;
    TextView academy_address;
    TextView academy_phone;
    Intent intent;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.each_academy);

        academy_name=(TextView)findViewById(R.id.academy_name);
        academy_address=(TextView)findViewById(R.id.academy_address);
        academy_phone=(TextView)findViewById(R.id.academy_phone);

        intent=getIntent();
        academy_name.setText(intent.getStringExtra("academy_name"));
        academy_address.setText(intent.getStringExtra("academy_address"));
        academy_phone.setText(intent.getStringExtra("academy_phone"));

    }
}
