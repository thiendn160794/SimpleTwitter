package com.thiendn.coderschool.simpletwitter.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.thiendn.coderschool.simpletwitter.fragments.FavoritedTweetFromProfile;
import com.thiendn.coderschool.simpletwitter.fragments.ImageFromProfile;
import com.thiendn.coderschool.simpletwitter.fragments.TweetFromProfile;
import com.thiendn.coderschool.simpletwitter.model.User;

/**
 * Created by thiendn on 12/03/2017.
 */

public class ProfilePagerAdapter extends FragmentPagerAdapter {
    private final int mCount = 3;
    private User mUser;
    private String[] mTitles = new String[]{"TWEETS", "PHOTOS", "FAVORITES"};

    public ProfilePagerAdapter(FragmentManager fm, User user) {
        super(fm);
        mUser = user;
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0)
            return TweetFromProfile.newInstance(mUser);
        if (position == 1)
            return ImageFromProfile.newInstance(mUser);
        if (position == 2)
            return FavoritedTweetFromProfile.newInstance(mUser);
        return null;
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
