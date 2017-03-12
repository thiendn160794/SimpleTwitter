package com.thiendn.coderschool.simpletwitter.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by thiendn on 05/03/2017.
 */

public class User implements Parcelable{
    @SerializedName("id_str") private String id;
    @SerializedName("name") private String name;
    @SerializedName("screen_name") private String screenName;
    @SerializedName("verified") private boolean verified;
    @SerializedName("profile_image_url") private String imageProfile;
    @SerializedName("followers_count") private int numberOfFollower;
    @SerializedName("friends_count") private int numberOfFollowing;
    @SerializedName("description") private String description;
    @SerializedName("profile_background_image_url") private String imageCover;

    public User(String id, String name, String screenName, boolean verified, String imageProfile, int numberOfFollower, int numberOfFollowing, String description, String cover) {
        this.id = id;
        this.name = name;
        this.screenName = screenName;
        this.verified = verified;
        this.imageProfile = imageProfile;
        this.numberOfFollower = numberOfFollower;
        this.numberOfFollowing = numberOfFollowing;
        this.description = description;
        this.imageCover = cover;
    }

    public User(String id, String name, String screenName, boolean verified, String imageProfile) {
        this.id = id;
        this.name = name;
        this.screenName = screenName;
        this.verified = verified;
        this.imageProfile = imageProfile;
    }

    public User() {
    }


    protected User(Parcel in) {
        id = in.readString();
        name = in.readString();
        screenName = in.readString();
        verified = in.readByte() != 0;
        imageProfile = in.readString();
        numberOfFollower = in.readInt();
        numberOfFollowing = in.readInt();
        description = in.readString();
        imageCover = in.readString();
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

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

    public int getNumberOfFollower() {
        return numberOfFollower;
    }

    public int getNumberOfFollowing() {
        return numberOfFollowing;
    }

    public String getDescription() {
        return description;
    }

    public String getImageCover() {
        return imageCover;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(name);
        dest.writeString(screenName);
        dest.writeByte((byte) (verified ? 1 : 0));
        dest.writeString(imageProfile);
        dest.writeInt(numberOfFollower);
        dest.writeInt(numberOfFollowing);
        dest.writeString(description);
        dest.writeString(imageCover);
    }
}
