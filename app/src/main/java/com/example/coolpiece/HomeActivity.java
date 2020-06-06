package com.example.coolpiece;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
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
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends Fragment {
    Button gineong;
    Button gisa;
    Button gisulsa;
    Button sanup;
    Button jido;
    Button guitar;
    TextView mylocation;
    TextView melocate;
    TextView location_search;
    EditText searchedittext;
    RecyclerView search_recycler;
    Button searchbutton;
    private Intent intent;
    public static HomeActivity homecontext;
    String arg1=null, arg2=null, arg3=null;

    private SearchAdapter searchAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private List<Search> list;
    private ArrayList<Search> searcharray;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.activity_main, container, false);

        homecontext=this;

        mylocation=(TextView) v.findViewById(R.id.mylocation);
        melocate=(TextView)v.findViewById(R.id.melocate);
        location_search=(TextView)v.findViewById(R.id.location_search);
        search_recycler=(RecyclerView)v.findViewById(R.id.search_recycler);
        searchbutton=(Button)v.findViewById(R.id.searchbutton);

        gineong=(Button)v.findViewById(R.id.gineong);
        gisa=(Button)v.findViewById(R.id.gisa);
        gisulsa=(Button)v.findViewById(R.id.gisulsa);
        sanup=(Button)v.findViewById(R.id.sanup);
        jido=(Button)v.findViewById(R.id.jido);
        guitar=(Button)v.findViewById(R.id.guitar);

        searchedittext=(EditText)v.findViewById(R.id.searchedittext);

        //locate.setOnClickListener(buttonClickListener);
        mylocation.setOnClickListener(buttonClickListener);
        location_search.setOnClickListener(buttonClickListener);
        searchbutton.setOnClickListener(buttonClickListener);

        gineong.setOnClickListener(buttonClickListener);
        gisa.setOnClickListener(buttonClickListener);
        gisulsa.setOnClickListener(buttonClickListener);
        sanup.setOnClickListener(buttonClickListener);
        jido.setOnClickListener(buttonClickListener);
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
                // case R.id.locate:
                case R.id.mylocation:
                case R.id.location_search:
                    intent=new Intent(getActivity(), DaumLocationActivity.class);
                    startActivity(intent);
                    return;
                case R.id.melocate:
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
                                ArrayList<ArrayList<Gisul>> gisul_total=ManageGisulData.getInstance().getGisul_total();
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
                                ArrayList<ArrayList<Gineongsa>> gineong_total=ManageGineongData.getInstance().getGineong_total();
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
                        }
                    }
                    if(flag==0){
                        Toast.makeText(getContext(), "자격증이 검색되지 않습니다.", Toast.LENGTH_SHORT).show();
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
                case R.id.jido:
                    intent=new Intent(getActivity(), Bigcategory.class);
                    intent.putExtra("CA", "지도사");
                    startActivity(intent);
                    return;
                case R.id.guitar:
                    intent=new Intent(getActivity(), Bigcategory.class);
                    intent.putExtra("CA", "기타/취미");
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
        list.add(new Search("가스기술사", 0));
        list.add(new Search("건설안전기술사", 0));
        list.add(new Search("가스기능사", 1));
        list.add(new Search("침투비파괴검사기능사", 1));
    }
}
