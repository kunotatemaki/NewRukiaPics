package com.rukiasoft.newrukiapics.ui.interfaces

import com.rukiasoft.newrukiapics.model.Pic
import android.arch.lifecycle.MutableLiveData
import android.view.View
import com.rukiasoft.newrukiapics.utils.FlickrConstants


/**
 * Created by Roll on 28/7/17.
 */
interface ListPicsContracts {

    interface ViewContracts{

        fun showProgressBar()

        fun hideProgressBar()

        fun setPicsInUI(pics : List<Pic>)

        fun getPicsFromCache()

        fun showToast(message : String)
    }

    interface PresenterContracts{

        fun observerListOfPics(pic: MutableLiveData<List<Pic>>)

        fun setDataFromNetworkOrCache(pic: MutableLiveData<List<Pic>>)

        fun cardClicked(view: View, pic: Pic)

        fun bindView(view: ViewContracts)

        fun unbindView()

        fun downloadPics(pic: MutableLiveData<List<Pic>>, tags: String, order: FlickrConstants.Order)
    }

    interface ObserverContracts{

        fun registerLifecyclerOwner(view: ViewContracts)
    }
}