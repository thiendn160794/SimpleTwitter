package com.thiendn.coderschool.simpletwitter.activity;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.thiendn.coderschool.simpletwitter.R;
import com.thiendn.coderschool.simpletwitter.adapters.ProfilePagerAdapter;
import com.thiendn.coderschool.simpletwitter.adapters.TimeLineAdapter;
import com.thiendn.coderschool.simpletwitter.application.RestApplication;
import com.thiendn.coderschool.simpletwitter.model.User;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by thiendn on 12/03/2017.
 */

public class ProfileActivity extends AppCompatActivity{
    public static final String USER = "USER";

    @BindView(R.id.ProfileTabLayout)
    TabLayout tabLayout;
    @BindView(R.id.ProfileViewPager)
    ViewPager viewPager;
    @BindView(R.id.ivCover)
    ImageView ivCover;
    @BindView(R.id.ivAvatar)
    ImageView ivAvatar;
    @BindView(R.id.tvUsername)
    TextView tvUsername;
    @BindView(R.id.tvScreenName)
    TextView tvScreenname;
    @BindView(R.id.tvDescription)
    TextView tvDescription;
    @BindView(R.id.tvNumberOfFollowing)
    TextView tvNumberOfFollowing;
    @BindView(R.id.tvNumberOfFollower)
    TextView tvNumberOfFollower;
    @BindView(R.id.btnFollow)
    Button btnFollow;

    private User mUser;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_profile);
        ButterKnife.bind(this);
        mUser = getIntent().getParcelableExtra(USER);
        Toast.makeText(getBaseContext(), mUser.getScreenName(), Toast.LENGTH_LONG).show();
        ProfilePagerAdapter adapter = new ProfilePagerAdapter(getSupportFragmentManager(), mUser);
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
        initView();
    }

    private void initView(){
        Glide.with(getBaseContext())
                .load(mUser.getImageCover())
                .into(ivCover);
        Glide.with(getBaseContext())
                .load(mUser.getImageProfile())
                .into(ivAvatar);
        tvUsername.setText(mUser.getName());
        tvScreenname.setText("@" + mUser.getScreenName());
        tvDescription.setText(mUser.getDescription());
        tvNumberOfFollower.setText(mUser.getNumberOfFollower() + "");
        tvNumberOfFollowing.setText(mUser.getNumberOfFollowing() + "");
        if (mUser.getScreenName().equals(RestApplication.mUser.getScreenName())){
            btnFollow.setVisibility(View.GONE);
        }
    }
}
