package com.thiendn.coderschool.simpletwitter.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.codepath.oauth.OAuthLoginActionBarActivity;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.thiendn.coderschool.simpletwitter.R;
import com.thiendn.coderschool.simpletwitter.adapter.TimeLineAdapter;
import com.thiendn.coderschool.simpletwitter.application.RestApplication;
import com.thiendn.coderschool.simpletwitter.dialog.ComposeDialog;
import com.thiendn.coderschool.simpletwitter.model.Tweet;
import com.thiendn.coderschool.simpletwitter.rest.RestClient;
import com.thiendn.coderschool.simpletwitter.util.ParseResponse;
import com.thiendn.coderschool.simpletwitter.util.RealmUtil;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cz.msebera.android.httpclient.Header;
import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by thiendn on 05/03/2017.
 */

public class HomeActivity extends OAuthLoginActionBarActivity<RestClient> {
    RestClient restClient;
    int page;
    List<Tweet> mTweets;
    TimeLineAdapter mAdapter;

    @BindView(R.id.btnAdd)
    FloatingActionButton btnAdd;
    @BindView(R.id.rvTimeline)
    RecyclerView rvTimeline;
    @BindView(R.id.swipeContainer)
    SwipeRefreshLayout swipeContainer;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Realm.init(getBaseContext());
        RealmConfiguration config = new RealmConfiguration
                .Builder()
                .deleteRealmIfMigrationNeeded()
                .build();
        Realm.setDefaultConfiguration(config);
        page = 1;
        ButterKnife.bind(this);
        if (RestApplication.MODE == 1){
            restClient = RestApplication.getRestClient();
            getTimeline();

            swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                @Override
                public void onRefresh() {
                    page = 1;
                    getTimeline();
                }
            });

            btnAdd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(getBaseContext(), "clicked", Toast.LENGTH_LONG).show();
                    android.app.FragmentManager fm = getFragmentManager();
                    ComposeDialog composeDialog = ComposeDialog.newInstance(null, getBaseContext(), new ComposeDialog.Listener() {
                        @Override
                        public void onComposeTweetSuccess(Tweet tweet) {
                            mTweets.add(0, tweet);
                            mAdapter.notifyDataSetChanged();
                        }
                    });
                    composeDialog.show(fm, "Pose");
                }
            });
        }else {
            mTweets = RealmUtil.getAll();
            mAdapter = new TimeLineAdapter(mTweets, new TimeLineAdapter.Listener() {
                @Override
                public void onLoadMore() {

                }

                @Override
                public void onItemClicked(View itemView) {

                }

                @Override
                public void onReply(String screenname) {

                }
            });
            fetchTimeline();
        }
    }

    private void getTimeline(){
        System.out.println("da toi day");
        restClient.getHomeTimeline(page, new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                super.onSuccess(statusCode, headers, response);
                if (response != null){
                    Log.d("Timeline Response", response.toString());
                    if (page == 1){
                        System.out.println("page =" + page);
                        mTweets = ParseResponse.getTweetFromResp(response);
                        setupAdapter();
                        fetchTimeline();
                        swipeContainer.setRefreshing(false);
                        RealmUtil.store(mTweets);
                    }else {
                        for (Tweet tweet: ParseResponse.getTweetFromResp(response)){
                            mTweets.add(tweet);
                            mAdapter.notifyDataSetChanged();
                        }
                    }
                    System.out.println(mTweets.size());
                }
                else {
                    Log.w("Timeline Response", "null");
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                super.onFailure(statusCode, headers, responseString, throwable);
                Log.w("GetTimeline 1", "fail");
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                super.onFailure(statusCode, headers, throwable, errorResponse);
                Log.w("GetTimeline 2", "fail");
                throwable.printStackTrace();
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONArray errorResponse) {
                super.onFailure(statusCode, headers, throwable, errorResponse);
                Log.w("GetTimeline 3", "fail");
                throwable.printStackTrace();
            }
        });
    }

    private void setupAdapter(){
        mAdapter = new TimeLineAdapter(mTweets, new TimeLineAdapter.Listener() {
            @Override
            public void onLoadMore() {
                page++;
                getTimeline();
            }

            @Override
            public void onItemClicked(View itemView) {

            }

            @Override
            public void onReply(String screenname) {
                android.app.FragmentManager fm = getFragmentManager();
                ComposeDialog composeDialog = ComposeDialog.newInstance(screenname, getBaseContext(), new ComposeDialog.Listener() {
                    @Override
                    public void onComposeTweetSuccess(Tweet tweet) {
                        mTweets.add(0, tweet);
                        mAdapter.notifyDataSetChanged();
                    }
                });
                composeDialog.show(fm, "Pose");
            }
        });
    }

    private void fetchTimeline(){
        RecyclerView.LayoutManager layoutManager =
                new LinearLayoutManager(getBaseContext(), LinearLayoutManager.VERTICAL, false);
        rvTimeline.setLayoutManager(layoutManager);
        rvTimeline.setAdapter(mAdapter);

    }

    @Override
    public void onLoginSuccess() {

    }

    @Override
    public void onLoginFailure(Exception e) {
        Log.d("HomeActivity", "login failed");
    }
}
