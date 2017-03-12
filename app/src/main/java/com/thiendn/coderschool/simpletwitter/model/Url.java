package com.thiendn.coderschool.simpletwitter.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by thiendn on 05/03/2017.
 */

public class Url implements Parcelable{
    @SerializedName("url") private String url;
    @SerializedName("expanded_url") private String expandedUrl;
    @SerializedName("display_url") private String displayUrl;

    public Url(String url, String expandedUrl, String displayUrl) {
        this.url = url;
        this.expandedUrl = expandedUrl;
        this.displayUrl = displayUrl;
    }

    public Url() {
    }

    protected Url(Parcel in) {
        url = in.readString();
        expandedUrl = in.readString();
        displayUrl = in.readString();
    }

    public static final Creator<Url> CREATOR = new Creator<Url>() {
        @Override
        public Url createFromParcel(Parcel in) {
            return new Url(in);
        }

        @Override
        public Url[] newArray(int size) {
            return new Url[size];
        }
    };

    public String getUrl() {
        return url;
    }

    public String getExpandedUrl() {
        return expandedUrl;
    }

    public String getDisplayUrl() {
        return displayUrl;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(url);
        dest.writeString(expandedUrl);
        dest.writeString(displayUrl);
    }
}
