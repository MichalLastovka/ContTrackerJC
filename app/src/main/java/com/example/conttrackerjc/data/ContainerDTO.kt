package com.example.conttrackerjc.data


import com.example.conttrackerjc.data.subclasses.Umschlaege
import com.google.gson.annotations.SerializedName

data class ContainerDTO(
    @SerializedName("containerid")
    val containerid: String,
    @SerializedName("umschlaege")
    val umschlaege: List<Umschlaege?>?
)

fun ContainerDTO.toContainer(): Container{
    /*
    if (umschlaege?.get(0)?.type == "om3"){
        return Container(
            containerId = containerid.uppercase(),
            terminal = umschlaege[0]?.cttFacility.toString(),
            carrierCode = umschlaege[0]?.cttContainerOperatorId.toString(),
            carrierNameEN = umschlaege[0]?.cttContainerOperatorName?.en.toString(),
            carrierNameDE = umschlaege[0]?.cttContainerOperatorName?.de.toString(),
            vesselCode =
            vesselName =
            status =
            delivery =
            onHold =
            emptyFull =
            isoCode = umschlaege[0]?.cttPublicData?.isoCode
            containerCode =
        )
    }else{*/
        return Container(
            containerId = containerid.uppercase(),
            terminal = umschlaege?.get(0)?.terminal?.value.toString(),
            carrierCode = umschlaege?.get(0)?.reedercode?.value.toString(),
            carrierNameEN = umschlaege?.get(0)?.reedername?.en.toString(),
            carrierNameDE = umschlaege?.get(0)?.reedername?.de.toString(),
            vesselCode = umschlaege?.get(0)?.schiffscode?.value.toString(),
            vesselName = umschlaege?.get(0)?.schiffsname?.value.toString(),
            status = umschlaege?.get(0)?.status.toString(),
            delivery = umschlaege?.get(0)?.angeliefertAm?.value.toString(),
            onHold = umschlaege?.get(0)?.gesperrt.toString(),
            emptyFull = umschlaege?.get(0)?.vollmerkmal?.value.toString(),
            isoCode = umschlaege?.get(0)?.isocode?.value.toString(),
            containerCode = umschlaege?.get(0)?.containerart?.value.toString()
        )
    }
fun ContainerDTO.toPartialContainer(): PartialContainer{
    return PartialContainer(
        containerId = containerid.uppercase(),
        terminal = umschlaege?.get(0)?.terminal?.value.toString(),
        carrierCode = umschlaege?.get(0)?.reedercode?.value.toString(),
        carrierNameEN = umschlaege?.get(0)?.reedername?.en.toString(),
        carrierNameDE = umschlaege?.get(0)?.reedername?.de.toString(),
        vesselCode = umschlaege?.get(0)?.schiffscode?.value.toString(),
        vesselName = umschlaege?.get(0)?.schiffsname?.value.toString(),
        status = umschlaege?.get(0)?.status.toString(),
        delivery = umschlaege?.get(0)?.angeliefertAm?.value.toString(),
        onHold = umschlaege?.get(0)?.gesperrt.toString(),
        emptyFull = umschlaege?.get(0)?.vollmerkmal?.value.toString(),
        isoCode = umschlaege?.get(0)?.isocode?.value.toString(),
        containerCode = umschlaege?.get(0)?.containerart?.value.toString()
    )
}