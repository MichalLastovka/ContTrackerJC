package com.example.conttrackerjc.data.subclasses.ctt


import com.google.gson.annotations.SerializedName

data class CarrierOrder(
    @SerializedName("carrierType")
    val carrierType: String,
    @SerializedName("detailedData")
    val detailedData: Any?,
    @SerializedName("vesselClassification")
    val vesselClassification: String,
    @SerializedName("vesselId")
    val vesselId: String,
    @SerializedName("vesselName")
    val vesselName: String
)