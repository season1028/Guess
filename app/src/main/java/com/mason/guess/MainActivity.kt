package com.mason.guess

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.function_row.view.*
import kotlinx.android.synthetic.main.linear_main.*
import kotlinx.android.synthetic.main.linear_main.view.*

class MainActivity : AppCompatActivity() {
  //  val TAG = "MainActivity"
    val TAG =MainActivity::class.java.simpleName
    val functions= listOf<String>(
        "Camera",
        "Invite friend",
        "News",
        "News",
        "News",
        "News",
        "News",
        "News",
        "New5s",
        "News",
        "News",
        "News",
        "News",
        "Ne2ws",
        "News",
        "Ne4ws",
        "News",
        "News",
        "New6s",
        "New6s",
        "New6s",
        "Maps",
        "Maps")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//RecycleView

        //******
    recycle.layoutManager=LinearLayoutManager(this)
    recycle.setHasFixedSize(true)
        recycle.adapter=FunctionAdapter()

    }
    inner class FunctionAdapter(): RecyclerView.Adapter<functionHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): functionHolder {
          var view=LayoutInflater.from(parent.context).inflate(R.layout.function_row,parent,false)
            val holder= functionHolder(view)
            return holder
        }

        override fun getItemCount(): Int {
         return  functions.size
        }

        override fun onBindViewHolder(holder: functionHolder, position: Int) {
            //****
              holder.nameTexr.text =functions.get(position)
        }
    }

    class functionHolder(view: View):RecyclerView.ViewHolder(view){
        var nameTexr:TextView=view.name
    }



}

