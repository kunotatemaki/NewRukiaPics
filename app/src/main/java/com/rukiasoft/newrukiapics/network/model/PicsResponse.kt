package com.rukiasoft.newrukiapics.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName



/**
 * Created by Roll on 28/7/17.
 */
class PicsResponse {

    @SerializedName("id")
    @Expose
    private val id: String? = null
    @SerializedName("owner")
    @Expose
    private val owner: String? = null
    @SerializedName("ownername")
    @Expose
    private val ownername: String? = null
    @SerializedName("secret")
    @Expose
    private val secret: String? = null
    @SerializedName("server")
    @Expose
    private val server: String? = null
    @SerializedName("farm")
    @Expose
    private val farm: Int? = null
    @SerializedName("title")
    @Expose
    private val title: String? = null
    @SerializedName("ispublic")
    @Expose
    private val ispublic: Int? = null
    @SerializedName("isfriend")
    @Expose
    private val isfriend: Int? = null
    @SerializedName("isfamily")
    @Expose
    private val isfamily: Int? = null
    @SerializedName("dateupload")
    @Expose
    private val dateupload: String? = null
    @SerializedName("datetaken")
    @Expose
    private val datetaken: String? = null
    @SerializedName("datetakengranularity")
    @Expose
    private val datetakengranularity: String? = null
    @SerializedName("datetakenunknown")
    @Expose
    private val datetakenunknown: String? = null
    @SerializedName("url_m")
    @Expose val urlM: String? = null
    @SerializedName("height_m")
    @Expose
    private val heightM: String? = null
    @SerializedName("width_m")
    @Expose
    private val widthM: String? = null

}