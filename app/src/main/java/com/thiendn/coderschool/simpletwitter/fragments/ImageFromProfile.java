package com.thiendn.coderschool.simpletwitter.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.thiendn.coderschool.simpletwitter.R;
import com.thiendn.coderschool.simpletwitter.application.RestApplication;
import com.thiendn.coderschool.simpletwitter.model.User;
import com.thiendn.coderschool.simpletwitter.rest.RestClient;

import org.json.JSONArray;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

/**
 * Created by thiendn on 12/03/2017.
 */

public class ImageFromProfile extends Fragment {
    private static final String USER = "USER";
    private RestClient mClient;
    private User mUser;
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
        mClient.getPannerFromUser(mUser.getScreenName(), new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                System.out.println("GET PANNER object: " + response);
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                super.onSuccess(statusCode, headers, response);
                System.out.println("GET PANNER array: " + response);
            }
        });
        return view;
    }
}
