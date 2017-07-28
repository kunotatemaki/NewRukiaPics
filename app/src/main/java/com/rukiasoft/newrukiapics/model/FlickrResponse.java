package com.rukiasoft.newrukiapics.model;


import com.google.gson.JsonObject;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Roll on 28/7/17.
 */


public class FlickrResponse {

    @SerializedName("photos")
    @Expose
    private JsonObject photos;

    public JsonObject getPhotos() {
        return photos;
    }

    public void setPhotos(JsonObject photos) {
        this.photos = photos;
    }
}

