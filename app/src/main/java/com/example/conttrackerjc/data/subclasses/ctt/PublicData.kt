package com.example.conttrackerjc.data.subclasses.ctt


import com.google.gson.annotations.SerializedName

data class PublicData(
    @SerializedName("dangerousGoods")
    val dangerousGoods: Boolean,
    @SerializedName("goodsEnclosed")
    val goodsEnclosed: Boolean,
    @SerializedName("isoCode")
    val isoCode: String,
    @SerializedName("outOfGauge")
    val outOfGauge: Boolean,
    @SerializedName("outboundExecutable")
    val outboundExecutable: Boolean,
    @SerializedName("outboundImpediments")
    val outboundImpediments: List<String>
)