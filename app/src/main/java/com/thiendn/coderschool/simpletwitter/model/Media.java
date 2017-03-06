package com.thiendn.coderschool.simpletwitter.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by thiendn on 06/03/2017.
 */

public class Media {
    @SerializedName("media_url") private String mediaUrl;

    public Media(String mediaUrl) {
        this.mediaUrl = mediaUrl;
    }

    public Media() {
    }

    public String getMediaUrl() {
        return mediaUrl;
    }
}
