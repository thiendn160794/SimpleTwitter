package com.thiendn.coderschool.simpletwitter.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by thiendn on 05/03/2017.
 */

public class Entity implements Parcelable{
    @SerializedName("media") private List<Media> media;

    public Entity(List<Media> media) {
        this.media = media;
    }

    public Entity() {
    }

    protected Entity(Parcel in) {
        media = in.createTypedArrayList(Media.CREATOR);
    }

    public static final Creator<Entity> CREATOR = new Creator<Entity>() {
        @Override
        public Entity createFromParcel(Parcel in) {
            return new Entity(in);
        }

        @Override
        public Entity[] newArray(int size) {
            return new Entity[size];
        }
    };

    public List<Media> getMedia() {
        return media;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(media);
    }
}
