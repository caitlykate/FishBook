package com.caitlykate.fishbook

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.content_layout.*
import kotlinx.android.synthetic.main.item_rw.*

class ContentActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.content_layout)

        tvTitleContent.text = intent.getStringExtra("title")
        tvMainContent.text = intent.getStringExtra("content")
        imContent.setImageResource(intent.getIntExtra("image",R.drawable.ic_for_menu))
    }
}