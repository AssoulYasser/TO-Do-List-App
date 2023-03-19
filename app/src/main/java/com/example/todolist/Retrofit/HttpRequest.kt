package com.example.todolist.Retrofit

import android.content.Context
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import com.example.todolist.data.DATA
import com.example.todolist.data.Model
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.create
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.properties.Delegates

object HttpRequest {


    fun getData(context: Context){
        Retrofit.
        getRetrofit().
        create<APIservice>().
        getAllTasks().
        enqueue(object :
            Callback<MutableList<Model>?> {
            override fun onResponse(
                call: Call<MutableList<Model>?>,
                response: Response<MutableList<Model>?>
            ) {
                if(response.isSuccessful && !response.body().isNullOrEmpty()) {
                    response.body()?.let { DATA.adapter.refreshData(it) }
                    Log.d("EXCEPTION: GET SUCCESS", "onResponse: ")
                }
            }

            override fun onFailure(call: Call<MutableList<Model>?>, t: Throwable) {
                Toast.makeText(context,"YOU ARE NOT CONNECTED", Toast.LENGTH_SHORT).show()
                Log.d("EXCEPTION: GET FAILED", "onResponse: ")
            }
        })
    }

    fun addData(data:Model,context: Context){
        Retrofit
            .getRetrofit()
            .create(APIservice::class.java)
            .addTask(data)
            .enqueue(object : Callback<Model?> {
                override fun onResponse(call: Call<Model?>, response: Response<Model?>){
                    if (response.isSuccessful) {
                        DATA.adapter.addData(data)
                        Log.d("EXCEPTION: ADD SUCCESS", "onResponse: ")
                    }
                    else {
                        Toast.makeText(context, response.toString(), Toast.LENGTH_LONG).show()
                        Log.d("EXCEPTION: ADD !SUCCESS", "onResponse: ")
                    }
                }

                override fun onFailure(call: Call<Model?>, t: Throwable) {
                    Toast.makeText(context, t.toString(),Toast.LENGTH_LONG).show()
                    Log.d("EXCEPTION: ADD FAILED", "onResponse: ")
                }
            })
    }

    fun deleteData(data: Long){
        Retrofit.
            getRetrofit().create(APIservice::class.java).
            deleteTask(data).
            enqueue(object : Callback<Void> {
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                if (response.isSuccessful) {
                    DATA.adapter.deleteData(data)
                    Log.d("EXCEPTION: DEL SUCCESS", "onResponse: ")
                }
                else {
                    Log.d("EXCEPTION: DEL !SUCCESS", "onResponse: ")
                }

            }

            override fun onFailure(call: Call<Void>, t: Throwable) {
                Log.d("EXCEPTION: DEL FAILED", "onResponse: ")
            }
        })
    }

    fun updateData(data:Model){
        Retrofit.getRetrofit().create(APIservice::class.java).
                updateTask(data).enqueue(object : Callback<Void> {
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                if(response.isSuccessful) {
                    DATA.adapter.updateData(data)
                    Log.d("EXCEPTION: UPD SUCCESS", "onResponse: ")
                }
                else {
                    Log.d("EXCEPTION: UPD !SUCCESS", "onResponse: ")
                }
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {
                Log.d("EXCEPTION: UPD FAILED", "onResponse: ")
            }
        })
    }
}