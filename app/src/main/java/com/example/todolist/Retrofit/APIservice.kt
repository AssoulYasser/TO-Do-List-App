package com.example.todolist.Retrofit

import com.example.todolist.data.Model
import retrofit2.Call
import retrofit2.http.*

interface APIservice {
    @POST("/tasks/")
    fun addTask(@Body model: Model) : Call<Model>


    @GET("/tasks/")
    fun getAllTasks() : Call<MutableList<Model>>

    @DELETE("/tasks/")
    fun deleteTask(@Query("id") id: Long) : Call<Model>

    @PUT("/tasks/")
    fun updateTask(@Body model: Model) : Call<Model>
}