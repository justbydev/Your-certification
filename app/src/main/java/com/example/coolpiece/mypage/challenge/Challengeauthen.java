package com.example.coolpiece.mypage.challenge;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.coolpiece.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class Challengeauthen extends AppCompatActivity {
    Button final_auth_button;
    ImageView certi_image;
    TextView picture_text;
    RadioButton board_upload_access;
    String certification;
    String day;
    String point;
    String attend;
    String startdate;
    ArrayList<String> day_check;
    int border;
    String title;
    DatabaseReference databaseReference;
    Challenge challenge;
    static final int REQUEST_IMAGE_CODE=1002;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.challenge_authen);

        final_auth_button=(Button)findViewById(R.id.final_auth_button);
        certi_image=(ImageView)findViewById(R.id.certi_image);
        picture_text=(TextView)findViewById(R.id.picture_text);
        board_upload_access=(RadioButton)findViewById(R.id.board_upload_access);

        certi_image.setOnClickListener(buttononclicklistener);
        picture_text.setOnClickListener(buttononclicklistener);

        Intent intent=getIntent();
        certification=intent.getStringExtra("certification");
        day=intent.getStringExtra("day");
        point=intent.getStringExtra("point");
        attend=intent.getStringExtra("attend");
        startdate=intent.getStringExtra("startdate");
        day_check=new ArrayList<>();
        day_check=intent.getStringArrayListExtra("day_check");
        try {
            border=getDay(startdate, day_check.size());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        final_auth_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                day_check.set(border, "ok");
                certification=certification.replace(" ", "-");
                title=certification+attend+day+point;
                databaseReference= FirebaseDatabase.getInstance().getReference("Challenge");
                String email= FirebaseAuth.getInstance().getCurrentUser().getEmail().toString();
                email=email.replace(".", "-");
                challenge=new Challenge();
                challenge.setCertification(certification);
                challenge.setDay(day);
                challenge.setStartdate(startdate);
                challenge.setPoint(point);
                challenge.setStartdate(startdate);
                challenge.setAttend(attend);
                challenge.setDay_check(day_check);
                databaseReference.child(email).child(title).setValue(challenge);
                Intent giveintent=new Intent(Challengeauthen.this, Challengedetail.class);
                giveintent.putExtra("certification", certification);
                giveintent.putExtra("day", day);
                giveintent.putExtra("startdate", startdate);
                giveintent.putExtra("attend", attend);
                giveintent.putExtra("point", point);
                giveintent.putStringArrayListExtra("day_check", day_check);
                startActivity(giveintent);
                finish();
            }
        });
    }

    public int getDay(String startdate, int about) throws ParseException {
        Date start;
        String today;
        int i=0;
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
        start=simpleDateFormat.parse(startdate);
        Calendar cal=Calendar.getInstance();
        SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
        Date time=new Date();
        today=format.format(time);
        for(i=0; i<about; i++){
            cal.setTime(start);
            cal.add(Calendar.DATE, i);
            if(today.equals(simpleDateFormat.format(cal.getTime()).toString())){
                break;
            }
        }
        return i;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent giveintent=new Intent(Challengeauthen.this, Challengedetail.class);
        giveintent.putExtra("certification", certification);
        giveintent.putExtra("day", day);
        giveintent.putExtra("startdate", startdate);
        giveintent.putExtra("attend", attend);
        giveintent.putExtra("point", point);
        giveintent.putStringArrayListExtra("day_check", day_check);
        startActivity(giveintent);
        finish();
    }

    public View.OnClickListener buttononclicklistener=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            int id=v.getId();
            switch(id){
                case R.id.certi_image:
                case R.id.picture_text:
                    if(Build.VERSION.SDK_INT>=23){
                        int permissionReadStorage= ContextCompat.checkSelfPermission(Challengeauthen.this, Manifest.permission.READ_EXTERNAL_STORAGE);
                        int permissionWriteStorage=ContextCompat.checkSelfPermission(Challengeauthen.this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
                        if(permissionReadStorage== PackageManager.PERMISSION_DENIED || permissionWriteStorage==PackageManager.PERMISSION_DENIED){
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
                default:
                    break;
            }
        }
    };

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
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
            Toast.makeText(Challengeauthen.this, "앨범 접근 권한이 설정되었습니다\n다시 사진 추가를 눌러주세요", Toast.LENGTH_SHORT).show();
        }
        else{
            android.app.AlertDialog.Builder builder=new AlertDialog.Builder(Challengeauthen.this);
            builder.setTitle("알림");
            builder.setMessage("[설정]->[권한]에서\n권한을 허용해주세요.\n");
            builder.setNegativeButton("확인", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });
            AlertDialog alertDialog=builder.create();
            alertDialog.show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==REQUEST_IMAGE_CODE){
            if(resultCode==RESULT_OK&&data!=null&&data.getData()!=null){
                Uri image=data.getData();
                certi_image.setBackgroundColor(getResources().getColor(R.color.design_default_color_background));
                certi_image.setImageURI(image);
                picture_text.setText("");
                /*try {
                    Bitmap bitmap= MediaStore.Images.Media.getBitmap(getContentResolver(), image);
                    certi_image.setBackgroundColor(getResources().getColor(R.color.design_default_color_background));
                    certi_image.setImageBitmap(bitmap);

                } catch (IOException e) {
                    e.printStackTrace();
                }*/
            }
        }
    }
}