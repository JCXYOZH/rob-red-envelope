package com.carlos.grabredenvelope.receiver

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import android.widget.TextView
import com.carlos.grabredenvelope.R
import com.carlos.grabredenvelope.activity.MainActivity

class MessageActivity : Activity() {

    private lateinit var ib_back: ImageButton
    private lateinit var tv_message: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.message)

        ib_back = findViewById(R.id.ib_back)
        ib_back.setOnClickListener {
            val intent = Intent(this@MessageActivity, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

        tv_message = findViewById(R.id.tv_message)
        tv_message.text = "\t\t" + intent.getStringExtra("data")

    }
}
