package com.rukiasoft.newrukiapics.network.endpoints

import retrofit2.http.GET
import retrofit2.http.QueryMap

/**
 * Created by Roll on 28/7/17.
 */
interface FlikrEndpoints {

    @GET(".")
    fun getPics(@QueryMap params: Map<String, String>)

}