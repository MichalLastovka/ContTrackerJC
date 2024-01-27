package com.example.conttrackerjc.data.subclasses


import com.google.gson.annotations.SerializedName

data class Umschlaege(
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
    val lagerbereich: Any?
)