package com.mason.guess

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.linear_main.*

class MainActivity : AppCompatActivity() {
    val secretNumber = SecretNumber()
  //  val TAG = "MainActivity"
    val TAG =MainActivity::class.java.simpleName
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.d(TAG, "secret:" + secretNumber.secret)
    }

    fun check(view: View) {
     //   resources.getString(R.string.Yes_you_got_it)
        val n = ed_text.text.toString().toInt()
        println("number:$n")
        Log.d(TAG, "numbber:" + n)
        val diff = secretNumber.validate(n)
        var message=getString(R.string.Yes_you_got_it)
        if (diff < 0) {
            message=getString(R.string.bigger)
        } else if (diff > 0) {
           message=getString(R.string.smaller)
        }
   //      Toast.makeText(this,message,Toast.LENGTH_LONG).show()
        AlertDialog.Builder(this)
            .setTitle(getString(R.string.dialog_title))
            .setMessage(message)
            .setPositiveButton(R.string.button,null)
            .show()
    }
}

