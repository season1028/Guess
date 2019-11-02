package com.mason.guess

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_record.*

class RecordActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_record)
       val count= intent.getIntExtra("COUNTER",-1)
        counter.setText(count.toString())
        //on click listener
    save.setOnClickListener { View ->
        val nick =nickname.text.toString()
        getSharedPreferences("guess",Context.MODE_PRIVATE)
            .edit()
            .putInt("REC_COUNTER",count)
            .putString("REX+NAME",nick)
            .apply()
    }
    }
}
