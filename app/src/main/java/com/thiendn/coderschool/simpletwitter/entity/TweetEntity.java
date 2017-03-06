package com.thiendn.coderschool.simpletwitter.entity;

import com.thiendn.coderschool.simpletwitter.model.Tweet;
import com.thiendn.coderschool.simpletwitter.model.User;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by thiendn on 06/03/2017.
 */

public class TweetEntity extends RealmObject {
    @PrimaryKey
    private long id;
    private String createdDate;
    private String idStr;
    private String text;
    private int favoriteCount;
    private boolean favorite;
    private int retweetCount;
//    private RealmList<UrlEntity> urls;
    private String url;
    private UserEntity user;
    private EntityEntity entity;

    public TweetEntity(String createdDate, String idStr, long id, String text, int favoriteCount, boolean favorite, int retweetCount, String url, UserEntity user, EntityEntity entity) {
        this.createdDate = createdDate;
        this.idStr = idStr;
        this.id = id;
        this.text = text;
        this.favoriteCount = favoriteCount;
        this.favorite = favorite;
        this.retweetCount = retweetCount;
        this.url = url;
        this.user = user;
        this.entity = entity;
    }

    public TweetEntity() {
    }

    public static TweetEntity fromTweet(Tweet tweet){
        return new TweetEntity(
                tweet.getCreatedDate(),
                tweet.getIdStr(),
                tweet.getId(),
                tweet.getText(),
                tweet.getFavoriteCount(),
                tweet.isFavorite(),
                tweet.getRetweetCount(),
                tweet.getUrl(),
                UserEntity.fromUser(tweet.getUser()),
                EntityEntity.fromEntity(tweet.getEntity())
        );
    }

    public Tweet toTweet(){
        User user = null;
        if (this.getUser() != null){
            user = this.getUser().toUser();
        }
        return new Tweet(
                this.getCreatedDate(),
                this.getIdStr(),
                this.getId(),
                this.getText(),
                this.getFavoriteCount(),
                this.isFavorite(),
                this.getRetweetCount(),
                null,
                this.getUrl(),
                user,
                this.getEntity().toEntity()
        );
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public String getIdStr() {
        return idStr;
    }

    public void setIdStr(String idStr) {
        this.idStr = idStr;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getFavoriteCount() {
        return favoriteCount;
    }

    public void setFavoriteCount(int favoriteCount) {
        this.favoriteCount = favoriteCount;
    }

    public boolean isFavorite() {
        return favorite;
    }

    public void setFavorite(boolean favorite) {
        this.favorite = favorite;
    }

    public int getRetweetCount() {
        return retweetCount;
    }

    public void setRetweetCount(int retweetCount) {
        this.retweetCount = retweetCount;
    }

//    public List<UrlEntity> getUrls() {
//        return urls;
//    }

//    public void setUrls(RealmList<UrlEntity> urls) {
//        this.urls = urls;
//    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public EntityEntity getEntity() {
        return entity;
    }

    public void setEntity(EntityEntity entity) {
        this.entity = entity;
    }
}
