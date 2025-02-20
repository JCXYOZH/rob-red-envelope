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
import kotlinx.android.synthetic.main.fragment_record.*
import kotlinx.coroutines.*

class RecordDingDIngFragment : BaseFragment(R.layout.fragment_record_dingding) {

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
            getData()
            if (list.isNullOrEmpty()) return@launch
            tv_record_title.text = "从${startTime}至今已助你抢到${total}元"
            arrayAdapter.notifyDataSetChanged()
        }
    }

    private suspend fun getData() {
        withContext(Dispatchers.IO) {
            list.clear()
            total = 0.0
            val dingDingRedEnvelopes = DingDingRedEnvelopeDb.allData
            for (dingDingRedEnvelope in dingDingRedEnvelopes.asReversed()) {
                total = total.doubleCount(dingDingRedEnvelope.count.toDouble())
                list.add("${getYearToMinute(dingDingRedEnvelope.time)} 助你抢到了 ${dingDingRedEnvelope.count}元")
//                LogUtils.d("total:" + total)
            }
            if (dingDingRedEnvelopes.isNotEmpty()) {
                startTime = getYearToMinute(dingDingRedEnvelopes[0].time)
            }
        }
    }
}
