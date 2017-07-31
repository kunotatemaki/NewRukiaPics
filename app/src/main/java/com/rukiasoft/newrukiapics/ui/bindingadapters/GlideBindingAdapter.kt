package com.rukiasoft.newrukiapics.ui.bindingadapters

import android.databinding.BindingAdapter
import android.widget.ImageView
import com.bumptech.glide.Glide

/**
 * Created by Roll on 31/7/17.
 */
class GlideBindingAdapter {
    @BindingAdapter("android:src")
    fun setImageUrl(view: ImageView, url: String) {
        Glide.with(view.context)
                .load(url)
                .into(view)
    }
}