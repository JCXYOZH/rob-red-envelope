package com.carlos.grabredenvelope.services

import android.os.Bundle
import android.view.accessibility.AccessibilityEvent
import android.view.accessibility.AccessibilityNodeInfo
import android.widget.EditText
import com.carlos.cutils.extend.findAndClickFirstNodeInfoByViewId
import com.carlos.cutils.extend.getNodeInfosByText
import com.carlos.cutils.extend.getNodeInfosByViewId
import com.carlos.cutils.extend.isExistNodeInfosByViewId
import com.carlos.cutils.util.AppUtils
import com.carlos.cutils.util.LogUtils
import com.carlos.grabredenvelope.data.RedEnvelopePreferences
import com.carlos.grabredenvelope.util.WechatConstants
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class EmojiService : BaseAccessibilityService() {

    private var windowClassName = ""
    private var text = ""
    private var times = 0
    private var interval = 0
    private var count = 0
    private var canSend = true

    override fun onCreate() {
        super.onCreate()
        WechatConstants.setVersion(AppUtils.getVersionName(WechatConstants.WECHAT_PACKAGE))
        loadConfig()
        canSend = true
    }

    override fun monitorContentChanged(event: AccessibilityEvent) {
    }

    override fun monitorWindowChanged(event: AccessibilityEvent) {
        LogUtils.d("monitorWindowChanged:$event")
        windowClassName = event.className.toString()

        if (canSend.not()) return
        sendMessage()
    }

    private fun loadConfig() {
        text = RedEnvelopePreferences.autoText
        times = RedEnvelopePreferences.emojiTimes
        interval = RedEnvelopePreferences.emojiInterval
        count = 0
        LogUtils.d("text:$text")
        LogUtils.d("times:$times")
        LogUtils.d("interval:$interval")
        LogUtils.d("count:$count")
    }

    /**
     * 找到文本框输入表情，找到发送按钮点击循环执行
     */
    private fun sendMessage() {
//        if (windowClassName != CHAT_ACTIVITY) return
        if (count >= times && times != 0) {
            return
        }
        LogUtils.d("count:$count")

        val accessibilityNodeInfo = getNodeInfosByViewId(WechatConstants.CHAT_EDITTEXT_ID)?: return

        for (editText in accessibilityNodeInfo) {
            if (editText.className == EditText::class.java.name) {
                val arguments = Bundle()
                arguments.putCharSequence(
                    AccessibilityNodeInfo.ACTION_ARGUMENT_SET_TEXT_CHARSEQUENCE,
                    text
                )
                editText.performAction(AccessibilityNodeInfo.ACTION_SET_TEXT, arguments)

                findAndClickFirstNodeInfoByViewId(WechatConstants.SEND_TEXT_ID)

                canSend =false

                count++
                GlobalScope.launch {
                    delay(interval.toLong())
                    sendMessage()
                }
            }
        }
    }
}
