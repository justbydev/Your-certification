package com.example.coolpiece;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class GisulAdapter extends RecyclerView.Adapter {
    private ArrayList<Gisul> mData=null;
    static Context context;
    public GisulAdapter(ArrayList<Gisul> mData){
        this.mData=mData;
    }
    public static class MyViewHolder extends RecyclerView.ViewHolder{
        public TextView each_certificate;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            each_certificate=(TextView)itemView.findViewById(R.id.each_certificate);
            context=itemView.getContext();
        }
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.certificate_itemlist, parent, false);
        MyViewHolder vh=new MyViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        MyViewHolder myViewHolder=(MyViewHolder)holder;
        myViewHolder.each_certificate.setText(mData.get(position).getName());

    }

    @Override
    public int getItemCount() {
        return mData==null?0:mData.size();
    }

    public void removeall(ArrayList<Gisul> newData){
        if(mData!=null){
            mData=null;
        }
        mData=newData;
        notifyDataSetChanged();
    }
}
