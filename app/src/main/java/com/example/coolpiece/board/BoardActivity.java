package com.example.coolpiece.board;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.coolpiece.R;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class BoardActivity extends Fragment {
    RecyclerView total_pic;
    BoardAdapter boardAdapter;
    GridLayoutManager gridLayoutManager;
    ArrayList<BoardPicture> total;
    int first=0;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.board, container, false);
        total_pic=(RecyclerView)v.findViewById(R.id.total_picture);

        total_pic.setHasFixedSize(true);
        gridLayoutManager=new GridLayoutManager(getContext(), 3);
        total_pic.setLayoutManager(gridLayoutManager);
        total=new ArrayList<>();

        DatabaseReference totalimg= FirebaseDatabase.getInstance().getReference("Images");
        totalimg.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                String img=dataSnapshot.getValue().toString();
                BoardPicture boardPicture=new BoardPicture();
                boardPicture.setImg_name(img);
                total.add(0, boardPicture);
                if(first==0){
                    first=1;
                    boardAdapter=new BoardAdapter(total);
                    total_pic.setAdapter(boardAdapter);
                }
                else{
                    boardAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        return v;
    }
}
