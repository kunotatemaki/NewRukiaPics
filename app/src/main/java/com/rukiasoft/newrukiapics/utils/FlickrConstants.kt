package com.rukiasoft.newrukiapics.utils

/**
 * Created by Roll on 28/7/17.
 */
object FlickrConstants{

    val BASE_URL : String = "https://api.flickr.com/services/rest/"

    val FLICKR_METHOD : String = "method"
    val FLICKR_API_KEY = "api_key"
    val FLICKR_TAGS = "tags"
    val FLICKR_SAFE_SEARCH = "safe_search"
    val FLICKR_EXTRAS = "extras"
    val FLICKR_FORMAT = "format"
    val FLICKR_PER_PAGE = "per_page"
    val FLICKR_SORT = "sort"
    val FLICKR_NO_JSON_CALLBACK = "nojsoncallback"
    val FLICKR_DELIMITER = ","

    enum class Order(val value: Int) {
        PUBLISHED(1),
        TAKEN(2)
    }
}