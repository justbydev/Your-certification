package com.example.coolpiece.mypage.authen;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.loader.content.CursorLoader;

import com.example.coolpiece.R;
import com.example.coolpiece.mypage.challenge.Challengeauthen;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.Console;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class AuthenFragment extends Fragment {
    @Nullable
    EditText certificate_name;
    EditText certificate_serial_num;
    EditText birthday;
    EditText certificate_date;
    EditText certificate_institution;
    ImageView cert_image;
    TextView picture_text;

    int pic_check=0;
    static final int REQUEST_IMAGE_CODE=1003;
    Uri img;
    String me;
    Context context;
    String Uripath;
    ProgressDialog dialog;

    FirebaseUser firebaseAuth;
    final FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference ref = database.getReference("certificate");

    Button buttonCertificate;

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.certificate_register, container, false);
        certificate_name = (EditText)v.findViewById(R.id.certificate_name);
        certificate_serial_num = (EditText)v.findViewById(R.id.certificate_serial_num);
        birthday = (EditText)v.findViewById(R.id.birthday);
        certificate_date = (EditText)v.findViewById(R.id.certificate_date);
        certificate_institution = (EditText)v.findViewById(R.id.certificate_institution);
        cert_image=(ImageView)v.findViewById(R.id.cert_reg_image);
        picture_text=(TextView)v.findViewById(R.id.picture_reg_text);

        me=FirebaseAuth.getInstance().getCurrentUser().getEmail().toString();
        context=getContext();

        buttonCertificate = (Button)v.findViewById(R.id.buttonCertificate);
        buttonCertificate.setOnClickListener(buttononclicklistener);
        cert_image.setOnClickListener(buttononclicklistener);
        picture_text.setOnClickListener(buttononclicklistener);
        return v;

    }
    private View.OnClickListener buttononclicklistener=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            int id = v.getId();
            switch (id) {
                case R.id.buttonCertificate:
                    firebaseAuth = FirebaseAuth.getInstance().getCurrentUser();

                    String temp = firebaseAuth.getEmail().toString();
                    final String email = temp.replace('.', '-');
                    String nametmp = certificate_name.getText().toString();
                    final String name=nametmp.replace(" ", "`");
                    String serial_numtmp = certificate_serial_num.getText().toString();
                    final String serial_num=serial_numtmp.replace(" ", "`");
                    String birthtmp = birthday.getText().toString();
                    final String birth=birthtmp.replace(" ", "`");
                    String datetmp = certificate_date.getText().toString();
                    final String date=datetmp.replace(" ", "`");
                    String institutiontmp = certificate_institution.getText().toString();
                    final String institution=institutiontmp.replace(" ", "`");
                    if (name == null || email.equals("") || name.equals("") || serial_num.equals("") || birth.equals("") || date.equals("") || institution.equals("")) {
                        Toast t = Toast.makeText(getContext(), "빈 항목이 있습니다.", Toast.LENGTH_SHORT);
                        t.show();
                    } else {
                        if (pic_check == 0) {
                            Toast.makeText(context, "자격증 인증 사진을 올려주세요", Toast.LENGTH_SHORT).show();
                        } else {
                            showProgressDialog();
                            me=me.replace(".", "-");
                            StorageReference storageReference= FirebaseStorage.getInstance().getReference();
                            final Uri file=Uri.fromFile(new File(Uripath));
                            String big="Cert of "+me+"/";
                            StorageReference fine=storageReference.child(big+file.getLastPathSegment());
                            UploadTask uploadTask=fine.putFile(file);
                            uploadTask.addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(context, "인증 업로드 실패", Toast.LENGTH_SHORT).show();
                                    dialog.dismiss();
                                    return;
                                }
                            }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                                @Override
                                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                    Authentificate_user new_user = new Authentificate_user(name, serial_num, birth, date, institution, file.getLastPathSegment());
                                    ref.child(email).push().setValue(new_user);
                                    Toast t = Toast.makeText(getContext(), "인증 업로드 완료", Toast.LENGTH_SHORT);
                                    t.show();
                                    certificate_name.setText("");
                                    certificate_serial_num.setText("");
                                    birthday.setText("");
                                    certificate_date.setText("");
                                    certificate_institution.setText("");
                                    cert_image.setImageURI(null);
                                    cert_image.setImageResource(0);
                                    cert_image.setBackgroundColor(Color.parseColor("#DBDBDB"));
                                    picture_text.setText("자격증 사진을 첨부해주세요");
                                    dialog.dismiss();
                                }
                            });
                        }

                    }
                    break;
                case R.id.cert_reg_image:
                case R.id.picture_reg_text:
                    if (Build.VERSION.SDK_INT >= 23) {
                        int permissionReadStorage = ContextCompat.checkSelfPermission(context, Manifest.permission.READ_EXTERNAL_STORAGE);
                        int permissionWriteStorage = ContextCompat.checkSelfPermission(context, Manifest.permission.WRITE_EXTERNAL_STORAGE);
                        if (permissionReadStorage == PackageManager.PERMISSION_DENIED || permissionWriteStorage == PackageManager.PERMISSION_DENIED) {
                            requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_IMAGE_CODE);
                        } else {
                            Intent imageintent = new Intent(Intent.ACTION_PICK);
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
            Toast.makeText(context, "앨범 접근 권한이 설정되었습니다\n다시 사진 추가를 눌러주세요", Toast.LENGTH_SHORT).show();
        }
        else{
            android.app.AlertDialog.Builder builder=new AlertDialog.Builder(context);
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
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==REQUEST_IMAGE_CODE){
            if(resultCode== Activity.RESULT_OK&&data!=null&&data.getData()!=null){
                img=data.getData();
                Uripath=getPath(img);
                System.out.println(Uripath);
                System.out.println("This is absolute image URi=====================");

                cert_image.setBackgroundColor(getResources().getColor(R.color.design_default_color_background));
                cert_image.setImageURI(img);
                picture_text.setText("");
                pic_check=1;

            }
        }
    }

    // uri 절대경로 가져오기
    public String getPath(Uri uri){

        String [] proj = {MediaStore.Images.Media.DATA};
        CursorLoader cursorLoader = new CursorLoader(context,uri,proj,null,null,null);

        Cursor cursor = cursorLoader.loadInBackground();
        int index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);

        cursor.moveToFirst();

        return cursor.getString(index);

    }
    public void showProgressDialog(){
        dialog=new ProgressDialog(context);
        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        dialog.setMessage("인증정보 등록 중...");
        dialog.show();
    }
}

