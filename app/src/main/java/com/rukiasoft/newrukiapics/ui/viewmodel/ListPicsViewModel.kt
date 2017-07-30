package com.rukiasoft.newrukiapics.ui.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.rukiasoft.newrukiapics.model.Pic

/**
 * Created by Roll on 28/7/17.
 */
class ListPicsViewModel: ViewModel() {
    lateinit var picsByPublishedDates: MutableLiveData<List<Pic>>
    lateinit var picsByOrderedDates: MutableLiveData<List<Pic>>
}