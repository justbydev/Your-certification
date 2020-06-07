package com.example.coolpiece;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class NotConnectAcademyAdapter extends RecyclerView.Adapter {
    private ArrayList<String> academy_name=null;
    private ArrayList<String> academy_address=null;
    private ArrayList<String> academy_phone=null;

    private static Context context;
    public NotConnectAcademyAdapter(ArrayList<String> academy_name, ArrayList<String> academy_address, ArrayList<String> academy_phone){
        this.academy_name=academy_name;
        this.academy_address=academy_address;
        this.academy_phone=academy_phone;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        public TextView each_academy;
        public ImageView academy_arrow;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            each_academy=itemView.findViewById(R.id.each_academy);
            academy_arrow=itemView.findViewById(R.id.academy_arrow);
            context=EachCertificate.context;
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
                intent.putExtra("academy_address", academy_address.get(pos));
                intent.putExtra("academy_phone", academy_phone.get(pos));

                v.getContext().startActivity(intent);
            }
        });
        myViewHolder.academy_arrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(v.getContext(), NotConnectCertificateAcademy.class);
                intent.putExtra("academy_name", academy_name.get(pos));
                intent.putExtra("academy_address", academy_address.get(pos));
                intent.putExtra("academy_phone", academy_phone.get(pos));

                v.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return academy_name==null?0:academy_name.size();
    }
}
