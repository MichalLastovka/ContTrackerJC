package com.example.conttrackerjc.data.subclasses.ctt


import com.google.gson.annotations.SerializedName

data class UmschlaegeCtt(
    @SerializedName("archiviert")
    val archiviert: Boolean,
    @SerializedName("containerOperatorId")
    val containerOperatorId: String,
    @SerializedName("containerOperatorName")
    val containerOperatorName: ContainerOperatorName,
    @SerializedName("containerServiceOrder")
    val containerServiceOrder: Any?,
    @SerializedName("detailedData")
    val detailedData: Any?,
    @SerializedName("facility")
    val facility: String,
    @SerializedName("id")
    val id: String,
    @SerializedName("lastGateMovementAt")
    val lastGateMovementAt: Any?,
    @SerializedName("physicalContainerData")
    val physicalContainerData: Any?,
    @SerializedName("publicData")
    val publicData: PublicData,
    @SerializedName("transitState")
    val transitState: String,
    @SerializedName("transportOrderInbound")
    val transportOrderInbound: TransportOrderInbound,
    @SerializedName("transportOrderOutbound")
    val transportOrderOutbound: TransportOrderOutbound,
    @SerializedName("@type")
    val type: String
)