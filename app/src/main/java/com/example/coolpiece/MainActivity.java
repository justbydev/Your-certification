package com.example.coolpiece;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    Button gineong;
    Button gisa;
    Button gisulsa;
    Button sanup;
    Button jido;
    Button guitar;
    TextView mylocation;
    TextView location_search;
    private Intent intent;
    public String mylocate=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mylocation=(TextView)findViewById(R.id.mylocation);
        location_search=(TextView)findViewById(R.id.location_search);

        gineong=(Button)findViewById(R.id.gineong);
        gisa=(Button)findViewById(R.id.gisa);
        gisulsa=(Button)findViewById(R.id.gisulsa);
        sanup=(Button)findViewById(R.id.sanup);
        jido=(Button)findViewById(R.id.jido);
        guitar=(Button)findViewById(R.id.guitar);

        mylocation.setOnClickListener(buttonClickListener);
        location_search.setOnClickListener(buttonClickListener);

        gineong.setOnClickListener(buttonClickListener);
        gisa.setOnClickListener(buttonClickListener);
        gisulsa.setOnClickListener(buttonClickListener);
        sanup.setOnClickListener(buttonClickListener);
        jido.setOnClickListener(buttonClickListener);
        guitar.setOnClickListener(buttonClickListener);
    }
    private View.OnClickListener buttonClickListener=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            int id=v.getId();
            switch(id){
                case R.id.mylocation:
                    return;
                case R.id.location_search:
                    intent=new Intent(MainActivity.this, DaumLocationActivity.class);
                    startActivity(intent);
                    return;
                case R.id.gineong:
                    intent=new Intent(MainActivity.this, Bigcategory.class);
                    intent.putExtra("CA", "기능사");
                    startActivity(intent);
                    return;
                case R.id.gisa:
                    intent=new Intent(MainActivity.this, Bigcategory.class);
                    intent.putExtra("CA", "기사");
                    startActivity(intent);
                    return;
                case R.id.gisulsa:
                    intent=new Intent(MainActivity.this, Bigcategory.class);
                    intent.putExtra("CA", "기술사");
                    startActivity(intent);
                    return;
                case R.id.sanup:
                    intent=new Intent(MainActivity.this, Bigcategory.class);
                    intent.putExtra("CA", "산업기사");
                    startActivity(intent);
                    return;
                case R.id.jido:
                    intent=new Intent(MainActivity.this, Bigcategory.class);
                    intent.putExtra("CA", "지도사");
                    startActivity(intent);
                    return;
                case R.id.guitar:
                    intent=new Intent(MainActivity.this, Bigcategory.class);
                    intent.putExtra("CA", "기타/취미");
                    startActivity(intent);
                    return;
                default:
                    return;
            }
        }
    };

}
