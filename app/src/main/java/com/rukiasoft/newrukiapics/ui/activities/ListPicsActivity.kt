package com.rukiasoft.newrukiapics.ui.activities

import android.arch.lifecycle.LifecycleObserver
import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.MutableLiveData
import android.content.Context
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import com.rukiasoft.newrukiapics.FlickrApplication
import com.rukiasoft.newrukiapics.R
import com.rukiasoft.newrukiapics.di.modules.ListPicsModule
import com.rukiasoft.newrukiapics.model.Pic
import com.rukiasoft.newrukiapics.network.interfaces.NetworkManager
import com.rukiasoft.newrukiapics.ui.interfaces.ListPicsContracts
import com.rukiasoft.newrukiapics.utils.FlickrConstants
import com.rukiasoft.newrukiapics.utils.LogHelper
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class ListPicsActivity : BaseActivity(), ListPicsContracts.ViewContracts {

    @Inject
    protected lateinit var network : NetworkManager

    @Inject
    protected lateinit var mPresenter : ListPicsContracts.PresenterContracts

    @Inject
    protected lateinit var context : Context

    @Inject
    protected lateinit var log : LogHelper

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {
                message.setText(R.string.title_home)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_dashboard -> {
                message.setText(R.string.title_dashboard)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_notifications -> {
                message.setText(R.string.title_notifications)
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var app = application as FlickrApplication

        app.mComponent.getListActivityComponent(ListPicsModule()).inject(this)

        log.d(this,"hola")


        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)

        //todo quitar esta inicializacion
        val list = MutableLiveData<List<Pic>>()
        network.getPics(tags = "perros", order = FlickrConstants.Order.PUBLISHED, listOfPics = list)
    }

    override fun showProgressBar() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun hideProgressBar() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun setPicsInUI(pics: List<Pic>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getPicsFromCache() : MutableLiveData<List<Pic>>{
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showToast(message: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getLifecycleOwner() : LifecycleOwner {
        return this
    }

    override fun getPresenter() : ListPicsContracts.PresenterContracts{
        return mPresenter
    }

    override fun registerObserver(observer: LifecycleObserver) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}
