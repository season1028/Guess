package com.mason.guess

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AlertDialog
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.ed_text

import kotlinx.android.synthetic.main.activity_material.*
import kotlinx.android.synthetic.main.content_material.*

class MaterialActivity : AppCompatActivity() {
    val secretNumber = SecretNumber()
    //  val TAG = "MainActivity"
    val TAG = MaterialActivity::class.java.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_material)
        setSupportActionBar(toolbar)
        Log.d(TAG, "secret:" + secretNumber.secret)

        fab.setOnClickListener { view ->
            AlertDialog.Builder(this)
                .setTitle("Replay game")
                .setMessage("Are you sure?")
                .setPositiveButton(getString(R.string.main_button), { dialog, which ->
                    secretNumber.reset()
                    counter.setText(secretNumber.count.toString())
                    ed_text.setText("")
                })
                .setNeutralButton("cancel", null)
                .show()
        }
        counter.setText(secretNumber.count.toString())
        Log.d(TAG, "oncreate: " + secretNumber.secret);
        val count = getSharedPreferences("guess",Context.MODE_PRIVATE)
            .getInt("REC_COUNTER",-1)
        val nick=getSharedPreferences("guess",Context.MODE_PRIVATE)
            .getString("REC_NICKNAME",null)
        Log.d(TAG, "data: $count / $nick");
    }

    fun check(view: View) {
        //   resources.getString(R.string.Yes_you_got_it)
        val n = ed_text.text.toString().toInt()
        println("number:$n")
        Log.d(TAG, "numbber:" + n)
        val diff = secretNumber.validate(n)
        var message = getString(R.string.Yes_you_got_it)
        if (diff < 0) {
            message = getString(R.string.bigger)
        } else if (diff > 0) {
            message = getString(R.string.smaller)
        }
        counter.setText(secretNumber.count.toString())
        //      Toast.makeText(this,message,Toast.LENGTH_LONG).show()
        AlertDialog.Builder(this)
            .setTitle(getString(R.string.dialog_title))
            .setMessage(message)
            .setPositiveButton(R.string.button, { dialog, which ->
                if (diff == 0) {
                    val intent = Intent(this, RecordActivity::class.java)
                    intent.putExtra("COUNTER",secretNumber.count)
                    startActivity(intent)
                }
            }).show()
    }


    }


