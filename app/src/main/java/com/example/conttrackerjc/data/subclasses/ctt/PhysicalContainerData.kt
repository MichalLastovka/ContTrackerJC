package com.example.conttrackerjc.data.subclasses.ctt


import com.google.gson.annotations.SerializedName

data class PhysicalContainerData(
    @SerializedName("condition")
    val condition: String,
    @SerializedName("grade")
    val grade: Any?,
    @SerializedName("sealAttached")
    val sealAttached: Boolean
)