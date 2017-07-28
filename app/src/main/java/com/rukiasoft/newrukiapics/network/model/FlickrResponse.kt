package com.rukiasoft.newrukiapics.network.model


import com.google.gson.JsonObject
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Created by Roll on 28/7/17.
 */


class FlickrResponse {

    @SerializedName("photos")
    @Expose
    var photos: JsonObject? = null
}

