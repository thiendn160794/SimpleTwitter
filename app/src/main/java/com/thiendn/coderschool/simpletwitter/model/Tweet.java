package com.thiendn.coderschool.simpletwitter.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by thiendn on 05/03/2017.
 */

public class Tweet implements Parcelable{
    @SerializedName("created_at") private String createdDate;
    @SerializedName("id_str") private String idStr;
    @SerializedName("id") private long id;
    @SerializedName("text") private String text;
    @SerializedName("favorite_count") private int favoriteCount;
    @SerializedName("favorited") private boolean favorite;
    @SerializedName("retweet_count") private int retweetCount;
    @SerializedName("urls") private List<Url> urls;
    @SerializedName("url") private String url;
    @SerializedName("user") private User user;
    @SerializedName("entities") private Entity entity;

    public Tweet(String createdDate, String idStr, long id, String text, int favoriteCount, boolean favorite, int retweetCount, List<Url> urls, String url, User user, Entity entity) {
        this.createdDate = createdDate;
        this.idStr = idStr;
        this.id = id;
        this.text = text;
        this.favoriteCount = favoriteCount;
        this.favorite = favorite;
        this.retweetCount = retweetCount;
        this.urls = urls;
        this.url = url;
        this.user = user;
        this.entity = entity;
    }

    public Tweet() {
    }

    protected Tweet(Parcel in) {
        createdDate = in.readString();
        idStr = in.readString();
        id = in.readLong();
        text = in.readString();
        favoriteCount = in.readInt();
        favorite = in.readByte() != 0;
        retweetCount = in.readInt();
        urls = in.createTypedArrayList(Url.CREATOR);
        url = in.readString();
        user = in.readParcelable(User.class.getClassLoader());
        entity = in.readParcelable(Entity.class.getClassLoader());
    }

    public static final Creator<Tweet> CREATOR = new Creator<Tweet>() {
        @Override
        public Tweet createFromParcel(Parcel in) {
            return new Tweet(in);
        }

        @Override
        public Tweet[] newArray(int size) {
            return new Tweet[size];
        }
    };

    public String getCreatedDate() {
        return createdDate;
    }

    public String getIdStr() {
        return idStr;
    }

    public long getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public int getFavoriteCount() {
        return favoriteCount;
    }

    public boolean isFavorite() {
        return favorite;
    }

    public int getRetweetCount() {
        return retweetCount;
    }

    public List<Url> getUrls() {
        return urls;
    }

    public String getUrl() {
        return url;
    }

    public User getUser() {
        return user;
    }

    public Entity getEntity() {
        return entity;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(createdDate);
        dest.writeString(idStr);
        dest.writeLong(id);
        dest.writeString(text);
        dest.writeInt(favoriteCount);
        dest.writeByte((byte) (favorite ? 1 : 0));
        dest.writeInt(retweetCount);
        dest.writeTypedList(urls);
        dest.writeString(url);
        dest.writeParcelable(user, flags);
        dest.writeParcelable(entity, flags);
    }
}
