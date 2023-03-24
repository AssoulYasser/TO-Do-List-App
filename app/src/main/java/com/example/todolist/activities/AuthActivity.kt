package com.example.todolist.activities

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.example.todolist.R
import com.example.todolist.Retrofit.HttpRequest
import com.example.todolist.Retrofit.HttpRequestCallBack
import com.example.todolist.data.SignInModel
import com.example.todolist.data.SignUpModel
import kotlinx.android.synthetic.main.activity_auth.*

class AuthActivity : AppCompatActivity(), HttpRequestCallBack {

    companion object{
        lateinit var sharedPreferences:SharedPreferences
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)

        Log.d("EXCEPTION RES:", "Auth Activity Started Here")


        sharedPreferences = getSharedPreferences("USER",MODE_PRIVATE)


        Log.d("EXCEPTION RES:", "Shared Pref has been init")

        if (sharedPreferences.getBoolean("IS_SIGNED", false)) {
            Log.d("EXCEPTION RES:", "The GetBoolean is True and Main Activity will start")
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }


        var isSigned = true
        signUP_BTN.setOnClickListener {
            if (isSigned) {
                Log.d("EXCEPTION RES:", "You Will create account")
                isSigned = false
                welcome_TV.text = "Get Started!"
                email_IL.visibility = View.VISIBLE
                signIN_BTN.text = "Sign Up"
                signUP_BTN.text = "Sign IN"
            } else {
                Log.d("EXCEPTION RES:", "You already have account")
                isSigned = true
                welcome_TV.text = "Welcome Back!"
                email_IL.visibility = View.GONE
                signIN_BTN.text = "Sign IN"
                signUP_BTN.text = "Sign Up"
            }
        }

        signIN_BTN.setOnClickListener {
            if (!isSigned){
                Log.d("EXCEPTION RES:", "Request to sign up")
                HttpRequest.addUser(SignUpModel(username = username_TF.text.toString(), email = email_TF.text.toString(), password = password_TF.text.toString()), sharedPreferences, this)
            }
            else{
                Log.d("EXCEPTION RES:", "Request to sign in")
                HttpRequest.getUser(SignInModel(username = username_TF.text.toString(), password = password_TF.text.toString()), sharedPreferences, this)
            }
        }
    }

    override fun onSuccess() {
        Log.d("EXCEPTION RES:", "your sign in/up working")
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }

    override fun onFailure() {
        Log.d("EXCEPTION RES:", "your sign in/up is not working")
    }
}