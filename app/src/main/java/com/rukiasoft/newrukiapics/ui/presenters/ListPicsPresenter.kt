package com.rukiasoft.newrukiapics.ui.presenters

import android.view.View
import com.rukiasoft.newrukiapics.di.scope.CustomScopes
import com.rukiasoft.newrukiapics.model.Pic
import com.rukiasoft.newrukiapics.network.interfaces.NetworkManager
import com.rukiasoft.newrukiapics.ui.interfaces.ListPicsContracts
import com.rukiasoft.newrukiapics.ui.observables.MyCustomObservable
import com.rukiasoft.newrukiapics.utils.FlickrConstants
import com.rukiasoft.newrukiapics.utils.LogHelper
import javax.inject.Inject

/**
 * Created by Roll on 28/7/17.
 */
@CustomScopes.ListPicsScope
class ListPicsPresenter @Inject constructor() :ListPicsContracts.PresenterContracts {

    private var mView : ListPicsContracts.ViewContracts? = null

    @Inject
    protected lateinit var network : NetworkManager

    @Inject
    protected lateinit var log: LogHelper

    override fun downloadPics(listOfPics: MyCustomObservable<MutableList<Pic>>, tags: String, order: FlickrConstants.Order) {
        mView?.let {
            mView!!.setTags(tags)
        }
        network.getPics(tags = tags, order = order, listOfPics = listOfPics)
    }

    override fun downloadPicsFromSearch(tags: String):Boolean {
        mView?.let {
            mView!!.cleanData()
            val order = mView!!.getSelectedOrder()
            val listOfPics = mView!!.getPicsFromCache(order)
            downloadPics(listOfPics, tags, order)
        }
        return true
    }

    override fun observerListOfPics(order: FlickrConstants.Order) {
        mView?.let {
            mView!!.addObserverToObservable(order, this::handleChangeInList)
        }
    }

    /***
     * handles the change on observed list of pics
     */
    private fun handleChangeInList(listOfPics: MutableList<Pic>?){
        log.d(this, "callback en presenter")
        mView!!.hideProgressBar()
        listOfPics?.let {
            when(listOfPics.isEmpty()){
                true -> mView!!.showNoDataFromNetwork()
                false -> mView!!.setPicsInUI(pics = listOfPics)
            }
        }
    }

    override fun setDataFromNetworkOrCache(listOfPics: MyCustomObservable<MutableList<Pic>>) {
        mView?.let {
            if (listOfPics.getObservableValue() == null) {
                mView!!.showProgressBar()
                downloadPics(listOfPics = listOfPics, tags = mView!!.getTags(), order = mView!!.getSelectedOrder())
            } else {
                mView!!.setPicsInUI(listOfPics.getObservableValue()!!)
                log.d(this, "estaban en cache")
            }
        }
    }

    override fun picClicked(view: View, pic: Pic) {
        log.d(this, "pulsada la foto: " + pic.picUrl)
        mView?.let {
            mView!!.showPicDetails(pic)
        }
    }

    override fun bindView(view: ListPicsContracts.ViewContracts) {
        mView = view
    }

    override fun unbindView() {
        mView = null
    }


}
