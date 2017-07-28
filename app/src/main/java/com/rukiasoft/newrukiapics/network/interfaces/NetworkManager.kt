package com.rukiasoft.newrukiapics.network.interfaces

import retrofit2.http.GET
import retrofit2.http.QueryMap

/**
 * Created by Roll on 28/7/17.
 */
interface NetworkManager {

    @GET(".")
    fun Any.getImages(@QueryMap params: Map<String, String>)

}