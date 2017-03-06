package com.thiendn.coderschool.simpletwitter.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by thiendn on 05/03/2017.
 */

public class Entity {
    @SerializedName("media") private List<Media> media;

    public Entity(List<Media> media) {
        this.media = media;
    }

    public Entity() {
    }

    public List<Media> getMedia() {
        return media;
    }
}
