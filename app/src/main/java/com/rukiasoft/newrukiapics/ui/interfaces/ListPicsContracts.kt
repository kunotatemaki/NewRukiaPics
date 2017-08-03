package com.rukiasoft.newrukiapics.ui.interfaces

import android.arch.lifecycle.LifecycleObserver
import android.arch.lifecycle.LifecycleOwner
import android.view.View
import com.rukiasoft.newrukiapics.model.Pic
import com.rukiasoft.newrukiapics.ui.observables.MyCustomObservable
import com.rukiasoft.newrukiapics.utils.FlickrConstants


/**
 * Created by Roll on 28/7/17.
 */
interface ListPicsContracts {

    interface ViewContracts{

        fun showProgressBar()

        fun hideProgressBar()

        fun setPicsInUI(pics : MutableList<Pic>)

        fun getPicsFromCache(order: FlickrConstants.Order) : MyCustomObservable<MutableList<Pic>>

        fun showNoDataFromNetwork()

        fun registerObserver(observer: LifecycleObserver)

        fun getPresenter() : PresenterContracts

        fun getSelectedOrder() : FlickrConstants.Order

        fun showPicDetails(pic: Pic)

        fun getTags(): String

        fun setTags(tags: String)

        fun cleanData()

        fun addObserverToObservable(order: FlickrConstants.Order, callback: (listOfPics: MutableList<Pic>?) -> Unit)

    }

    interface PresenterContracts{

        //fun observerListOfPics(listOfPics: MyCustomObservable<MutableList<Pic>>)
        fun observerListOfPics(order: FlickrConstants.Order)

        fun setDataFromNetworkOrCache(listOfPics: MyCustomObservable<MutableList<Pic>>)

        fun picClicked(view: View, pic: Pic)

        fun bindView(view: ViewContracts)

        fun unbindView()

        fun downloadPics(listOfPics: MyCustomObservable<MutableList<Pic>>, tags: String, order: FlickrConstants.Order)

        fun downloadPicsFromSearch(tags: String):Boolean

    }

    interface ObserverContracts{

        fun registerInLifecyclerOwner(lifecycleOwner: ViewContracts)

    }
}