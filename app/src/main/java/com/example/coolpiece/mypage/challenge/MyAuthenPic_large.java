package com.example.coolpiece.mypage.challenge;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.coolpiece.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class MyAuthenPic_large extends AppCompatActivity {
    ImageView myimage;
    Intent intent;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.myauthenpic_large);
        myimage=(ImageView)findViewById(R.id.myimage);

        intent=getIntent();
        String big=intent.getStringExtra("big");
        String img=intent.getStringExtra("img");
        StorageReference storageReference= FirebaseStorage.getInstance().getReference().child(big).child(img);
        storageReference.getDownloadUrl().addOnCompleteListener(new OnCompleteListener<Uri>() {
            @Override
            public void onComplete(@NonNull Task<Uri> task) {
                if(task.isSuccessful()){
                    Glide.with(MyAuthenPic_large.this)
                            .load(task.getResult())
                            .into(myimage);
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}
