package com.rukiasoft.newrukiapics.ui.activities

import android.arch.lifecycle.LifecycleRegistry
import android.arch.lifecycle.LifecycleRegistryOwner
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.text.TextUtils
import android.widget.TextView


/**
 * Created by Roll on 28/7/17.
 */
open class BaseActivity : AppCompatActivity(), LifecycleRegistryOwner {

    val mLifecycle = LifecycleRegistry(this)

    protected fun setToolbar(toolbar: Toolbar, backArrow: Boolean) {
        setSupportActionBar(toolbar)
        if (supportActionBar != null) {
            supportActionBar!!.setDisplayShowTitleEnabled(true)
            supportActionBar!!.setDisplayHomeAsUpEnabled(backArrow)
            try {
                if (toolbar::class != null) {
                    val f = toolbar::class.java.getDeclaredField("mTitleTextView")
                    f.setAccessible(true)
                    val titleTextView = f.get(toolbar) as TextView
                    titleTextView.ellipsize = TextUtils.TruncateAt.MARQUEE
                    titleTextView.isFocusable = true
                    titleTextView.isFocusableInTouchMode = true
                    titleTextView.requestFocus()
                    titleTextView.setSingleLine(true)
                    titleTextView.isSelected = true
                    titleTextView.marqueeRepeatLimit = -1
                }
            } catch (e: NoSuchFieldException) {
                e.printStackTrace()
            } catch (e: IllegalAccessException) {
                e.printStackTrace()
            }

        }

    }

    override fun getLifecycle(): LifecycleRegistry {
        return mLifecycle
    }

}
