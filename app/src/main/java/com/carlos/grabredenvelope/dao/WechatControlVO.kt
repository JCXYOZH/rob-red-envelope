package com.carlos.grabredenvelope.dao

import kotlinx.serialization.Serializable

@Serializable
data class WechatControlVO(
    @Transient
    var isMonitorNotification: Boolean = true,  // 是否监控通知
    var isMonitorChat: Boolean = true,  // 是否监控聊天列表页面
    var ifGrabSelf: Boolean = true,  // 是否抢自己发的红包
    @Transient
    var delayOpenTime: Int = 0,
    @Transient
    var delayCloseTime: Int = 1,
    @Transient
    var isCustomClick: Boolean = false,
    var pointX: Long = 0,
    var pointY: Long = 0
)