package com.example.conttrackerjc.data.subclasses


import com.google.gson.annotations.SerializedName

data class Verwahrstatus(
    @SerializedName("value")
    val value: String?,
    @SerializedName("valid")
    val valid: Boolean?
)