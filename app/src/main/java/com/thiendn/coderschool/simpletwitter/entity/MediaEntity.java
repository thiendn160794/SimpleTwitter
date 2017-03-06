package com.thiendn.coderschool.simpletwitter.entity;

import com.thiendn.coderschool.simpletwitter.model.Media;

import io.realm.RealmObject;

/**
 * Created by thiendn on 07/03/2017.
 */

public class MediaEntity extends RealmObject {
    private String mediaUrl;

    public MediaEntity(String mediaUrl) {
        this.mediaUrl = mediaUrl;
    }

    public MediaEntity() {
    }

    public static MediaEntity fromMedia(Media media){
        return new MediaEntity(media.getMediaUrl());
    }

    public Media toMedia(){
        return new Media(this.mediaUrl);
    }

    public String getMediaUrl() {
        return mediaUrl;
    }

    public void setMediaUrl(String mediaUrl) {
        this.mediaUrl = mediaUrl;
    }
}
