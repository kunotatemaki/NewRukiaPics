package com.rukiasoft.newrukiapics.ui.observables

/**
 * Created by Roll on 3/8/17.
 */
interface MyCustomObservable<T>{

    fun setObservableValue(observableValue: T)

    fun getObservableValue(): T?



}