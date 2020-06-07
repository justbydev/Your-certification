package com.example.coolpiece;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ManageGisulData {
    private static ManageGisulData manageGisulData;
    Gisul gisul;
    private ArrayList<Gisul> gisulArrayList;
    private ArrayList<ArrayList<Gisul>> gisul_total;
    List<String> category= Arrays.asList("안전관리");


    //get ManageGisulData instance, make singleton pattern
    public static ManageGisulData getInstance(){
        if(manageGisulData==null){
            manageGisulData=new ManageGisulData();
        }
        return manageGisulData;
    }
    //Constructor
    ManageGisulData(){
        gisul=new Gisul();
        gisulArrayList=new ArrayList<>(20);
        gisul_total=new ArrayList<>(20);
    }

    public ArrayList<ArrayList<Gisul>> getGisul_total() {
        return gisul_total;
    }

    //get Gisulsa's certification list that category what I want
    public ArrayList<Gisul> getGisulArrayList(int position){
        return gisul_total.get(position);
    }
    //parsing json data, String json is result of String data getting from url
    public void startParsing(String json) throws JSONException {
        JSONObject jsonObject=new JSONObject(json);
        //for each category
        for(int i=0; i<category.size(); i++){
            JSONArray jsonArray=jsonObject.getJSONArray(category.get(i));
            for(int j=0; j<jsonArray.length(); j++){
                JSONObject temp=jsonArray.getJSONObject(j);
                gisul=new Gisul();
                gisul.setName(temp.get("name").toString());
                gisul.setIntro(temp.get("intro(개요)").toString());
                gisul.setAssociation(temp.get("association(실시기관)").toString());
                gisul.setMajor(temp.get("major(관련학과)").toString());
                gisul.setTraining_center(temp.get("training_center(훈련기관)").toString());
                gisul.setTest_subject(temp.get("test_subject(시험과목)").toString());
                gisul.setTest_method(temp.get("test_method(검정방법)").toString());
                gisul.setCut_line(temp.get("cut_line(합격기준)").toString());
                JSONArray cal=temp.getJSONArray("calender(일정)");
                ArrayList<String> pilgi_apply=new ArrayList<>();
                ArrayList<String> pilgi_test=new ArrayList<>();
                ArrayList<String> pilgi_balpyo=new ArrayList<>();
                ArrayList<String> pilgi_balpyo_final=new ArrayList<>();
                ArrayList<String> silgi_apply=new ArrayList<>();
                ArrayList<String> silgi_test=new ArrayList<>();
                ArrayList<String> final_balpyo=new ArrayList<>();
                for(int a=0; a<cal.length(); a++){//calander list in jsonArray
                    JSONObject temp2=cal.getJSONObject(a);
                    pilgi_apply.add(temp2.get("pilgi_apply(필기 원서접수)").toString());
                    pilgi_test.add(temp2.get("pilgi_test(필기시험)").toString());
                    pilgi_balpyo.add(temp2.get("pilgi_balpyo(필기시험합격예정자 발표)").toString());
                    pilgi_balpyo_final.add(temp2.get("pilgi_balpyo(응시자격서류제출/필기시험합격자결정)").toString());
                    silgi_apply.add(temp2.get("silgi_apply(면접시험원서접수(인터넷))").toString());
                    silgi_test.add(temp2.get("silgi_test(면접시험)").toString());
                    final_balpyo.add(temp2.get("final_balpyo(합격자발표)").toString());
                }
                gisul.setPilgi_apply(pilgi_apply);
                gisul.setPilgi_test(pilgi_test);
                gisul.setPilgi_balpyo(pilgi_balpyo);
                gisul.setPilgi_balpyo_final(pilgi_balpyo_final);
                gisul.setSilgi_apply(silgi_apply);
                gisul.setSilgi_test(silgi_test);
                gisul.setFinal_balpyo(final_balpyo);
                /*JSONArray aca=temp.getJSONArray("academy(학원)");
                ArrayList<String> academy_name=new ArrayList<>();
                ArrayList<String> academy_address=new ArrayList<>();
                ArrayList<String> academy_phone=new ArrayList<>();
                ArrayList<String> academy_connect=new ArrayList<>();
                for(int a=0; a<jsonArray.length(); a++){
                    JSONObject temp3=aca.getJSONObject(a);
                    academy_name.add(temp3.get("academy_name").toString());
                    academy_address.add(temp3.get("academy_address").toString());
                    academy_phone.add(temp3.get("academy_phone").toString());
                    academy_connect.add(temp3.get("").toString());
                }
                gisul.setAcademy_name(academy_name);
                gisul.setAcademy_address(academy_address);
                gisul.setAcademy_phone(academy_phone);
                gisul.setAcademy_connect(academy_connect);*/
                //when setting one certification, then add to gisulArrayList
                gisulArrayList.add(gisul);
                gisul=new Gisul();
            }//end of one category
            gisul_total.add(gisulArrayList);
            gisulArrayList=new ArrayList<>(20);
        }
    }
}
