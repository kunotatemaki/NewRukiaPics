package com.rukiasoft.newrukiapics.ui.observables

import android.arch.lifecycle.MutableLiveData
import javax.inject.Inject

/**
 * Created by Roll on 3/8/17.
 */
class ListOfPicsObservableAndroid<T> @Inject constructor() : MutableLiveData<T>(), MyCustomObservable<T> {

    override fun setObservableValue(observableValue: T) {
        this.value = observableValue
    }

    override fun getObservableValue(): T? {
        return this.value
    }


}