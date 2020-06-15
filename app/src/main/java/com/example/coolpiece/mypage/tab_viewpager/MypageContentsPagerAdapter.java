package com.example.coolpiece.mypage.tab_viewpager;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.coolpiece.mypage.calendar.CalendarFragment;
import com.example.coolpiece.mypage.challenge.ChallengeFragment;
import com.example.coolpiece.mypage.mycard.MycardFragment;
import com.example.coolpiece.mypage.schedule.MyscheduleFragment;

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
                MycardFragment mycardFragment=new MycardFragment();
                return mycardFragment;
            case 3:
                ChallengeFragment challengeFragment=new ChallengeFragment();
                return challengeFragment;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return pagecount;
    }
}
