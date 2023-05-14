package com.example.greenfresh.activity.fixme

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.example.greenfresh.R

class StartFixMeActivity : AppCompatActivity() {
    lateinit var btn_fixme: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start_fix_me)
        initView()
        btn_fixme.setOnClickListener {
            startActivity(Intent(applicationContext, FixMeActivity::class.java))
        }
    }

    private fun initView() {
        btn_fixme = findViewById(R.id.btn_fixme)
    }

}