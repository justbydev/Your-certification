package com.example.coolpiece.mypage.card;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.coolpiece.R;

import java.util.ArrayList;

public class CardAdapter extends RecyclerView.Adapter {
    ArrayList<String> certificatelist;
    public CardAdapter(ArrayList<String> cardlist){
        this.certificatelist = cardlist;
    };


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.certificate_list,parent,false);
        customViewHolder holder = new customViewHolder(view);

        return holder;

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        customViewHolder cvh=(customViewHolder)holder;
        String t=certificatelist.get(position);
        cvh.certificate_name.setText(t);


    }



    @Override
    public int getItemCount() {
        return 0;
    }
    public static class customViewHolder extends RecyclerView.ViewHolder {
        protected TextView certificate_name;
        public customViewHolder(@NonNull View itemView) {
            super(itemView);
            this.certificate_name = (TextView) itemView.findViewById(R.id.certificate_name);
        }
    }
}
