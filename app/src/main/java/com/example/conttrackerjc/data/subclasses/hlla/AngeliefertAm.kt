package com.example.conttrackerjc.data.subclasses.hlla


import com.google.gson.annotations.SerializedName

data class AngeliefertAm(
    @SerializedName("value")
    val value: Any?,
    @SerializedName("valid")
    val valid: Boolean?
)