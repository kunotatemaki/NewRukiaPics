package com.rukiasoft.newrukiapics.preferences.implementations

import android.content.Context
import android.preference.PreferenceManager
import javax.inject.Inject

/**
 * Created by Roll on 2/8/17.
 */
class AndroidPreferences @Inject constructor(){
    @Inject
    lateinit var context: Context

    fun getIntFromPreferences(key: String): Int{
        val prefs = PreferenceManager.getDefaultSharedPreferences(context)
        return prefs.getInt(key, -1)
    }
    fun getStringFromPreferences(key: String): String{
        val prefs = PreferenceManager.getDefaultSharedPreferences(context)
        return prefs.getString(key, "")
    }
    fun getBooleanFromPreferences(key: String): Boolean{
        val prefs = PreferenceManager.getDefaultSharedPreferences(context)
        return prefs.getBoolean(key, false)
    }
    fun getLongFromPreferences(key: String): Long{
        val prefs = PreferenceManager.getDefaultSharedPreferences(context)
        return prefs.getLong(key, (-1).toLong())
    }
    fun getFloatFromPreferences(key: String): Float{
        val prefs = PreferenceManager.getDefaultSharedPreferences(context)
        return prefs.getFloat(key, (-1).toFloat())
    }

    fun setIntFromPreferences(key: String, value: Int){
        val prefs = PreferenceManager.getDefaultSharedPreferences(context)
        prefs.edit().putInt(key, value).apply()
    }
    fun setStringFromPreferences(key: String, value: String){
        val prefs = PreferenceManager.getDefaultSharedPreferences(context)
        prefs.edit().putString(key, value).apply()
    }
    fun setBooleanFromPreferences(key: String, value: Boolean){
        val prefs = PreferenceManager.getDefaultSharedPreferences(context)
        prefs.edit().putBoolean(key, value).apply()
    }
    fun setLongFromPreferences(key: String, value: Long){
        val prefs = PreferenceManager.getDefaultSharedPreferences(context)
        prefs.edit().putLong(key, value).apply()
    }
    fun setFloatFromPreferences(key: String, value: Float){
        val prefs = PreferenceManager.getDefaultSharedPreferences(context)
        prefs.edit().putFloat(key, value).apply()
    }






}