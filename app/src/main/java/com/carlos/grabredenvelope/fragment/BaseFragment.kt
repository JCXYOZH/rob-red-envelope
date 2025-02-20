package com.carlos.grabredenvelope.fragment

import android.view.View
import com.carlos.cutils.base.fragment.CBaseFragment
import kotlinx.coroutines.Job

open class BaseFragment(val layoutid: Int) : CBaseFragment() {

    open lateinit var job: Job

    override fun initView(view: View) {}

    override fun layoutId(): Int {
        return layoutid
    }

    override fun onDestroyView() {
        super.onDestroyView()
        if (::job.isInitialized) {
            job.cancel()
        }
    }
}