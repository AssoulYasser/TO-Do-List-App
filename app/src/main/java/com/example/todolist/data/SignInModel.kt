package com.example.todolist.data

data class SignUpModel(val username:String, val password:String, val email:String)
data class SignInModel(val username: String, val password: String)
data class AuthModel(val token:String, val username:String)