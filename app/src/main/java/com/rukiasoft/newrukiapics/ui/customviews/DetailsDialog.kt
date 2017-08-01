package com.rukiasoft.newrukiapics.ui.customviews

import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.support.v7.app.AlertDialog
import android.view.KeyEvent
import android.view.ViewGroup
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import android.widget.TextView.OnEditorActionListener
import com.rukiasoft.newrukiapics.R


/**
 * Created by Roll on 1/8/17.
 */
class DetailsDialog : DialogFragment() {


    override fun onCreateDialog(savedInstanceState: Bundle): Dialog {
        // Set a theme on the dialog builder constructor!
        val builder = AlertDialog.Builder(getActivity(), R.style.DetailsDialogTheme)

        builder
                .setTitle("Your title")
                .setMessage("Your message")
                .setPositiveButton("OK", DialogInterface.OnClickListener { dialog, which -> dismiss() })
        return builder.create()
    }
}