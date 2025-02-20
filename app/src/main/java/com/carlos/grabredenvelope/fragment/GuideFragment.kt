package com.carlos.grabredenvelope.fragment

import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.view.View
import com.carlos.grabredenvelope.R
import com.carlos.grabredenvelope.activity.MainActivity
import kotlinx.android.synthetic.main.fragment_about.*

class GuideFragment : BaseFragment(R.layout.fragment_about) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        b_donate_me.setOnClickListener {
            val mainActivity2 = activity as MainActivity
            mainActivity2.checkItem(4)
        }
        tv_use.movementMethod = ScrollingMovementMethod.getInstance()
    }

}