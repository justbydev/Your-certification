package com.example.coolpiece.mypage.card;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.ClipData;
import android.content.ClipDescription;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.DragEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.example.coolpiece.R;
import com.example.coolpiece.mypage.challenge.Challengeauthen;
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

public class CardFragment extends Fragment{

    RelativeLayout image_layout;
    ImageView certi_view;
    public TextView certi_list;
    public TextView font_list;
    public TextView background_list;
    public TextView add_text;
    ArrayList<String> mList;
    LayoutInflater layoutInflater;

    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;
    DatabaseReference databaseReference;
    public boolean flag=false;
    private boolean first;
    static Context context;
    String mychoicecert=null;
    static final int REQUEST_IMAGE_CODE=1001;

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.card, container, false);

        context=getContext();
        certi_view=(ImageView) v.findViewById(R.id.certi_view);
        image_layout=(RelativeLayout)v.findViewById(R.id.image_layout);
        certi_list=(TextView)v.findViewById(R.id.certi_list);
        font_list=(TextView)v.findViewById(R.id.font_list);
        background_list=(TextView)v.findViewById(R.id.background_list);
        add_text=(TextView)v.findViewById(R.id.add_text);


        firebaseUser=FirebaseAuth.getInstance().getCurrentUser();
        String temp=firebaseUser.getEmail().toString();
        String name=temp.replace('.', '-');
        mList=new ArrayList<>();
        databaseReference= FirebaseDatabase.getInstance().getReference("certificate/data").child(name);
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot snapshot:dataSnapshot.getChildren()){
                    String json=snapshot.getValue().toString();
                    try {
                        JSONObject jsonObject=new JSONObject(json);
                        String certificate_name=jsonObject.getString("certificate_name");
                        mList.add(certificate_name);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        certi_list.setOnClickListener(buttononclicklistener);
        font_list.setOnClickListener(buttononclicklistener);
        background_list.setOnClickListener(buttononclicklistener);
        add_text.setOnClickListener(buttononclicklistener);

        return v;
    }

    private View.OnClickListener buttononclicklistener=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            int id=v.getId();
            switch(id) {
                case R.id.certi_list:
                    AlertDialog.Builder builder=new AlertDialog.Builder(context);
                    builder.setTitle("자격증 목록");
                    final ArrayAdapter<String> adapter=new ArrayAdapter<>(
                            context, android.R.layout.select_dialog_singlechoice
                    );
                    for(int i=0; i<mList.size(); i++){
                        adapter.add(mList.get(i));
                    }

                    builder.setAdapter(adapter,
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    mychoicecert=adapter.getItem(which);
                                    final AlertDialog.Builder inBuilder=new AlertDialog.Builder(context);
                                    inBuilder.setMessage(mychoicecert+"입니다.");
                                    inBuilder.setTitle("당신이 선택한 것은 ");
                                    inBuilder.setPositiveButton("확인",
                                            new DialogInterface.OnClickListener() {
                                                @Override
                                                public void onClick(DialogInterface dialog, int which) {
                                                    dialog.dismiss();
                                                }
                                            });
                                    inBuilder.show();
                                }
                            });
                    builder.setNegativeButton("취소",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });

                    builder.show();
                    break;
                case R.id.font_list:
                    //do
                    break;
                case R.id.background_list:
                    if(Build.VERSION.SDK_INT>=23){
                        int permissionReadStorage= ContextCompat.checkSelfPermission(context, Manifest.permission.READ_EXTERNAL_STORAGE);
                        int permissionWriteStorage=ContextCompat.checkSelfPermission(context, Manifest.permission.WRITE_EXTERNAL_STORAGE);
                        if(permissionReadStorage== PackageManager.PERMISSION_DENIED||permissionWriteStorage==PackageManager.PERMISSION_DENIED){
                            requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_IMAGE_CODE);
                        }
                        else{
                            Intent imageintent=new Intent(Intent.ACTION_PICK);
                            imageintent.setType(MediaStore.Images.Media.CONTENT_TYPE);
                            imageintent.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                            startActivityForResult(imageintent, REQUEST_IMAGE_CODE);
                        }
                    }
                    break;
                case R.id.add_text:
                    AlertDialog.Builder a=new AlertDialog.Builder(context);
                    a.setTitle("새 텍스트 추가");
                    final EditText first=new EditText(context);
                    a.setView(first);
                    a.setPositiveButton("확인",
                            new DialogInterface.OnClickListener() {
                                @SuppressLint("ClickableViewAccessibility")
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    final TextView tx=new TextView(context);
                                    tx.setText(first.getText().toString());
                                    tx.setTextColor(Color.BLACK);
                                    tx.setTextSize(20);
                                    tx.setOnTouchListener(new View.OnTouchListener() {

                                        float x, y;
                                        float ax, ay;
                                        float bx, by;

                                        @Override
                                        public boolean onTouch(View v, MotionEvent event) {
                                            switch(event.getAction()){
                                                case MotionEvent.ACTION_DOWN:
                                                    x=v.getX()-event.getRawX();
                                                    y=v.getY()-event.getRawY();
                                                    bx=event.getRawX();
                                                    by=event.getRawY();
                                                    break;
                                                case MotionEvent.ACTION_UP:
                                                    ax=event.getRawX();
                                                    ay=event.getRawY();
                                                    System.out.println(ax+" "+ay+" "+bx+" "+by);
                                                    System.out.println("finger up");
                                                    if((bx-ax)>=-10&&(bx-ax)<=10&&(by-ay)>=-10&&(by-ay)<=10){
                                                        AlertDialog.Builder changechoice=new AlertDialog.Builder(context);
                                                        changechoice.setTitle("글자/색 변경");
                                                        final ArrayAdapter<String> ad=new ArrayAdapter<>(
                                                                context, android.R.layout.select_dialog_singlechoice);
                                                        ad.add("텍스트 변경");
                                                        ad.add("글자색 변경");
                                                        changechoice.setNegativeButton("취소",
                                                                new DialogInterface.OnClickListener() {
                                                                    @Override
                                                                    public void onClick(DialogInterface dialog, int which) {
                                                                        dialog.dismiss();
                                                                    }
                                                                });
                                                        changechoice.setAdapter(ad,
                                                                new DialogInterface.OnClickListener() {
                                                                    @Override
                                                                    public void onClick(DialogInterface dialog, int which) {
                                                                        switch(which){
                                                                            case 1:
                                                                                AlertDialog.Builder color=new AlertDialog.Builder(context);
                                                                                color.setTitle("텍스트 색깔 변경");
                                                                                final ArrayAdapter<String> cl=new ArrayAdapter<>(
                                                                                        context, android.R.layout.select_dialog_singlechoice);
                                                                                cl.add("검은색");
                                                                                cl.add("회색");
                                                                                cl.add("초록색");
                                                                                cl.add("빨간색");
                                                                                cl.add("노란색");
                                                                                cl.add("파란색");
                                                                                color.setNegativeButton("취소",
                                                                                        new DialogInterface.OnClickListener() {
                                                                                            @Override
                                                                                            public void onClick(DialogInterface dialog, int which) {
                                                                                                dialog.dismiss();
                                                                                            }
                                                                                        });
                                                                                color.setAdapter(cl,
                                                                                        new DialogInterface.OnClickListener() {
                                                                                            @Override
                                                                                            public void onClick(DialogInterface dialog, int which) {
                                                                                                switch(which){
                                                                                                    case 0:
                                                                                                        tx.setTextColor(Color.BLACK);
                                                                                                        break;
                                                                                                    case 1:
                                                                                                        tx.setTextColor(Color.GRAY);
                                                                                                        break;
                                                                                                    case 2:
                                                                                                        tx.setTextColor(Color.GREEN);
                                                                                                        break;
                                                                                                    case 3:
                                                                                                        tx.setTextColor(Color.RED);
                                                                                                        break;
                                                                                                    case 4:
                                                                                                        tx.setTextColor(Color.YELLOW);
                                                                                                        break;
                                                                                                    case 5:
                                                                                                        tx.setTextColor(Color.BLUE);
                                                                                                        break;
                                                                                                }
                                                                                            }
                                                                                        });
                                                                                color.show();
                                                                                break;
                                                                            case 0:
                                                                                AlertDialog.Builder newtext=new AlertDialog.Builder(context);
                                                                                newtext.setTitle("새로운 텍스트 입력");
                                                                                final EditText newinput=new EditText(context);
                                                                                newtext.setView(newinput);
                                                                                newtext.setPositiveButton("확인",
                                                                                        new DialogInterface.OnClickListener() {
                                                                                            @Override
                                                                                            public void onClick(DialogInterface dialog, int which) {
                                                                                                tx.setText(newinput.getText().toString());
                                                                                                dialog.dismiss();
                                                                                            }
                                                                                        });
                                                                                newtext.setNegativeButton("취소",
                                                                                        new DialogInterface.OnClickListener() {
                                                                                            @Override
                                                                                            public void onClick(DialogInterface dialog, int which) {
                                                                                                dialog.dismiss();
                                                                                            }
                                                                                        });
                                                                                newtext.show();
                                                                        }
                                                                    }
                                                                });
                                                        changechoice.show();
                                                    }
                                                case MotionEvent.ACTION_MOVE:
                                                    v.animate()
                                                            .x(event.getRawX()+x)
                                                            .y(event.getRawY()+y)
                                                            .setDuration(0)
                                                            .start();
                                                    break;
                                            }
                                            return true;
                                        }
                                    });

                                    image_layout.addView(tx);
                                    dialog.dismiss();
                                }
                            });
                    a.setNegativeButton("취소",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                    a.show();
                    break;
                default:break;
            }
        }


    };

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch(requestCode){
            case REQUEST_IMAGE_CODE:
                int cnt=0;
                for(int i=0; i<permissions.length; i++){
                    String permission=permissions[i];
                    int grantResult=grantResults[i];
                    if(permission.equals(Manifest.permission.READ_EXTERNAL_STORAGE)){
                        if(grantResult==PackageManager.PERMISSION_GRANTED){
                            cnt+=1;
                        }
                    }
                    if(permission.equals(Manifest.permission.WRITE_EXTERNAL_STORAGE)){
                        if(grantResult==PackageManager.PERMISSION_GRANTED){
                            cnt+=1;
                        }
                    }
                }
                if(cnt==2){
                    Toast.makeText(context, "앨범 접근 권한이 설정되었습니다\n다시 사진 추가를 눌러주세요", Toast.LENGTH_SHORT).show();
                }
                else{
                    AlertDialog.Builder builder=new AlertDialog.Builder(context);
                    builder.setTitle("알림");
                    builder.setMessage("[설정]->[권한]에서\n권한을 허용해주세요.\n");
                    builder.setNegativeButton("확인", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            return;
                        }
                    });
                    builder.create().show();
                }
                break;
            default:
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch(requestCode) {
            case REQUEST_IMAGE_CODE:
                if (resultCode == Activity.RESULT_OK) {
                    try {
                        Uri image = data.getData();
                        certi_view.setImageURI(image);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                break;
            default:
                break;
        }
    }
}
