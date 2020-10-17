package com.gabodev.widetech

import com.google.gson.annotations.SerializedName

data class ProductResponse (

    @SerializedName("Description")
    val description: String?,

    @SerializedName("ImageUrl")
    val imageUrl: String?,

    @SerializedName("Name")
    val name: String?
)