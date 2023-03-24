package com.example.todolist.activities

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatDelegate
import com.example.todolist.R
import com.example.todolist.Retrofit.HttpRequest
import com.example.todolist.data.DATA
import com.example.todolist.data.TaskModel
import kotlinx.android.synthetic.main.activity_task_form.*
import kotlinx.android.synthetic.main.activity_task_form.titleTV

class TaskFormActivity : AppCompatActivity() {
    val contextTaskFormActivity = this
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task_form)

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

        hourTV.setAdapter(ArrayAdapter(this, R.layout.dropdown_item, resources.getStringArray(R.array.hours)))
        minutTV.setAdapter(ArrayAdapter(this, R.layout.dropdown_item, resources.getStringArray(R.array.minuts)))

        hourTV.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                Log.d("TAG", "beforeTextChanged: ")
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                Log.d("TAG", "beforeTextChanged: ")
            }

            override fun afterTextChanged(s: Editable?) {
                if(!s.isNullOrEmpty()){
                    if(s.length>=2 && s.toString() !in "00".."23")
                        hourTV.setText("23")
                }

            }
        })


        minutTV.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                Log.d("TAG", "beforeTextChanged: ")
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                Log.d("TAG", "beforeTextChanged: ")
            }

            override fun afterTextChanged(s: Editable?) {
                if (!s.isNullOrEmpty()) {
                    if(s.length>=2 && s.toString() !in "00".."59")
                        minutTV.setText("59")
                }
            }
        })


        addBtn.setOnClickListener {
            if(isValid()) {
                DATA.newData = TaskModel(
                    0,
                    titleTV.text.toString(),
                    descriptionTV.text.toString(),
                    "${hourTV.text}:${minutTV.text}:00",
                    false
                )
                HttpRequest.addData(DATA.newData,contextTaskFormActivity)
                finish()
            }
            else Toast.makeText(contextTaskFormActivity,"INVALID DATA",Toast.LENGTH_SHORT).show()
        }




    }

    private fun description(): String {
        if (descriptionTV.text!!.isEmpty())
            return ""
        return descriptionTV.text.toString()
    }

    private fun isValid(): Boolean {
        if(titleTV.text!!.isEmpty())
            return false
        return true
    }
}