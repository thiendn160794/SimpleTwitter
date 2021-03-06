package com.thiendn.coderschool.simpletwitter.rest;

import android.content.Context;

import com.codepath.oauth.OAuthBaseClient;
import com.codepath.utils.AsyncSimpleTask;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.scribe.builder.api.Api;
import org.scribe.builder.api.TwitterApi;

/**
 * Created by thiendn on 05/03/2017.
 */

public class RestClient extends OAuthBaseClient {
    public static final Class<? extends Api> REST_API_CLASS = TwitterApi.class; // Change this
    public static final String REST_URL = "https://api.twitter.com/1.1"; // Change this, base API URL
    public static final String REST_CONSUMER_KEY = "Bk004lXjN0kqvu1OVv9ervWyd";       // Change this
    public static final String REST_CONSUMER_SECRET = "6hwfl9UkyE7OgLRuGDAGerMXue1rg70mCXGMhFcWQYvFnIHxqd"; // Change this
    public static final String REST_CALLBACK_URL = "oauth://codepathtweets"; // Change this (here and in manifest)

    public RestClient(Context context) {
        super(context, REST_API_CLASS, REST_URL, REST_CONSUMER_KEY, REST_CONSUMER_SECRET, REST_CALLBACK_URL);
    }

    public void getHomeTimeline(int page, AsyncHttpResponseHandler handler) {
        String apiUrl = getApiUrl("statuses/home_timeline.json");
        RequestParams params = new RequestParams();
        params.put("page", String.valueOf(page));
        getClient().get(apiUrl, params, handler);
    }

    public void postTweet(String body, AsyncHttpResponseHandler handler) {
        String apiUrl = getApiUrl("statuses/update.json");
        RequestParams params = new RequestParams();
        params.put("status", body);
        getClient().post(apiUrl, params, handler);
    }

    public void getUserCurrent(JsonHttpResponseHandler handler){
        String apiUrl = getApiUrl("account/verify_credentials.json");
        RequestParams params = new RequestParams();
        getClient().get(apiUrl, params, handler);
    }

    public void favoriteTweet(long idPost, JsonHttpResponseHandler handler){
        String apiUrl = getApiUrl("favorites/create.json");
        RequestParams params = new RequestParams();
        params.put("id", idPost);
        getClient().post(apiUrl, params, handler);
    }

    public void unFavoriteTweet(long idPost, JsonHttpResponseHandler handler){
        String apiUrl = getApiUrl("favorites/destroy.json");
        RequestParams params = new RequestParams();
        params.put("id", idPost);
        getClient().post(apiUrl, params, handler);
    }

    public void replyTweet(long idPost, String body, JsonHttpResponseHandler handler){
        String apiUrl = getApiUrl("statuses/update.json");
        RequestParams params = new RequestParams();
        params.put("in_reply_to_status_id", idPost);
        params.put("status", body);
        getClient().post(apiUrl, params, handler);
    }

    public void getMentionTimeline(int page, JsonHttpResponseHandler handler){
        String apiUrl = getApiUrl("statuses/mentions_timeline.json");
        RequestParams params = new RequestParams();
        params.put("page", String.valueOf(page));
        getClient().get(apiUrl, params, handler);
    }

    public void getTweetByUser(String screenName, int page, JsonHttpResponseHandler handler){
        String apiUrl = getApiUrl("statuses/user_timeline.json");
        RequestParams params = new RequestParams();
        params.put("page", String.valueOf(page));
        params.put("screen_name", screenName);
        getClient().get(apiUrl, params, handler);
    }

    public void getPannerFromUser(String screenName, JsonHttpResponseHandler handler){
        String apiUrl = getApiUrl("users/profile_banner.json");
        RequestParams params = new RequestParams();
        params.put("screen_name", screenName);
        getClient().get(apiUrl, params, handler);
    }

    public void searchTweets(String query, boolean entities, JsonHttpResponseHandler handler){
        String apiUrl = getApiUrl("search/tweets.json");
        RequestParams params = new RequestParams();
        params.put("q", query);
        params.put("include_entities", entities);
        params.put("count", 50);
        getClient().get(apiUrl, params, handler);
    }

    public void getFavoriteTweets(int page, String screenName, JsonHttpResponseHandler handler){
        String apiUrl = getApiUrl("favorites/list.json");
        RequestParams params = new RequestParams();
        params.put("screen_name", screenName);
        params.put("count", 30);
        params.put("page", page);
        getClient().get(apiUrl, params, handler);
    }
}
