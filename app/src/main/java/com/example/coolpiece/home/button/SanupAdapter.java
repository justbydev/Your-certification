package com.example.coolpiece.home.button;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.coolpiece.R;
import com.example.coolpiece.home.button.certification.EachCertificate;
import com.example.coolpiece.splash.dataclass.Sanup;

import java.util.ArrayList;

public class SanupAdapter extends RecyclerView.Adapter {
    private ArrayList<Sanup> mData=null;
    String cat;
    static Context context;
    public SanupAdapter(ArrayList<Sanup> mData, String cat){
        this.mData=mData;
        this.cat=cat;
    }
    public static class MyViewHolder extends RecyclerView.ViewHolder{
        public TextView each_certificate;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            each_certificate=itemView.findViewById(R.id.each_certificate);
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
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        MyViewHolder myViewHolder=(MyViewHolder)holder;
        myViewHolder.each_certificate.setText(mData.get(position).getName());
        final int pos=position;
        myViewHolder.each_certificate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, EachCertificate.class);
                intent.putExtra("certification", mData.get(pos));
                intent.putExtra("cat", cat);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mData==null?0:mData.size();
    }
    public void removeall(ArrayList<Sanup> newData){
        if(mData!=null){
            mData=null;
        }
        mData=newData;
        notifyDataSetChanged();
    }
}
