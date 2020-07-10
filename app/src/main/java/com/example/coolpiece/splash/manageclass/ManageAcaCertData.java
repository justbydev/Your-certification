package com.example.coolpiece.splash.manageclass;

import com.example.coolpiece.splash.dataclass.AcaCerti;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ManageAcaCertData {
    private static ManageAcaCertData manageAcaCertData;
    AcaCerti acaCerti;
    private ArrayList<AcaCerti> acacerti_total;
    public static ManageAcaCertData getInstance(){
        if(manageAcaCertData==null){
            manageAcaCertData=new ManageAcaCertData();
        }
        return manageAcaCertData;
    }
    ManageAcaCertData(){
        acaCerti=new AcaCerti();
        acacerti_total=new ArrayList<>();
    }
    public ArrayList<AcaCerti> getAcacerti_total(){
        return acacerti_total;
    }
    public void startParsing(String json) throws JSONException {
        JSONArray jsonArray=new JSONArray(json);
        for(int i=0; i<jsonArray.length(); i++){
            JSONObject jsonObject=jsonArray.getJSONObject(i);
            acaCerti.setCert(jsonObject.getString("자격증"));
            System.out.println(jsonObject.getString("자격증"));
            acaCerti.setAcademy(jsonObject.getString("학원이름"));
            System.out.println(jsonObject.getString("학원이름"));
            acaCerti.setConnect(jsonObject.getInt("제휴유무"));
            acacerti_total.add(acaCerti);
            acaCerti=new AcaCerti();
        }
    }
}
