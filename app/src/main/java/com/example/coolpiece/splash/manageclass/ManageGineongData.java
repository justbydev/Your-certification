package com.example.coolpiece.splash.manageclass;

import com.example.coolpiece.splash.dataclass.Gineongsa;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ManageGineongData {

    private static ManageGineongData manageGineongData;
    Gineongsa gineongsa;
    private ArrayList<Gineongsa> gineongArrayList;
    private ArrayList<ArrayList<Gineongsa>> gineong_total;
    List<String> category= Arrays.asList("안전관리");

    int testflag=0;

    //get ManageGineongData instance, make singleton pattern
    public static ManageGineongData getInstance(){
        if(manageGineongData==null){
            manageGineongData=new ManageGineongData();
        }
        return manageGineongData;
    }
    //Constructor
    ManageGineongData(){
        gineongsa=new Gineongsa();
        gineongArrayList=new ArrayList<>(20);
        gineong_total=new ArrayList<>(20);
    }

    public ArrayList<ArrayList<Gineongsa>> getGineong_total() {
        return gineong_total;
    }

    //get Gineongsa's certification list that category what I want
    public ArrayList<Gineongsa> getGineongArrayList(int position){
        return gineong_total.get(position);
    }
    //parsing json data, String json is result of String data getting from url
    public void startParsing(String json) throws JSONException, IOException {
        JSONObject jsonObject=new JSONObject(json);
        //for each category
        for(int i=0; i<category.size(); i++){
            JSONArray jsonArray=jsonObject.getJSONArray(category.get(i));
            for(int j=0; j<jsonArray.length(); j++){
                JSONObject temp=jsonArray.getJSONObject(j);
                gineongsa.setName(temp.get("name").toString());
                gineongsa.setIntro(temp.get("intro(개요)").toString());
                gineongsa.setAssociation(temp.get("association(실시기관)").toString());
                gineongsa.setMajor(temp.get("major(관련학과)").toString());
                gineongsa.setTraining_center(temp.get("training_center(훈련기관)").toString());
                gineongsa.setTest_subject(temp.get("test_subject(시험과목)").toString());
                gineongsa.setTest_method(temp.get("test_method(검정방법)").toString());
                gineongsa.setCut_line(temp.get("cut_line(합격기준)").toString());
                JSONArray cal=temp.getJSONArray("calender(일정)");
                ArrayList<String> pilgi_apply=new ArrayList<>();
                ArrayList<String> pilgi_test=new ArrayList<>();
                ArrayList<String> pilgi_balpyo=new ArrayList<>();
                ArrayList<String> silgi_apply=new ArrayList<>();
                ArrayList<String> silgi_test=new ArrayList<>();
                ArrayList<String> final_balpyo=new ArrayList<>();
                for(int a=0; a<cal.length(); a++){
                    JSONObject temp2=cal.getJSONObject(a);
                    pilgi_apply.add(temp2.get("pilgi_apply(필기 원서접수)").toString());
                    pilgi_test.add(temp2.get("pilgi_test(필기시험)").toString());
                    pilgi_balpyo.add(temp2.get("pilgi_balpyo(필기합격자발표)").toString());
                    silgi_apply.add(temp2.get("silgi_apply(실기시험 원서접수)").toString());
                    silgi_test.add(temp2.get("silgi_test실기시험)").toString());
                    final_balpyo.add(temp2.get("final_balpyo(합격자발표)").toString());
                }
                gineongsa.setPilgi_apply(pilgi_apply);
                gineongsa.setPilgi_test(pilgi_test);
                gineongsa.setPilgi_balpyo(pilgi_balpyo);
                gineongsa.setSilgi_apply(silgi_apply);
                gineongsa.setSilgi_test(silgi_test);
                gineongsa.setFinal_balpyo(final_balpyo);
                JSONArray aca=temp.getJSONArray("academy(학원)");
                ArrayList<String> academy_name=new ArrayList<>();
                ArrayList<String> academy_address=new ArrayList<>();
                ArrayList<String> academy_phone=new ArrayList<>();
                ArrayList<String> academy_connect=new ArrayList<>();
                for(int a=0; a<jsonArray.length(); a++){//for each academy jsonArray
                    JSONObject temp3=aca.getJSONObject(a);
                    String address=temp3.get("academy_address").toString();
                    academy_name.add(temp3.get("academy_name").toString());
                    academy_address.add(address);
                    academy_phone.add(temp3.get("academy_phone").toString());
                    //academy_connect.add(temp3.get("").toString());
                    if(testflag==0){
                        academy_connect.add("yes");
                        testflag=1;
                    }
                    else{
                        academy_connect.add("no");
                    }
                }
                gineongsa.setAcademy_name(academy_name);
                gineongsa.setAcademy_address(academy_address);
                gineongsa.setAcademy_phone(academy_phone);
                gineongsa.setAcademy_connect(academy_connect);
                gineongsa.setOnline(temp.get("online(온라인강의)").toString());
                gineongArrayList.add(gineongsa);
                gineongsa=new Gineongsa();
            }//end of one category
            gineong_total.add(gineongArrayList);
            gineongArrayList=new ArrayList<>(20);
        }
    }
}
