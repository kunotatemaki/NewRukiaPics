package com.rukiasoft.newrukiapics.utils

import android.util.Log
import javax.inject.Inject


/**
 * Created by Roll on 29/7/17.
 */
class LogHelper @Inject constructor(){
    private val LOG_PREFIX = "Rukia_"
    private val LOG_PREFIX_LENGTH = LOG_PREFIX.length
    private val MAX_LOG_TAG_LENGTH = 23

    private fun makeLogTag(str: String): String {
        if (str.length > MAX_LOG_TAG_LENGTH - LOG_PREFIX_LENGTH) {
            return LOG_PREFIX + str.substring(0, MAX_LOG_TAG_LENGTH - LOG_PREFIX_LENGTH - 1)
        }

        return LOG_PREFIX + str
    }

    fun v(theClass: Any, vararg messages: Any) {
        // Only log VERBOSE if build type is DEBUG
        //if (BuildConfig.DEBUG) {
        log(theClass, Log.VERBOSE, null, *messages)
        //}
    }

    fun d(theClass: Any, vararg messages: Any) {
        // Only log DEBUG if build type is DEBUG
        //if (BuildConfig.DEBUG) {
        log(theClass, Log.DEBUG, null, *messages)
        //}
    }

    fun i(theClass: Any, vararg messages: Any) {
        log(theClass, Log.INFO, null, *messages)
    }

    fun w(theClass: Any, vararg messages: Any) {
        log(theClass, Log.WARN, null, *messages)
    }


    fun e(theClass: Any, vararg messages: Any) {
        log(theClass, Log.ERROR, null, *messages)
    }



    fun log(theClass: Any, level: Int, t: Throwable?, vararg messages: Any) {
        val tag = makeLogTag(theClass::class.java.simpleName.toString())
        if (Log.isLoggable(tag, level)) {
            val message: String
            if (t == null && messages != null && messages.size == 1) {
                // handle this common case without the extra cost of creating a stringbuffer:
                message = messages[0].toString()
            } else {
                val sb = StringBuilder()
                if (messages != null)
                    for (m in messages) {
                        sb.append(m)
                    }
                if (t != null) {
                    sb.append("\n").append(Log.getStackTraceString(t))
                }
                message = sb.toString()
            }
            Log.println(level, tag, message)
        }
    }
}