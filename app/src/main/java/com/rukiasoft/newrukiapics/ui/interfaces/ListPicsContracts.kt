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

        fun setPicsInUI(pics : List<Pic>)

        fun getPicsFromCache(order: FlickrConstants.Order) : MutableLiveData<List<Pic>>

        fun showToast(message : String)

        fun getLifecycleOwner() : LifecycleOwner

        fun registerObserver(observer: LifecycleObserver)

        fun getPresenter() : PresenterContracts

    }

    interface PresenterContracts{

        fun observerListOfPics(listOfPics: MutableLiveData<List<Pic>>)

        fun setDataFromNetworkOrCache(listOfPics: MutableLiveData<List<Pic>>)

        fun cardClicked(view: View, pic: Pic)

        fun bindView(view: ViewContracts)

        fun unbindView()

        fun downloadPics(listOfPics: MutableLiveData<List<Pic>>, tags: String, order: FlickrConstants.Order)
    }

    interface ObserverContracts{

        fun registerInLifecyclerOwner(view: ViewContracts)
    }
}