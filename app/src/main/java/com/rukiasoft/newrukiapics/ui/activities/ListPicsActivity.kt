package com.rukiasoft.newrukiapics.ui.activities

import android.arch.lifecycle.LifecycleObserver
import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.widget.StaggeredGridLayoutManager
import android.view.View
import com.rukiasoft.newrukiapics.FlickrApplication
import com.rukiasoft.newrukiapics.R
import com.rukiasoft.newrukiapics.databinding.ListPicsActivityBinding
import com.rukiasoft.newrukiapics.di.modules.ListPicsModule
import com.rukiasoft.newrukiapics.model.Pic
import com.rukiasoft.newrukiapics.network.interfaces.NetworkManager
import com.rukiasoft.newrukiapics.ui.adapters.ListPicsAdapter
import com.rukiasoft.newrukiapics.ui.interfaces.ListPicsContracts
import com.rukiasoft.newrukiapics.ui.observers.ListPicsObserver
import com.rukiasoft.newrukiapics.ui.viewmodel.ListPicsViewModel
import com.rukiasoft.newrukiapics.utils.DisplayUtils
import com.rukiasoft.newrukiapics.utils.FlickrConstants
import com.rukiasoft.newrukiapics.utils.LogHelper
import kotlinx.android.synthetic.main.list_pics_activity.*
import javax.inject.Inject

class ListPicsActivity : BaseActivity(), ListPicsContracts.ViewContracts {

    @Inject
    protected lateinit var mPresenter : ListPicsContracts.PresenterContracts

    //@Inject
    protected lateinit var mObserver : ListPicsContracts.ObserverContracts

    @Inject
    protected lateinit var log : LogHelper

    private lateinit var mBinding: ListPicsActivityBinding

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_published -> {
                ViewModelProviders.of(this).get(ListPicsViewModel::class.java).lastSelectedOrder = FlickrConstants.Order.PUBLISHED
                mPresenter.setDataFromNetworkOrCache(getPicsFromCache(order = FlickrConstants.Order.PUBLISHED))
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_taken -> {
                ViewModelProviders.of(this).get(ListPicsViewModel::class.java).lastSelectedOrder = FlickrConstants.Order.TAKEN
                mPresenter.setDataFromNetworkOrCache(getPicsFromCache(order = FlickrConstants.Order.TAKEN))
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, R.layout.list_pics_activity)

        //inject dependencies
        (application as FlickrApplication).mComponent.getListActivityComponent(ListPicsModule()).inject(this)

        //initialize recyclerview
        val columns : Int = DisplayUtils.calculateNoOfColumns(applicationContext)
        val layout = StaggeredGridLayoutManager(columns, StaggeredGridLayoutManager.VERTICAL)
        pics_recycler_view.layoutManager = layout
        pics_recycler_view.setHasFixedSize(true)

        //observer
        mObserver = ListPicsObserver()
        mObserver.registerInLifecyclerOwner(this)

    }

    override fun onResume() {
        super.onResume()
        //set clicklistener for navigation
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        //force navigation to last state -> or published if first time
        navigation.selectedItemId = getIdFromOrder(getSelectedOrder())
    }

    override fun showProgressBar() {
        swipe_refresh_layout.visibility = View.VISIBLE
    }

    override fun hideProgressBar() {
        swipe_refresh_layout.visibility = View.INVISIBLE
    }

    override fun setPicsInUI(pics: MutableList<Pic>) {
        val adapter = ListPicsAdapter(pics = pics, presenter = mPresenter)
        pics_recycler_view.adapter =adapter
    }

    override fun getPicsFromCache(order: FlickrConstants.Order) : MutableLiveData<MutableList<Pic>> {
        val viewModel = ViewModelProviders.of(this).get(ListPicsViewModel::class.java)
        when(order){
            FlickrConstants.Order.PUBLISHED -> {
                return@getPicsFromCache viewModel.picsByPublishedDates
            }
            FlickrConstants.Order.TAKEN -> {
                return@getPicsFromCache viewModel.picsByOrderedDates
            }
        }
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
        getLifecycle().addObserver(observer)
    }

    override fun getSelectedOrder(): FlickrConstants.Order {
        return ViewModelProviders.of(this).get(ListPicsViewModel::class.java).lastSelectedOrder
    }

    private fun getIdFromOrder(order: FlickrConstants.Order) : Int{
        when(order){
            FlickrConstants.Order.PUBLISHED -> {return@getIdFromOrder R.id.navigation_published}
            FlickrConstants.Order.TAKEN -> {return@getIdFromOrder R.id.navigation_taken}
        }
    }

}
