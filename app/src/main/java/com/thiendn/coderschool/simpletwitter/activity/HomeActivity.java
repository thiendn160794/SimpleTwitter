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

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cz.msebera.android.httpclient.Header;

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
        ButterKnife.bind(this);
        restClient = RestApplication.getRestClient();
        initSetup();
        getTimeline();
        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getTimeline();
            }
        });
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getBaseContext(), "clicked", Toast.LENGTH_LONG).show();
                android.app.FragmentManager fm = getFragmentManager();
                ComposeDialog composeDialog = ComposeDialog.newInstance(getBaseContext(), new ComposeDialog.Listener() {
                    @Override
                    public void onComposeTweetSuccess(Tweet tweet) {
                        mTweets.add(0, tweet);
                        mAdapter.notifyDataSetChanged();
                    }
                });
                composeDialog.show(fm, "Setting");
            }
        });
    }

    private void getTimeline(){
        System.out.println("da toi day");
        restClient.getHomeTimeline(1, new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                super.onSuccess(statusCode, headers, response);
                if (response != null){
                    Log.d("Timeline Response", response.toString());
                    mTweets = ParseResponse.getTweetFromResp(response);
                    System.out.println(mTweets.size());

                    fetchTimeline();
                    swipeContainer.setRefreshing(false);
//                    for (Tweet tweet: mTweets){
//                        Log.d("List value", tweet.getEntity().getMedia() + "");
//                    }
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

    private void initSetup(){
        page = 1;
    }

    private void fetchTimeline(){
        mAdapter = new TimeLineAdapter(mTweets);
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
