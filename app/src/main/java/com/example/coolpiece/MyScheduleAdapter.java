package com.example.coolpiece;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyScheduleAdapter extends RecyclerView.Adapter {
    ArrayList<String> schedulelist;
    ArrayList<String> dday;
    ArrayList<String> wantdate;

    public MyScheduleAdapter(ArrayList<String> schedulelist, ArrayList<String> dday, ArrayList<String> wantdate){
        this.schedulelist=schedulelist;
        this.dday=dday;
        this.wantdate=wantdate;
    }
    public static class MyViewHolder extends RecyclerView.ViewHolder{
        public TextView calendar_item;
        public TextView dday;
        public TextView wantdate;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            calendar_item=itemView.findViewById(R.id.calendar_item);
            dday=itemView.findViewById(R.id.dday);
            wantdate=itemView.findViewById(R.id.wantdate);
        }
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.schedule_itemlist, parent, false);
        MyViewHolder vh=new MyViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        MyViewHolder myViewHolder=(MyViewHolder)holder;
        myViewHolder.calendar_item.setText(schedulelist.get(position));
        myViewHolder.dday.setText(dday.get(position));
        myViewHolder.wantdate.setText(wantdate.get(position));
    }

    @Override
    public int getItemCount() {
        return schedulelist!=null?schedulelist.size():0;
    }
}
