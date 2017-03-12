package com.thiendn.coderschool.simpletwitter.activity;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.thiendn.coderschool.simpletwitter.R;
import com.thiendn.coderschool.simpletwitter.adapters.HomePagerAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by thiendn on 12/03/2017.
 */

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.tabLayoutHome)
    TabLayout tabLayout;
    @BindView(R.id.viewPagerHome)
    ViewPager viewPager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        HomePagerAdapter adapter = new HomePagerAdapter(getSupportFragmentManager(), MainActivity.this);
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
    }
}
