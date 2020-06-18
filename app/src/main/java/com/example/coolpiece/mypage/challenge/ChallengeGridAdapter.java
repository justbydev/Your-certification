package com.example.coolpiece.mypage.challenge;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.coolpiece.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class ChallengeGridAdapter extends RecyclerView.Adapter {
    ArrayList<String> day_check=null;
    String today;
    Date start;
    public ChallengeGridAdapter(ArrayList<String> day_check, String start) throws ParseException {
        this.day_check=day_check;
        SimpleDateFormat fm=new SimpleDateFormat("yyyy-MM-dd");
        this.start=fm.parse(start);
        SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
        Date time=new Date();
        today=format.format(time);
    }
    public static class MyViewHolder extends RecyclerView.ViewHolder{
        ImageView challenge_image;
        TextView challenge_day;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            challenge_image=(ImageView)itemView.findViewById(R.id.challenge_image);
            challenge_day=(TextView)itemView.findViewById(R.id.challenge_day);
        }
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.challenge_grid_item, parent, false);
        MyViewHolder vh=new MyViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        MyViewHolder myViewHolder=(MyViewHolder)holder;
        myViewHolder.challenge_day.setText(Integer.toString(position+1));
        Calendar cal=Calendar.getInstance();
        cal.setTime(start);
        cal.add(Calendar.DATE, position);
        SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd");
        if(today.equals(df.format(cal.getTime()).toString())){
            if(day_check.get(position).equals("yet")){
                myViewHolder.challenge_image.setImageResource(R.drawable.today);
            }
            else if(day_check.get(position).equals("ok")){
                myViewHolder.challenge_image.setImageResource(R.drawable.ok);
            }
        }
        else{
            if(day_check.get(position).equals("not")){
                myViewHolder.challenge_image.setImageResource(R.drawable.not);
            }
            else if(day_check.get(position).equals("ok")){
                myViewHolder.challenge_image.setImageResource(R.drawable.ok);
            }
            else if(day_check.get(position).equals("yet")){
                myViewHolder.challenge_image.setImageResource(R.drawable.yet);
            }
        }

    }

    @Override
    public int getItemCount() {
        return day_check==null?0:day_check.size();
    }
}
