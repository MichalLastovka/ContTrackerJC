package com.example.conttrackerjc.data.subclasses.ctt


import com.google.gson.annotations.SerializedName

data class ContainerDaoCtt(
    @SerializedName("containerid")
    val containerid: String,
    @SerializedName("umschlaege")
    val umschlaege: List<UmschlaegeCtt>
)