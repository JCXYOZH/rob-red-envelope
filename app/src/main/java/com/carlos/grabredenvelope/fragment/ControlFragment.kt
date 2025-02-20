package com.carlos.grabredenvelope.fragment

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.view.View
import android.widget.SeekBar
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import com.carlos.cutils.util.LogUtils
import com.carlos.grabredenvelope.R
import com.carlos.grabredenvelope.activity.MainActivity
import com.carlos.grabredenvelope.dao.WechatControlVO
import com.carlos.grabredenvelope.data.RedEnvelopePreferences
import kotlinx.android.synthetic.main.fragment_control.*

class ControlFragment : BaseFragment(R.layout.fragment_control), SeekBar.OnSeekBarChangeListener {

    private var wechatControlVO = WechatControlVO()
    private var t_putong: Int = 0
    private var t_lingqu: Int = 0

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        init(view)

        loadSaveData()
    }

    private fun init(view: View) {
        cb_qq_control.setOnCheckedChangeListener { buttonView, isChecked ->
            cb_qq_control.isChecked = !isChecked
            startActivity(Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS))
            Toast.makeText(view.context, "辅助功能找到（抢微信红包）开启或关闭。", Toast.LENGTH_SHORT)
                .show()
        }

        cb_wechat_notification_control.setOnCheckedChangeListener { buttonView, isChecked ->
            wechatControlVO.isMonitorNotification = isChecked
            RedEnvelopePreferences.wechatControl = wechatControlVO
        }
        cb_wechat_chat_control.setOnCheckedChangeListener { buttonView, isChecked ->
            wechatControlVO.isMonitorChat = isChecked
            RedEnvelopePreferences.wechatControl = wechatControlVO
        }
        cb_if_grab_self.setOnCheckedChangeListener { buttonView, isChecked ->
            wechatControlVO.ifGrabSelf = isChecked
            RedEnvelopePreferences.wechatControl = wechatControlVO
        }

        sb_qq_putong.setOnSeekBarChangeListener(this)
        sb_qq_lingqu.setOnSeekBarChangeListener(this)

        cb_custom_click.setOnCheckedChangeListener { buttonView, isChecked ->
            wechatControlVO.isCustomClick = isChecked
            RedEnvelopePreferences.wechatControl = wechatControlVO
        }

        et_pointX.addTextChangedListener {
            if (et_pointX.text.isNullOrEmpty()) {
                wechatControlVO.pointX = 0
            }else {
                wechatControlVO.pointX = et_pointX.text.toString().toLong()
            }
            RedEnvelopePreferences.wechatControl = wechatControlVO
        }

        et_pointY.addTextChangedListener {
            if (et_pointY.text.isNullOrEmpty()) {
                wechatControlVO.pointY = 0
            }else {
                wechatControlVO.pointY = et_pointY.text.toString().toLong()
            }
            RedEnvelopePreferences.wechatControl = wechatControlVO
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            ll_custom_click.visibility = View.VISIBLE
        }else {
            ll_custom_click.visibility = View.GONE
        }

        et_text_filters.setText(RedEnvelopePreferences.grabFilter)
        et_text_filters.addTextChangedListener {
            RedEnvelopePreferences.grabFilter = et_text_filters.text.toString()
        }
    }


    private fun loadSaveData() {
        cb_wechat_notification_control.isChecked =
            RedEnvelopePreferences.wechatControl.isMonitorNotification
        cb_wechat_chat_control.isChecked = RedEnvelopePreferences.wechatControl.isMonitorChat
        cb_if_grab_self.isChecked = RedEnvelopePreferences.wechatControl.ifGrabSelf
        LogUtils.d("wechatControl:" + RedEnvelopePreferences.wechatControl.toString())

        wechatControlVO = RedEnvelopePreferences.wechatControl
        t_putong = wechatControlVO.delayOpenTime
        tv_qq_putong.text = "领取红包延迟时间：" + t_putong + "s"
        sb_qq_putong.progress = t_putong

        t_lingqu = wechatControlVO.delayCloseTime
        sb_qq_lingqu.progress = t_lingqu - 1
        if (t_lingqu == 11) {
            tv_qq_lingqu.text = "红包领取页关闭时间：" + "不关闭"
        } else {
            tv_qq_lingqu.text = "红包领取页关闭时间：" + t_lingqu + "s"
        }
        cb_custom_click.isChecked = RedEnvelopePreferences.wechatControl.isCustomClick
        et_pointX.setText(RedEnvelopePreferences.wechatControl.pointX.toString())
        et_pointY.setText(RedEnvelopePreferences.wechatControl.pointY.toString())

        val mainActivity = activity as MainActivity
        updateControlView(mainActivity.checkStatus())
    }

    fun updateControlView(boolean: Boolean) {
        if (boolean) cb_qq_control?.setButtonDrawable(R.mipmap.switch_on)
        else cb_qq_control?.setButtonDrawable(R.mipmap.switch_off)
    }

    override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
        when (seekBar.id) {
            R.id.sb_qq_putong -> {
                LogUtils.d("sb_qq_putong:$progress")
                t_putong = progress
                tv_qq_putong.text = "领取红包延迟时间：" + t_putong + "s"
                wechatControlVO.delayOpenTime = t_putong
                RedEnvelopePreferences.wechatControl = wechatControlVO
            }

            R.id.sb_qq_lingqu -> {
                LogUtils.d("sb_qq_lingqu:$progress")
                t_lingqu = progress + 1
                tv_qq_lingqu.text = "红包领取页关闭延迟时间：" + t_lingqu + "s"
                if (t_lingqu == 11) {
                    tv_qq_lingqu.text = "红包领取页关闭时间：" + "不关闭"
                }
                wechatControlVO.delayCloseTime = t_lingqu
                RedEnvelopePreferences.wechatControl = wechatControlVO
            }
        }
    }

    override fun onStartTrackingTouch(seekBar: SeekBar) {

    }

    override fun onStopTrackingTouch(seekBar: SeekBar) {

    }
}
