package com.carlos.grabredenvelope.fragment

import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.view.View
import android.widget.ArrayAdapter
import com.carlos.cutils.extend.doubleCount
import com.carlos.cutils.extend.getYearToMinute
import com.carlos.cutils.util.LogUtils
import com.carlos.grabredenvelope.R
import com.carlos.grabredenvelope.db.DingDingRedEnvelopeDb
import com.carlos.grabredenvelope.db.WechatRedEnvelopeDb
import kotlinx.android.synthetic.main.fragment_record.*
import kotlinx.coroutines.*

class RecordFragment : BaseFragment(R.layout.fragment_record) {

    var list = ArrayList<String>()
    lateinit var arrayAdapter: ArrayAdapter<String>
    var startTime = getYearToMinute()
    var total = 0.0

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init(view)
        initData()
    }

    private fun init(view: View) {
        arrayAdapter = ArrayAdapter(
            view.context, R.layout.item_wechat_record, R.id.tv_item_wechat_record, list
        )
        lv_wechat_record.adapter = arrayAdapter
    }

    private fun initData() {
        job = GlobalScope.launch(Dispatchers.Main) {
            list.clear()
            list.addAll(getData())
            if (list.isNullOrEmpty()) return@launch
            tv_record_title.text = "从${startTime}至今已助你抢到${total}元"
            arrayAdapter.notifyDataSetChanged()
        }
    }

    private suspend fun getData(): ArrayList<String> {
        return withContext(Dispatchers.IO) {
            var list = ArrayList<String>()
            total = 0.0
            val wechatRedEnvelopes = WechatRedEnvelopeDb.allData
            for (wechatRedEnvelope in wechatRedEnvelopes.asReversed()) {
                total = total.doubleCount(wechatRedEnvelope.count.split("元")[0].toDouble())
                list.add("${getYearToMinute(wechatRedEnvelope.time)} 助你抢到了 ${wechatRedEnvelope.count}")
            }
            if (wechatRedEnvelopes.isNotEmpty()) {
                startTime = getYearToMinute(wechatRedEnvelopes[0].time)
            }
            return@withContext list
        }
    }
}
