package com.example.coolpiece.mycert;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.coolpiece.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class EachmyCert extends AppCompatActivity {
    ImageView mycert_image;
    TextView cert_name;
    TextView cert_serial_num;
    TextView birthday;
    TextView cert_date;
    TextView cert_institution;
    TextView back_button;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.each_mycert);

        mycert_image=findViewById(R.id.mycert_image);
        cert_name=findViewById(R.id.cert_name);
        cert_serial_num=findViewById(R.id.cert_serial_num);
        birthday=findViewById(R.id.birthday);
        cert_date=findViewById(R.id.cert_date);
        cert_institution=findViewById(R.id.cert_institution);
        back_button=findViewById(R.id.back_button);

        Intent intent=getIntent();
        String img=intent.getStringExtra("img_name");
        String me= FirebaseAuth.getInstance().getCurrentUser().getEmail().toString();
        me=me.replace(".", "-");
        String big="Cert of "+me;

        StorageReference storageReference= FirebaseStorage.getInstance().getReference().child(big).child(img);
        storageReference.getDownloadUrl().addOnCompleteListener(new OnCompleteListener<Uri>() {
            @Override
            public void onComplete(@NonNull Task<Uri> task) {
                if(task.isSuccessful()){
                    Glide.with(EachmyCert.this)
                            .load(task.getResult())
                            .into(mycert_image);
                }
            }
        });


        cert_name.setText(intent.getStringExtra("cert_name"));
        cert_serial_num.setText(intent.getStringExtra("cert_serial_num"));
        birthday.setText(intent.getStringExtra("birthday"));
        cert_date.setText(intent.getStringExtra("cert_date"));
        cert_institution.setText(intent.getStringExtra("cert_institution"));

        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}
