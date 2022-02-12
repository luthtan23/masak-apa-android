package com.luthtan.base_architecture_android.data.dtos

import com.google.gson.annotations.SerializedName

data class HomeModel (
    // TODO SerializedName for difference variable name between response and model
    @SerializedName("success")
    val status: String,
    val message: String,
    val content: String
)