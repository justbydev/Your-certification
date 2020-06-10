package com.example.coolpiece;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.coolpiece.home.button.certification.EachCertificate;
import com.example.coolpiece.home.location.GpsTracker;
import com.example.coolpiece.splash.manageclass.ManageGineongData;
import com.example.coolpiece.splash.manageclass.ManageGisulData;
import com.example.coolpiece.home.button.Bigcategory;
import com.example.coolpiece.home.AcademyGridAdapter;
import com.example.coolpiece.home.search.SearchAdapter;
import com.example.coolpiece.splash.dataclass.Gineongsa;
import com.example.coolpiece.splash.dataclass.Gisul;
import com.example.coolpiece.home.search.Search;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class HomeActivity extends Fragment {
    Button gineong;
    Button gisa;
    Button gisulsa;
    Button sanup;
    Button jido;
    Button guitar;
    TextView mylocation;
    TextView melocate;
    EditText searchedittext;
    RecyclerView search_recycler;
    Button searchbutton;
    RecyclerView grid_recyclerview;
    private Intent intent;
    public static HomeActivity homecontext;
    String arg1=null, arg2=null, arg3=null;
    String nowlocation=null;
    private GpsTracker gpsTracker;
    private double latitude;
    private double longitude;

    private SearchAdapter searchAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private List<Search> list;
    private ArrayList<Search> searcharray;

    private AcademyGridAdapter academyGridAdapter;
    private RecyclerView.LayoutManager layoutManager2;
    private ArrayList<String> academy_name;

    int locationFirstcheck;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.activity_main, container, false);

        homecontext=this;

        mylocation=(TextView) v.findViewById(R.id.mylocation);
        melocate=(TextView)v.findViewById(R.id.melocate);
        search_recycler=(RecyclerView)v.findViewById(R.id.search_recycler);
        searchbutton=(Button)v.findViewById(R.id.searchbutton);

        gineong=(Button)v.findViewById(R.id.gineong);
        gisa=(Button)v.findViewById(R.id.gisa);
        gisulsa=(Button)v.findViewById(R.id.gisulsa);
        sanup=(Button)v.findViewById(R.id.sanup);
        jido=(Button)v.findViewById(R.id.jido);
        guitar=(Button)v.findViewById(R.id.guitar);

        searchedittext=(EditText)v.findViewById(R.id.searchedittext);

        grid_recyclerview=(RecyclerView)v.findViewById(R.id.grid_recyclerview);

        //locate.setOnClickListener(buttonClickListener);
        melocate.setOnClickListener(buttonClickListener);
        searchbutton.setOnClickListener(buttonClickListener);

        gineong.setOnClickListener(buttonClickListener);
        gisa.setOnClickListener(buttonClickListener);
        gisulsa.setOnClickListener(buttonClickListener);
        sanup.setOnClickListener(buttonClickListener);
        jido.setOnClickListener(buttonClickListener);
        guitar.setOnClickListener(buttonClickListener);

        search_recycler.setHasFixedSize(true);
        layoutManager=new LinearLayoutManager(getContext());
        search_recycler.setLayoutManager(layoutManager);

        list=new ArrayList<>();

        settingList();

        searcharray=new ArrayList<>();
        searcharray.addAll(list);

        searchAdapter=new SearchAdapter(list);
        search_recycler.setAdapter(searchAdapter);

        list.clear();
        searchAdapter.notifyDataSetChanged();

        grid_recyclerview.setHasFixedSize(true);
        layoutManager2=new GridLayoutManager(getContext(), 3);
        grid_recyclerview.setLayoutManager(layoutManager2);

        academy_name=new ArrayList<>();

        /***example****/
        academy_name.add("학원1");
        academy_name.add("학원2");
        academy_name.add("학원3");
        academy_name.add("학원4");
        academy_name.add("학원5");
        academy_name.add("학원6");
        academy_name.add("학원7");
        academy_name.add("학원8");
        academy_name.add("학원9");
        academy_name.add("학원10");
        academy_name.add("학원11");
        academy_name.add("학원12");
        academy_name.add("학원13");
        academy_name.add("학원14");
        academy_name.add("학원15");
        academy_name.add("학원16");
        /***example***/
        academyGridAdapter=new AcademyGridAdapter(academy_name);
        grid_recyclerview.setAdapter(academyGridAdapter);


        searchedittext.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String text=searchedittext.getText().toString();
                search(text);
            }
        });

        return v;
    }
    /****butonclicklistener for big category like gisulsa, gineongsa, etc****/
    private View.OnClickListener buttonClickListener=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            int id=v.getId();
            switch(id){
                case R.id.melocate:
                    getlocationpermission();
                    return;
                case R.id.searchbutton:
                    String name=searchedittext.getText().toString();
                    int flag=0;
                    for(int i=0; i<searcharray.size(); i++){
                        if(name.equals(searcharray.get(i).getName())){
                            Intent intent=new Intent(getActivity(), EachCertificate.class);
                            int type=searcharray.get(i).getType();
                            if(type==0){
                                intent.putExtra("cat", "기술사");
                                ArrayList<ArrayList<Gisul>> gisul_total= ManageGisulData.getInstance().getGisul_total();
                                for(int j=0; j<gisul_total.size(); j++){
                                    ArrayList<Gisul> gisul=gisul_total.get(j);
                                    for(int k=0; k<gisul.size(); k++){
                                        if(name.equals(gisul.get(k).getName())){
                                            flag=1;
                                            intent.putExtra("certification", gisul.get(k));
                                            startActivity(intent);
                                        }
                                    }
                                }
                            }
                            else if(type==1){
                                intent.putExtra("cat", "기능사");
                                ArrayList<ArrayList<Gineongsa>> gineong_total= ManageGineongData.getInstance().getGineong_total();
                                for(int j=0; j<gineong_total.size(); j++){
                                    ArrayList<Gineongsa> gineongsa=gineong_total.get(j);
                                    for(int k=0; k<gineongsa.size(); k++){
                                        if(name.equals(gineongsa.get(k).getName())){
                                            flag=1;
                                            intent.putExtra("certification", gineongsa.get(k));
                                            startActivity(intent);
                                        }
                                    }
                                }
                            }
                        }
                    }
                    if(flag==0){
                        Toast.makeText(getContext(), "자격증이 검색되지 않습니다.", Toast.LENGTH_SHORT).show();
                    }
                    return;
                case R.id.gineong:
                    intent=new Intent(getActivity(), Bigcategory.class);
                    intent.putExtra("CA", "기능사");
                    startActivity(intent);
                    return;
                case R.id.gisa:
                    intent=new Intent(getActivity(), Bigcategory.class);
                    intent.putExtra("CA", "기사");
                    startActivity(intent);
                    return;
                case R.id.gisulsa:
                    intent=new Intent(getActivity(), Bigcategory.class);
                    intent.putExtra("CA", "기술사");
                    startActivity(intent);
                    return;
                case R.id.sanup:
                    intent=new Intent(getActivity(), Bigcategory.class);
                    intent.putExtra("CA", "산업기사");
                    startActivity(intent);
                    return;
                case R.id.jido:
                    intent=new Intent(getActivity(), Bigcategory.class);
                    intent.putExtra("CA", "지도사");
                    startActivity(intent);
                    return;
                case R.id.guitar:
                    intent=new Intent(getActivity(), Bigcategory.class);
                    intent.putExtra("CA", "기타/취미");
                    startActivity(intent);
                    return;
                default:
                    return;
            }
        }
    };

    /*public void closeApplication(){
        new AlertDialog.Builder(this)
                .setTitle("쿨피스")
                .setMessage("앱을 종료하시겠습니까?")
                .setNegativeButton("아니요", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ;
                    }})
                .setPositiveButton("네", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        moveTaskToBack(true);
                        finish();
                        android.os.Process.killProcess(android.os.Process.myPid());
                    }})

                .show();
    }*/

    /***filtering for search***/
    public void search(String text){
        list.clear();
        if(text.length()==0){
            list.clear();
        }
        else{
            for(int i=0; i<searcharray.size(); i++){
                if(searcharray.get(i).getName().toLowerCase().contains(text)){
                    list.add(searcharray.get(i));
                }
            }
        }
        searchAdapter.notifyDataSetChanged();
    }
    private void settingList(){
        list.add(new Search("가스기술사", 0));
        list.add(new Search("건설안전기술사", 0));
        list.add(new Search("가스기능사", 1));
        list.add(new Search("침투비파괴검사기능사", 1));
    }

    public void getlocationpermission(){
        if(Build.VERSION.SDK_INT<23){
            return;
        }
        if(!checkLocationServicesStatus()){//핸드폰 자체의 위치 설정 허가를 위한 것
            showDialogForLocationServiceSetting();
        }
        else{//핸드폰 자체의 위치 정보 사용이 허가되었으면 앱의 위치 권한 허가를 위한 것
            int permissioncoarse=ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION);
            int permissionfine=ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION);
            if(permissioncoarse==PackageManager.PERMISSION_DENIED||permissionfine==PackageManager.PERMISSION_DENIED){
                requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION,
                                Manifest.permission.ACCESS_COARSE_LOCATION},
                        1001);
            }
            else{
                nowlocation=getnowaddress();
                mylocation.setText(nowlocation);
            }
        }

    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch(requestCode){
            case 1001:
                int cnt=0;
                for(int i=0; i<permissions.length; i++){
                    String permission=permissions[i];
                    int grantResult=grantResults[i];
                    if(permission.equals(Manifest.permission.ACCESS_COARSE_LOCATION)){
                        if(grantResult==PackageManager.PERMISSION_GRANTED){
                            cnt+=1;
                        }
                    }
                    if (permission.equals(Manifest.permission.ACCESS_FINE_LOCATION)) {
                        if(grantResult==PackageManager.PERMISSION_GRANTED){
                            cnt+=1;
                        }
                    }
                }
                if(cnt==2){
                    Toast.makeText(getContext(), "위치 권한이 설정되었습니다", Toast.LENGTH_SHORT).show();
                }
                else{

                    android.app.AlertDialog.Builder builder=new AlertDialog.Builder(getContext());
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
                return;
            default:
                return;
        }

    }

    public String getnowaddress(){
        gpsTracker=new GpsTracker(getContext());
        latitude=gpsTracker.getLatitude();
        longitude=gpsTracker.getLongitude();

        Geocoder geocoder=new Geocoder(getContext(), Locale.KOREAN);
        List<Address> addresses;

        try{
            addresses=geocoder.getFromLocation(latitude, longitude, 1);
        }catch(IOException ioException){
            Toast.makeText(getContext(), "지오코더 서비스 사용불가", Toast.LENGTH_SHORT).show();

            return "지오코더 서비스 사용불가";
        }

        if(addresses==null||addresses.size()==0){

            Toast.makeText(getContext(), "주소를 발견하지 못했습니다", Toast.LENGTH_SHORT).show();

            return "내 위치를 다시 눌러주세요";
        }
        Address address=addresses.get(0);
        return address.getAddressLine(0).toString();

    }

    private void showDialogForLocationServiceSetting() {

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("위치 서비스 비활성화");
        builder.setMessage("앱을 사용하기 위해서는 위치 서비스가 필요합니다.\n"
                + "위치 설정을 수정하시겠습니까?");
        builder.setCancelable(true);
        builder.setPositiveButton("설정", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                Intent callGPSSettingIntent
                        = new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                startActivityForResult(callGPSSettingIntent, 2001);
            }
        });
        builder.setNegativeButton("취소", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();
            }
        });
        builder.create().show();
    }

    public boolean checkLocationServicesStatus() {
        LocationManager locationManager = (LocationManager) getContext().getSystemService(Context.LOCATION_SERVICE);

        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
                || locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
    }
}
