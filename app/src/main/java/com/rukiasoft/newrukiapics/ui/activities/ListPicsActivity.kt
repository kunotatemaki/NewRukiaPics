package com.rukiasoft.newrukiapics.ui.activities

import android.arch.lifecycle.LifecycleObserver
import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.design.widget.Snackbar
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
import com.rukiasoft.newrukiapics.ui.observables.MyCustomObservable
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

        mBinding.setVariable(BR.presenter, mPresenter)

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

    fun showSettings(view: View) {
        intent = Intent(this, SettingsActivity::class.java)
        startActivity(intent)
    }

    override fun showProgressBar() {
        ViewModelProviders.of(this).get(ListPicsViewModel::class.java).showRefresh = true
        list_content.list_progressbar.visibility = View.VISIBLE
    }

    override fun hideProgressBar() {
        ViewModelProviders.of(this).get(ListPicsViewModel::class.java).showRefresh = false
        list_content.list_progressbar.visibility = View.INVISIBLE
    }

    override fun setPicsInUI(pics: MutableList<Pic>) {
        val adapter = ListPicsAdapter(pics = pics, presenter = mPresenter)
        list_content.pics_recycler_view.swapAdapter(adapter,false)
    }

    override fun getPicsFromCache(order: FlickrConstants.Order) : MyCustomObservable<MutableList<Pic>> {
        val viewModel = ViewModelProviders.of(this).get(ListPicsViewModel::class.java)
        when(order){
            FlickrConstants.Order.PUBLISHED -> {
                return@getPicsFromCache viewModel.picsByPublishedDates
            }
            FlickrConstants.Order.TAKEN -> {
                return@getPicsFromCache viewModel.picsByTakenDates
            }
        }
    }

    override fun showNoDataFromNetwork() {
        val message = getString(R.string.no_response_from_network)
        Snackbar.make(mBinding.root, message, Snackbar.LENGTH_LONG).show()
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
                .setNegativeButton(getString(R.string.close), { dialog, _ ->
                    dialog.cancel()
                })
        //store reference to dialog
        val dialog = builder.create()
        dialog.show()
    }

    override fun getTags(): String{
        return ViewModelProviders.of(this).get(ListPicsViewModel::class.java).tags
    }

    override fun setTags(tags: String){
        ViewModelProviders.of(this).get(ListPicsViewModel::class.java).tags = tags
    }

    override fun cleanData(){
        ViewModelProviders.of(this).get(ListPicsViewModel::class.java).picsByTakenDates.getObservableValue()?.clear()
        ViewModelProviders.of(this).get(ListPicsViewModel::class.java).picsByPublishedDates.getObservableValue()?.clear()
        DisplayUtils.hideSoftKeyboard(this)

    }

    override fun addObserverToObservable(order: FlickrConstants.Order, callback: (listOfPics: MutableList<Pic>?) -> Unit) {
        ViewModelProviders.of(this).get(ListPicsViewModel::class.java).addObserver(order, this, callback)
    }
}
