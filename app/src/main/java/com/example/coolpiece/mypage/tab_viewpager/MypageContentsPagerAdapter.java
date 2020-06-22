package com.example.coolpiece.mypage.tab_viewpager;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.coolpiece.mypage.calendar.CalendarFragment;
import com.example.coolpiece.mypage.card.CardFragment;
import com.example.coolpiece.mypage.challenge.ChallengeFragment;
import com.example.coolpiece.mypage.schedule.MyscheduleFragment;
import com.example.coolpiece.mypage.authen.AuthenFragment;

public class MypageContentsPagerAdapter extends FragmentStatePagerAdapter {
    int pagecount;
    public MypageContentsPagerAdapter(@NonNull FragmentManager fm, int pagecount) {
        super(fm, pagecount);
        this.pagecount=pagecount;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch(position){
            case 0:
                CalendarFragment calendarFragment=new CalendarFragment();
                return calendarFragment;
            case 1:
                MyscheduleFragment myscheduleFragment=new MyscheduleFragment();
                return myscheduleFragment;
            case 2:
                CardFragment cardFragment=new CardFragment();
                return cardFragment;
            case 3:
                ChallengeFragment challengeFragment=new ChallengeFragment();
                return challengeFragment;
            case 4:
                AuthenFragment authenFragment = new AuthenFragment();
                return authenFragment;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return pagecount;
    }
}
