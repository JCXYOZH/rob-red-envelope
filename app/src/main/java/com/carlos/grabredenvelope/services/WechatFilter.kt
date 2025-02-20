package com.carlos.grabredenvelope.services

import android.view.accessibility.AccessibilityNodeInfo
import com.carlos.cutils.util.AccessibilityServiceUtils
import com.carlos.cutils.util.LogUtils
import com.carlos.grabredenvelope.data.RedEnvelopePreferences

object WechatFilter {

    fun isNotifacationFilter(text: String?) : Boolean{
        val grabNots = RedEnvelopePreferences.grabFilter.split("@")
        for (grabNot in grabNots) {
//            LogUtils.d("grabNot:$grabNot-text:$text")
            if (grabNot.contains(text?:"")) return true
        }
        return false
    }

    fun isRemarkFilter(accessibilityNodeInfo: AccessibilityNodeInfo?) : Boolean{
        val grabNots = RedEnvelopePreferences.grabFilter.split("@")
        for (grabNot in grabNots) {
            if(AccessibilityServiceUtils.isExistNodeInfosByText(grabNot, accessibilityNodeInfo))
                return true
        }
        return false
    }
}