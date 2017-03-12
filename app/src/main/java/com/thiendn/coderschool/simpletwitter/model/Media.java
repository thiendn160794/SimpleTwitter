package com.thiendn.coderschool.simpletwitter.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by thiendn on 06/03/2017.
 */

public class Media implements Parcelable{
    @SerializedName("media_url") private String mediaUrl;

    public Media(String mediaUrl) {
        this.mediaUrl = mediaUrl;
    }

    public Media() {
    }

    protected Media(Parcel in) {
        mediaUrl = in.readString();
    }

    public static final Creator<Media> CREATOR = new Creator<Media>() {
        @Override
        public Media createFromParcel(Parcel in) {
            return new Media(in);
        }

        @Override
        public Media[] newArray(int size) {
            return new Media[size];
        }
    };

    public String getMediaUrl() {
        return mediaUrl;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mediaUrl);
    }
}
