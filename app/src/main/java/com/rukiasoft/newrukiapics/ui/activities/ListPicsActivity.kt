package com.rukiasoft.newrukiapics.ui.activities

import android.content.Context
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import com.rukiasoft.newrukiapics.FlickrApplication
import com.rukiasoft.newrukiapics.R
import com.rukiasoft.newrukiapics.di.modules.ListPicsModule
import com.rukiasoft.newrukiapics.model.Pic
import com.rukiasoft.newrukiapics.network.interfaces.NetworkManager
import com.rukiasoft.newrukiapics.ui.interfaces.ListPicsContracts
import com.rukiasoft.newrukiapics.utils.FlickrConstants
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class ListPicsActivity : BaseActivity(), ListPicsContracts.ViewContracts {

    @Inject
    protected lateinit var network : NetworkManager

    @Inject
    protected lateinit var context : Context

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

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)

        network.getPics(tags = "perros", order = FlickrConstants.Order.PUBLISHED)
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

    override fun getPicsFromCache() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showToast(message: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}
