package com.example.coolpiece;

import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.UiThread;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import com.naver.maps.geometry.LatLng;
import com.naver.maps.map.CameraPosition;
import com.naver.maps.map.MapFragment;
import com.naver.maps.map.MapView;
import com.naver.maps.map.NaverMap;
import com.naver.maps.map.OnMapReadyCallback;
import com.naver.maps.map.overlay.Marker;
import com.naver.maps.map.util.MarkerIcons;

import java.io.IOException;
import java.text.Collator;
import java.util.List;

public class CertificateAcademy extends AppCompatActivity implements OnMapReadyCallback {
    TextView academy_name;
    TextView academy_address;
    TextView academy_phone;
    Intent intent;
    String address;


    double lat;
    double lon;
    //private Collator NaverMapSdk;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.certificate_academy);


        academy_name=(TextView)findViewById(R.id.academy_name);
        academy_address=(TextView)findViewById(R.id.academy_address);
        academy_phone=(TextView)findViewById(R.id.academy_phone);


        intent=getIntent();
        address=intent.getStringExtra("academy_address");
        academy_name.setText(intent.getStringExtra("academy_name"));
        academy_address.setText(address);
        academy_phone.setText(intent.getStringExtra("academy_phone"));

        initMap();
    }
    @UiThread
    @Override
    public void onMapReady(@NonNull NaverMap naverMap) {
        Geocoder geocoder=new Geocoder(this);
        lat=35.179805;
        lon=129.074969;
        /*use Google's Geocoder to get latitude and longitude from Korean address*/
        try {
            List<Address> list=geocoder.getFromLocationName(address, 1);
            lat=list.get(0).getLatitude();        // 위도
            lon=list.get(0).getLongitude();    // 경도
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
    /***Initialize map fragment***/
    private void initMap(){
        FragmentManager fragmentManager=getSupportFragmentManager();
        MapFragment mapFragment=(MapFragment)fragmentManager.findFragmentById(R.id.map_view);
        if(mapFragment==null){
            mapFragment=MapFragment.newInstance();
            fragmentManager.beginTransaction().add(R.id.map_view, mapFragment).commit();
        }
        mapFragment.getMapAsync(this);
    }
}