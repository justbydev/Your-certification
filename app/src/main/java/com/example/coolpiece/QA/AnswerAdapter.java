package com.example.coolpiece.QA;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.coolpiece.R;

import java.util.ArrayList;

public class AnswerAdapter extends RecyclerView.Adapter {
    ArrayList<String> answer=null;
    public AnswerAdapter(ArrayList<String> answer){
        this.answer=answer;
    }
    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView answeritem;
        TextView answer_user;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            answeritem=(TextView)itemView.findViewById(R.id.answer_item);
            answer_user=itemView.findViewById(R.id.answer_user);
        }
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.answer_itemlist, parent, false);
        MyViewHolder vh=new MyViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        MyViewHolder myViewHolder=(MyViewHolder)holder;
        myViewHolder.answeritem.setText(answer.get(position));
        myViewHolder.answer_user.setText("익명"+position);
    }

    @Override
    public int getItemCount() {
        return answer==null?0:answer.size();
    }
}
