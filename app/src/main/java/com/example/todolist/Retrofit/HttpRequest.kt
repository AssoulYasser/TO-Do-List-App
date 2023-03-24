package com.example.todolist.Retrofit

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import android.widget.Toast
import com.example.todolist.activities.AuthActivity
import com.example.todolist.data.*
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
            Callback<MutableList<TaskModel>?> {
            override fun onResponse(
                call: Call<MutableList<TaskModel>?>,
                response: Response<MutableList<TaskModel>?>
            ) {
                if(response.isSuccessful && !response.body().isNullOrEmpty()) {
                    response.body()?.let { DATA.adapter.refreshData(it) }
                    Log.d("EXCEPTION: GET SUCCESS", "onResponse: ")
                }
            }

            override fun onFailure(call: Call<MutableList<TaskModel>?>, t: Throwable) {
                Toast.makeText(context,"YOU ARE NOT CONNECTED", Toast.LENGTH_SHORT).show()
                Log.d("EXCEPTION: GET FAILED", "onResponse: ")
            }
        })
    }

    fun addData(data:TaskModel, context: Context){
        Retrofit
            .getRetrofit()
            .create(APIservice::class.java)
            .addTask(data)
            .enqueue(object : Callback<TaskModel?> {
                override fun onResponse(call: Call<TaskModel?>, response: Response<TaskModel?>){
                    if (response.isSuccessful) {
                        DATA.adapter.addData(data)
                        Log.d("EXCEPTION: ADD SUCCESS", "onResponse: ")
                    }
                    else {
                        Toast.makeText(context, response.toString(), Toast.LENGTH_LONG).show()
                        Log.d("EXCEPTION: ADD !SUCCESS", "onResponse: ")
                    }
                }

                override fun onFailure(call: Call<TaskModel?>, t: Throwable) {
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

    fun updateData(data:TaskModel){
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

    fun addUser(user:SignUpModel, sharedPreferences: SharedPreferences, callback: HttpRequestCallBack){
        Retrofit.getRetrofit().create(APIservice::class.java).addUser(user).enqueue(object : Callback<AuthModel?> {
            override fun onResponse(call: Call<AuthModel?>, response: Response<AuthModel?>) {
                Log.d("EXCEPTION RES:", "Starting AddUser request")
                if (response.isSuccessful) {
                    Log.d("EXCEPTION RES:", "Response is success")
                    with(sharedPreferences.edit()){
                        putBoolean("IS_SIGNED", true)
                        response.body()?.let {
                            putString("USERNAME", it.username)
                            putString("TOKEN", it.token)
                        }
                        apply()
                    }
                    callback.onSuccess()
                }
                Log.d("EXCEPTION RES:", "IS_SIGNED = " + sharedPreferences.getBoolean("IS_SIGNED", false))
                Log.d("EXCEPTION RES:", response.toString())
            }

            override fun onFailure(call: Call<AuthModel?>, t: Throwable) {
                Log.d("EXCEPTION RES:", t.toString())
                callback.onFailure()
            }
        })
    }

    fun getUser(user:SignInModel, sharedPreferences: SharedPreferences, callback: HttpRequestCallBack) {
        Log.d("EXCEPTION RES:ٌُ", user.toString())
        Log.d("EXCEPTION RES:ٌُ", user.username)
        Log.d("EXCEPTION RES:ٌُ", user.password)

        Retrofit.getRetrofit().create(APIservice::class.java).getUser(user).enqueue(object : Callback<AuthModel?> {
            override fun onResponse(call: Call<AuthModel?>, response: Response<AuthModel?>) {
                if(response.isSuccessful) {
                    with(sharedPreferences.edit()) {
                        putBoolean("IS_SIGNED", true)
                        response.body()?.let {
                            putString("USERNAME", it.username)
                            putString("TOKEN", it.token)
                        }
                        apply()
                    }
                    callback.onSuccess()
                }
                Log.d("EXCEPTION RES:ٌُ", response.toString() + response.body())
            }

            override fun onFailure(call: Call<AuthModel?>, t: Throwable) {
                Log.d("EXCEPTION RES:", t.toString())
                callback.onFailure()
            }
        })
    }
}