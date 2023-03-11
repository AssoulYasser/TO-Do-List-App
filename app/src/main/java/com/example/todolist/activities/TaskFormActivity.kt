package com.example.todolist.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
import com.example.todolist.R
import com.example.todolist.RecyclerView.DATA
import com.example.todolist.RecyclerView.Model
import com.example.todolist.Retrofit.APIservice
import com.example.todolist.Retrofit.Retrofit
import kotlinx.android.synthetic.main.activity_task_form.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TaskFormActivity : AppCompatActivity() {
    val contextTaskFormActivity = this
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task_form)

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

        addBtn.setOnClickListener {
            if(isValid()) {
                DATA.newData = Model(
                    0,
                    titleTV.text.toString(),
                    descriptionTV.text.toString()
                )
                Retrofit
                    .getRetrofit()
                    .create(APIservice::class.java)
                    .addTask(DATA.newData)
                    .enqueue(object : Callback<Model?> {
                        override fun onResponse(call: Call<Model?>, response: Response<Model?>){
                            Log.d("Response : ", response.toString())
                        }

                        override fun onFailure(call: Call<Model?>, t: Throwable) {
                            Log.d("Response : ", t.toString())
                        }
                    })
                finish()
            }
            else Toast.makeText(contextTaskFormActivity,"Invalid Data",Toast.LENGTH_SHORT).show()
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