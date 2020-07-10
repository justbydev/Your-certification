package com.example.coolpiece.mycert;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.coolpiece.R;

import java.util.ArrayList;

public class MyCertAdapter extends RecyclerView.Adapter {
    ArrayList<MyCert_Info> mList;
    static Context context;

    public MyCertAdapter(ArrayList<MyCert_Info> mList){
        this.mList=mList;
    }
    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView mycert_item;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            mycert_item=itemView.findViewById(R.id.mycert_item);
            context=itemView.getContext();
        }
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.mycert_itemlist, parent, false);
        MyViewHolder vh=new MyViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        MyViewHolder myViewHolder=(MyViewHolder)holder;
        myViewHolder.mycert_item.setText(mList.get(position).getCertificate_name());
        final int pos=position;
        myViewHolder.mycert_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, EachmyCert.class);
                intent.putExtra("cert_name", mList.get(pos).getCertificate_name());
                intent.putExtra("birthday", mList.get(pos).getBirthday());
                intent.putExtra("cert_date", mList.get(pos).getCertificate_date());
                intent.putExtra("cert_institution", mList.get(pos).getCertificate_institution());
                intent.putExtra("cert_serial_num", mList.get(pos).getCertificate_serial_num());
                intent.putExtra("img_name", mList.get(pos).getImg_name());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList==null?0:mList.size();
    }
}
