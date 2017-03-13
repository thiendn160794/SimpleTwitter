package com.thiendn.coderschool.simpletwitter.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.thiendn.coderschool.simpletwitter.R;
import com.thiendn.coderschool.simpletwitter.adapters.HomePagerAdapter;
import com.thiendn.coderschool.simpletwitter.application.RestApplication;

import butterknife.BindView;
import butterknife.ButterKnife;

import static java.security.AccessController.getContext;

/**
 * Created by thiendn on 12/03/2017.
 */

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.tabLayoutHome)
    TabLayout tabLayout;
    @BindView(R.id.viewPagerHome)
    ViewPager viewPager;
//    @BindView(R.id.btnAdd)
//    FloatingActionButton btnAdd;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        HomePagerAdapter adapter = new HomePagerAdapter(getSupportFragmentManager(), MainActivity.this);
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);

//        btnAdd.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(getBaseContext(), "FAB CLICKED", Toast.LENGTH_LONG).show();
//            }
//        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.view_personal){
            Intent intent = new Intent(getBaseContext(), ProfileActivity.class);
            //TODO add user to start profile
//                intent.putExtra(ProfileActivity.SCREEN_NAME, screentname);
            intent.putExtra(ProfileActivity.USER, RestApplication.mUser);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}
