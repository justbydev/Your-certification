package com.example.coolpiece.splash.manageclass;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.coolpiece.splash.dataclass.Gineongsa;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class ManageGineongData {

    private static ManageGineongData manageGineongData;
    Gineongsa gineongsa;
    private ArrayList<Gineongsa> gineongArrayList;
    private ArrayList<ArrayList<Gineongsa>> gineong_total;
    private ArrayList<String> gineonglist;
    List<String> category= Arrays.asList("건설", "경영,회계,사무", "광업자원", "기계", "농림어업",
            "문화,예술,디자인,방송", "보건,의료", "사회복지,종교", "섬유,의복", "식품,가공", "안전관리", "영업,판매",
            "운전,운송", "음식서비스", "이용,숙박,여행,오락,스포츠", "인쇄,목재,가구,공예", "재료", "전기,전자", "정보통신",
            "화학", "환경,에너지");

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
        gineonglist=new ArrayList<>();
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
        System.out.println(category.size());
        for(int i=0; i<category.size(); i++){
            System.out.println("**********************************");
            System.out.println(category.get(i));
            System.out.println("**********************************");
            JSONArray jsonArray=jsonObject.getJSONArray(category.get(i));
            System.out.println(jsonArray.length());
            for(int j=0; j<jsonArray.length()-1; j++){
                System.out.println(j);
                System.out.println("-=-=-=-=-=-=-==-=-=-=-=-=-=-=-=-=-=-=-");
                JSONObject temp=jsonArray.getJSONObject(j);
                System.out.println(temp.get("name").toString());
                gineongsa.setName(temp.get("name").toString());
                gineonglist.add(temp.get("name").toString());
                System.out.println(temp.get("intro(개요)").toString());
                gineongsa.setIntro(temp.get("intro(개요)").toString());
                System.out.println(temp.get("association(실시기관)").toString());
                gineongsa.setAssociation(temp.get("association(실시기관)").toString());
                System.out.println(temp.get("major(관련학과)").toString());
                gineongsa.setMajor(temp.get("major(관련학과)").toString());
                System.out.println(temp.get("training_center(훈련기관)").toString());
                gineongsa.setTraining_center(temp.get("training_center(훈련기관)").toString());
                System.out.println(temp.get("test_subject(시험과목)").toString());
                gineongsa.setTest_subject(temp.get("test_subject(시험과목)").toString());
                System.out.println(temp.get("test_method(검정방법)").toString());
                gineongsa.setTest_method(temp.get("test_method(검정방법)").toString());
                System.out.println(temp.get("cut_line(합격기준)").toString());
                gineongsa.setCut_line(temp.get("cut_line(합격기준)").toString());
                //JSONArray cal=temp.getJSONArray("calender(일정)");
                /*ArrayList<String> pilgi_apply=new ArrayList<>();
                ArrayList<String> pilgi_test=new ArrayList<>();
                ArrayList<String> pilgi_balpyo=new ArrayList<>();
                ArrayList<String> pilgi_balpyo_final=new ArrayList<>();
                ArrayList<String> silgi_apply=new ArrayList<>();
                ArrayList<String> silgi_test=new ArrayList<>();
                ArrayList<String> final_balpyo=new ArrayList<>();
                System.out.println(cal.length());
                System.out.println("where is calendar");
                for(int a=0; a<cal.length(); a++){
                    System.out.println(a);
                    System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
                    JSONObject temp2=cal.getJSONObject(a);
                    System.out.println("==================================");
                    System.out.println(temp2.get("pilgi_apply(필기 원서접수)").toString());
                    System.out.println("+++++++++++++++++++++++++++++++++");
                    pilgi_apply.add(temp2.get("pilgi_apply(필기 원서접수)").toString());
                    System.out.println(temp2.get("pilgi_test(필기시험)").toString());
                    pilgi_test.add(temp2.get("pilgi_test(필기시험)").toString());
                    System.out.println(temp2.get("pilgi_balpyo(필기시험합격예정자 발표)").toString());
                    pilgi_balpyo.add(temp2.get("pilgi_balpyo(필기시험합격예정자 발표)").toString());
                    System.out.println(temp2.get("pilgi_balpyo(응시자격서류제출/필기시험합격자결정)").toString());
                    pilgi_balpyo_final.add(temp2.get("pilgi_balpyo(응시자격서류제출/필기시험합격자결정)").toString());
                    System.out.println(temp2.get("silgi_apply(면접시험원서접수(인터넷))").toString());
                    silgi_apply.add(temp2.get("silgi_apply(면접시험원서접수(인터넷))").toString());
                    System.out.println(temp2.get("silgi_test(면접시험)").toString());
                    silgi_test.add(temp2.get("silgi_test(면접시험)").toString());
                    System.out.println(temp2.get("final_balpyo(합격자발표)").toString());
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
                for(int a=0; a<aca.length(); a++){//for each academy jsonArray
                    JSONObject temp3=aca.getJSONObject(a);
                    System.out.println(temp3.get("location").toString());
                    String address=temp3.get("location").toString();
                    System.out.println(temp3.get("name").toString());
                    academy_name.add(temp3.get("name").toString());
                    academy_address.add(address);
                    System.out.println(temp3.get("phone").toString());
                    academy_phone.add(temp3.get("phone").toString());
                    //academy_connect.add(temp3.get("jaehyu").toString());
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
                gineongsa.setAcademy_connect(academy_connect);*/
                //gineongsa.setOnline(temp.get("online(온라인강의)").toString());
                gineongArrayList.add(gineongsa);
                gineongsa=new Gineongsa();
            }//end of one category
            System.out.println("here");
            gineong_total.add(gineongArrayList);
            System.out.println("bere");
            gineongArrayList=new ArrayList<>(20);
            System.out.println("cere");
        }
    }
    public ArrayList<String> getGineonglist(){
        return gineonglist;
    }
}
