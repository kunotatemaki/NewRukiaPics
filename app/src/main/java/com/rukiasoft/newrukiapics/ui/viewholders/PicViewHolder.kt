package com.rukiasoft.newrukiapics.ui.viewholders

import android.support.v7.widget.RecyclerView
import com.rukiasoft.newrukiapics.BR
import com.rukiasoft.newrukiapics.databinding.PicItemBinding
import com.rukiasoft.newrukiapics.model.Pic
import com.rukiasoft.newrukiapics.ui.interfaces.ListPicsContracts
import com.bumptech.glide.request.RequestOptions.circleCropTransform
import com.bumptech.glide.Glide
import android.databinding.BindingAdapter
import android.widget.ImageView


/**
 * Created by Roll on 28/7/17.
 */
class PicViewHolder(val binding: PicItemBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(pic: Pic, presenter: ListPicsContracts.PresenterContracts) {
        binding.setVariable(BR.pic, pic)
        binding.setVariable(BR.presenter, presenter)
        binding.cardviewItem.tag = pic
        binding.executePendingBindings()
    }


}