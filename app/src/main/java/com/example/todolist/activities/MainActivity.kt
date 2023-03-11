package com.example.todolist.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.todolist.R
import com.example.todolist.RecyclerView.*
import com.example.todolist.Retrofit.*
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

        val contextMainActivity = this
        val recycler:RecyclerView = findViewById(R.id.myRecyclerView)

        recycler.adapter = DATA.adapter
        recycler.layoutManager = LinearLayoutManager(this)


        addTaskBtn.setOnClickListener{
            startActivity(Intent(contextMainActivity,TaskFormActivity::class.java))
        }

        /*fun myLightNightSwitch(){
            switch1.isChecked = AppCompatDelegate.getDefaultNightMode() != AppCompatDelegate.MODE_NIGHT_YES

            switch1.setOnClickListener {
                if(switch1.isChecked)
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                else
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }
        }*/





    }
}