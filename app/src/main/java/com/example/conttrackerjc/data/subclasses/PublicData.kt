package com.example.conttrackerjc.data.subclasses


import com.google.gson.annotations.SerializedName

data class PublicData(
    @SerializedName("isoCode")
    val isoCode: String?,
    @SerializedName("goodsEnclosed")
    val goodsEnclosed: Boolean?,
    @SerializedName("dangerousGoods")
    val dangerousGoods: Boolean?,
    @SerializedName("outOfGauge")
    val outOfGauge: Boolean?,
    @SerializedName("outboundExecutable")
    val outboundExecutable: Boolean?,
    @SerializedName("outboundImpediments")
    val outboundImpediments: List<Any?>?
)