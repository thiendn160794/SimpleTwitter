package com.thiendn.coderschool.simpletwitter.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;

import com.codepath.oauth.OAuthLoginActionBarActivity;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.thiendn.coderschool.simpletwitter.R;
import com.thiendn.coderschool.simpletwitter.application.RestApplication;
import com.thiendn.coderschool.simpletwitter.model.Tweet;
import com.thiendn.coderschool.simpletwitter.model.User;
import com.thiendn.coderschool.simpletwitter.rest.RestClient;
import com.thiendn.coderschool.simpletwitter.util.ParseResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;

import butterknife.ButterKnife;
import cz.msebera.android.httpclient.Header;

/**
 * Created by thiendn on 05/03/2017.
 */

public class HomeActivity extends OAuthLoginActionBarActivity<RestClient> {
    RestClient restClient;
    int page;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
        restClient = RestApplication.getRestClient();
        getTimeline();
    }

    private void getTimeline(){
        restClient.getHomeTimeline(page, new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                super.onSuccess(statusCode, headers, response);
                if (response != null){
                    Log.d("Timeline Response", response.toString());
                    List<Tweet> tweets = ParseResponse.getTweetFromResp(response);
                    System.out.println(tweets.size());
                    fetchTimeline(tweets);
                }
            }
        });
    }

    private void initSetup(){
        page = 1;
    }

    private void fetchTimeline(List<Tweet> tweets){

    }

    @Override
    public void onLoginSuccess() {

    }

    @Override
    public void onLoginFailure(Exception e) {

    }
}
