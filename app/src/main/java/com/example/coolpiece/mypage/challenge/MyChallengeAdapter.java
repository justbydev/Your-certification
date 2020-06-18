package com.example.coolpiece.mypage.challenge;

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

public class MyChallengeAdapter extends RecyclerView.Adapter {
    ArrayList<Challenge> list=null;
    ArrayList<String> title=null;
    static Context context;
    public MyChallengeAdapter(ArrayList<Challenge> list, ArrayList<String> title){
        this.list=list;
        this.title=title;
    }
    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView challenge_item;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            challenge_item=itemView.findViewById(R.id.challenge_item);
            context=itemView.getContext();
        }
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.challenge_itemlist, parent, false);
        MyViewHolder vh=new MyViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        MyViewHolder myViewHolder=(MyViewHolder)holder;
        String t=list.get(position).getCertification()+" 출석률 "+list.get(position).getAttend()+"%";
        System.out.println("onBindViewHolder");
        System.out.println(t);
        myViewHolder.challenge_item.setText(t);
        final int pos=position;
        myViewHolder.challenge_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, Challengedetail.class);
                intent.putExtra("certification", list.get(pos).getCertification());
                intent.putExtra("day", list.get(pos).getDay());
                intent.putExtra("point", list.get(pos).getPoint());
                intent.putExtra("attend", list.get(pos).getAttend());
                intent.putExtra("startdate", list.get(pos).getStartdate());
                intent.putStringArrayListExtra("day_check", list.get(pos).getDay_check());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list==null?0:list.size();
    }

    public void updatearray(String certification, String attend, Challenge challenge){
        System.out.println("here is Updatearray");
        System.out.println(title.size());
        System.out.println("==============================");
        for(int i=0; i<title.size(); i++){
            System.out.println(title.get(i));
            System.out.println("++++++++++++++++++++++++++++++++++");
            if(title.get(i).equals(certification+" 출석률 "+attend+"%")){
                list.set(i, challenge);
                notifyDataSetChanged();
                return;
            }
        }
    }
}
