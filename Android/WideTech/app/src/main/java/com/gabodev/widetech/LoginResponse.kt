package com.gabodev.widetech

import com.google.gson.annotations.SerializedName

data class LoginResponse (

    @SerializedName("ExpirationDate")
    val expirationDate: Long?
)