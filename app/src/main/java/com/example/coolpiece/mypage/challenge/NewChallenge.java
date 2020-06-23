package com.example.coolpiece.mypage.challenge;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.coolpiece.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class NewChallenge extends AppCompatActivity {
    TextView challenge_name;
    Spinner day_spinner;
    String selectedday;
    Spinner certificate_spinner;
    RadioGroup pointradio;
    RadioButton checked_point;
    String selectedValue;
    Intent intent;
    RadioGroup radioGroup;
    String selectedcerti;
    Button challenge_register;
    Button challenge_cancel;
    List<String> gineonglist= Arrays.asList("가구제작기능사", "가스기능사", "거푸집기능사", "건설기계정비기능사", "건설재료시험기능사", "건축도장기능사", "건축목공기능사", "공유압기능사", "공조냉동기계기능사", "광고도장기능사", "광학기능사", "굴삭기운전기능사", "궤도장비정비기능사", "귀금속가공기능사", "금속도장기능사", "금속재료시험기능사", "금속재창호기능사", "금형기능사", "기계가공조립기능사", "기계정비기능사", "기중기운전기능사", "농기계운전기능사", "농기계정비기능사", "도배기능사", "도자기공예기능사", "도화기능사", "동력기계정비기능사", "로더운전기능사", "롤러운전기능사", "모터그레이더운전기능사", "목공예기능사", "미용사(네일)", "미용사(메이크업)", "미용사(일반)", "미용사(피부)", "미장기능사", "반도체장비유지보수기능사", "방사선비파괴검사기능사", "방수기능사", "배관기능사", "버섯종균기능사", "보석가공기능사", "보석감정사", "복어조리기능사", "불도저운전기능사", "비계기능사", "사진기능사", "산림기능사", "생산자동화기능사", "석공기능사", "석공예기능사", "선체건조기능사", "설비보전기능사", "세탁기능사", "수산양식기능사", "승강기기능사", "식육처리기능사", "식품가공기능사", "신발류제조기능사", "신재생에너지발전설비기능사(태양광)", "실내건축기능사", "아스팔트피니셔운전기능사", "압연기능사", "양복기능사", "양식조리기능사", "양장기능사", "양화장치운전기능사", "에너지관리기능사", "연삭기능사", "열처리기능사", "염색기능사(날염)", "염색기능사(침염)", "온수온돌기능사", "용접기능사", "원예기능사", "원형기능사", "웹디자인기능사", "위험물기능사", "유기농업기능사", "유리시공기능사", "의료전자기능사", "이용사", "인쇄기능사", "일식조리기능사", "임산가공기능사", "임업종묘기능사", "자기비파괴검사기능사", "자동차보수도장기능사", "자동차정비기능사", "자동차차체수리기능사", "잠수기능사", "전기기능사", "전산응용건축제도기능사", "전산응용기계제도기능사", "전산응용조선제도기능사", "전산응용토목제도기능사", "전자계산기기능사", "전자기기기능사", "전자부품장착(SMT)기능사", "전자출판기능사", "전자캐드기능사", "정밀측정기능사", "정보기기운용기능사", "정보처리기능사", "제강기능사", "제과기능사", "제빵기능사", "제선기능사", "제품응용모델링기능사", "조경기능사", "조적기능사", "조주기능사", "종자기능사", "주조기능사", "중식조리기능사", "지게차운전기능사", "지도제작기능사", "지적기능사", "천공기운전기능사", "천장크레인운전기능사", "철근기능사", "철도전기신호기능사", "철도차량정비기능사", "철도토목기능사", "초음파비파괴검사기능사", "축로기능사", "축산기능사", "측량기능사", "침투비파괴검사기능사", "컨테이너크레인운전기능사", "컴퓨터그래픽스운용기능사", "컴퓨터응용밀링기능사", "컴퓨터응용선반기능사", "콘크리트기능사", "타워크레인운전기능사", "타일기능사", "특수용접기능사", "판금제관기능사", "표면처리기능사", "플라스틱창호기능사", "피아노조율기능사", "한복기능사", "한식조리기능사", "항공기관정비기능사", "항공기체정비기능사", "항공사진기능사", "항공장비정비기능사", "항공전자정비기능사", "항로표지기능사", "화약취급기능사", "화학분석기능사", "화훼장식기능사", "환경기능사", "3D프린터운용기능사", "떡제조기능사");
    List<String> gisulsalist=Arrays.asList("가스기술사", "건설기계기술사", "건설안전기술사", "건축구조기술사", "건축기계설비기술사", "건축시공기술사", "건축전기설비기술사", "건축품질시험기술사", "공장관리기술사", "공조냉동기계기술사", "교통기술사", "금속가공기술사", "금속재료기술사", "금속제련기술사", "금형기술사", "기계기술사", "기계안전기술사", "기상예보기술사", "농어업토목기술사", "농화학기술사", "대기관리기술사", "도로및공항기술사", "도시계획기술사", "발송배전기술사", "비파괴검사기술사", "산림기술사", "산업계측제어기술사", "산업기계설비기술사", "산업위생관리기술사", "상하수도기술사", "섬유기술사", "세라믹기술사", "소방기술사", "소음진동기술사", "수산양식기술사", "수산제조기술사", "수자원개발기술사", "수질관리기술사", "시설원예기술사", "식품기술사", "어로기술사", "용접기술사", "의류기술사", "인간공학기술사", "자연환경관리기술사", "전기안전기술사", "전기응용기술사", "전기철도기술사", "전자응용기술사", "정보관리기술사", "제품디자인기술사", "조경기술사", "조선기술사", "종자기술사", "지적기술사", "지질및지반기술사", "차량기술사", "철도기술사", "철도신호기술사", "철도차량기술사", "축산기술사", "측량및지형공간정보기술사", "컴퓨터시스템응용기술사", "토목구조기술사", "토목시공기술사", "토목품질시험기술사", "토양환경기술사", "토질및기초기술사", "폐기물처리기술사", "포장기술사", "표면처리기술사", "품질관리기술사", "항공기관기술사", "항공기체기술사", "항만및해안기술사", "해양기술사", "화공기술사", "화공안전기술사", "화약류관리기술사");
    List<String> gisalist=Arrays.asList("가스기사", "건설기계설비기사", "건설기계정비기사", "건설안전기사", "건설재료시험기사", "건축기사", "건축설비기사", "공조냉동기계기사", "광학기사", "교통기사", "궤도장비정비기사", "그린전동자동차기사", "금속재료기사", "기계설계기사", "기상감정기사", "기상기사", "농업기계기사", "누설비파괴검사기사", "대기환경기사", "도시계획기사", "메카트로닉스기사", "반도체설계기사", "방사선비파괴검사기사", "사출금형설계기사", "산림기사", "산업안전기사", "산업위생관리기사", "생물공학기사", "생물분류기사(동물)", "생물분류기사(식물)", "설비보전기사", "섬유기사", "소방설비기사(기계분야)", "소방설비기사(전기분야)", "소음진동기사", "수산양식기사", "수산제조기사", "수질환경기사", "승강기기사", "시각디자인기사", "시설원예기사", "식물보호기사", "식품기사", "신재생에너지발전설비기사(태양광)", "실내건축기사", "어업생산관리기사", "에너지관리기사", "온실가스관리기사", "와전류비파괴검사기사", "용접기사", "유기농업기사", "응용지질기사", "의공기사", "의류기사", "인간공학기사", "인쇄기사", "일반기계기사", "임베디드기사", "임산가공기사", "임업종묘기사", "자기비파괴검사기사", "자동차정비기사", "자연생태복원기사", "전기공사기사", "전기기사", "전기철도기사", "전자계산기기사", "전자계산기조직응용기사", "전자기사", "정보처리기사", "제품디자인기사", "조경기사", "조선기사", "종자기사", "지적기사", "철도신호기사", "철도차량기사", "철도토목기사", "초음파비파괴검사기사", "축산기사", "측량및지형공간정보기사", "침투비파괴검사기사", "컬러리스트기사", "콘크리트기사", "토목기사", "토양환경기사", "폐기물처리기사", "포장기사", "품질경영기사", "프레스금형설계기사", "항공기사", "항로표지기사", "해양공학기사", "해양자원개발기사", "해양환경기사", "화공기사", "화약류관리기사", "화약류제조기사", "화재감식평가기사", "화학분석기사", "화훼장식기사", "식육가공기사", "농작업안전보건기사", "로봇기구개발기사", "로봇소프트웨어개발기사", "로봇하드웨어개발기사", "방재기사", "환경위해관리기사");
    List<String> sanuplist=Arrays.asList("가스산업기사", "건설기계설비산업기사", "건설기계정비산업기사", "건설안전산업기사", "건설재료시험산업기사", "건축목공산업기사", "건축산업기사", "건축설비산업기사", "건축일반시공산업기사", "공조냉동기계산업기사", "광학기기산업기사", "교통산업기사", "국제의료관광코디네이터", "궤도장비정비산업기사", "귀금속가공산업기사", "금속재료산업기사", "기계가공조립산업기사", "기계설계산업기사", "기계정비산업기사", "농림토양평가관리산업기사", "농업기계산업기사", "대기환경산업기사", "반도체설계산업기사", "방사선비파괴검사산업기사", "방수산업기사", "배관산업기사", "사무자동화산업기사", "사출금형산업기사", "산림산업기사", "산업안전산업기사", "산업위생관리산업기사", "생산자동화산업기사", "섬유디자인산업기사", "섬유산업기사", "소방설비산업기사(기계분야)", "소방설비산업기사(전기분야)", "소음진동산업기사", "수산양식산업기사", "수질환경산업기사", "승강기산업기사", "시각디자인산업기사", "식물보호산업기사", "식품산업기사", "신재생에너지발전설비산업기사(태양광)", "실내건축산업기사", "어로산업기사", "에너지관리산업기사", "온실가스관리산업기사", "용접산업기사", "위험물산업기사", "유기농업산업기사", "의공산업기사", "인쇄산업기사", "임산가공산업기사", "자기비파괴검사산업기사", "자동차정비산업기사", "자연생태복원산업기사", "잠수산업기사", "재료조직평가산업기사", "전기공사산업기사", "전기산업기사", "전기철도산업기사", "전자계산기제어산업기사", "전자부품장착(SMT)산업기사", "전자산업기사", "정밀측정산업기사", "정보처리산업기사", "제품디자인산업기사", "조경산업기사", "조리산업기사(복어)", "조리산업기사(양식)", "조리산업기사(일식)", "조리산업기사(중식)", "조리산업기사(한식)", "조선산업기사", "종자산업기사", "주조산업기사", "지적산업기사", "철도신호산업기사", "철도운송산업기사", "철도차량산업기사", "철도토목산업기사", "초음파비파괴검사산업기사", "축산산업기사", "측량및지형공간정보산업기사", "치공구설계산업기사", "침투비파괴검사산업기사", "컬러리스트산업기사", "컴퓨터응용가공산업기사", "콘크리트산업기사", "토목산업기사", "판금제관산업기사", "패션디자인산업기사", "패션머천다이징산업기사", "폐기물처리산업기사", "포장산업기사", "표면처리산업기사", "품질경영산업기사", "프레스금형산업기사", "피아노조율산업기사", "한복산업기사", "항공산업기사", "항로표지산업기사", "해양조사산업기사", "화약류관리산업기사", "화약류제조산업기사", "화재감식평가산업기사", "3D프린터개발산업기사", "가구제작산업기사", "바이오화학제품제조산업기사", "버섯산업기사", "보석감정산업기사", "보석디자인산업기사", "화훼장식산업기사");
    List<String> guitarlist=Arrays.asList("예절교육지도사 1급", "장애인인식개선지도사 1급", "급퍼스널컬러컨설턴트 1급", "플로리스트전문가 1급", "캘리그라피지도사 1급", "SNS마케팅전문가 1급", "영어요리지도사 1급", "정리수납전문가 1급", "쇼핑몰관리사 1급", "리더십지도사 1급", "가정관리사 1급", "커피바리스타전문가 1급", "커피핸드드립전문가 1급", "커피로스팅마스터 1급", "커피품질평가사 1급", "와인소믈리에 1급", "티소믈리에 1급", "품질검사마스터 1급", "3D프린팅지도사 1급", "6시그마(GB) 1급", "코딩지도사 1급", "군심리상담사 1급", "푸드아트심리상담사 1급", "애니어그램심리상담사 1급", "도형심리상담사 1급", "독서심리상담사 1급", "인지행동심리상담사 1급", "문학심리상담사 1급", "스포츠심리상담사 1급", "가족심리상담사 1급", "색채심리상담사 1급", "놀이심리상담사 1급", "노인미술심리상담사 1급", "노인음악심리상담사 1급", "노인심리상담사 1급", "청소년미술심리상담사 1급", "아동미술심리상담사 1급", "아동학대예방상담사 1급", "학교폭력예방상담사 1급", "다문화심리상담사 1급", "부부심리상담사 1급", "미술심리상담사 1급", "음악심리상담사 1급", "원예심리상담사 1급", "아동심리상담사 1급", "부모교육상담사 1급", "분노조절상담사 1급", "심리분석사 1급", "심리상담사 1급", "주차관리사 1급", "광고기획전문가 1급", "고객상담사 1급", "검색광고마케터 1급", "창업상권분석지도사 1급", "4대보험관리사 1급", "콜센터전문상담사 1급", "감정노동관리사 1급", "이미지메이킹지도사 1급", "프리젠테이션전문가 1급", "개인정보관리사 1급", "인사총무관리자 1급", "스피치지도사 1급", "골프전문캐디 1급", "결혼상담사 1급", "운동처방사 1급", "CS강사 1급", "스토리텔링그림책지도사 1급", "하브루타지도사 1급", "책놀이지도사 1급", "아동청소년스피치지도사 1급", "방과후영어교육지도사 1급", "방과후돌봄교실지도사 1급", "스토리텔링수학지도사 1급", "창의과학교육지도사 1급", "색종이접기지도사 1급", "레크리에이션지도사 1급", "방과후학교지도사 1급", "마술교육지도사 1급", "자원봉사지도사 1급", "영재놀이지도사 1급", "보드게임지도사 1급", "영어독서지도사 1급", "클레이아트 1급", "자기주도학습지도사 1급", "진로적성상담사 1급", "공부습관지도사 1급", "독서토론지도사 1급", "공부방지도사 1급", "독서지도사 1급", "진로직업상담사 1급", "독서논술지도사 1급", "DCS 1급", "항공서비스전문가 1급", "아동요리지도사 1급", "놀이교육지도사 1급", "아동교육지도사 1급", "동화구연지도사 1급", "아동독서지도사 1급", "아동미술지도사 1급", "손유희지도사 1급", "아동복지상담사 1급", "특수아동지도사 1급", "아동발달전문지도사 1급", "병원원무행정사 1급", "보험심사평가사 1급", "실버병원코디네이터 1급", "병원진료접수매니저 1급", "병원서비스매니저 1급", "병원행정관리사 1급", "병원코디네이터 1급", "요양병원관리사 1급", "노인두뇌훈련지도사 1급", "실버레크레이션지도자 1급", "실버건강지도사 1급", "노후설계지도사 1급", "노인교육지도사 1급", "학교보안안전지도사 1급", "안전교육지도사 1급", "재난안전지도사 1급", "한국어지도사 1급", "한국사지도사 1급", "인문학지도사 1급", "한자지도사 1급", "네트워크전문가 1급", "컴퓨터사무정보처리사 1급", "정보보안전문가 1급", "해킹보안전문가 1급", "소프트웨어교육지도사 1급", "스마트IT컴퓨터지도사 1급", "컴퓨터OA마스터 1급", "빅데이터전문가 1급", "건물관리사 1급", "부동산권리분석사 1급", "빌딩관리사 1급", "부동산분양상담전문가 1급", "부동산자산관리지도사 1급", "반려동물관리사 1급", "반려동물장례지도사 1급", "반려동물행동교정사 1급", "HRD전문가 1급", "전문비서 1급", "스마트폰활용지도사 1급", "전산회계 1급");
    ArrayAdapter arrayAdapter=null;
    String attend;
    Challenge challenge=null;
    DatabaseReference databaseReference;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.newchallenge);

        challenge_name=(TextView)findViewById(R.id.challenge_name);
        pointradio=(RadioGroup)findViewById(R.id.point_radiogroup);
        checked_point=(RadioButton)findViewById(pointradio.getCheckedRadioButtonId());
        day_spinner=(Spinner)findViewById(R.id.day_spinner);
        certificate_spinner=(Spinner)findViewById(R.id.certificate_spinner);
        radioGroup=(RadioGroup)findViewById(R.id.category_radiogroup);
        challenge_register=(Button)findViewById(R.id.challenge_register);
        challenge_cancel=(Button)findViewById(R.id.challenge_cancel);

        intent=getIntent();
        challenge_name.setText(intent.getStringExtra("name"));
        attend=intent.getStringExtra("attend");

        arrayAdapter=new ArrayAdapter(
                NewChallenge.this,
                android.R.layout.simple_dropdown_item_1line,
                gineonglist
        );
        certificate_spinner.setAdapter(arrayAdapter);
        certificate_spinner.setSelection(0);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch(checkedId){
                    case R.id.radio_gineong:
                        arrayAdapter=null;
                        arrayAdapter=new ArrayAdapter(
                                NewChallenge.this,
                                android.R.layout.simple_dropdown_item_1line,
                                gineonglist
                        );
                        certificate_spinner.setAdapter(arrayAdapter);
                        certificate_spinner.setSelection(0);
                        selectedcerti=certificate_spinner.getSelectedItem().toString();
                        return;
                    case R.id.radio_gisulsa:
                        arrayAdapter=null;
                        arrayAdapter=new ArrayAdapter(
                                NewChallenge.this,
                                android.R.layout.simple_dropdown_item_1line,
                                gisulsalist
                        );
                        certificate_spinner.setAdapter(arrayAdapter);
                        certificate_spinner.setSelection(0);
                        selectedcerti=certificate_spinner.getSelectedItem().toString();
                        return;
                    case R.id.radio_gisa:
                        arrayAdapter=null;
                        arrayAdapter=new ArrayAdapter(
                                NewChallenge.this,
                                android.R.layout.simple_dropdown_item_1line,
                                gisalist
                        );
                        certificate_spinner.setAdapter(arrayAdapter);
                        certificate_spinner.setSelection(0);
                        selectedcerti=certificate_spinner.getSelectedItem().toString();
                        return;
                    case R.id.radio_sanup:
                        arrayAdapter=null;
                        arrayAdapter=new ArrayAdapter(
                                NewChallenge.this,
                                android.R.layout.simple_dropdown_item_1line,
                                sanuplist
                        );
                        certificate_spinner.setAdapter(arrayAdapter);
                        certificate_spinner.setSelection(0);
                        selectedcerti=certificate_spinner.getSelectedItem().toString();
                        return;
                    case R.id.radio_guitar:
                        arrayAdapter=null;
                        arrayAdapter=new ArrayAdapter(
                                NewChallenge.this,
                                android.R.layout.simple_dropdown_item_1line,
                                guitarlist
                        );
                        certificate_spinner.setAdapter(arrayAdapter);
                        certificate_spinner.setSelection(0);
                        selectedcerti=certificate_spinner.getSelectedItem().toString();
                        return;
                    default:
                        return;
                }
            }
        });
        selectedday=day_spinner.getSelectedItem().toString();




        day_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedday=day_spinner.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        selectedcerti=certificate_spinner.getSelectedItem().toString();

        certificate_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedcerti=certificate_spinner.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        selectedValue="5000";
        pointradio.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch(checkedId){
                    case R.id.radio_5000:
                        selectedValue="5000";
                        return;
                    case R.id.radio_10000:
                        selectedValue="10000";
                        return;
                    case R.id.radio_15000:
                        selectedValue="15000";
                        return;
                    default:
                        return;
                }

            }
        });

        challenge_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        challenge_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedday=selectedday.replace("일", "");
                challenge=new Challenge();
                selectedcerti=selectedcerti.replace(" ", "-");
                challenge.setCertification(selectedcerti);
                challenge.setDay(selectedday);
                challenge.setPoint(selectedValue);
                challenge.setAttend(attend);
                SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
                Date time=new Date();
                String start=format.format(time);
                challenge.setStartdate(start);
                ArrayList<String> checked=new ArrayList<>();
                for(int i=0; i<Integer.parseInt(selectedday); i++){
                    checked.add("yet");
                }
                challenge.setDay_check(checked);
                databaseReference= FirebaseDatabase.getInstance().getReference("Challenge");
                String email= FirebaseAuth.getInstance().getCurrentUser().getEmail().toString();
                email=email.replace('.', '-');
                String title=selectedcerti+attend+selectedday+selectedValue;
                databaseReference.child(email).child(title).setValue(challenge);
                finish();
            }
        });
    }
}
