package com.example.coolpiece.mypage;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.coolpiece.R;
import com.example.coolpiece.mypage.tab_viewpager.MypageContentsPagerAdapter;
import com.google.android.material.tabs.TabLayout;

public class MypageActivity extends Fragment {
    TabLayout mypage_tab_layout;
    ViewPager mypage_pager_content;
    MypageContentsPagerAdapter mypageContentsPagerAdapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.mypage, container, false);

        mypage_pager_content=(ViewPager)v.findViewById(R.id.mypage_pager_content);
        mypage_tab_layout=(TabLayout)v.findViewById(R.id.mypage_tab_layout);
        mypage_tab_layout.addTab(mypage_tab_layout.newTab().setText("달력"));
        mypage_tab_layout.addTab(mypage_tab_layout.newTab().setText("내일정"));
        mypage_tab_layout.addTab(mypage_tab_layout.newTab().setText("명함"));
        mypage_tab_layout.addTab(mypage_tab_layout.newTab().setText("챌린지"));

        mypageContentsPagerAdapter=new MypageContentsPagerAdapter(getChildFragmentManager(), mypage_tab_layout.getTabCount());
        mypage_pager_content.setAdapter(mypageContentsPagerAdapter);

        mypage_pager_content.addOnPageChangeListener(
                new TabLayout.TabLayoutOnPageChangeListener(mypage_tab_layout)
        );

        mypage_tab_layout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                mypage_pager_content.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        return v;
    }
}
