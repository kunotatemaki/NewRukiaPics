package com.rukiasoft.newrukiapics.model

/**
 * Created by Roll on 28/7/17.
 */
class Pic(response: PicsResponse) {
    val picUrl : String? = response.urlM
    val title : String? = response.title
    val taken : String? = response.datetaken
    val published : String? = response.dateupload
    val owner : String? = response.ownername

}