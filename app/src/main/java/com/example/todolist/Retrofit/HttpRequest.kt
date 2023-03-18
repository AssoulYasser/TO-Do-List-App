package com.example.todolist.Retrofit

import android.content.Context
import android.util.Log
import android.widget.Toast
import com.example.todolist.data.DATA
import com.example.todolist.data.Model
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.create

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
                }
                else{
                    Toast.makeText(context,"Failed to load", Toast.LENGTH_SHORT).show()
                }
                Log.d("RESP:", response.toString())
            }

            override fun onFailure(call: Call<MutableList<Model>?>, t: Throwable) {
                Toast.makeText(context,"YOU ARE NOT CONNECTED", Toast.LENGTH_SHORT).show()
                Log.d("TAG", t.toString())
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
                    if (response.isSuccessful)
                        DATA.adapter.addData(data)
                    else
                        Toast.makeText(context, response.toString(),Toast.LENGTH_LONG).show()
                }

                override fun onFailure(call: Call<Model?>, t: Throwable) {
                    Toast.makeText(context, t.toString(),Toast.LENGTH_LONG).show()
                }
            })
    }

    fun deleteData(data: Long){
        Retrofit.
            getRetrofit().create(APIservice::class.java).
            deleteTask(data).
            enqueue(object : Callback<Model?> {
            override fun onResponse(call: Call<Model?>, response: Response<Model?>) {
                if (response.isSuccessful)
                    DATA.adapter.deleteData(data)

                else
                    Log.d("TEST", response.toString())
//                    Toast.makeText(context, response.toString(),Toast.LENGTH_LONG).show()

            }

            override fun onFailure(call: Call<Model?>, t: Throwable) {
                Log.d("TEST", t.toString())
//                Toast.makeText(context, t.toString(),Toast.LENGTH_LONG).show()
            }
        })
    }

    fun updateData(data:Model){
        Retrofit.getRetrofit().create(APIservice::class.java).
                updateTask(data).enqueue(object : Callback<Model?> {
            override fun onResponse(call: Call<Model?>, response: Response<Model?>) {
                if(response.isSuccessful) {
                    DATA.adapter.updateData(data)
                }

                Log.d("TEST", response.toString())
//                    Toast.makeText(context, response.toString(),Toast.LENGTH_LONG).show()

            }

            override fun onFailure(call: Call<Model?>, t: Throwable) {
                Log.d("TEST", t.toString())
            }
        })
    }
}