package com.rukiasoft.newrukiapics.ui.presenters

import android.arch.lifecycle.MutableLiveData
import android.view.View
import com.rukiasoft.newrukiapics.model.Pic
import com.rukiasoft.newrukiapics.network.interfaces.NetworkManager
import com.rukiasoft.newrukiapics.ui.interfaces.ListPicsContracts
import com.rukiasoft.newrukiapics.utils.FlickrConstants
import com.rukiasoft.newrukiapics.utils.LogHelper
import javax.inject.Inject

/**
 * Created by Roll on 28/7/17.
 */
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
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun setDataFromNetworkOrCache(listOfPics: MutableLiveData<MutableList<Pic>>) {
        if(listOfPics.value == null){
            network.getPics(tags = "perros", order = FlickrConstants.Order.PUBLISHED, listOfPics = listOfPics)
        }else{
            mView?.setPicsInUI(listOfPics.value!!)
            log.d(this, "estaban en cache")
        }

        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun cardClicked(view: View, pic: Pic) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun bindView(view: ListPicsContracts.ViewContracts) {
        mView = view;
    }

    override fun unbindView() {
        mView = null;
    }


}
