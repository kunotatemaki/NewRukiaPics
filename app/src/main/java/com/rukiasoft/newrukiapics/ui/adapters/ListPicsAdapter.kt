package com.rukiasoft.newrukiapics.ui.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.rukiasoft.newrukiapics.databinding.PicItemBinding
import com.rukiasoft.newrukiapics.model.Pic
import com.rukiasoft.newrukiapics.ui.bindingadapters.GlideBindingComponent
import com.rukiasoft.newrukiapics.ui.interfaces.ListPicsContracts
import com.rukiasoft.newrukiapics.ui.viewholders.PicViewHolder

/**
 * Created by Roll on 28/7/17.
 */
class ListPicsAdapter(val pics: List<Pic>, val presenter: ListPicsContracts.PresenterContracts) :
RecyclerView.Adapter<PicViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): PicViewHolder {
        val inflater = LayoutInflater.from(parent?.context)
        val binding = PicItemBinding.inflate(
                inflater,
                GlideBindingComponent()
        )

        return PicViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PicViewHolder?, position: Int) {
        val pic: Pic = pics[position]
        holder?.bind(pic, presenter)
    }

    override fun getItemCount(): Int {
        return pics.size
    }



}