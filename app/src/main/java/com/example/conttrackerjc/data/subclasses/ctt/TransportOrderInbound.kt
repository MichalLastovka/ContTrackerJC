package com.example.conttrackerjc.data.subclasses.ctt


import com.google.gson.annotations.SerializedName

data class TransportOrderInbound(
    @SerializedName("carrierOrder")
    val carrierOrder: CarrierOrder,
    @SerializedName("detailedData")
    val detailedData: Any?,
    @SerializedName("executionTime")
    val executionTime: Any?,
    @SerializedName("state")
    val state: String
)