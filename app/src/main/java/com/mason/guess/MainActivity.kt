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
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.linear_main)
        Log.d("MainActivity", "secret:" + secretNumber.secret)
    }

    fun check(view: View) {
        val n = ed_text.text.toString().toInt()
        println("number:$n")
        Log.d("MainActivity", "numbber:" + n)
        val diff = secretNumber.validate(n)
        var message="Right"
        if (diff < 0) {
            message="BBigger"
        } else if (diff > 0) {
           message="Smaller"
        }
   //      Toast.makeText(this,message,Toast.LENGTH_LONG).show()
        AlertDialog.Builder(this)
            .setTitle("Message")
            .setMessage(message)
            .setPositiveButton("Ok",null)
            .show()
    }
}

