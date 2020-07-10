package com.example.coolpiece.home.button.academy;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.coolpiece.home.button.certification.EachCertificate;
import com.example.coolpiece.R;

import java.util.ArrayList;

public class NotConnectAcademyAdapter extends RecyclerView.Adapter {
    private ArrayList<String> academy_name=null;

    private static Context context;
    public NotConnectAcademyAdapter(ArrayList<String> academy_name){
        this.academy_name=academy_name;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        public TextView each_academy;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            each_academy=itemView.findViewById(R.id.each_academy);
            context= EachCertificate.context;
        }
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.academy_itemlist, parent, false);
        AcademyAdapter.MyViewHolder vh=new AcademyAdapter.MyViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        AcademyAdapter.MyViewHolder myViewHolder=(AcademyAdapter.MyViewHolder)holder;
        myViewHolder.each_academy.setText(academy_name.get(position));
        final int pos=position;
        myViewHolder.each_academy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(v.getContext(), NotConnectCertificateAcademy.class);
                intent.putExtra("academy_name", academy_name.get(pos));

                v.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return academy_name==null?0:academy_name.size();
    }
}
