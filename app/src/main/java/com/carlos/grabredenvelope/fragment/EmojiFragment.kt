package com.carlos.grabredenvelope.fragment

import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import android.view.View
import android.widget.Toast
import androidx.core.widget.doAfterTextChanged
import com.carlos.grabredenvelope.R
import com.carlos.grabredenvelope.data.RedEnvelopePreferences
import kotlinx.android.synthetic.main.fragment_emoji.*

class EmojiFragment : BaseFragment(R.layout.fragment_emoji) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
        tv_control.setOnClickListener {
            startActivity(Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS))
            Toast.makeText(view?.context, "辅助功能找到（自动发送表情）开启或关闭。", Toast.LENGTH_SHORT)
                .show()
        }

        et_emoji.setText(RedEnvelopePreferences.autoText)
        et_emoji.doAfterTextChanged {
            RedEnvelopePreferences.autoText = et_emoji.text.toString()
        }

        np_times.minValue = 0
        np_times.maxValue = 100
        np_times.value = RedEnvelopePreferences.emojiTimes
        np_times.setOnValueChangedListener { picker, oldVal, newVal ->
            RedEnvelopePreferences.emojiTimes = newVal
        }

        np_interval.minValue = 0
        np_interval.maxValue = 3000
        np_interval.value = RedEnvelopePreferences.emojiInterval
        np_interval.setOnValueChangedListener { picker, oldVal, newVal ->
            RedEnvelopePreferences.emojiInterval = newVal
        }
    }

}