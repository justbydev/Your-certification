package com.example.coolpiece.QA;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.coolpiece.R;
import com.example.coolpiece.home.button.GisaAdapter;

import java.util.ArrayList;

public class QAlistAdapter extends RecyclerView.Adapter {
    ArrayList<QA> list=null;
    static Context context;
    public QAlistAdapter(ArrayList<QA> list){
        this.list=list;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView question_item;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            question_item=itemView.findViewById(R.id.question_item);
            context=itemView.getContext();
        }
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.qa_itemlist, parent, false);
        MyViewHolder vh=new MyViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        MyViewHolder myViewHolder=(MyViewHolder)holder;
        String text=" 제목: "+list.get(position).getTitle();
        if(text.length()>23){
            text=text.substring(0, 21);
            text=text+"...";
        }
        myViewHolder.question_item.setText(text);
        final int pos=position;
        myViewHolder.question_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, QAwithanswer.class);
                intent.putExtra("title", list.get(pos).getTitle());
                intent.putExtra("content", list.get(pos).getContent());
                intent.putExtra("email", list.get(pos).getEmail());
                intent.putExtra("number", list.get(pos).getNumber());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list==null?0:list.size();
    }
}
