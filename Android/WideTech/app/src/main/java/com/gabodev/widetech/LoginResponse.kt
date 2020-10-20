package com.gabodev.widetech

import com.google.gson.annotations.SerializedName

data class LoginResponse (

    @SerializedName("UserName")
    val userName: String?,

    @SerializedName("Password")
    val password: String?
)