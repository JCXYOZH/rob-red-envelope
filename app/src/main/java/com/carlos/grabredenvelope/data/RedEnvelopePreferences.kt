package com.carlos.grabredenvelope.data

import com.carlos.cutils.base.CBasePreferences
import com.carlos.cutils.util.LogUtils
import com.carlos.grabredenvelope.MyApplication
import com.carlos.grabredenvelope.dao.WechatControlVO
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

object RedEnvelopePreferences :
    CBasePreferences("redenvelope_preferences", MyApplication.instance.applicationContext) {

    private const val WECHAT_CONTROL = "wechat_control"
    private const val USE_STATUS = "use_status"
    private const val EMOJI_TEXT = "emoji_text"
    private const val EMOJI_TIMES = "emoji_times"
    private const val EMOJI_INTERVAL = "emoji_interval"
    private const val STOP_TIME = "stop_time"
    private const val GRAB_FILTER = "grab_filter"

    var wechatControl: WechatControlVO
        get() {
            val data = getString(WECHAT_CONTROL, "")
            if (data.isNullOrEmpty()) return WechatControlVO()
            return try {
                Json.decodeFromString(data)
            } catch (e: Exception) {
                LogUtils.e("error:", e)
                setString(
                    WECHAT_CONTROL,
                    Json.encodeToString(WechatControlVO())
                )
                WechatControlVO()
            }
        }
        set(value) {
            setString(WECHAT_CONTROL, Json.encodeToString(value))
        }

    var useStatus: Boolean
        get() = getBoolean(USE_STATUS, true)
        set(value) {
            setBoolean(USE_STATUS, value)
        }

    var autoText: String
        get() = getString(EMOJI_TEXT, "[烟花]")
        set(value) {
            setString(EMOJI_TEXT, value)
        }

    var emojiTimes: Int
        get() = getInt(EMOJI_TIMES, 1)
        set(value) {
            setInt(EMOJI_TIMES, value)
        }

    var emojiInterval: Int
        get() = getInt(EMOJI_INTERVAL, 1)
        set(value) {
            setInt(EMOJI_INTERVAL, value)
        }

    var stopTime: String
        get() = getString(STOP_TIME, "")
        set(value) {
            setString(STOP_TIME, value)
        }

    var grabFilter: String
        get() = getString(GRAB_FILTER, "")
        set(value) {
            setString(GRAB_FILTER, value)
        }

}