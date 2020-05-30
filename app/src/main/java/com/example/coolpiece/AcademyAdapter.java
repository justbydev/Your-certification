package com.example.coolpiece;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AcademyAdapter extends RecyclerView.Adapter {
    private ArrayList<String> academy_name=null;
    private ArrayList<String> academy_address=null;
    private ArrayList<String> academy_phone=null;
    static Context context;
    public AcademyAdapter(ArrayList<String> academy_name, ArrayList<String> academy_address, ArrayList<String> academy_phone){
        this.academy_name=academy_name;
        this.academy_address=academy_address;
        this.academy_phone=academy_phone;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        public TextView each_academy;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            each_academy=itemView.findViewById(R.id.each_academy);
            context=itemView.getContext();
        }
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.academy_itemlist, parent, false);
        MyViewHolder vh=new MyViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        MyViewHolder myViewHolder=(MyViewHolder)holder;
        myViewHolder.each_academy.setText(academy_name.get(position));
        myViewHolder.each_academy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, CertificateAcademy.class);
                intent.putExtra("academy_name", academy_name.get(position));
                intent.putExtra("academy_address", academy_address.get(position));
                intent.putExtra("academy_phone", academy_phone.get(position));
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return academy_name==null?0:academy_name.size();
    }
}
