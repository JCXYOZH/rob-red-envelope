package com.carlos.grabredenvelope.util

import android.content.Context
import android.text.TextUtils
import android.util.Log
import com.carlos.cutils.util.AppUtils
import com.carlos.cutils.util.LogUtils
import com.carlos.cutils.util.NetUtils
import com.carlos.grabredenvelope.data.RedEnvelopePreferences
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.json.JSONObject
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

class ControlUse(private val context: Context) {

    private var message: String? = null
    private var isStop = false

    init {
        getStopTime()
        setLimitTime()
    }

    fun setLimitTime() {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)
        val hour = calendar.get(Calendar.HOUR_OF_DAY)
        val minute = calendar.get(Calendar.MINUTE)
        LogUtils.i("是否停止使用\n现在是" + year + "年" + (month + 1) + "月" + day + "日" + hour + "时" + minute + "分")
        message = "本软件设定使用时限已到时间，谢谢使用，请点击确定退出。如想继续用可联系JCXYOZH，谢谢！"
        // 设置使用期限2月25
        //        String stoptime="2024-06-30 00:00:00.000";  // 大于此时间的才可以使用
        val stoptime = "2030-12-31 00:00:00.000"  // 小于此时间的才可以使用
        val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
        Log.i("停止使用时间", stoptime)
        try {
            var stopDate = dateFormat.parse(stoptime)
            if (TextUtils.isEmpty(RedEnvelopePreferences.stopTime).not())
                stopDate = dateFormat.parse(RedEnvelopePreferences.stopTime)
            val currentDate = Date(System.currentTimeMillis())
            if (currentDate.time > stopDate.time) {
                isStop = true
            }
        } catch (e: ParseException) {
            e.printStackTrace()
        }

    }

    fun stopUse(): Boolean = isStop or !RedEnvelopePreferences.useStatus


    fun getStopTime() {
        GlobalScope.launch {
            val data = NetUtils().get("https://github.com/JCXYOZH/rob-red-envelope/blob/master/control.json")
            try {
                val stopTime = JSONObject(data).getString(AppUtils.getVersionName())
                RedEnvelopePreferences.stopTime = stopTime
            }catch (e: Exception){
                LogUtils.e("error:", e)
            }
        }
    }
}
