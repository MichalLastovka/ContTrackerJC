package com.example.conttrackerjc.data.subclasses.ctt


import com.google.gson.annotations.SerializedName

data class TransportOrderOutbound(
    @SerializedName("carrierOrder")
    val carrierOrder: Any?,
    @SerializedName("detailedData")
    val detailedData: Any?,
    @SerializedName("state")
    val state: String
)