package com.mason.guess

import android.content.Intent
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
        "Guess number",
        "Record list",
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
              holder.itemView.setOnClickListener {
                  functiononClick(position)
              }
        }
    }

    private fun functiononClick(position: Int) {
        when(position){
            1->startActivity(Intent(this,MaterialActivity::class.java))
            //**
            else -> return
        }

    }

    class functionHolder(view: View):RecyclerView.ViewHolder(view){
        var nameTexr:TextView=view.name
    }



}

