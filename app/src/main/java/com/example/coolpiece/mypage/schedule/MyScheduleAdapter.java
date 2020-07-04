package com.example.coolpiece.mypage.schedule;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.coolpiece.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MyScheduleAdapter extends RecyclerView.Adapter {
    ArrayList<String> schedulelist;
    ArrayList<String> dday;
    ArrayList<String> wantdate;
    static Context context;

    public MyScheduleAdapter(ArrayList<String> schedulelist, ArrayList<String> dday, ArrayList<String> wantdate){
        this.schedulelist=schedulelist;
        this.dday=dday;
        this.wantdate=wantdate;
    }
    public static class MyViewHolder extends RecyclerView.ViewHolder{
        public TextView calendar_item;
        public TextView dday;
        public TextView wantdate;
        public CardView schedule_cardview;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            calendar_item=itemView.findViewById(R.id.calendar_item);
            dday=itemView.findViewById(R.id.dday);
            wantdate=itemView.findViewById(R.id.wantdate);
            schedule_cardview=itemView.findViewById(R.id.schedule_cardview);
            context=itemView.getContext();
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
        final MyViewHolder myViewHolder=(MyViewHolder)holder;
        myViewHolder.calendar_item.setText(schedulelist.get(position));
        myViewHolder.dday.setText(dday.get(position));
        myViewHolder.wantdate.setText(wantdate.get(position));
        final int pos=position;
        myViewHolder.schedule_cardview.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                AlertDialog.Builder sch=new AlertDialog.Builder(context);
                sch.setMessage("삭제하시겠습니까?");
                sch.setNegativeButton("취소",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                sch.setPositiveButton("확인",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                deleteItem(pos);
                            }
                        });
                sch.show();
                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        return schedulelist!=null?schedulelist.size():0;
    }

    public void deleteItem(int position){
        String tt=schedulelist.get(position);
        final String text=tt.replace(" ", "$");
        String dte=wantdate.get(position);
        final String date=dte.replace(".", "-");
        schedulelist.remove(position);
        dday.remove(position);
        wantdate.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, schedulelist.size());
        FirebaseUser firebaseUser= FirebaseAuth.getInstance().getCurrentUser();
        String me=firebaseUser.getEmail().toString();
        me=me.replace(".", "-");
        DatabaseReference databaseReference=FirebaseDatabase.getInstance().getReference("Schedule").child(me);
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot snapshot:dataSnapshot.getChildren()){
                    String json=snapshot.getValue().toString();
                    try {
                        JSONObject jsonObject=new JSONObject(json);
                        String t=jsonObject.getString("text");
                        if(t.equals(text)){
                            snapshot.getRef().removeValue();
                            break;
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        DatabaseReference d=FirebaseDatabase.getInstance().getReference("Calendar").child(me).child(date);
        d.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot snapshot:dataSnapshot.getChildren()){
                    String t=snapshot.getValue().toString();
                    if(t.equals(text)){
                        snapshot.getRef().removeValue();
                        break;
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
