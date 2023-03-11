package com.example.todolist.Retrofit

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Retrofit {

    private const val BASE_URL = "http://192.168.1.2:8000/"
    private val LOGGING = HttpLoggingInterceptor()
    private val CLIENT = OkHttpClient.Builder()

    fun getRetrofit(): Retrofit {

        LOGGING.level = HttpLoggingInterceptor.Level.BODY
        CLIENT.addInterceptor(LOGGING)

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(CLIENT.build())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }


}