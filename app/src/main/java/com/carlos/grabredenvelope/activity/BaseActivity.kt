package com.carlos.grabredenvelope.activity

import android.app.AlertDialog
import android.os.Bundle
import android.widget.ImageButton
import android.widget.TextView
import cn.jpush.android.api.JPushInterface
import com.carlos.cutils.base.activity.CBaseAccessibilityActivity
import com.carlos.grabredenvelope.R
import com.carlos.grabredenvelope.util.ControlUse
import com.umeng.analytics.MobclickAgent

open class BaseActivity : CBaseAccessibilityActivity() {

    private lateinit var mBack: ImageButton
    private lateinit var tv_title: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        controlUse()
    }

    override fun onResume() {
        super.onResume()
        JPushInterface.onResume(this)
        MobclickAgent.onResume(this)
    }

    override fun onPause() {
        super.onPause()
        JPushInterface.onPause(this)
        MobclickAgent.onPause(this)
    }

    fun back() {
        mBack = findViewById(R.id.ib_back)
        mBack.setOnClickListener { finish() }
    }

    fun setMenuTitle(title: String) {
        tv_title = findViewById(R.id.tv_title)
        tv_title.text = title
    }

    private fun controlUse() {
        val controlUse = ControlUse(this)
        if (controlUse.stopUse()) {
            val dialog = AlertDialog.Builder(this).setTitle("提示")
                .setMessage("本软件设定使用时限已到时间，谢谢使用，请点击确定退出。如想继续用可联系小不点，谢谢！").setCancelable(false)
                .setPositiveButton("确定") { dialog, which ->
                    dialog.dismiss()
                }
                .create()
            //		dialog.getWindow().setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
            dialog.show()
        }
    }

}
