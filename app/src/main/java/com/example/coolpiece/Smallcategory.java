package com.example.coolpiece;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class Smallcategory extends AppCompatActivity {
    TextView smallcategory;
    private Intent intent;
    String small;
    String big;
    private FirebaseStorage storage;
    private StorageReference storageRef;
    String url0="https://firebasestorage.googleapis.com/v0/b/pusan-4628a.appspot.com/o/Data%2Fsample.json?alt=media&token=95df83b3-ff69-45f0-8f21-23bcf5dd089a";
    String url1=null;
    String url2=null;
    final int CONN_TIME=5000;
    String json;
    JSONObject jsonObject=null;
    JSONObject temp=null;
    JSONArray jsonArray=null;
    List<String> category=Arrays.asList("건설", "경영.회계.사무", "광업자원", "기계", "농림어업", "문화.예술.디자인.방송", "보건.의료", "사회복지.종교", "섬유.의복", "식품.가공", "안전관리", "영업.판매", "운전.운송", "음식서비스", "이용.숙박.여행.오락.스포츠", "인쇄.목재.가구.공예", "재료", "전기.전자", "정보통신", "화학", "환경.에너지");
    List<String> gisullist= Arrays.asList("가스기술사", "건설기계기술사", "건설안전기술사", "건축구조기술사", "건축기계설비기술사", "건축시공기술사", "건축전기설비기술사", "건축품질시험기술사", "공장관리기술사", "공조냉동기계기술사", "교통기술사", "금속가공기술사", "금속재료기술사", "금속제련기술사", "금형기술사", "기계기술사", "기계안전기술사", "기상예보기술사", "농어업토목기술사", "농화학기술사", "대기관리기술사", "도로및공항기술사", "도시계획기술사", "발송배전기술사", "비파괴검사기술사", "산림기술사", "산업계측제어기술사", "산업기계설비기술사", "산업위생관리기술사", "상하수도기술사", "섬유기술사", "세라믹기술사", "소방기술사", "소음진동기술사", "수산양식기술사", "수산제조기술사", "수자원개발기술사", "수질관리기술사", "시설원예기술사", "식품기술사", "어로기술사", "용접기술사", "의류기술사", "인간공학기술사", "자연환경관리기술사", "전기안전기술사", "전기응용기술사", "전기철도기술사", "전자응용기술사", "정보관리기술사", "제품디자인기술사", "조경기술사", "조선기술사", "종자기술사", "지적기술사", "지질및지반기술사", "차량기술사", "철도기술사", "철도신호기술사", "철도차량기술사", "축산기술사", "측량및지형공간정보기술사", "컴퓨터시스템응용기술사", "토목구조기술사", "토목시공기술사", "토목품질시험기술사", "토양환경기술사", "토질및기초기술사", "폐기물처리기술사", "포장기술사", "표면처리기술사", "품질관리기술사", "항공기관기술사", "항공기체기술사", "항만및해안기술사", "해양기술사", "화공기술사", "화공안전기술사", "화약류관리기술사");
    List<String> gisalist=Arrays.asList("3D프린터개발산업기사", "가구제작산업기사", "가스기사", "가스산업기사", "객화차정비산업기사", "건설기계설비기사", "건설기계설비산업기사", "건설기계정비기사", "건설기계정비산업기사", "건설안전기사", "건설안전산업기사", "건설재료시험기사", "건설재료시험산업기사", "건축기사", "건축목공산업기사", "건축산업기사", "건축설비기사", "건축설비산업기사", "건축일반시공산업기사", "공조냉동기계기사", "공조냉동기계산업기사", "광학기기산업기사", "광학기사", "교통기사", "교통산업기사", "국제의료관광코디네이터", "궤도장비정비기사", "궤도장비정비산업기사", "귀금속가공산업기사", "그린전동자동차기사", "금속재료기사", "금속재료산업기사", "기계설계기사", "기계설계산업기사", "기계정비산업기사", "기계조립산업기사", "기상감정기사", "기상기사", "농림토양평가관리기사", "농림토양평가관리산업기사", "농업기계기사", "농업기계산업기사", "농작업안전보건기사", "누설비파괴검사기사", "대기환경기사", "대기환경산업기사", "도시계획기사", "로봇기구개발기사", "로봇소프트웨어개발기사", "로봇하드웨어개발기사", "멀티미디어콘텐츠제작전문가", "메카트로닉스기사", "메카트로닉스산업기사", "바이오화학제품제조산업기사", "반도체설계기사", "반도체설계산업기사", "방사선비파괴검사기사", "방사선비파괴검사산업기사", "방수산업기사", "방재기사", "배관산업기사", "버섯산업기사", "보석감정산업기사", "보석디자인산업기사", "복어조리산업기사", "사무자동화산업기사", "사출금형산업기사", "사출금형설계기사", "사회조사분석사1급", "사회조사분석사2급", "산림기사", "산림산업기사", "산업안전기사", "산업안전산업기사", "산업위생관리기사", "산업위생관리산업기사", "생물공학기사", "생물분류기사(동물)", "생물분류기사(식물)", "생산자동화산업기사", "설비보전기사", "섬유기사", "섬유디자인산업기사", "섬유산업기사", "소방설비기사(기계분야)", "소방설비기사(전기분야)", "소방설비산업기사(기계분야)", "소방설비산업기사(전기분야)", "소비자전문상담사1급", "소비자전문상담사2급", "소음진동기사", "소음진동산업기사", "수산양식기사", "수산양식산업기사", "수산제조기사", "수질환경기사", "수질환경산업기사", "스포츠경영관리사", "승강기기사", "승강기산업기사", "시각디자인기사", "시각디자인산업기사", "시설원예기사", "식물보호기사", "식물보호산업기사", "식육가공기사", "식품기사", "식품산업기사", "신재생에너지발전설비기사(태양광)", "신재생에너지발전설비산업기사(태양광)", "실내건축기사", "실내건축산업기사", "양식조리산업기사", "어로산업기사", "어병기사", "어업생산관리기사", "에너지관리기사", "에너지관리산업기사", "온실가스관리기사", "온실가스관리산업기사", "와전류비파괴검사기사", "용접기사", "용접산업기사", "위험물산업기사", "유기농업기사", "유기농업산업기사", "응용지질기사", "의공기사", "의공산업기사", "의류기사", "인간공학기사", "인쇄기사", "인쇄산업기사", "일반기계기사", "일식조리산업기사", "임베디드기사", "임산가공기사", "임산가공산업기사", "임상심리사1급", "임상심리사2급", "임업종묘기사", "자기비파괴검사기사", "자기비파괴검사산업기사", "자동차정비기사", "자동차정비산업기사", "자연생태복원기사", "자연생태복원산업기사", "잠수산업기사", "재료조직평가산업기사", "전기공사기사", "전기공사산업기사", "전기기기산업기사", "전기기사", "전기산업기사", "전기철도기사", "전기철도산업기사", "전자계산기기사", "전자계산기제어산업기사", "전자계산기조직응용기사", "전자기사", "전자부품장착산업기사", "전자산업기사", "전자회로설계산업기사", "정밀측정산업기사", "정보처리기사", "정보처리산업기사", "제판기사", "제품디자인기사", "제품디자인산업기사", "조경기사", "조경산업기사", "조선기사", "조선산업기사", "종자기사", "종자산업기사", "주조산업기사", "중식조리산업기사", "지적기사", "지적산업기사", "직업상담사1급", "직업상담사2급", "철도동력차기관정비산업기사", "철도동력차전기정비산업기사", "철도신호기사", "철도신호산업기사", "철도운송산업기사", "철도차량기사", "철도차량산업기사", "철도토목기사", "철도토목산업기사", "초음파비파괴검사기사", "초음파비파괴검사산업기사", "축산기사", "축산산업기사", "측량및지형공간정보기사", "측량및지형공간정보산업기사", "치공구설계산업기사", "침투비파괴검사기사", "침투비파괴검사산업기사", "컨벤션기획사1급", "컨벤션기획사2급", "컬러리스트기사", "컬러리스트산업기사", "컴퓨터응용가공산업기사", "콘크리트기사", "콘크리트산업기사", "텔레마케팅관리사", "토목기사", "토목산업기사", "토양환경기사", "판금제관산업기사", "패션디자인산업기사", "패션머천다이징산업기사", "폐기물처리기사", "폐기물처리산업기사", "포장기사", "포장산업기사", "표면처리산업기사", "품질경영기사", "품질경영산업기사", "프레스금형산업기사", "프레스금형설계기사", "피아노조율산업기사", "한복산업기사", "한식조리산업기사", "항공기사", "항공산업기사", "항로표지기사", "항로표지산업기사", "해양공학기사", "해양자원개발기사", "해양조사산업기사", "해양환경기사", "화공기사", "화약류관리기사", "화약류관리산업기사", "화약류제조기사", "화약류제조산업기사", "화재감식평가기사", "화재감식평가산업기사", "화학분석기사", "화훼장식기사", "화훼장식산업기사", "환경위해관리기사");
    List<String> gineongsalist=Arrays.asList("3D프린터운용기능사", "가구제작기능사", "가스기능사", "거푸집기능사", "건설기계정비기능사", "건설재료시험기능사", "건축도장기능사", "건축목공기능사", "공구제작기능사보", "공유압기능사", "공조냉동기계기능사", "광고도장기능사", "광산보안기능사(화약분야)", "광산차량기계운전기능사", "광산환경기능사", "광학기능사", "궤도장비정비기능사", "귀금속가공기능사", "금속도장기능사", "금속재료시험기능사", "금속재창호기능사", "금형기능사", "기계가공조립기능사", "기계정비기능사", "기중기운전기능사", "농기계운전기능사", "농기계정비기능사", "도배기능사", "도자공예기능사", "도화기능사", "동력기계정비기능사", "떡제조기능사", "로더운전기능사", "롤러운전기능사", "메카트로닉스기능사", "모터그레이더운전기능사", "목공예기능사", "목재창호기능사", "목질재료기능사", "미장기능사", "반도체장비유지보수기능사", "방사선비파괴검사기능사", "방수기능사", "배관기능사", "버섯종균기능사", "보석가공기능사", "보석감정사", "복어조리기능사", "불도저운전기능사", "비계기능사", "사진기능사", "사출금형기능사보", "산림기능사", "생산자동화기능사", "석공기능사", "석공예기능사", "선체건조기능사", "설비보전기능사", "세탁기능사", "수산양식기능사", "승강기기능사", "식육처리기능사", "식품가공기능사", "신발류제조기능사", "신재생에너지발전설비기능사(태양광)", "실내건축기능사", "아스팔트피니셔운전기능사", "압연기능사", "양복기능사", "양장기능사", "양화장치운전기능사", "에너지관리기능사", "연삭기능사", "열처리기능사", "염색기능사(날염)", "염색기능사(침염)", "온수온돌기능사", "용접기능사", "원예기능사", "원형기능사", "웹디자인기능사", "위험물기능사", "유기농업기능사", "유리시공기능사", "의료전자기능사", "이용사", "인쇄기능사", "임산가공기능사", "임업종묘기능사", "자기비파괴검사기능사", "자동차보수도장기능사", "자동차정비기능사", "자동차차체수리기능사", "잠수기능사", "전기기능사", "전산응용건축제도기능사", "전산응용기계제도기능사", "전산응용조선제도기능사", "전산응용토목제도기능사", "전자계산기기능사", "전자기기기능사", "전자부품장착기능사", "전자출판기능사", "전자캐드기능사", "정밀측정기능사", "제강기능사", "제선기능사", "제품응용모델링기능사", "조경기능사", "조적기능사", "조주기능사", "종자기능사", "주조기능사", "지도제작기능사", "지적기능사", "천공기운전기능사", "천장크레인운전기능사", "철근기능사", "철도전기신호기능사", "철도차량정비기능사", "철도토목기능사", "초음파비파괴검사기능사", "축로기능사", "축산기능사", "측량기능사", "침투비파괴검사기능사", "컨테이너크레인운전기능사", "컴퓨터그래픽스운용기능사", "컴퓨터응용밀링기능사", "컴퓨터응용선반기능사", "콘크리트기능사", "타워크레인운전기능사", "타일기능사", "특수용접기능사", "판금제관기능사", "표면처리기능사", "프레스금형기능사보", "플라스틱창호기능사", "피아노조율기능사", "한복기능사", "항공기관정비기능사", "항공기체정비기능사", "항공사진기능사", "항공장비정비기능사", "항공전자정비기능사", "항로표지기능사", "화약취급기능사", "화학분석기능사", "화훼장식기능사", "환경기능사");
    Gisul gisul;
    Gisa gisa;
    Gineongsa gineongsa;
    ArrayList<Gisul> gisularray;
    ArrayList<Gisa> gisaarray;
    ArrayList<Gineongsa> gineongarray;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.smallcategory);

        smallcategory=(TextView)findViewById(R.id.smallcategory);

        intent=getIntent();
        small=intent.getStringExtra("SCA");
        big=intent.getStringExtra("BCA");
        smallcategory.setText(big+'-'+small);

        new Thread(){
            public void run(){
                for(int i=0; i<3; i++){
                    json=getjsonHtml("url"+Integer.toString(i));
                    if(i==0){
                        try {
                            jsonObject=new JSONObject(json);
                            for(int j=0; j<category.size(); j++){
                                jsonObject=jsonObject.getJSONObject(category.get(j));
                                for(int k=0; k<gisullist.size(); k++){
                                    jsonObject=jsonObject.getJSONObject(gisullist.get(k));
                                    gisul=new Gisul();
                                    gisul.setIntro(jsonObject.get("intro(개요)").toString());
                                    gisul.setAssociation(jsonObject.get("association(실기기관)").toString());
                                    gisul.setMajor(jsonObject.get("major(관련학과)").toString());
                                    gisul.setTraining_center(jsonObject.get("training_center(훈련기관)").toString());
                                    gisul.setTest_subject(jsonObject.get("test_subject(시험과목)").toString());
                                    gisul.setTest_method(jsonObject.get("test_method(검정방법)").toString());
                                    gisul.setCut_line(jsonObject.get("cut_line(합격기준)").toString());
                                    jsonArray=jsonObject.getJSONArray("calender(일정)");
                                    ArrayList<String> pilgi_apply=new ArrayList<>();
                                    ArrayList<String> pilgi_test=new ArrayList<>();
                                    ArrayList<String> pilgi_balpyo=new ArrayList<>();
                                    ArrayList<String> silgi_apply=new ArrayList<>();
                                    ArrayList<String> silgi_test=new ArrayList<>();
                                    ArrayList<String> final_balpyo=new ArrayList<>();
                                    for(int a=0; a<jsonArray.length(); a++){
                                        jsonObject=jsonArray.getJSONObject(a);
                                        pilgi_apply.add(jsonObject.get("pilgi_apply(필기 원서접수)").toString());
                                        pilgi_test.add(jsonObject.get("pilgi_test(필기시험)").toString());
                                        pilgi_balpyo.add(jsonObject.get("pilgi_balpyo(필기합격자발표)").toString());
                                        silgi_apply.add(jsonObject.get("silgi_apply(실기시험 원서접수").toString());
                                        silgi_test.add(jsonObject.get("silgi_test(실기시험)").toString());
                                        final_balpyo.add(jsonObject.get("final_balpyo(합격자발표)").toString());
                                    }
                                    gisul.setPilgi_apply(pilgi_apply);
                                    gisul.setPilgi_test(pilgi_test);
                                    gisul.setPilgi_balpyo(pilgi_balpyo);
                                    gisul.setSilgi_apply(silgi_apply);
                                    gisul.setSilgi_test(silgi_test);
                                    gisul.setFinal_balpyo(final_balpyo);
                                    jsonArray=jsonObject.getJSONArray("academy(학원)");
                                    ArrayList<String> academy_name=new ArrayList<>();
                                    ArrayList<String> academy_address=new ArrayList<>();
                                    ArrayList<String> academy_phone=new ArrayList<>();
                                    for(int a=0; a<jsonArray.length(); a++){
                                        jsonObject=jsonArray.getJSONObject(a);
                                        academy_name.add(jsonObject.get("academy_name").toString());
                                        academy_address.add(jsonObject.get("academy_address").toString());
                                        academy_phone.add(jsonObject.get("academy_phone").toString());
                                    }
                                    gisul.setAcademy_name(academy_name);
                                    gisul.setAcademy_address(academy_address);
                                    gisul.setAcademy_phone(academy_phone);
                                }
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                    else if(i==1){
                        try {
                            gisa=new Gisa();
                            jsonObject=new JSONObject(json);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                    else if(i==2){
                        try {
                            gineongsa=new Gineongsa();
                            jsonObject=new JSONObject(json);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }
                System.out.println(json);
                try {
                    jsonObject=new JSONObject(json);
                    jsonObject=jsonObject.getJSONObject("기능사");
                    jsonObject=jsonObject.getJSONObject("안전관리");
                    System.out.println(jsonObject.get("name"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }.start();

    }

    private String getjsonHtml(String url){
        String jsonHtml="";
        HttpURLConnection con=null;
        InputStreamReader isr=null;
        BufferedReader br=null;
        try{
            URL Url=new URL(url);
            con=(HttpURLConnection)Url.openConnection();
            con.setConnectTimeout(CONN_TIME);
            con.setReadTimeout(CONN_TIME);

            isr=new InputStreamReader(con.getInputStream());
            br=new BufferedReader(isr);

            String str=null;
            while((str=br.readLine())!=null){
                //System.out.println(str);
                jsonHtml+=str+"\n";
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return jsonHtml;
    }
}
