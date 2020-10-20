package com.gabodev.widetech

import retrofit2.Call
import android.util.Log
import android.os.Bundle
import android.view.View
import retrofit2.Response
import android.view.Window
import android.widget.Toast
import android.content.Intent
import com.google.gson.JsonObject
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.login.LoginResult
import com.facebook.login.LoginManager
import android.annotation.SuppressLint
import com.google.firebase.auth.FirebaseAuth
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FacebookAuthProvider
import kotlinx.android.synthetic.main.activity_login.*
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions

class LoginActivity : AppCompatActivity() {

    private lateinit var googleSignInClient: GoogleSignInClient

    private val callbackManager = CallbackManager.Factory.create()

    override fun onCreate(savedInstanceState: Bundle?) {

        window.requestFeature(Window.FEATURE_ACTION_BAR)

        super.onCreate(savedInstanceState)

        supportActionBar!!.hide()

        setContentView(R.layout.activity_login)

        title = this.resources.getString(R.string.login_page_title)

        val activity = this

        toggleRadio.setOnCheckedChangeListener { _, _ ->
            if (etRePassword.visibility == View.VISIBLE) {
                etRePassword.visibility == View.GONE
            } else {
                etRePassword.visibility == View.VISIBLE
            }
        }

        googleButton.setOnClickListener {
            val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build()
            googleSignInClient = GoogleSignIn.getClient(this, gso)
            val signInIntent = googleSignInClient.signInIntent
            startActivityForResult(signInIntent, GOOGLE_SIGN_IN)
        }

        facebookButton.setOnClickListener {

            Log.i("APP_WIDETECH", "facebookButton onClick")

            LoginManager.getInstance().logInWithReadPermissions(this, listOf("email"))

            LoginManager.getInstance().registerCallback(callbackManager, object : FacebookCallback<LoginResult> {

                override fun onSuccess(result: LoginResult?) {

                    Log.i("APP_WIDETECH", "onSuccess")

                    result?.let {
                        val token = it.accessToken

                        val credential = FacebookAuthProvider.getCredential(token.token)

                        FirebaseAuth.getInstance().signInWithCredential(credential).addOnCompleteListener { response ->

                            if (response.isSuccessful) {
                                val intent = Intent(activity, ListProductsActivity::class.java)
                                startActivity(intent)
                            }
                        }
                    }
                }

                override fun onCancel() {
                    Log.i("APP_WIDETECH", "onCancel")
                }

                @SuppressLint("ShowToast")
                override fun onError(error: FacebookException?) {
                    Log.i("APP_WIDETECH", "onError")
                    Toast.makeText(activity, "Error in the service", Toast.LENGTH_SHORT)
                }
            })
        }

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

        contactButton.setOnClickListener {
            val intent = Intent(activity, ContactUsActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

        super.onActivityResult(requestCode, resultCode, data)

        Log.i("APP_WIDETECH", "requestCode: $requestCode")

        if (requestCode == GOOGLE_SIGN_IN) {

            // val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            // var userToken: String? = null

            val intent = Intent(this, ListProductsActivity::class.java)

            startActivity(intent)

            /*try {
                val account: GoogleSignInAccount? = task.getResult(ApiException::class.java)

                if (account != null) userToken = account.idToken

                Log.i("APP_WIDETECH", "userToken: $userToken")

                val intent = Intent(this, ListProductsActivity::class.java)

                startActivity(intent)

            } catch (exception: Exception) {
                Log.d("APP_WIDETECH", exception.toString())
            }*/
        }
    }

    companion object {
        private const val GOOGLE_SIGN_IN = 1237
    }
}