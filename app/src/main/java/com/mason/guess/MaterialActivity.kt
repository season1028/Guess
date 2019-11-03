package com.mason.guess

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AlertDialog
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import com.mason.guess.data.GameDatabase
import com.mason.guess.data.Record
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.ed_text

import kotlinx.android.synthetic.main.activity_material.*
import kotlinx.android.synthetic.main.content_material.*

class MaterialActivity : AppCompatActivity() {
    private val REQUEST_CODE = 100
    val secretNumber = SecretNumber()
    //  val TAG = "MainActivity"
    val TAG = MaterialActivity::class.java.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate:   ");
        setContentView(R.layout.activity_material)
        setSupportActionBar(toolbar)
        Log.d(TAG, "secret:" + secretNumber.secret)

        fab.setOnClickListener { view ->
            replay()
        }

        counter.setText(secretNumber.count.toString())
        Log.d(TAG, "oncreate: " + secretNumber.secret);
        val count = getSharedPreferences("guess",Context.MODE_PRIVATE)
            .getInt("REC_COUNTER",-1)
        val nick = getSharedPreferences("guess",Context.MODE_PRIVATE)
            .getString("REC_NICKNAME",null)
        Log.d(TAG, "data: $count / $nick");
        //Room test
        val database = Room.databaseBuilder(this,GameDatabase::class.java,"game.db")
            .build()
        val record=Record("Jack",3)
        Thread(){
            database.recordDao().insert(record)
        }.start()

    }

    private fun replay() {
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

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart:   ");
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onCStop:   ");
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause:   ");
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume:   ");
    }

    override fun onRestart() {
        super.onRestart()
        Log.d(TAG, "onRestart:   ");
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy:   ");
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
                   // startActivity(intent)
                    startActivityForResult(intent,REQUEST_CODE)
                }
            }).show()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode== REQUEST_CODE){
            if(resultCode == Activity.RESULT_OK){
                val nickname = data?.getStringExtra("Nick")
                Log.d(TAG, "onActivityResult:$nickname ");
                replay()
            }
        }
    }
}


