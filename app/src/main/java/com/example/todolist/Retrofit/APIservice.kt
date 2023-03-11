package com.example.todolist.Retrofit

import com.example.todolist.RecyclerView.Model
import retrofit2.Call
import retrofit2.http.*

interface APIservice {
    @POST("/tasks/")
    fun addTask(@Body model: Model) : Call<Model>

}