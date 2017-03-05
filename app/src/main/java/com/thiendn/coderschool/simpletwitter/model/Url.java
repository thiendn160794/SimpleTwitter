package com.thiendn.coderschool.simpletwitter.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by thiendn on 05/03/2017.
 */

public class Url {
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

    public String getUrl() {
        return url;
    }

    public String getExpandedUrl() {
        return expandedUrl;
    }

    public String getDisplayUrl() {
        return displayUrl;
    }
}
