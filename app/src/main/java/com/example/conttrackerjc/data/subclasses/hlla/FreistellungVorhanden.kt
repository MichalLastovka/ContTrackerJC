package com.example.conttrackerjc.data.subclasses.hlla


import com.google.gson.annotations.SerializedName

data class FreistellungVorhanden(
    @SerializedName("value")
    val value: String?,
    @SerializedName("valid")
    val valid: Boolean?
)