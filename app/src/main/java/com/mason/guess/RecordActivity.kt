package com.mason.guess

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.room.Room
import com.mason.guess.data.GameDatabase
import com.mason.guess.data.Record
import kotlinx.android.synthetic.main.activity_record.*

class RecordActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_record)
       val count= intent.getIntExtra("COUNTER",-1)
        counter.setText(count.toString())
        //on click listener
    save.setOnClickListener { view ->
        val nick =nickname.text.toString()
        getSharedPreferences("guess",Context.MODE_PRIVATE)
            .edit()
            .putInt("REC_COUNTER",count)
            .putString("REC_NICKNAME",nick)
            .apply()
        // Insert to Room
        //Room test
//        val database = Room.databaseBuilder(this, GameDatabase::class.java,"game.db")
//            .build()
       // val database=GameDatabase.getInstance(this)
        val record= Record(nick,count)
        Thread(){
            GameDatabase.getInstance(this)?.recordDao()?.
                insert(record)
        }.start()

        var intent = Intent()
        intent.putExtra("Nick",nick)
        setResult(Activity.RESULT_OK,intent)
        finish()

    }
    }
}
