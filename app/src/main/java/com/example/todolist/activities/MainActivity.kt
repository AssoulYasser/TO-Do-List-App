package com.example.todolist.activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.todolist.R
import com.example.todolist.RecyclerView.*
import com.example.todolist.Retrofit.*
import com.example.todolist.data.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.header_item.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.create

class MainActivity : AppCompatActivity() {
    private val contextMainActivity = this
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val sharedPreferences = getSharedPreferences("USER", MODE_PRIVATE)

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

        myTextHeader.text = "Good Morning, ${sharedPreferences.getString("USERNAME", "USERNAME")}"

        val recycler:RecyclerView = findViewById(R.id.myRecyclerView)

        recycler.adapter = DATA.adapter
        recycler.layoutManager = LinearLayoutManager(this)


        HttpRequest.getData(contextMainActivity)


        addTaskBtn.setOnClickListener{
            startActivity(Intent(contextMainActivity,TaskFormActivity::class.java))
        }

        logout.setOnClickListener {
            Log.d("EXCEPTION RES:", "Shared Pref in main activity")
            val sharedPreferences = AuthActivity.sharedPreferences
            with(sharedPreferences.edit()){
                putBoolean("IS_SIGNED", false)
                putString("TOKEN", "")
                putString("USERNAME", "")
                apply()
            }
            Log.d("EXCEPTION RES:", "After pressing logout your boolean is : " + sharedPreferences.getBoolean("IS_SIGNED", false).toString())
            Log.d("EXCEPTION RES:", "Getting back to Auth Act")
            startActivity(Intent(this,AuthActivity::class.java))
            finish()
        }





    }

    fun refresh(view: View) {
        HttpRequest.getData(contextMainActivity)
    }


    /*private fun myLightNightSwitch(){
        switch1.isChecked = AppCompatDelegate.getDefaultNightMode() != AppCompatDelegate.MODE_NIGHT_YES

        switch1.setOnClickListener {
            if(switch1.isChecked)
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            else
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }
    }*/

}