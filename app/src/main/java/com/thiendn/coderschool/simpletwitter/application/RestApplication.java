package com.thiendn.coderschool.simpletwitter.application;

import android.app.Application;
import android.content.Context;

import com.thiendn.coderschool.simpletwitter.model.User;
import com.thiendn.coderschool.simpletwitter.rest.RestClient;

/**
 * Created by thiendn on 05/03/2017.
 */

public class RestApplication extends Application {
    private static Context context;
    public static User mUser;
    @Override
    public void onCreate() {
        super.onCreate();

//        FlowManager.init(new FlowConfig.Builder(this).build());
//        FlowLog.setMinimumLoggingLevel(FlowLog.Level.V);

        RestApplication.context = this;
    }

    public static RestClient getRestClient() {
        return (RestClient) RestClient.getInstance(RestClient.class, RestApplication.context);
    }
}
