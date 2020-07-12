package com.example.coolpiece.home;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.coolpiece.home.button.academy.CertificateAcademy;
import com.example.coolpiece.home.button.academy.NotConnectCertificateAcademy;
import com.example.coolpiece.R;

import java.util.ArrayList;

public class AcademyGridAdapter extends RecyclerView.Adapter {
    ArrayList<String> academy_name=null;
    private static Context context;
    public AcademyGridAdapter(ArrayList<String> academy_name){
        this.academy_name=academy_name;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        public TextView home_academy_name;
        public ImageView academy_image;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            home_academy_name=itemView.findViewById(R.id.home_academy_name);
            academy_image=itemView.findViewById(R.id.academy_image);
            context=itemView.getContext();
        }
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.grid_recyclerview_item, parent, false);
        MyViewHolder vh=new MyViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        MyViewHolder myViewHolder=(MyViewHolder)holder;
        String text=academy_name.get(position);
        if(text.length()>=12){
            text=text.substring(0, 10);
            text=text+"...";
        }
        myViewHolder.home_academy_name.setText(text);
        final int pos=position;
        myViewHolder.home_academy_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, CertificateAcademy.class);
                intent.putExtra("academy_name", academy_name.get(pos));

                context.startActivity(intent);
            }
        });
        myViewHolder.academy_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, CertificateAcademy.class);
                intent.putExtra("academy_name", academy_name.get(pos));

                context.startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return academy_name!=null?academy_name.size():0;
    }
}
