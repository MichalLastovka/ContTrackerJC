package com.example.conttrackerjc.data.subclasses.hlla


import com.example.conttrackerjc.data.subclasses.ctt.ContainerOperatorName
import com.example.conttrackerjc.data.subclasses.ctt.PhysicalContainerData
import com.example.conttrackerjc.data.subclasses.ctt.PublicData
import com.example.conttrackerjc.data.subclasses.ctt.TransportOrderInbound
import com.example.conttrackerjc.data.subclasses.ctt.TransportOrderOutbound
import com.google.gson.annotations.SerializedName

data class Umschlaege(

    //hhla type
    @SerializedName("@type")
    val type: String?,
    @SerializedName("umschlagid")
    val umschlagid: String?,
    @SerializedName("terminal")
    val terminal: Terminal?,
    @SerializedName("reedercode")
    val reedercode: Reedercode?,
    @SerializedName("archiviert")
    val archiviert: Boolean?,
    @SerializedName("vorgemeldet")
    val vorgemeldet: Boolean?,
    @SerializedName("angeliefert")
    val angeliefert: Boolean?,
    @SerializedName("ausgeliefert")
    val ausgeliefert: Boolean?,
    @SerializedName("status")
    val status: String?,
    @SerializedName("reedername")
    val reedername: Reedername?,
    @SerializedName("angeliefertAm")
    val angeliefertAm: AngeliefertAm?,
    @SerializedName("gesperrt")
    val gesperrt: Boolean?,
    @SerializedName("schiffscode")
    val schiffscode: Schiffscode?,
    @SerializedName("schiffsname")
    val schiffsname: Schiffsname?,
    @SerializedName("vollmerkmal")
    val vollmerkmal: Vollmerkmal?,
    @SerializedName("isocode")
    val isocode: Isocode?,
    @SerializedName("containerart")
    val containerart: Containerart?,
    @SerializedName("freistellungVorhanden")
    val freistellungVorhanden: FreistellungVorhanden?,
    @SerializedName("verwahrstatus")
    val verwahrstatus: Verwahrstatus?,
    @SerializedName("gefahrgut")
    val gefahrgut: Boolean?,
    @SerializedName("zollZAPPFreigabe")
    val zollZAPPFreigabe: Any?,
    @SerializedName("lagerbereich")
    val lagerbereich: Any?,

    // ctt type
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
    val physicalContainerData: PhysicalContainerData?,
    @SerializedName("publicData")
    val publicData: PublicData,
    @SerializedName("transitState")
    val transitState: String,
    @SerializedName("transportOrderInbound")
    val transportOrderInbound: TransportOrderInbound,
    @SerializedName("transportOrderOutbound")
    val transportOrderOutbound: TransportOrderOutbound,
)