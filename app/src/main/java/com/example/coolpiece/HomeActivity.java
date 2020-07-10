package com.example.coolpiece;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.coolpiece.home.button.Bigcategory_guitar;
import com.example.coolpiece.home.button.certification.EachCertificate;
import com.example.coolpiece.home.location.GpsTracker;
import com.example.coolpiece.splash.dataclass.Academy;
import com.example.coolpiece.splash.dataclass.Gisa;
import com.example.coolpiece.splash.dataclass.Guitar;
import com.example.coolpiece.splash.dataclass.Sanup;
import com.example.coolpiece.splash.manageclass.ManageAcademyData;
import com.example.coolpiece.splash.manageclass.ManageGineongData;
import com.example.coolpiece.splash.manageclass.ManageGisaData;
import com.example.coolpiece.splash.manageclass.ManageGisulData;
import com.example.coolpiece.home.button.Bigcategory;
import com.example.coolpiece.home.AcademyGridAdapter;
import com.example.coolpiece.home.search.SearchAdapter;
import com.example.coolpiece.splash.dataclass.Gineongsa;
import com.example.coolpiece.splash.dataclass.Gisul;
import com.example.coolpiece.home.search.Search;
import com.example.coolpiece.splash.manageclass.ManageGuitarData;
import com.example.coolpiece.splash.manageclass.ManageSanupData;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public class HomeActivity extends Fragment {
    Button gineong;
    Button gisa;
    Button gisulsa;
    Button sanup;
    Button guitar;
    TextView mylocation;
    TextView melocate;
    EditText searchedittext;
    RecyclerView search_recycler;
    Button searchbutton;
    RecyclerView grid_recyclerview;
    private Intent intent;
    public static HomeActivity homecontext;
    String arg1=null, arg2=null, arg3=null;
    String nowlocation=null;
    private GpsTracker gpsTracker;
    private double latitude;
    private double longitude;

    private SearchAdapter searchAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private List<Search> list;
    private ArrayList<Search> searcharray;

    private AcademyGridAdapter academyGridAdapter;
    private RecyclerView.LayoutManager layoutManager2;
    private ArrayList<String> academy_name;

    ArrayList<String> gineonglist;
    ArrayList<String> gisulsalist;
    ArrayList<String> gisalist;
    ArrayList<String> sanuplist;
    ArrayList<String> guitarlist;
    ArrayList<Academy> academy_total;
    //List<String> gineonglist= Arrays.asList("가구제작기능사", "가스기능사", "거푸집기능사", "건설기계정비기능사", "건설재료시험기능사", "건축도장기능사", "건축목공기능사", "공유압기능사", "공조냉동기계기능사", "광고도장기능사", "광학기능사", "굴삭기운전기능사", "궤도장비정비기능사", "귀금속가공기능사", "금속도장기능사", "금속재료시험기능사", "금속재창호기능사", "금형기능사", "기계가공조립기능사", "기계정비기능사", "기중기운전기능사", "농기계운전기능사", "농기계정비기능사", "도배기능사", "도자기공예기능사", "도화기능사", "동력기계정비기능사", "로더운전기능사", "롤러운전기능사", "모터그레이더운전기능사", "목공예기능사", "미용사(네일)", "미용사(메이크업)", "미용사(일반)", "미용사(피부)", "미장기능사", "반도체장비유지보수기능사", "방사선비파괴검사기능사", "방수기능사", "배관기능사", "버섯종균기능사", "보석가공기능사", "보석감정사", "복어조리기능사", "불도저운전기능사", "비계기능사", "사진기능사", "산림기능사", "생산자동화기능사", "석공기능사", "석공예기능사", "선체건조기능사", "설비보전기능사", "세탁기능사", "수산양식기능사", "승강기기능사", "식육처리기능사", "식품가공기능사", "신발류제조기능사", "신재생에너지발전설비기능사(태양광)", "실내건축기능사", "아스팔트피니셔운전기능사", "압연기능사", "양복기능사", "양식조리기능사", "양장기능사", "양화장치운전기능사", "에너지관리기능사", "연삭기능사", "열처리기능사", "염색기능사(날염)", "염색기능사(침염)", "온수온돌기능사", "용접기능사", "원예기능사", "원형기능사", "웹디자인기능사", "위험물기능사", "유기농업기능사", "유리시공기능사", "의료전자기능사", "이용사", "인쇄기능사", "일식조리기능사", "임산가공기능사", "임업종묘기능사", "자기비파괴검사기능사", "자동차보수도장기능사", "자동차정비기능사", "자동차차체수리기능사", "잠수기능사", "전기기능사", "전산응용건축제도기능사", "전산응용기계제도기능사", "전산응용조선제도기능사", "전산응용토목제도기능사", "전자계산기기능사", "전자기기기능사", "전자부품장착(SMT)기능사", "전자출판기능사", "전자캐드기능사", "정밀측정기능사", "정보기기운용기능사", "정보처리기능사", "제강기능사", "제과기능사", "제빵기능사", "제선기능사", "제품응용모델링기능사", "조경기능사", "조적기능사", "조주기능사", "종자기능사", "주조기능사", "중식조리기능사", "지게차운전기능사", "지도제작기능사", "지적기능사", "천공기운전기능사", "천장크레인운전기능사", "철근기능사", "철도전기신호기능사", "철도차량정비기능사", "철도토목기능사", "초음파비파괴검사기능사", "축로기능사", "축산기능사", "측량기능사", "침투비파괴검사기능사", "컨테이너크레인운전기능사", "컴퓨터그래픽스운용기능사", "컴퓨터응용밀링기능사", "컴퓨터응용선반기능사", "콘크리트기능사", "타워크레인운전기능사", "타일기능사", "특수용접기능사", "판금제관기능사", "표면처리기능사", "플라스틱창호기능사", "피아노조율기능사", "한복기능사", "한식조리기능사", "항공기관정비기능사", "항공기체정비기능사", "항공사진기능사", "항공장비정비기능사", "항공전자정비기능사", "항로표지기능사", "화약취급기능사", "화학분석기능사", "화훼장식기능사", "환경기능사", "3D프린터운용기능사", "떡제조기능사");
    //List<String> gisulsalist=Arrays.asList("가스기술사", "건설기계기술사", "건설안전기술사", "건축구조기술사", "건축기계설비기술사", "건축시공기술사", "건축전기설비기술사", "건축품질시험기술사", "공장관리기술사", "공조냉동기계기술사", "교통기술사", "금속가공기술사", "금속재료기술사", "금속제련기술사", "금형기술사", "기계기술사", "기계안전기술사", "기상예보기술사", "농어업토목기술사", "농화학기술사", "대기관리기술사", "도로및공항기술사", "도시계획기술사", "발송배전기술사", "비파괴검사기술사", "산림기술사", "산업계측제어기술사", "산업기계설비기술사", "산업위생관리기술사", "상하수도기술사", "섬유기술사", "세라믹기술사", "소방기술사", "소음진동기술사", "수산양식기술사", "수산제조기술사", "수자원개발기술사", "수질관리기술사", "시설원예기술사", "식품기술사", "어로기술사", "용접기술사", "의류기술사", "인간공학기술사", "자연환경관리기술사", "전기안전기술사", "전기응용기술사", "전기철도기술사", "전자응용기술사", "정보관리기술사", "제품디자인기술사", "조경기술사", "조선기술사", "종자기술사", "지적기술사", "지질및지반기술사", "차량기술사", "철도기술사", "철도신호기술사", "철도차량기술사", "축산기술사", "측량및지형공간정보기술사", "컴퓨터시스템응용기술사", "토목구조기술사", "토목시공기술사", "토목품질시험기술사", "토양환경기술사", "토질및기초기술사", "폐기물처리기술사", "포장기술사", "표면처리기술사", "품질관리기술사", "항공기관기술사", "항공기체기술사", "항만및해안기술사", "해양기술사", "화공기술사", "화공안전기술사", "화약류관리기술사");
    //List<String> gisalist=Arrays.asList("가스기사", "건설기계설비기사", "건설기계정비기사", "건설안전기사", "건설재료시험기사", "건축기사", "건축설비기사", "공조냉동기계기사", "광학기사", "교통기사", "궤도장비정비기사", "그린전동자동차기사", "금속재료기사", "기계설계기사", "기상감정기사", "기상기사", "농업기계기사", "누설비파괴검사기사", "대기환경기사", "도시계획기사", "메카트로닉스기사", "반도체설계기사", "방사선비파괴검사기사", "사출금형설계기사", "산림기사", "산업안전기사", "산업위생관리기사", "생물공학기사", "생물분류기사(동물)", "생물분류기사(식물)", "설비보전기사", "섬유기사", "소방설비기사(기계분야)", "소방설비기사(전기분야)", "소음진동기사", "수산양식기사", "수산제조기사", "수질환경기사", "승강기기사", "시각디자인기사", "시설원예기사", "식물보호기사", "식품기사", "신재생에너지발전설비기사(태양광)", "실내건축기사", "어업생산관리기사", "에너지관리기사", "온실가스관리기사", "와전류비파괴검사기사", "용접기사", "유기농업기사", "응용지질기사", "의공기사", "의류기사", "인간공학기사", "인쇄기사", "일반기계기사", "임베디드기사", "임산가공기사", "임업종묘기사", "자기비파괴검사기사", "자동차정비기사", "자연생태복원기사", "전기공사기사", "전기기사", "전기철도기사", "전자계산기기사", "전자계산기조직응용기사", "전자기사", "정보처리기사", "제품디자인기사", "조경기사", "조선기사", "종자기사", "지적기사", "철도신호기사", "철도차량기사", "철도토목기사", "초음파비파괴검사기사", "축산기사", "측량및지형공간정보기사", "침투비파괴검사기사", "컬러리스트기사", "콘크리트기사", "토목기사", "토양환경기사", "폐기물처리기사", "포장기사", "품질경영기사", "프레스금형설계기사", "항공기사", "항로표지기사", "해양공학기사", "해양자원개발기사", "해양환경기사", "화공기사", "화약류관리기사", "화약류제조기사", "화재감식평가기사", "화학분석기사", "화훼장식기사", "식육가공기사", "농작업안전보건기사", "로봇기구개발기사", "로봇소프트웨어개발기사", "로봇하드웨어개발기사", "방재기사", "환경위해관리기사");
    //List<String> sanuplist=Arrays.asList("가스산업기사", "건설기계설비산업기사", "건설기계정비산업기사", "건설안전산업기사", "건설재료시험산업기사", "건축목공산업기사", "건축산업기사", "건축설비산업기사", "건축일반시공산업기사", "공조냉동기계산업기사", "광학기기산업기사", "교통산업기사", "국제의료관광코디네이터", "궤도장비정비산업기사", "귀금속가공산업기사", "금속재료산업기사", "기계가공조립산업기사", "기계설계산업기사", "기계정비산업기사", "농림토양평가관리산업기사", "농업기계산업기사", "대기환경산업기사", "반도체설계산업기사", "방사선비파괴검사산업기사", "방수산업기사", "배관산업기사", "사무자동화산업기사", "사출금형산업기사", "산림산업기사", "산업안전산업기사", "산업위생관리산업기사", "생산자동화산업기사", "섬유디자인산업기사", "섬유산업기사", "소방설비산업기사(기계분야)", "소방설비산업기사(전기분야)", "소음진동산업기사", "수산양식산업기사", "수질환경산업기사", "승강기산업기사", "시각디자인산업기사", "식물보호산업기사", "식품산업기사", "신재생에너지발전설비산업기사(태양광)", "실내건축산업기사", "어로산업기사", "에너지관리산업기사", "온실가스관리산업기사", "용접산업기사", "위험물산업기사", "유기농업산업기사", "의공산업기사", "인쇄산업기사", "임산가공산업기사", "자기비파괴검사산업기사", "자동차정비산업기사", "자연생태복원산업기사", "잠수산업기사", "재료조직평가산업기사", "전기공사산업기사", "전기산업기사", "전기철도산업기사", "전자계산기제어산업기사", "전자부품장착(SMT)산업기사", "전자산업기사", "정밀측정산업기사", "정보처리산업기사", "제품디자인산업기사", "조경산업기사", "조리산업기사(복어)", "조리산업기사(양식)", "조리산업기사(일식)", "조리산업기사(중식)", "조리산업기사(한식)", "조선산업기사", "종자산업기사", "주조산업기사", "지적산업기사", "철도신호산업기사", "철도운송산업기사", "철도차량산업기사", "철도토목산업기사", "초음파비파괴검사산업기사", "축산산업기사", "측량및지형공간정보산업기사", "치공구설계산업기사", "침투비파괴검사산업기사", "컬러리스트산업기사", "컴퓨터응용가공산업기사", "콘크리트산업기사", "토목산업기사", "판금제관산업기사", "패션디자인산업기사", "패션머천다이징산업기사", "폐기물처리산업기사", "포장산업기사", "표면처리산업기사", "품질경영산업기사", "프레스금형산업기사", "피아노조율산업기사", "한복산업기사", "항공산업기사", "항로표지산업기사", "해양조사산업기사", "화약류관리산업기사", "화약류제조산업기사", "화재감식평가산업기사", "3D프린터개발산업기사", "가구제작산업기사", "바이오화학제품제조산업기사", "버섯산업기사", "보석감정산업기사", "보석디자인산업기사", "화훼장식산업기사");
    //List<String> guitarlist=Arrays.asList("예절교육지도사 1급", "장애인인식개선지도사 1급", "급퍼스널컬러컨설턴트 1급", "플로리스트전문가 1급", "캘리그라피지도사 1급", "SNS마케팅전문가 1급", "영어요리지도사 1급", "정리수납전문가 1급", "쇼핑몰관리사 1급", "리더십지도사 1급", "가정관리사 1급", "커피바리스타전문가 1급", "커피핸드드립전문가 1급", "커피로스팅마스터 1급", "커피품질평가사 1급", "와인소믈리에 1급", "티소믈리에 1급", "품질검사마스터 1급", "3D프린팅지도사 1급", "6시그마(GB) 1급", "코딩지도사 1급", "군심리상담사 1급", "푸드아트심리상담사 1급", "애니어그램심리상담사 1급", "도형심리상담사 1급", "독서심리상담사 1급", "인지행동심리상담사 1급", "문학심리상담사 1급", "스포츠심리상담사 1급", "가족심리상담사 1급", "색채심리상담사 1급", "놀이심리상담사 1급", "노인미술심리상담사 1급", "노인음악심리상담사 1급", "노인심리상담사 1급", "청소년미술심리상담사 1급", "아동미술심리상담사 1급", "아동학대예방상담사 1급", "학교폭력예방상담사 1급", "다문화심리상담사 1급", "부부심리상담사 1급", "미술심리상담사 1급", "음악심리상담사 1급", "원예심리상담사 1급", "아동심리상담사 1급", "부모교육상담사 1급", "분노조절상담사 1급", "심리분석사 1급", "심리상담사 1급", "주차관리사 1급", "광고기획전문가 1급", "고객상담사 1급", "검색광고마케터 1급", "창업상권분석지도사 1급", "4대보험관리사 1급", "콜센터전문상담사 1급", "감정노동관리사 1급", "이미지메이킹지도사 1급", "프리젠테이션전문가 1급", "개인정보관리사 1급", "인사총무관리자 1급", "스피치지도사 1급", "골프전문캐디 1급", "결혼상담사 1급", "운동처방사 1급", "CS강사 1급", "스토리텔링그림책지도사 1급", "하브루타지도사 1급", "책놀이지도사 1급", "아동청소년스피치지도사 1급", "방과후영어교육지도사 1급", "방과후돌봄교실지도사 1급", "스토리텔링수학지도사 1급", "창의과학교육지도사 1급", "색종이접기지도사 1급", "레크리에이션지도사 1급", "방과후학교지도사 1급", "마술교육지도사 1급", "자원봉사지도사 1급", "영재놀이지도사 1급", "보드게임지도사 1급", "영어독서지도사 1급", "클레이아트 1급", "자기주도학습지도사 1급", "진로적성상담사 1급", "공부습관지도사 1급", "독서토론지도사 1급", "공부방지도사 1급", "독서지도사 1급", "진로직업상담사 1급", "독서논술지도사 1급", "DCS 1급", "항공서비스전문가 1급", "아동요리지도사 1급", "놀이교육지도사 1급", "아동교육지도사 1급", "동화구연지도사 1급", "아동독서지도사 1급", "아동미술지도사 1급", "손유희지도사 1급", "아동복지상담사 1급", "특수아동지도사 1급", "아동발달전문지도사 1급", "병원원무행정사 1급", "보험심사평가사 1급", "실버병원코디네이터 1급", "병원진료접수매니저 1급", "병원서비스매니저 1급", "병원행정관리사 1급", "병원코디네이터 1급", "요양병원관리사 1급", "노인두뇌훈련지도사 1급", "실버레크레이션지도자 1급", "실버건강지도사 1급", "노후설계지도사 1급", "노인교육지도사 1급", "학교보안안전지도사 1급", "안전교육지도사 1급", "재난안전지도사 1급", "한국어지도사 1급", "한국사지도사 1급", "인문학지도사 1급", "한자지도사 1급", "네트워크전문가 1급", "컴퓨터사무정보처리사 1급", "정보보안전문가 1급", "해킹보안전문가 1급", "소프트웨어교육지도사 1급", "스마트IT컴퓨터지도사 1급", "컴퓨터OA마스터 1급", "빅데이터전문가 1급", "건물관리사 1급", "부동산권리분석사 1급", "빌딩관리사 1급", "부동산분양상담전문가 1급", "부동산자산관리지도사 1급", "반려동물관리사 1급", "반려동물장례지도사 1급", "반려동물행동교정사 1급", "HRD전문가 1급", "전문비서 1급", "스마트폰활용지도사 1급", "전산회계 1급");
    int locationFirstcheck;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.activity_main, container, false);

        homecontext=this;

        mylocation=(TextView) v.findViewById(R.id.mylocation);
        melocate=(TextView)v.findViewById(R.id.melocate);
        search_recycler=(RecyclerView)v.findViewById(R.id.search_recycler);
        searchbutton=(Button)v.findViewById(R.id.searchbutton);

        gineong=(Button)v.findViewById(R.id.gineong);
        gisa=(Button)v.findViewById(R.id.gisa);
        gisulsa=(Button)v.findViewById(R.id.gisulsa);
        sanup=(Button)v.findViewById(R.id.sanup);
        guitar=(Button)v.findViewById(R.id.guitar);

        searchedittext=(EditText)v.findViewById(R.id.searchedittext);

        grid_recyclerview=(RecyclerView)v.findViewById(R.id.grid_recyclerview);

        //locate.setOnClickListener(buttonClickListener);
        melocate.setOnClickListener(buttonClickListener);
        searchbutton.setOnClickListener(buttonClickListener);

        gineong.setOnClickListener(buttonClickListener);
        gisa.setOnClickListener(buttonClickListener);
        gisulsa.setOnClickListener(buttonClickListener);
        sanup.setOnClickListener(buttonClickListener);
        guitar.setOnClickListener(buttonClickListener);

        search_recycler.setHasFixedSize(true);
        layoutManager=new LinearLayoutManager(getContext());
        search_recycler.setLayoutManager(layoutManager);

        list=new ArrayList<>();

        settingList();

        searcharray=new ArrayList<>();
        searcharray.addAll(list);

        searchAdapter=new SearchAdapter(list);
        search_recycler.setAdapter(searchAdapter);

        list.clear();
        searchAdapter.notifyDataSetChanged();

        grid_recyclerview.setHasFixedSize(true);
        layoutManager2=new GridLayoutManager(getContext(), 3);
        grid_recyclerview.setLayoutManager(layoutManager2);

        academy_name=new ArrayList<>();

        academy_total=new ArrayList<>();
        academy_total= ManageAcademyData.getInstance().getAcademy_total();
        for(int i=0; i<academy_total.size(); i++){
            if(academy_total.get(i).getConnect()==1){
                academy_name.add(academy_total.get(i).getAcademy_name());
            }
        }

        academyGridAdapter=new AcademyGridAdapter(academy_name);
        grid_recyclerview.setAdapter(academyGridAdapter);


        searchedittext.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String text=searchedittext.getText().toString();
                search(text);
            }
        });

        return v;
    }
    /****butonclicklistener for big category like gisulsa, gineongsa, etc****/
    private View.OnClickListener buttonClickListener=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            int id=v.getId();
            switch(id){
                case R.id.melocate:
                    getlocationpermission();
                    return;
                case R.id.searchbutton:
                    String name=searchedittext.getText().toString();
                    int flag=0;
                    for(int i=0; i<searcharray.size(); i++){
                        if(name.equals(searcharray.get(i).getName())){
                            Intent intent=new Intent(getActivity(), EachCertificate.class);
                            int type=searcharray.get(i).getType();
                            if(type==0){
                                intent.putExtra("cat", "기술사");
                                ArrayList<ArrayList<Gisul>> gisul_total= ManageGisulData.getInstance().getGisul_total();
                                for(int j=0; j<gisul_total.size(); j++){
                                    ArrayList<Gisul> gisul=gisul_total.get(j);
                                    for(int k=0; k<gisul.size(); k++){
                                        if(name.equals(gisul.get(k).getName())){
                                            flag=1;
                                            intent.putExtra("certification", gisul.get(k));
                                            startActivity(intent);
                                        }
                                    }
                                }
                            }
                            else if(type==1){
                                intent.putExtra("cat", "기능사");
                                ArrayList<ArrayList<Gineongsa>> gineong_total= ManageGineongData.getInstance().getGineong_total();
                                for(int j=0; j<gineong_total.size(); j++){
                                    ArrayList<Gineongsa> gineongsa=gineong_total.get(j);
                                    for(int k=0; k<gineongsa.size(); k++){
                                        if(name.equals(gineongsa.get(k).getName())){
                                            flag=1;
                                            intent.putExtra("certification", gineongsa.get(k));
                                            startActivity(intent);
                                        }
                                    }
                                }
                            }
                            else if(type==2){
                                intent.putExtra("cat", "기사");
                                ArrayList<ArrayList<Gisa>> gisa_total= ManageGisaData.getInstance().getGisa_total();
                                for(int j=0; j<gisa_total.size(); j++){
                                    ArrayList<Gisa> gisa=gisa_total.get(j);
                                    for(int k=0; k<gisa.size(); k++){
                                        if(name.equals(gisa.get(k).getName())){
                                            flag=1;
                                            intent.putExtra("certification", gisa.get(k));
                                            startActivity(intent);
                                        }
                                    }
                                }
                            }
                            else if(type==3){
                                intent.putExtra("cat", "산업기사");
                                ArrayList<ArrayList<Sanup>> sanup_total= ManageSanupData.getInstance().getSanup_total();
                                for(int j=0; j<sanup_total.size(); j++){
                                    ArrayList<Sanup> sanup=sanup_total.get(j);
                                    for(int k=0; k<sanup.size(); k++){
                                        if(name.equals(sanup.get(k).getName())){
                                            flag=1;
                                            intent.putExtra("certification", sanup.get(k));
                                            startActivity(intent);
                                        }
                                    }
                                }
                            }
                            else if(type==4){
                                intent.putExtra("cat", "지도사/기타/취미");
                                ArrayList<ArrayList<Guitar>> guitar_total= ManageGuitarData.getInstance().getGuitar_total();
                                for(int j=0; j<guitar_total.size(); j++){
                                    ArrayList<Guitar> guitar=guitar_total.get(j);
                                    for(int k=0; k<guitar.size(); k++){
                                        if(name.equals(guitar.get(k).getName())){
                                            flag=1;
                                            intent.putExtra("certification", guitar.get(k));
                                            startActivity(intent);
                                        }
                                    }
                                }
                            }
                        }
                    }
                    if(flag==0){
                        Toast.makeText(getContext(), "자격증에 대한 정보가 없습니다.", Toast.LENGTH_SHORT).show();
                    }
                    return;
                case R.id.gineong:
                    intent=new Intent(getActivity(), Bigcategory.class);
                    intent.putExtra("CA", "기능사");
                    startActivity(intent);
                    return;
                case R.id.gisa:
                    intent=new Intent(getActivity(), Bigcategory.class);
                    intent.putExtra("CA", "기사");
                    startActivity(intent);
                    return;
                case R.id.gisulsa:
                    intent=new Intent(getActivity(), Bigcategory.class);
                    intent.putExtra("CA", "기술사");
                    startActivity(intent);
                    return;
                case R.id.sanup:
                    intent=new Intent(getActivity(), Bigcategory.class);
                    intent.putExtra("CA", "산업기사");
                    startActivity(intent);
                    return;
                case R.id.guitar:
                    intent=new Intent(getActivity(), Bigcategory_guitar.class);
                    intent.putExtra("CA", "지도사/기타/취미");
                    startActivity(intent);
                    return;
                default:
                    return;
            }
        }
    };

    /*public void closeApplication(){
        new AlertDialog.Builder(this)
                .setTitle("쿨피스")
                .setMessage("앱을 종료하시겠습니까?")
                .setNegativeButton("아니요", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ;
                    }})
                .setPositiveButton("네", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        moveTaskToBack(true);
                        finish();
                        android.os.Process.killProcess(android.os.Process.myPid());
                    }})

                .show();
    }*/

    /***filtering for search***/
    public void search(String text){
        list.clear();
        if(text.length()==0){
            list.clear();
        }
        else{
            for(int i=0; i<searcharray.size(); i++){
                if(searcharray.get(i).getName().toLowerCase().contains(text)){
                    list.add(searcharray.get(i));
                }
            }
        }
        searchAdapter.notifyDataSetChanged();
    }
    private void settingList(){
        gisulsalist=ManageGisulData.getInstance().getGisullist();
        for(int i=0; i<gisulsalist.size(); i++){
            list.add(new Search(gisulsalist.get(i), 0));
        }
        gineonglist=ManageGineongData.getInstance().getGineonglist();
        for(int i=0; i<gineonglist.size(); i++){
            list.add(new Search(gineonglist.get(i), 1));
        }
        gisalist=ManageGisaData.getInstance().getGisalist();
        for(int i=0; i<gisalist.size(); i++){
            list.add(new Search(gisalist.get(i), 2));
        }
        sanuplist=ManageSanupData.getInstance().getSanuplist();
        for(int i=0; i<sanuplist.size(); i++){
            list.add(new Search(sanuplist.get(i), 3));
        }
        guitarlist=ManageGuitarData.getInstance().getGuitarlist();
        for(int i=0; i<guitarlist.size(); i++){
            list.add(new Search(guitarlist.get(i), 4));
        }
    }

    public void getlocationpermission(){
        if(Build.VERSION.SDK_INT<23){
            return;
        }
        if(!checkLocationServicesStatus()){//핸드폰 자체의 위치 설정 허가를 위한 것
            showDialogForLocationServiceSetting();
        }
        else{//핸드폰 자체의 위치 정보 사용이 허가되었으면 앱의 위치 권한 허가를 위한 것
            int permissioncoarse=ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION);
            int permissionfine=ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION);
            if(permissioncoarse==PackageManager.PERMISSION_DENIED||permissionfine==PackageManager.PERMISSION_DENIED){
                requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION,
                                Manifest.permission.ACCESS_COARSE_LOCATION},
                        1001);
            }
            else{
                nowlocation=getnowaddress();
                mylocation.setText(nowlocation);
            }
        }

    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch(requestCode){
            case 1001:
                int cnt=0;
                for(int i=0; i<permissions.length; i++){
                    String permission=permissions[i];
                    int grantResult=grantResults[i];
                    if(permission.equals(Manifest.permission.ACCESS_COARSE_LOCATION)){
                        if(grantResult==PackageManager.PERMISSION_GRANTED){
                            cnt+=1;
                        }
                    }
                    if (permission.equals(Manifest.permission.ACCESS_FINE_LOCATION)) {
                        if(grantResult==PackageManager.PERMISSION_GRANTED){
                            cnt+=1;
                        }
                    }
                }
                if(cnt==2){
                    Toast.makeText(getContext(), "위치 권한이 설정되었습니다", Toast.LENGTH_SHORT).show();
                }
                else{

                    android.app.AlertDialog.Builder builder=new AlertDialog.Builder(getContext());
                    builder.setTitle("알림");
                    builder.setMessage("[설정]->[권한]에서\n권한을 허용해주세요.\n");
                    builder.setNegativeButton("확인", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });
                    AlertDialog alertDialog=builder.create();
                    alertDialog.show();
                }
                return;
            default:
                return;
        }

    }

    public String getnowaddress(){
        gpsTracker=new GpsTracker(getContext());
        latitude=gpsTracker.getLatitude();
        longitude=gpsTracker.getLongitude();

        Geocoder geocoder=new Geocoder(getContext(), Locale.KOREAN);
        List<Address> addresses;

        try{
            addresses=geocoder.getFromLocation(latitude, longitude, 1);
        }catch(IOException ioException){
            Toast.makeText(getContext(), "지오코더 서비스 사용불가", Toast.LENGTH_SHORT).show();

            return "지오코더 서비스 사용불가";
        }

        if(addresses==null||addresses.size()==0){

            Toast.makeText(getContext(), "주소를 발견하지 못했습니다", Toast.LENGTH_SHORT).show();

            return "내 위치를 다시 눌러주세요";
        }
        Address address=addresses.get(0);
        return address.getAddressLine(0).toString();

    }

    private void showDialogForLocationServiceSetting() {

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("위치 서비스 비활성화");
        builder.setMessage("앱을 사용하기 위해서는 위치 서비스가 필요합니다.\n"
                + "위치 설정을 수정하시겠습니까?");
        builder.setCancelable(true);
        builder.setPositiveButton("설정", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                Intent callGPSSettingIntent
                        = new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                startActivityForResult(callGPSSettingIntent, 2001);
            }
        });
        builder.setNegativeButton("취소", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();
            }
        });
        builder.create().show();
    }

    public boolean checkLocationServicesStatus() {
        LocationManager locationManager = (LocationManager) getContext().getSystemService(Context.LOCATION_SERVICE);

        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
                || locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
    }
}
