package com.rukiasoft.newrukiapics.ui.observers

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.LifecycleObserver
import android.arch.lifecycle.OnLifecycleEvent
import com.rukiasoft.newrukiapics.ui.interfaces.ListPicsContracts
import com.rukiasoft.newrukiapics.utils.LogHelper

/**
 * Created by Roll on 28/7/17.
 */
class ListPicsObserver : LifecycleObserver, ListPicsContracts.ObserverContracts {

    private var mLifecycleOwner : ListPicsContracts.ViewContracts? = null

    override fun registerInLifecyclerOwner(view: ListPicsContracts.ViewContracts) {
        view.registerObserver(this)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun injectViewInPresenter() {
        val log = LogHelper()
        log.d(this, "inyecto la vista en el presentador")
        mLifecycleOwner?.let { mLifecycleOwner?.getPresenter()?.bindView(it) }
        //observo la lista de usuarios
        //mLifecycleOwner?.getPresenter()?.observerListOfPics((mLifecycleOwner as ListPicsContracts.ViewContracts).getPicsFromCache())
        //pinto en la pantalla los usuarios
        //mLifecycleOwner?.getPresenter()?.setDataFromNetworkOrCache((mLifecycleOwner as ListPicsContracts.ViewContracts).getPicsFromCache())
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun removeActivityReferenceFromObserver() {
        val log = LogHelper()
        log.d(this, "quito")
        mLifecycleOwner?.getPresenter()?.unbindView()
        mLifecycleOwner = null
    }
}