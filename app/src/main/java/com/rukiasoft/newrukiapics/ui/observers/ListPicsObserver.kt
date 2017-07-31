package com.rukiasoft.newrukiapics.ui.observers

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.LifecycleObserver
import android.arch.lifecycle.OnLifecycleEvent
import com.rukiasoft.newrukiapics.ui.interfaces.ListPicsContracts
import com.rukiasoft.newrukiapics.utils.FlickrConstants
import com.rukiasoft.newrukiapics.utils.LogHelper
import javax.inject.Inject

/**
 * Created by Roll on 28/7/17.
 */
class ListPicsObserver  /*@Inject constructor()*/: LifecycleObserver, ListPicsContracts.ObserverContracts {

    private var mLifecycleOwner : ListPicsContracts.ViewContracts? = null

    override fun registerInLifecyclerOwner(view: ListPicsContracts.ViewContracts) {
        mLifecycleOwner = view
        view.registerObserver(this)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun injectViewInPresenterAndRegisterForListEvents() {
        val log = LogHelper()
        log.d(this, "inyecto la vista en el presentador")
        mLifecycleOwner?.let {
            //inject view in presenter
            mLifecycleOwner?.getPresenter()?.bindView(it)
            //force presenter to observe list of pics
            mLifecycleOwner?.getPresenter()?.observerListOfPics(mLifecycleOwner!!
                    .getPicsFromCache(FlickrConstants.Order.PUBLISHED))
            mLifecycleOwner?.getPresenter()?.observerListOfPics(mLifecycleOwner!!
                    .getPicsFromCache(FlickrConstants.Order.TAKEN))
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun removeActivityReferenceFromObserver() {
        val log = LogHelper()
        log.d(this, "quito")
        mLifecycleOwner?.getPresenter()?.unbindView()
        mLifecycleOwner = null
    }
}