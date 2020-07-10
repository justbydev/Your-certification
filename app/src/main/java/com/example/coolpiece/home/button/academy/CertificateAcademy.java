package com.example.coolpiece.home.button.academy;

import android.content.Intent;

import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.UiThread;
import androidx.appcompat.app.AppCompatActivity;


import com.example.coolpiece.R;
import com.example.coolpiece.splash.dataclass.Academy;
import com.example.coolpiece.splash.manageclass.ManageAcademyData;
import com.naver.maps.geometry.LatLng;
import com.naver.maps.map.CameraPosition;

import com.naver.maps.map.MapView;
import com.naver.maps.map.NaverMap;
import com.naver.maps.map.OnMapReadyCallback;
import com.naver.maps.map.overlay.Marker;
import com.naver.maps.map.util.MarkerIcons;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class CertificateAcademy extends AppCompatActivity implements OnMapReadyCallback {
    TextView back_button;
    TextView academy_name;
    TextView academy_address;
    TextView academy_phone;
    Intent intent;
    String name=null;
    String address=null;
    String phone=null;
    MapView mapView;


    double lat;
    double lon;
    String x="";
    String y="";
    //private Collator NaverMapSdk;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.certificate_academy);

        back_button=(TextView)findViewById(R.id.back_button);

        academy_name=(TextView)findViewById(R.id.academy_name);
        academy_address=(TextView)findViewById(R.id.academy_address);
        academy_phone=(TextView)findViewById(R.id.academy_phone);
        mapView=(MapView)findViewById(R.id.map_view);

        back_button.setOnClickListener(buttononclicklistener);

        intent=getIntent();
        name=intent.getStringExtra("academy_name");
        academy_name.setText(name);
        ArrayList<Academy> total=new ArrayList<>();
        total= ManageAcademyData.getInstance().getAcademy_total();
        for(int i=0; i<total.size(); i++){
            if(name.equals(total.get(i).getAcademy_name())){
                address=total.get(i).getAcademy_address();
                phone=total.get(i).getAcademy_number();
                break;
            }
        }
        academy_address.setText(address);
        academy_phone.setText(phone);

        //new markmap().execute(address);

        mapView.getMapAsync(this);

    }


    @UiThread
    @Override
    public void onMapReady(@NonNull NaverMap naverMap) {


        //lat=Double.parseDouble(x);
        //lon=Double.parseDouble(y);
        Geocoder geocoder=new Geocoder(CertificateAcademy.this, Locale.KOREAN);
        try {
            List<Address> list=null;
            while(true){
                list=geocoder.getFromLocationName(address, 1);
                if(list!=null){
                    break;
                }
            }

            lat=list.get(0).getLatitude();
            lon=list.get(0).getLongitude();
        } catch (IOException e) {
            e.printStackTrace();
        }
        /*LatLng, CameraPosition, Marker all in naver.maps.sdk*/
        LatLng location=new LatLng(lat, lon);
        /*bigger zoom, just like camera*/
        /*move camera to my lat and lon*/
        CameraPosition cameraPosition=new CameraPosition(location, 17);

        /*대중교통 정보도 네이버 지도에 함께 띄우기*/
        naverMap.setLayerGroupEnabled(NaverMap.LAYER_GROUP_TRANSIT, true);
        /*건물 현상, 주소 심벌 등도 네이버 지도에 함께 띄우기*/
        naverMap.setLayerGroupEnabled(NaverMap.LAYER_GROUP_BUILDING, true);
        /*move Map to Camera Position*/
        naverMap.setCameraPosition(cameraPosition);

        Marker marker=new Marker();
        marker.setPosition(new LatLng(lat, lon));
        marker.setIcon(MarkerIcons.GREEN);
        //marker.setIconTintColor(Color.rgb(231, 138, 138));
        marker.setWidth(Marker.SIZE_AUTO);
        marker.setHeight(Marker.SIZE_AUTO);
        marker.setMap(naverMap);


    }
    private View.OnClickListener buttononclicklistener=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            int id=v.getId();
            switch(id){
                case R.id.back_button:
                    finish();
                    return;
                default:
                    return;
            }
        }
    };

    @Override
    public void onBackPressed() {
        finish();
    }
}
