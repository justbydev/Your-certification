package com.example.coolpiece;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class EachDateScheduleAdapter extends RecyclerView.Adapter {
    ArrayList<String> schedulelist;

    public EachDateScheduleAdapter(ArrayList<String> schedulelist){
        this.schedulelist=schedulelist;
    }
    public static class MyViewHolder extends RecyclerView.ViewHolder{
        public TextView eachitem;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            eachitem=itemView.findViewById(R.id.calendar_item);
        }
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.calendar_itemlist, parent, false);
        MyViewHolder vh=new MyViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        MyViewHolder myViewHolder=(MyViewHolder)holder;
        myViewHolder.eachitem.setText(schedulelist.get(position));
    }

    @Override
    public int getItemCount() {
        return schedulelist!=null?schedulelist.size():0;
    }

    public void setchange(ArrayList<String> mlist){
        if(schedulelist!=null){
            schedulelist=null;
        }
        schedulelist=mlist;
        notifyDataSetChanged();
    }
}
