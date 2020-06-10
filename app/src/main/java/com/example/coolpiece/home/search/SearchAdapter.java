package com.example.coolpiece.home.search;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.coolpiece.home.button.certification.EachCertificate;
import com.example.coolpiece.splash.dataclass.Gineongsa;
import com.example.coolpiece.splash.dataclass.Gisul;
import com.example.coolpiece.splash.manageclass.ManageGineongData;
import com.example.coolpiece.splash.manageclass.ManageGisulData;
import com.example.coolpiece.R;

import java.util.ArrayList;
import java.util.List;

public class SearchAdapter extends RecyclerView.Adapter {
    List<Search> list=null;
    static Context context;

    public SearchAdapter(List<Search> list){
        this.list=list;
    }
    public static class MyViewHolder extends RecyclerView.ViewHolder{
        public TextView each_certificate;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            each_certificate=(TextView)itemView.findViewById(R.id.each_certificate);
            context=itemView.getContext();
        }
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.searchlist, parent, false);
        MyViewHolder vh=new MyViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        MyViewHolder myViewHolder=(MyViewHolder)holder;
        myViewHolder.each_certificate.setText(list.get(position).getName());
        final int pos=position;
        myViewHolder.each_certificate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name=list.get(pos).getName().toString();
                int type=list.get(pos).getType();
                if(type==0){//Gisul
                    ArrayList<ArrayList<Gisul>> gisul_total= ManageGisulData.getInstance().getGisul_total();
                    for(int i=0; i<gisul_total.size(); i++){
                        ArrayList<Gisul> gisul=gisul_total.get(i);
                        for(int j=0; j<gisul.size(); j++){
                            if(name.equals(gisul.get(j).getName())){
                                Intent intent=new Intent(context, EachCertificate.class);
                                intent.putExtra("cat", "기술사");
                                intent.putExtra("certification", gisul.get(j));
                                context.startActivity(intent);
                            }
                        }
                    }
                }
                else if(type==1){//Gineongsa
                    ArrayList<ArrayList<Gineongsa>> gineong_total= ManageGineongData.getInstance().getGineong_total();
                    for(int i=0; i<gineong_total.size(); i++){
                        ArrayList<Gineongsa> gineong=gineong_total.get(i);
                        for(int j=0; j<gineong.size(); j++){
                            if(name.equals(gineong.get(j).getName())){
                                Intent intent=new Intent(context, EachCertificate.class);
                                intent.putExtra("cat", "기능사");
                                intent.putExtra("certification", gineong.get(j));
                                context.startActivity(intent);
                            }
                        }
                    }
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list==null?0:list.size();
    }
}
