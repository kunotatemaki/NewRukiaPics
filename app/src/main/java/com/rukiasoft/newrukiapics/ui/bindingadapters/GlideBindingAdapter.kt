package com.rukiasoft.newrukiapics.ui.bindingadapters

import android.databinding.BindingAdapter
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.rukiasoft.newrukiapics.utils.DisplayUtils

/**
 * Created by Roll on 31/7/17.
 */
class GlideBindingAdapter {
    @BindingAdapter("android:src")
    fun setImageUrl(view: ImageView, url: String?) {
        //square images
        url?.let {
            val width = DisplayUtils.getScreenWidth(view.context) / 2

            Glide.with(view.context)
                    .load(url)
                    .apply(RequestOptions()
                            .override(width, width)
                            .centerCrop())
                    .into(view)
        }
    }
}