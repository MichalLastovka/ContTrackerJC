package com.example.conttrackerjc.data.subclasses.ctt


import com.google.gson.annotations.SerializedName

data class ContainerOperatorName(
    @SerializedName("de")
    val de: String,
    @SerializedName("en")
    val en: String
)