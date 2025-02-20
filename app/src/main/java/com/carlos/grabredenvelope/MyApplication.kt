package com.carlos.grabredenvelope

import android.app.Application
import androidx.multidex.MultiDexApplication
import com.carlos.cutils.CUtils
import com.carlos.cutils.util.LogUtils

class MyApplication : MultiDexApplication() {

    override fun onCreate() {
        super.onCreate()
        LogUtils.d("onCreate")

        instance = this

        CUtils.init(this)

        AppInit()

    }

    companion object {
        lateinit var instance: MyApplication
            private set
    }
}
