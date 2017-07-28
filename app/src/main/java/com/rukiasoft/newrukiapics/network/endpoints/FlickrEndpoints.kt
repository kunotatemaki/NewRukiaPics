package com.rukiasoft.newrukiapics.network.endpoints

import com.rukiasoft.newrukiapics.model.FlickrResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.QueryMap

/**
 * Created by Roll on 28/7/17.
 */
interface FlickrEndpoints {

    @GET(".")
    fun getPics(@QueryMap params: Map<String, String>) : Call<FlickrResponse>

}