package com.rukiasoft.newrukiapics.ui.activities

import android.arch.lifecycle.LifecycleObserver
import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModelProviders
import android.content.DialogInterface
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AlertDialog
import android.support.v7.widget.GridLayoutManager
import android.view.View
import com.rukiasoft.newrukiapics.BR
import com.rukiasoft.newrukiapics.FlickrApplication
import com.rukiasoft.newrukiapics.R
import com.rukiasoft.newrukiapics.databinding.ListPicsActivityBinding
import com.rukiasoft.newrukiapics.databinding.PicDetailsBinding
import com.rukiasoft.newrukiapics.di.modules.ListPicsModule
import com.rukiasoft.newrukiapics.model.Pic
import com.rukiasoft.newrukiapics.ui.adapters.ListPicsAdapter
import com.rukiasoft.newrukiapics.ui.interfaces.ListPicsContracts
import com.rukiasoft.newrukiapics.ui.viewmodel.ListPicsViewModel
import com.rukiasoft.newrukiapics.utils.DisplayUtils
import com.rukiasoft.newrukiapics.utils.FlickrConstants
import com.rukiasoft.newrukiapics.utils.LogHelper
import kotlinx.android.synthetic.main.content_list_pics_activity.view.*
import kotlinx.android.synthetic.main.list_pics_activity.*
import javax.inject.Inject


class ListPicsActivity : BaseActivity(), ListPicsContracts.ViewContracts {

    @Inject
    protected lateinit var mPresenter : ListPicsContracts.PresenterContracts

    @Inject
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

        //set toolbar
        setToolbar(toolbar = list_toolbar, backArrow = false)
        //not allow refresh on swipe
        //list_content.swipe_refresh_layout.isEnabled = false
        //initialize recyclerview
        val columns : Int = DisplayUtils.calculateNoOfColumns(applicationContext)
        val layout = GridLayoutManager(applicationContext, columns, GridLayoutManager.VERTICAL, false)
        list_content.pics_recycler_view.layoutManager = layout
        list_content.pics_recycler_view.setHasFixedSize(true)

        //observer
        mObserver.registerInLifecyclerOwner(this)

    }

    override fun onStart() {
        super.onStart()
        //set clicklistener for navigation
        list_content.navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        //force navigation to last state -> or published if first time
        list_content.navigation.selectedItemId = getIdFromOrder(getSelectedOrder())
    }

    override fun onResume() {
        super.onResume()
        when(ViewModelProviders.of(this).get(ListPicsViewModel::class.java).showRefresh){
            true -> showProgressBar()
            false -> hideProgressBar()
        }
    }

    override fun showProgressBar() {
        ViewModelProviders.of(this).get(ListPicsViewModel::class.java).showRefresh = true
        list_content.list_progressbar.visibility = View.VISIBLE
    }

    override fun hideProgressBar() {
        ViewModelProviders.of(this).get(ListPicsViewModel::class.java).showRefresh = true
        list_content.list_progressbar.visibility = View.INVISIBLE
    }

    override fun setPicsInUI(pics: MutableList<Pic>) {
        val adapter = ListPicsAdapter(pics = pics, presenter = mPresenter)
        list_content.pics_recycler_view.swapAdapter(adapter,false)
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
        lifecycle.addObserver(observer)
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

    override fun showPicDetails(pic: Pic) {
        val builder = AlertDialog.Builder(this)
        val detailsBinding = PicDetailsBinding.inflate(layoutInflater)
        detailsBinding.setVariable(BR.pic, pic)
        builder.setView(detailsBinding.root)
                .setNegativeButton(getString(R.string.close), DialogInterface.OnClickListener { dialog, id ->
                    dialog.cancel()
                })
        //store reference to dialog
        val dialog = builder.create()
        dialog.show()
    }

}
