package com.gabodev.widetech

import android.annotation.SuppressLint
import android.content.Intent
import retrofit2.Call
import android.os.Bundle
import android.util.Log
import android.view.View
import retrofit2.Response
import android.view.Window
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import com.google.gson.JsonObject
import com.google.gson.annotations.SerializedName
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        window.requestFeature(Window.FEATURE_ACTION_BAR)

        super.onCreate(savedInstanceState)

        supportActionBar!!.hide()

        setContentView(R.layout.activity_login)

        title = this.resources.getString(R.string.login_page_title)

        toggleRadio.setOnCheckedChangeListener { _, _ ->
            if (etRePassword.visibility == View.VISIBLE) {
                etRePassword.visibility == View.GONE
            } else {
                etRePassword.visibility == View.VISIBLE
            }
        }

        val activity = this

        btnLogin.setOnClickListener {

            val jsonObject = JsonObject()
            jsonObject.addProperty("UserName", "")
            jsonObject.addProperty("Password", "")

            RetrofitClient.instance.login(jsonObject).enqueue(object : retrofit2.Callback<LoginResponse> {

                @SuppressLint("ShowToast")
                override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                    Toast.makeText(activity, "Error in the service", Toast.LENGTH_SHORT)
                }

                override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                    response.body()?.let {
                        val intent = Intent(activity, ListProductsActivity::class.java)
                        startActivity(intent)
                    }
                }
            })
        }
    }
}