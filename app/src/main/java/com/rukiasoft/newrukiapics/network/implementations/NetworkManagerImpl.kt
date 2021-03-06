package com.rukiasoft.newrukiapics.network.implementations

import com.google.gson.Gson
import com.rukiasoft.newrukiapics.BuildConfig
import com.rukiasoft.newrukiapics.model.Pic
import com.rukiasoft.newrukiapics.model.PicsResponse
import com.rukiasoft.newrukiapics.network.endpoints.FlickrEndpoints
import com.rukiasoft.newrukiapics.network.interfaces.NetworkManager
import com.rukiasoft.newrukiapics.network.model.FlickrResponse
import com.rukiasoft.newrukiapics.preferences.interfaces.PreferencesManager
import com.rukiasoft.newrukiapics.ui.observables.MyCustomObservable
import com.rukiasoft.newrukiapics.utils.FlickrConstants
import com.rukiasoft.newrukiapics.utils.LogHelper
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject


/**
 * Created by Roll on 28/7/17.
 */

class NetworkManagerImpl @Inject constructor() : NetworkManager{

    val retrofit : Retrofit = Retrofit.Builder()
            .baseUrl(FlickrConstants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Inject
    protected lateinit var log : LogHelper

    @Inject
    protected lateinit var prefs: PreferencesManager

    override fun getPics(tags: String, order: FlickrConstants.Order, listOfPics: MyCustomObservable<MutableList<Pic>>) {

        var orderType : String = ""
        if (order == FlickrConstants.Order.PUBLISHED) {
            orderType = "date-posted-desc"
        } else if (order == FlickrConstants.Order.TAKEN) {
            orderType = "date-taken-desc"
        }

        val nPicsToRequest = prefs.getNumberOfPicsToDownload()

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

        val myCall : Call<FlickrResponse> = flickEndpoints.getPics(params)
        myCall.enqueue(object : Callback<FlickrResponse> {
            override fun onResponse(call: Call<FlickrResponse>?, response: Response<FlickrResponse>?) {
                if (response?.isSuccessful as Boolean) {
                    val list : MutableList<Pic> = arrayListOf()
                    //map photos to observable value
                    response.body()
                            ?.photos
                            ?.get("photo")
                            ?.asJsonArray?.mapTo(list) {
                        Pic(Gson().fromJson<PicsResponse>(it, PicsResponse::class.java))
                    }

                    log.d(NetworkManagerImpl::class.java, list.size.toString())
                    listOfPics.setObservableValue(list)
                } else {
                    listOfPics.getObservableValue()?.clear()
                    log.d(NetworkManagerImpl::class.java, "a ver qué sale")
                }
            }

            override fun onFailure(call: Call<FlickrResponse>?, t: Throwable?) {
                log.d(NetworkManagerImpl::class.java, t?.message.toString())
                listOfPics.getObservableValue()?.clear()
            }
        })



    }



}