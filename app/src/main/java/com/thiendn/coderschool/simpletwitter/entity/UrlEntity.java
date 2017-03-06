package com.thiendn.coderschool.simpletwitter.entity;

import com.google.gson.annotations.SerializedName;
import com.thiendn.coderschool.simpletwitter.model.Url;

import io.realm.RealmObject;

/**
 * Created by thiendn on 07/03/2017.
 */

public class UrlEntity extends RealmObject{
    private String url;
    private String expandedUrl;
    private String displayUrl;

    public UrlEntity(String url, String expandedUrl, String displayUrl) {
        this.url = url;
        this.expandedUrl = expandedUrl;
        this.displayUrl = displayUrl;
    }

    public UrlEntity() {
    }

    public static UrlEntity fromUrl(Url url){
        return new UrlEntity(url.getUrl(), url.getExpandedUrl(), url.getDisplayUrl());
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getExpandedUrl() {
        return expandedUrl;
    }

    public void setExpandedUrl(String expandedUrl) {
        this.expandedUrl = expandedUrl;
    }

    public String getDisplayUrl() {
        return displayUrl;
    }

    public void setDisplayUrl(String displayUrl) {
        this.displayUrl = displayUrl;
    }
}
