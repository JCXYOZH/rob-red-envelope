package com.carlos.grabredenvelope.execption

import com.carlos.cutils.execption.CUncaughtExceptionHandler
import io.sentry.Sentry

class MyUncaughtExceptionHandler : CUncaughtExceptionHandler() {

    override fun uncaughtException(t: Thread?, e: Throwable?) {
        if (t == null || e == null) return
        Sentry.captureException(e)
    }

}