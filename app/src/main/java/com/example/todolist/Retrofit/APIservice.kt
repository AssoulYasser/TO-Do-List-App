package com.example.todolist.Retrofit

import com.example.todolist.data.AuthModel
import com.example.todolist.data.TaskModel
import com.example.todolist.data.SignInModel
import com.example.todolist.data.SignUpModel
import retrofit2.Call
import retrofit2.http.*

interface APIservice {
    @POST("/tasks/")
    fun addTask(@Body taskModel: TaskModel) : Call<TaskModel>

    @GET("/tasks/")
    fun getAllTasks() : Call<MutableList<TaskModel>>

    @DELETE("/tasks/")
    fun deleteTask(@Query("id") id: Long) : Call<Void>

    @PUT("/tasks/")
    fun updateTask(@Body taskModel: TaskModel) : Call<Void>

    @POST("sign-up/")
    fun addUser(@Body signUpModel:SignUpModel) : Call<AuthModel>

    @POST("sign-in/")
    fun getUser(@Body signInModel:SignInModel) : Call<AuthModel>
}