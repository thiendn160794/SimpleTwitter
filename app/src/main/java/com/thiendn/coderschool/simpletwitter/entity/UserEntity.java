package com.thiendn.coderschool.simpletwitter.entity;

import com.google.gson.annotations.SerializedName;
import com.thiendn.coderschool.simpletwitter.model.User;

import io.realm.RealmObject;

/**
 * Created by thiendn on 07/03/2017.
 */

public class UserEntity extends RealmObject {
    private String id;
    private String name;
    private String screenName;
    private boolean verified;
    private String imageProfile;

    public UserEntity(String id, String name, String screenName, boolean verified, String imageProfile) {
        this.id = id;
        this.name = name;
        this.screenName = screenName;
        this.verified = verified;
        this.imageProfile = imageProfile;
    }

    public UserEntity() {
    }

    public User toUser(){
        return new User(this.id, this.name, this.screenName, this.verified, this.imageProfile);
    }

    public static UserEntity fromUser(User user){
        return new UserEntity(user.getId(), user.getName(), user.getScreenName(), user.isVerified(), user.getImageProfile());
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getScreenName() {
        return screenName;
    }

    public void setScreenName(String screenName) {
        this.screenName = screenName;
    }

    public boolean isVerified() {
        return verified;
    }

    public void setVerified(boolean verified) {
        this.verified = verified;
    }

    public String getImageProfile() {
        return imageProfile;
    }

    public void setImageProfile(String imageProfile) {
        this.imageProfile = imageProfile;
    }
}
