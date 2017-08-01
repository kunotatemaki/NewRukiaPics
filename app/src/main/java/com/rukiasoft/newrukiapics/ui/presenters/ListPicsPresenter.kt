package com.rukiasoft.newrukiapics.ui.presenters

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Observer
import android.view.View
import com.rukiasoft.newrukiapics.di.scope.CustomScopes
import com.rukiasoft.newrukiapics.model.Pic
import com.rukiasoft.newrukiapics.network.interfaces.NetworkManager
import com.rukiasoft.newrukiapics.ui.interfaces.ListPicsContracts
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

    override fun downloadPics(listOfPics: MutableLiveData<MutableList<Pic>>, tags: String, order: FlickrConstants.Order) {
        network?.getPics(tags = tags, order = order, listOfPics = listOfPics)
    }

    override fun observerListOfPics(listOfPics: MutableLiveData<MutableList<Pic>>) {
        mView?.let {
            listOfPics.observe(mView!!.getLifecycleOwner(), Observer {
                log.d(this, "callback en presenter")
                mView!!.hideProgressBar()
                listOfPics.value?.let {
                    mView!!.setPicsInUI(pics = listOfPics.value!!)
                }
            })
        }
    }

    override fun setDataFromNetworkOrCache(listOfPics: MutableLiveData<MutableList<Pic>>) {
        mView?.let {
            if (listOfPics.value == null) {
                mView!!.showProgressBar()
                downloadPics(listOfPics = listOfPics, tags = "perros", order = mView!!.getSelectedOrder())
            } else {
                mView!!.setPicsInUI(listOfPics.value!!)
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
        mView = view;
    }

    override fun unbindView() {
        mView = null;
    }


}
