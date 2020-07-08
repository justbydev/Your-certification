package com.example.coolpiece.mypage.challenge;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.coolpiece.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;

public class MyAuthenAdapter extends RecyclerView.Adapter {
    ArrayList<MyPicture> mList;
    static Context context;

    public MyAuthenAdapter(ArrayList<MyPicture> mList){
        this.mList=mList;
    }
    public static class MyViewHolder extends RecyclerView.ViewHolder{
        ImageView myauthen_image;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            myauthen_image=itemView.findViewById(R.id.myauthen_image);
            context=itemView.getContext();
        }
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.myauthenpic_itemlist, parent, false);
        MyViewHolder vh=new MyViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        final MyViewHolder myViewHolder=(MyViewHolder)holder;
        String owner=mList.get(position).getOwner();
        owner=owner.replace("-", ".");
        final String big="Images of "+owner;
        final String img=mList.get(position).getImg_name();
        StorageReference storageReference= FirebaseStorage.getInstance().getReference().child(big).child(img);
        storageReference.getDownloadUrl().addOnCompleteListener(new OnCompleteListener<Uri>() {
            @Override
            public void onComplete(@NonNull Task<Uri> task) {
                if(task.isSuccessful()){
                    Glide.with(context)
                            .load(task.getResult())
                            .into(myViewHolder.myauthen_image);
                }
            }
        });
        myViewHolder.myauthen_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, MyAuthenPic_large.class);
                intent.putExtra("big", big);
                intent.putExtra("img", img);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList==null?0:mList.size();
    }
}
