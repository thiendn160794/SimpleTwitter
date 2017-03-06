package com.thiendn.coderschool.simpletwitter.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by thiendn on 05/03/2017.
 */

public class Tweet {
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
}
