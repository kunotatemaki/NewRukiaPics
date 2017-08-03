package com.rukiasoft.newrukiapics.ui.interfaces

import android.arch.lifecycle.LifecycleObserver
import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.MutableLiveData
import android.view.View
import com.rukiasoft.newrukiapics.model.Pic
import com.rukiasoft.newrukiapics.utils.FlickrConstants


/**
 * Created by Roll on 28/7/17.
 */
interface ListPicsContracts {

    interface ViewContracts{

        fun showProgressBar()

        fun hideProgressBar()

        fun setPicsInUI(pics : MutableList<Pic>)

        fun getPicsFromCache(order: FlickrConstants.Order) : MutableLiveData<MutableList<Pic>>

        fun showNoDataFromNetwork()

        fun getLifecycleOwner() : LifecycleOwner

        fun registerObserver(observer: LifecycleObserver)

        fun getPresenter() : PresenterContracts

        fun getSelectedOrder() : FlickrConstants.Order

        fun showPicDetails(pic: Pic)

        fun getTags(): String

        fun setTags(tags: String)

        fun cleanData()

    }

    interface PresenterContracts{

        fun observerListOfPics(listOfPics: MutableLiveData<MutableList<Pic>>)

        fun setDataFromNetworkOrCache(listOfPics: MutableLiveData<MutableList<Pic>>)

        fun picClicked(view: View, pic: Pic)

        fun bindView(view: ViewContracts)

        fun unbindView()

        fun downloadPics(listOfPics: MutableLiveData<MutableList<Pic>>, tags: String, order: FlickrConstants.Order)

        fun downloadPicsFromSearch(tags: String):Boolean

    }

    interface ObserverContracts{

        fun registerInLifecyclerOwner(lifecycleOwner: ViewContracts)

        fun addObserverToListOfPublishedPics()

        fun addObserverToListOfTakenPics()
    }
}