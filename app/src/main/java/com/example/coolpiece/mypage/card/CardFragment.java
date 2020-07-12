package com.example.coolpiece.mypage.card;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.ClipData;
import android.content.ClipDescription;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.ParcelFileDescriptor;
import android.provider.MediaStore;
import android.view.DragEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
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
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class CardFragment extends Fragment{

    RelativeLayout image_layout;
    ImageView certi_view;
    Button store_button;
    public TextView card_explain;
    public TextView certi_list;
    public TextView size_list;
    public TextView background_list;
    public TextView add_text;
    ArrayList<String> mList;

    FirebaseUser firebaseUser;
    DatabaseReference databaseReference;
    private boolean first;
    static Context context;
    String mychoicecert=null;
    static final int REQUEST_IMAGE_CODE=1001;
    TextView mycert;
    int choose=0;

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.card, container, false);

        context=getContext();
        card_explain=(TextView)v.findViewById(R.id.card_explain);
        certi_view=(ImageView) v.findViewById(R.id.certi_view);
        image_layout=(RelativeLayout)v.findViewById(R.id.image_layout);
        certi_list=(TextView)v.findViewById(R.id.certi_list);
        size_list=(TextView)v.findViewById(R.id.size_list);
        background_list=(TextView)v.findViewById(R.id.background_list);
        add_text=(TextView)v.findViewById(R.id.add_text);
        store_button=v.findViewById(R.id.store_button);

        mycert=new TextView(context);


        firebaseUser=FirebaseAuth.getInstance().getCurrentUser();
        String temp=firebaseUser.getEmail().toString();
        String name=temp.replace('.', '-');
        mList=new ArrayList<>();
        databaseReference= FirebaseDatabase.getInstance().getReference("certificate").child(name);
        databaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                String json=dataSnapshot.getValue().toString();
                json=json.replaceAll(" ", "`");
                try {
                    JSONObject jsonObject=new JSONObject(json);
                    String certificate_name=jsonObject.optString("certificate_name", "nothing");
                    if(certificate_name.equals("nothing")){
                        certificate_name=jsonObject.getString("`certificate_name");
                        certificate_name=certificate_name.replace("`", " ");
                    }
                    mList.add(certificate_name);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        card_explain.setOnClickListener(buttononclicklistener);
        certi_list.setOnClickListener(buttononclicklistener);
        size_list.setOnClickListener(buttononclicklistener);
        background_list.setOnClickListener(buttononclicklistener);
        add_text.setOnClickListener(buttononclicklistener);
        store_button.setOnClickListener(buttononclicklistener);

        return v;
    }

    private View.OnClickListener buttononclicklistener=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            int id=v.getId();
            switch(id) {
                case R.id.card_explain:
                    AlertDialog.Builder explain=new AlertDialog.Builder(context);
                    explain.setTitle("명함 사용법");
                    explain.setMessage("1. 추가된 텍스트를 조금씩 움직일 수 있습니다(명함 범위 내에서)\n" +
                            "2. 추가된 텍스트를 클릭하면 추가 변경 사항이 나옵니다\n" +
                            "3. 자격증 이름은 위치가 상단으로 고정됩니다\n" +
                            "4. 자격증 이름을 길게 누르면 색깔을 변경할 수 있습니다\n" +
                            "5. 배경은 자신의 갤러리에서 사진을 가져옵니다");
                    explain.setPositiveButton("확인",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                    explain.show();
                    break;
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
                                                    if(choose==1){
                                                        image_layout.removeView(mycert);
                                                    }
                                                    mycert.setText("");
                                                    mycert.setText(mychoicecert);
                                                    mycert.setTextColor(Color.BLACK);
                                                    mycert.setTextSize(23);
                                                    mycert.setOnLongClickListener(new View.OnLongClickListener() {
                                                        @Override
                                                        public boolean onLongClick(View v) {
                                                            AlertDialog.Builder certcolor=new AlertDialog.Builder(context);
                                                            certcolor.setTitle("텍스트 색깔 변경");
                                                            final ArrayAdapter<String> ccl=new ArrayAdapter<>(
                                                                    context, android.R.layout.select_dialog_singlechoice);
                                                            ccl.add("검은색");
                                                            ccl.add("회색");
                                                            ccl.add("초록색");
                                                            ccl.add("빨간색");
                                                            ccl.add("노란색");
                                                            ccl.add("파란색");
                                                            certcolor.setNegativeButton("취소",
                                                                    new DialogInterface.OnClickListener() {
                                                                        @Override
                                                                        public void onClick(DialogInterface dialog, int which) {
                                                                            dialog.dismiss();
                                                                        }
                                                                    });
                                                            certcolor.setAdapter(ccl,
                                                                    new DialogInterface.OnClickListener() {
                                                                        @Override
                                                                        public void onClick(DialogInterface dialog, int which) {
                                                                            switch(which){
                                                                                case 0:
                                                                                    mycert.setTextColor(Color.BLACK);
                                                                                    break;
                                                                                case 1:
                                                                                    mycert.setTextColor(Color.GRAY);
                                                                                    break;
                                                                                case 2:
                                                                                    mycert.setTextColor(Color.GREEN);
                                                                                    break;
                                                                                case 3:
                                                                                    mycert.setTextColor(Color.RED);
                                                                                    break;
                                                                                case 4:
                                                                                    mycert.setTextColor(Color.YELLOW);
                                                                                    break;
                                                                                case 5:
                                                                                    mycert.setTextColor(Color.BLUE);
                                                                                    break;
                                                                            }
                                                                        }
                                                                    });
                                                            certcolor.show();
                                                            return true;
                                                        }
                                                    });
                                                    image_layout.addView(mycert);
                                                    choose=1;
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
                case R.id.size_list:
                    AlertDialog.Builder size=new AlertDialog.Builder(context);
                    size.setTitle("크기 결정");
                    size.setNegativeButton("취소",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                    ArrayAdapter<String> sizeadapter=new ArrayAdapter<>(context,
                            android.R.layout.select_dialog_singlechoice);
                    sizeadapter.add("가로로");
                    sizeadapter.add("세로로");
                    size.setAdapter(sizeadapter,
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    switch(which) {
                                        case 0:
                                            LinearLayout.LayoutParams p = new LinearLayout.LayoutParams(
                                                    LinearLayout.LayoutParams.MATCH_PARENT, 1000
                                            );
                                            p.setMargins(0, 400, 0, 0);
                                            image_layout.setLayoutParams(p);
                                            break;
                                        case 1:
                                            LinearLayout.LayoutParams p2 = new LinearLayout.LayoutParams(
                                                    LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT
                                            );
                                            p2.setMargins(180, 0, 180, 0);
                                            image_layout.setLayoutParams(p2);
                                            break;
                                    }
                                }
                            }
                    );
                    size.show();
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
                                                        changechoice.setTitle("글자/색 변경, 삭제");
                                                        final ArrayAdapter<String> ad=new ArrayAdapter<>(
                                                                context, android.R.layout.select_dialog_singlechoice);
                                                        ad.add("텍스트 변경");
                                                        ad.add("글자색 변경");
                                                        ad.add("텍스트 삭제");
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
                                                                                break;
                                                                            case 2:
                                                                                image_layout.removeView(tx);
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
                case R.id.store_button:
                    if(choose==0){
                        Toast.makeText(context, "목록에서 자격증 종류를 선택해주세요", Toast.LENGTH_SHORT).show();
                    }
                    else if(choose==1){
                        Bitmap bitmap=Bitmap.createBitmap(image_layout.getWidth(), image_layout.getHeight(), Bitmap.Config.ARGB_8888);
                        Canvas canvas=new Canvas(bitmap);
                        image_layout.draw(canvas);
                        if(Build.VERSION.SDK_INT>=23){//갤러리에 접근해서 생성한 이미지 저장을 위한 권한 접근, WRITE_EXTERNAL_STORAGE 사용을 위해
                            int permissionReadStorage= ContextCompat.checkSelfPermission(context, Manifest.permission.READ_EXTERNAL_STORAGE);
                            int permissionWriteStorage=ContextCompat.checkSelfPermission(context, Manifest.permission.WRITE_EXTERNAL_STORAGE);
                            if(permissionReadStorage== PackageManager.PERMISSION_DENIED||permissionWriteStorage==PackageManager.PERMISSION_DENIED){
                                requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_IMAGE_CODE);
                            }
                            else{//Scoped Storage 적용

                                if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.Q){
                                    SimpleDateFormat day = new SimpleDateFormat("yyyyMMddHHmmss");
                                    Date date = new Date();
                                    ContentValues values=new ContentValues();
                                    String relativelocation=Environment.DIRECTORY_PICTURES+File.separator+"Coolpiece";
                                    //values.put(MediaStore.Images.Media.RELATIVE_PATH, Environment.DIRECTORY_PICTURES);
                                    values.put(MediaStore.Images.Media.MIME_TYPE, "image/*");
                                    //values.put(MediaStore.Images.Media.DATE_ADDED, System.currentTimeMillis());
                                    values.put(MediaStore.Images.Media.DATE_TAKEN, System.currentTimeMillis());
                                    values.put(MediaStore.Images.Media.TITLE, day.format(date));
                                    values.put(MediaStore.Images.Media.IS_PENDING, 1);
                                    //values.put(MediaStore.MediaColumns.RELATIVE_PATH, relativelocation);
                                    ContentResolver contentResolver=context.getContentResolver();
                                    Uri item=contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
                                    try {
                                        ParcelFileDescriptor pdf=contentResolver.openFileDescriptor(item, "w", null);
                                        if(pdf!=null){
                                            ByteArrayOutputStream stream=new ByteArrayOutputStream();
                                            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
                                            byte[] byteArray=stream.toByteArray();
                                            FileOutputStream fos=new FileOutputStream(pdf.getFileDescriptor());
                                            fos.write(byteArray);
                                            fos.close();
                                            Toast.makeText(context, "명함이 갤러리에 저장되었습니다", Toast.LENGTH_SHORT).show();

                                        }
                                    } catch (FileNotFoundException e) {
                                        e.printStackTrace();
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                }
                                else{
                                    String path=Environment.getExternalStorageDirectory().getAbsolutePath()+"/DCIM/Coolpiece";
                                    File file=new File(path);
                                    if(!file.exists()){
                                        file.mkdirs();
                                    }
                                    SimpleDateFormat day = new SimpleDateFormat("yyyyMMddHHmmss");
                                    Date date = new Date();
                                    image_layout.buildDrawingCache();
                                    Bitmap captureview = image_layout.getDrawingCache();

                                    FileOutputStream fos = null;
                                    try{
                                        fos = new FileOutputStream(path+"/"+day.format(date)+".jpg");
                                        captureview.compress(Bitmap.CompressFormat.JPEG, 100, fos);
                                        context.sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.parse("file://" + path + "/" + day.format(date) + ".JPG")));
                                        Toast.makeText(context, "명함이 갤러리에 저장되었습니다", Toast.LENGTH_SHORT).show();
                                        fos.flush();
                                        fos.close();
                                        image_layout.destroyDrawingCache();
                                    } catch (FileNotFoundException e) {
                                        e.printStackTrace();
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }



                                }

                            }
                        }
                    }
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
                    Toast.makeText(context, "앨범 접근 권한이 설정되었습니다\n다시 눌러주세요", Toast.LENGTH_SHORT).show();
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
