package com.rukiasoft.newrukiapics.network.implementations

import android.util.Log
import com.rukiasoft.newrukiapics.BuildConfig
import com.rukiasoft.newrukiapics.model.FlickrResponse
import com.rukiasoft.newrukiapics.network.endpoints.FlickrEndpoints
import com.rukiasoft.newrukiapics.network.interfaces.NetworkManager
import com.rukiasoft.newrukiapics.utils.FlickrConstants
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject


/**
 * Created by Roll on 28/7/17.
 */

class NetworkManagerImpl : NetworkManager{

    val retrofit : Retrofit

    @Inject
    constructor() {
        this.retrofit = Retrofit.Builder()
        .baseUrl(FlickrConstants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
    }

    override fun getPics(tags: String, order: FlickrConstants.Order ) {

        var orderType : String = ""
        if (order == FlickrConstants.Order.PUBLISHED) {
            orderType = "date-posted-desc"
        } else if (order == FlickrConstants.Order.TAKEN) {
            orderType = "date-taken-desc"
        }
        //TODO "recuperar el número de fotos a devolver de las preferencias"
        val nPicsToRequest = 5

        //Creo el mapa con los parámetros
        val params = hashMapOf<String, String>()
        params.put(key = FlickrConstants.FLICKR_METHOD, value = "flickr.photos.search")
        params.put(key = FlickrConstants.FLICKR_API_KEY, value = BuildConfig.API_KEY)
        params.put(key = FlickrConstants.FLICKR_TAGS, value = tags)
        params.put(key = FlickrConstants.FLICKR_SAFE_SEARCH, value = "1")
        params.put(key = FlickrConstants.FLICKR_EXTRAS, value = "date_upload,date_taken,url_m,owner_name")
        params.put(key = FlickrConstants.FLICKR_FORMAT, value = "json")
        params.put(key = FlickrConstants.FLICKR_PER_PAGE, value = nPicsToRequest.toString())
        params.put(key = FlickrConstants.FLICKR_SORT, value = orderType)
        params.put(key = FlickrConstants.FLICKR_NO_JSON_CALLBACK, value = "5")


        val flickEndpoints = retrofit.create(FlickrEndpoints::class.java)

        val call : Call<FlickrResponse> = flickEndpoints.getPics(params)
        call.enqueue(object : Callback<FlickrResponse> {
            override fun onResponse(call: Call<FlickrResponse>?, response: Response<FlickrResponse>?) {

                if (response != null && response.isSuccessful) {
                    val respuesta = response.body()
                    //todo tratar la respuesta
                } else {
                    Log.d("PRUEBA", "a ver qué sale")
                }


            }

            override fun onFailure(call: Call<FlickrResponse>?, t: Throwable?) {
                Log.d("ERROR", t?.message)
            }
        })



    }

}