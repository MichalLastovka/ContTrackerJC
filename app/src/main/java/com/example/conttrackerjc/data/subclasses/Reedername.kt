package com.example.conttrackerjc.data.subclasses


import com.google.gson.annotations.SerializedName

data class Reedername(
    @SerializedName("en")
    val en: Any?,
    @SerializedName("de")
    val de: String?
)