package com.rukiasoft.newrukiapics.ui.bindingadapters

import android.databinding.DataBindingComponent
import com.bumptech.glide.Glide

/**
 * Created by Roll on 31/7/17.
 */
class GlideBindingComponent : DataBindingComponent {
    override fun getGlideBindingAdapter(): GlideBindingAdapter {
        return GlideBindingAdapter()
    }

}