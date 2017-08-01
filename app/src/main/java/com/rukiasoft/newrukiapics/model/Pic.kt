package com.rukiasoft.newrukiapics.model

/**
 * Created by Roll on 28/7/17.
 */
class Pic(response: PicsResponse) {
    val picUrl : String = response.urlM as String
    val title : String = response.title as String
    val taken : String = response.datetaken as String
    val published : String = response.dateupload as String
    val owner : String = response.ownername as String

}