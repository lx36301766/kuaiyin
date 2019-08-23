package com.yc.kuaiyin

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.yc.kuaiyin.util.YLog


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        YLog.d("12")
        YLog.tag("aab").d("12")



    }

}
