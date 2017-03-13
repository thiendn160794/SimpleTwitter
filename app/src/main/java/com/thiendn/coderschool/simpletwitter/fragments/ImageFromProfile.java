package com.thiendn.coderschool.simpletwitter.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.thiendn.coderschool.simpletwitter.R;
import com.thiendn.coderschool.simpletwitter.adapters.PictureAdapter;
import com.thiendn.coderschool.simpletwitter.application.RestApplication;
import com.thiendn.coderschool.simpletwitter.model.Tweet;
import com.thiendn.coderschool.simpletwitter.model.User;
import com.thiendn.coderschool.simpletwitter.rest.RestClient;
import com.thiendn.coderschool.simpletwitter.util.ParseResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;

import butterknife.BindView;
import cz.msebera.android.httpclient.Header;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.L;

/**
 * Created by thiendn on 12/03/2017.
 */

public class ImageFromProfile extends Fragment {
    private static final String USER = "USER";
    private RestClient mClient;
    private User mUser;
    private List<Tweet> mTweets;

    @BindView(R.id.rvPicture)
    RecyclerView rvPicture;
    public static ImageFromProfile newInstance(User user){
        ImageFromProfile fragment = new ImageFromProfile();
        Bundle bundle = new Bundle();
        bundle.putParcelable(USER, user);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_images_in_profile, container, false);
        mUser = getArguments().getParcelable(USER);
        mClient = RestApplication.getRestClient();
        mClient.getTweetByUser(mUser.getScreenName(), 1, new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                super.onSuccess(statusCode, headers, response);
                mTweets = ParseResponse.getTweetFromResp(response);
                PictureAdapter adapter = new PictureAdapter(mTweets);
                rvPicture.setAdapter(adapter);
//                RecyclerView.LayoutManager layoutManager = new StaggeredGridLayoutManager()
            }
        });
        return view;
    }
}
