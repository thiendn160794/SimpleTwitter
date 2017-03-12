package com.thiendn.coderschool.simpletwitter.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.thiendn.coderschool.simpletwitter.R;
import com.thiendn.coderschool.simpletwitter.activity.ProfileActivity;
import com.thiendn.coderschool.simpletwitter.adapters.TimeLineAdapter;
import com.thiendn.coderschool.simpletwitter.application.RestApplication;
import com.thiendn.coderschool.simpletwitter.dialog.ComposeDialog;
import com.thiendn.coderschool.simpletwitter.model.Tweet;
import com.thiendn.coderschool.simpletwitter.model.User;
import com.thiendn.coderschool.simpletwitter.rest.RestClient;
import com.thiendn.coderschool.simpletwitter.util.ParseResponse;
import com.thiendn.coderschool.simpletwitter.util.RealmUtil;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cz.msebera.android.httpclient.Header;

import static com.thiendn.coderschool.simpletwitter.R.id.swipeContainer;

/**
 * Created by thiendn on 12/03/2017.
 */

public class TweetFromProfile extends Fragment {
    private static String USER = "USER";
    private User mUser;
    private List<Tweet> mTweets;
    private RestClient mClient;
    private TimeLineAdapter mAdapter;
    private int page;
    @BindView(R.id.rvTimeline)
    RecyclerView rvTimeline;

    public static TweetFromProfile newInstance(User user){
        TweetFromProfile fragment = new TweetFromProfile();
        Bundle bundle = new Bundle();
        bundle.putParcelable(USER, user);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tweets_in_profile, container, false);
        ButterKnife.bind(this, view);
        page = 1;
//        mUser = RestApplication.mUser;
        mUser = getArguments().getParcelable(USER);
        mClient = RestApplication.getRestClient();
        getTimeline();
//        mClient.getTweetByUser(mUser.getScreenName(), page, new JsonHttpResponseHandler() {
//            @Override
//            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
//                super.onSuccess(statusCode, headers, response);
//                mTweets = ParseResponse.getTweetFromResp(response);
//                setupAdapter();
//                fetchTimeline();
//            }
//        });
        return view;
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
            public void onReply(long postId, String screenname) {
                android.app.FragmentManager fm = getActivity().getFragmentManager();
                ComposeDialog composeDialog = ComposeDialog.newInstance(postId, screenname, getContext(), new ComposeDialog.Listener() {
                    @Override
                    public void onComposeTweetSuccess(Tweet tweet) {
                        mTweets.add(0, tweet);
                        mAdapter.notifyDataSetChanged();
                    }
                });
                composeDialog.show(fm, "Pose");
            }

            @Override
            public void onProfileClicked(User user) {
                Intent intent = new Intent(getContext(), ProfileActivity.class);
                //TODO add user to start profileActivity
//                intent.putExtra(ProfileActivity.SCREEN_NAME, screentname);
                intent.putExtra("USER", user);
                startActivity(intent);
            }
        });
    }

    private void fetchTimeline(){
        RecyclerView.LayoutManager layoutManager =
                new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        rvTimeline.setLayoutManager(layoutManager);
        rvTimeline.setAdapter(mAdapter);
    }

    private void getTimeline(){
        System.out.println("da toi day");
        mClient.getTweetByUser(mUser.getScreenName(), page, new JsonHttpResponseHandler(){
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
}
