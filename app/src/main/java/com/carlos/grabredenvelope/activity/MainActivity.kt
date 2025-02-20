package com.carlos.grabredenvelope.activity

import android.Manifest
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.carlos.cutils.base.adapter.CBaseMyPagerAdapter
import com.carlos.cutils.listener.PermissionListener
import com.carlos.cutils.util.LogUtils
import com.carlos.grabredenvelope.databinding.ActivityMainBinding
import com.carlos.grabredenvelope.fragment.*
import com.carlos.grabredenvelope.util.Update
import kotlinx.android.synthetic.main.activity_main.*

open class MainActivity : BaseActivity() {

    private lateinit var binding: ActivityMainBinding

    private val WECHAT_SERVICE_NAME = "com.carlos.grabredenvelope/.services.WechatService"

    var fragments = mutableListOf<Fragment>(ControlFragment(), GuideFragment(), AboutFragment(),
        CodeFragment(), RewardFragment(), RecordFragment(), EmojiFragment()
    )
    var titles = mutableListOf("控制", "教程", "说明", "源码", "打赏", "微信", "表情")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        initView()

        getPermissions()
        checkVersion()
        addListener()
    }

    private fun checkVersion() {
        val update = Update(this, 1)
        update.update()
    }

    private fun initView() {
        val adapter = CBaseMyPagerAdapter(supportFragmentManager, fragments, titles)
        binding.viewPager.adapter = adapter
        binding.slidingTabs.setupWithViewPager(viewPager)
        viewPager.offscreenPageLimit = 2
    }

    private fun addListener() {
        addAccessibilityServiceListener(object :
            AccessibilityServiceListeners {
            override fun updateStatus(boolean: Boolean) {
                LogUtils.d("updateStatus:$boolean")
                val controlFragment = fragments[0] as ControlFragment
                controlFragment.updateControlView(boolean)
            }
        }, WECHAT_SERVICE_NAME)
    }

    private fun getPermissions() {
        requestPermission(100, object : PermissionListener {
            override fun permissionSuccess() {}
            override fun permissionFail() {}
        }, Manifest.permission.READ_PHONE_STATE, Manifest.permission.ACCESS_NETWORK_STATE, Manifest.permission.WRITE_EXTERNAL_STORAGE)
    }

    fun checkItem(item: Int) {
        viewPager.currentItem = item
    }

}