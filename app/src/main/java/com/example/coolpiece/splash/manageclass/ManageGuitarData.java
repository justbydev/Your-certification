package com.example.coolpiece.splash.manageclass;

import com.example.coolpiece.splash.dataclass.Guitar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ManageGuitarData {
    private static ManageGuitarData manageGuitarData;
    Guitar guitar;
    private ArrayList<Guitar> guitarArrayList;
    private ArrayList<ArrayList<Guitar>> guitar_total;
    private ArrayList<String> guitarlist;
    List<String> category= Arrays.asList("강사과정", "커피과정", "산업과정", "심리상담과정", "전문가과정",
            "방과후과정", "공부과정", "항공과정", "아동과정", "병원과정", "실버과정", "안전과정",
            "인문과정", "IT과정", "부동산과정", "반려과정", "취업과정");
    public static ManageGuitarData getInstance(){
        if(manageGuitarData==null){
            manageGuitarData=new ManageGuitarData();
        }
        return manageGuitarData;
    }
    ManageGuitarData(){
        guitar=new Guitar();
        guitarArrayList=new ArrayList<>(20);
        guitar_total=new ArrayList<>(20);
        guitarlist=new ArrayList<>();
    }
    public ArrayList<ArrayList<Guitar>> getGuitar_total(){return guitar_total;}

    public ArrayList<Guitar> getGuitarArrayList(int position){
        return guitar_total.get(position);
    }
    public void startParsing(String json) throws JSONException {
        JSONObject jsonObject=new JSONObject(json);
        for(int i=0; i<category.size(); i++){
            JSONArray jsonArray=jsonObject.getJSONArray(category.get(i));
            System.out.println(category.get(i));
            for(int j=0; j<jsonArray.length()-1; j++){
                JSONObject temp=jsonArray.getJSONObject(j);
                guitar.setName(temp.get("name").toString());
                guitarlist.add(temp.get("name").toString());
                System.out.println(temp.get("name").toString());
                System.out.println("==========================");
                guitar.setIntro(temp.get("intro(개요)").toString());
                guitar.setExam(temp.get("exam(시험방식)").toString());
                guitar.setCut_line(temp.get("cut_line(합격기준)").toString());
                guitar.setEnrollment(temp.get("enrollment(자격등록기관)").toString());
                guitar.setAssociation(temp.get("association(자격발급기관)").toString());
                guitar.setTraining_center(temp.get("training_center(교육운영기관)").toString());
                //JSONArray cal=temp.getJSONArray("calender(일정)");
                /*ArrayList<String> pilgi_apply=new ArrayList<>();
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
                gisa.setPilgi_apply(pilgi_apply);
                gisa.setPilgi_test(pilgi_test);
                gisa.setPilgi_balpyo(pilgi_balpyo);
                gisa.setPilgi_balpyo_final(pilgi_balpyo_final);
                gisa.setSilgi_apply(silgi_apply);
                gisa.setSilgi_test(silgi_test);
                gisa.setFinal_balpyo(final_balpyo);*/
                /*JSONArray aca=temp.getJSONArray("academy(학원)");
                ArrayList<String> academy_name=new ArrayList<>();
                ArrayList<String> academy_address=new ArrayList<>();
                ArrayList<String> academy_phone=new ArrayList<>();
                ArrayList<String> academy_connect=new ArrayList<>();
                for(int a=0; a<aca.length(); a++){
                    JSONObject temp3=aca.getJSONObject(a);
                    academy_name.add(temp3.get("academy_name").toString());
                    academy_phone.add(temp3.get("academy_number").toString());
                    academy_address.add(temp3.get("academy_position").toString());
                    academy_connect.add(temp3.get("academy_partner").toString());
                    //academy_connect.add("no");
                }
                guitar.setAcademy_name(academy_name);
                guitar.setAcademy_address(academy_address);
                guitar.setAcademy_phone(academy_phone);
                guitar.setAcademy_connect(academy_connect);*/
                //when setting one certification, then add to gisulArrayList
                guitarArrayList.add(guitar);
                guitar=new Guitar();
            }//end of one category
            guitar_total.add(guitarArrayList);
            guitarArrayList=new ArrayList<>(20);
        }
    }
    public ArrayList<String> getGuitarlist(){
        return guitarlist;
    }
}
