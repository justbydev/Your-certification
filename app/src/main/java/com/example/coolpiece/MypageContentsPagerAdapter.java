package com.example.coolpiece;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

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
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return pagecount;
    }
}
