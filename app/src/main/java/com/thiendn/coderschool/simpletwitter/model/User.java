package com.thiendn.coderschool.simpletwitter.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by thiendn on 05/03/2017.
 */

public class User {
    @SerializedName("id_str") private String id;
    @SerializedName("name") private String name;
    @SerializedName("screen_name") private String screenName;
    @SerializedName("verified") private boolean verified;
    @SerializedName("profile_image_url") private String imageProfile;

    public User(String id, String name, String screenName, boolean verified, String imageProfile) {
        this.id = id;
        this.name = name;
        this.screenName = screenName;
        this.verified = verified;
        this.imageProfile = imageProfile;
    }

    public User() {
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getScreenName() {
        return screenName;
    }

    public boolean isVerified() {
        return verified;
    }

    public String getImageProfile() {
        return imageProfile;
    }
}
