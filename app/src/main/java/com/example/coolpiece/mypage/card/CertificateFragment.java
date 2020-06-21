package com.example.coolpiece.mypage.card;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CertificateFragment {
    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;
    DatabaseReference databaseReference;
    DatabaseReference scheduleReference;
    @Nullable
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        firebaseUser=FirebaseAuth.getInstance().getCurrentUser();
        String temp=firebaseUser.getEmail().toString();
        String name=temp.replace('.', '-');
        databaseReference= FirebaseDatabase.getInstance().getReference("Certificate").child(name);

        return null;
    }

}
