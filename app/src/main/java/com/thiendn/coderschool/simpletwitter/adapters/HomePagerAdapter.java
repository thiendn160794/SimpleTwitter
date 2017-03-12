package com.thiendn.coderschool.simpletwitter.adapters;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.thiendn.coderschool.simpletwitter.fragments.HomePageFragment;
import com.thiendn.coderschool.simpletwitter.fragments.MentionPageFragment;

/**
 * Created by thiendn on 12/03/2017.
 */

public class HomePagerAdapter extends FragmentPagerAdapter {
    private Context mContext;
    private final static int mCount = 2;
    private String[] mTitles = new String[]{"HOME", "MENTION"};
    public HomePagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        mContext = context;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0: return HomePageFragment.newInstance();
            case 1: return MentionPageFragment.newInstance();
        }
        return HomePageFragment.newInstance();
    }

    @Override
    public int getCount() {
        return mCount;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles[position];
    }
}
