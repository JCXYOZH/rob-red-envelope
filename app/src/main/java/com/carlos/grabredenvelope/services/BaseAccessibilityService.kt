package com.carlos.grabredenvelope.services

import android.app.Notification
import android.app.PendingIntent
import android.app.Service
import android.content.Intent
import android.graphics.BitmapFactory
import android.view.accessibility.AccessibilityEvent
import com.carlos.cutils.base.CBaseAccessibilityService
import com.carlos.cutils.util.LogUtils
import com.carlos.grabredenvelope.MyApplication
import com.carlos.grabredenvelope.R
import com.carlos.grabredenvelope.activity.MainActivity
import com.carlos.grabredenvelope.util.ControlUse
import io.sentry.Sentry

abstract class BaseAccessibilityService : CBaseAccessibilityService() {

    private lateinit var controlUse: ControlUse
    open var notificationTitle: String = ""

    /* 状态切换，流程更直观，避免人为点击的误操作，等待红包——点击红包关键字——点击红包——拆红包——等待红包循环 */
    var status: Int = WAIT_NEW
    companion object {
        const val WAIT_NEW = 0  // 等待新的红包
        const val HAS_RECEIVED = 1  // 通知或聊天列表页面收到红包
        const val HAS_CLICKED = 2  // 已点击红包弹出了红包框
        const val HAS_OPENED = 3  // 点击了拆开红包按钮
    }

    override fun onCreate() {
        super.onCreate()
        LogUtils.d("服务 onCreate上")
        controlUse = ControlUse(applicationContext)
        if (controlUse.stopUse()) isMonitor = false
    }

    override fun onDestroy() {
        super.onDestroy()
        LogUtils.d("服务 onDestroy上")
    }

    override fun onInterrupt() {
        LogUtils.e("服务中断时的服务")
        Sentry.captureMessage("${this.javaClass.name} onInterrupt")
    }

    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {
        var flags = flags
        LogUtils.d("service onstart 命令")
        val builder = Notification.Builder(MyApplication.instance.applicationContext)
        val notificationIntent = Intent(this, MainActivity::class.java)

        builder.setContentIntent(PendingIntent.getActivity(this, 0, notificationIntent, 0))
            .setLargeIcon(
                BitmapFactory.decodeResource(
                    this.resources,
                    R.mipmap.xiaobudian
                )
            )  // 在下拉列表中设置大图标
            .setContentTitle(getString(R.string.app_name))  // 设置标题在下拉列表中
            .setSmallIcon(R.mipmap.xiaobudian)  // 在 State中设置小图标
            .setContentText(getString(R.string.app_content))  // 设置上下文内容
            .setWhen(System.currentTimeMillis())  // 设置通知发生的时间

        val notification = builder.build()
        notification.defaults = Notification.DEFAULT_SOUND  // 设置默认声音

        startForeground(110, notification)
        flags = Service.START_FLAG_REDELIVERY
        return super.onStartCommand(intent, flags, startId)
    }


    override fun monitorNotificationChanged(event: AccessibilityEvent) {
        val text = event.text.toString()
        if (status!= WAIT_NEW) return
        if (text.isEmpty() or notificationTitle.isNullOrEmpty()) {
            return
        }
        if (text.contains(notificationTitle).not()) return
        LogUtils.d("通知包含红包")
        try {
            val notification = event.parcelableData as Notification
            val pendingIntent = notification.contentIntent
            pendingIntent.send()
            status = HAS_RECEIVED
            LogUtils.d("单击红包通知")
        } catch (e: PendingIntent.CanceledException) {
            e.printStackTrace()
        }
    }
}