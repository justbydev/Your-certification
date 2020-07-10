package com.example.coolpiece.splash.manageclass;

import com.example.coolpiece.splash.dataclass.Academy;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ManageAcademyData {
    private static ManageAcademyData manageAcademyData;
    Academy academy;
    private ArrayList<Academy> academy_total;
    public static ManageAcademyData getInstance(){
        if(manageAcademyData==null){
            manageAcademyData=new ManageAcademyData();
        }
        return manageAcademyData;
    }
    ManageAcademyData(){
        academy=new Academy();
        academy_total=new ArrayList<>();
    }
    public ArrayList<Academy> getAcademy_total(){
        return academy_total;
    }
    public void startParsing(String json) throws JSONException {
        JSONArray jsonArray=new JSONArray(json);
        for(int i=0; i<jsonArray.length(); i++){
            JSONObject jsonObject=jsonArray.getJSONObject(i);
            academy.setAcademy_name(jsonObject.getString("학원이름"));
            System.out.println(jsonObject.getString("학원이름"));
            academy.setAcademy_address(jsonObject.getString("학원주소"));
            System.out.println(jsonObject.getString("학원주소"));
            academy.setAcademy_post(jsonObject.getInt("우편번호"));
            System.out.println(jsonObject.getInt("우편번호"));
            academy.setAcademy_number(jsonObject.getString("전화번호"));
            System.out.println(jsonObject.getString("전화번호"));
            academy.setAcademy_cert(jsonObject.getString("자격증"));
            System.out.println(jsonObject.getString("자격증"));
            academy.setConnect(jsonObject.getInt("제휴여부"));
            academy_total.add(academy);
            academy=new Academy();
        }
    }
}
