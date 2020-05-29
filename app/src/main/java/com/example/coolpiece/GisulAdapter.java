package com.example.coolpiece;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class GisulAdapter extends RecyclerView.Adapter {
    private ArrayList<Gisul> mData=null;

    public GisulAdapter(ArrayList<Gisul> mData){
        this.mData=mData;
    }
    public static class MyViewHolder extends RecyclerView.ViewHolder{
        public TextView each_certicate;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            each_certicate=itemView.findViewById(R.id.each_certificate);
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
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        MyViewHolder myViewHolder=(MyViewHolder)holder;
        myViewHolder.each_certicate.setText(mData.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return mData==null?0:mData.size();
    }
}
