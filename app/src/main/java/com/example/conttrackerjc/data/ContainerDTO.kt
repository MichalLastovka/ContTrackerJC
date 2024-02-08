package com.example.conttrackerjc.data


import com.example.conttrackerjc.data.subclasses.hlla.Umschlaege
import com.google.gson.annotations.SerializedName

data class ContainerDTO(
    @SerializedName("containerid")
    val containerid: String,
    @SerializedName("umschlaege")
    val umschlaege: List<Umschlaege?>?
)

fun ContainerDTO.toContainer(): Container {
    if (umschlaege?.get(0)?.type == "om3") {
        return Container(
            containerId = containerid.uppercase(),
            terminal = umschlaege[0]?.facility.toString(),
            carrierCode = umschlaege[0]?.containerOperatorId.toString(),
            carrierNameEN = umschlaege[0]?.containerOperatorName?.en.toString(),
            carrierNameDE = umschlaege[0]?.containerOperatorName?.de.toString(),
            vesselCode = umschlaege[0]?.transportOrderInbound?.carrierOrder?.vesselId.toString(),
            vesselName = umschlaege[0]?.transportOrderInbound?.carrierOrder?.vesselName.toString(),
            status = umschlaege[0]?.transitState.toString(),
            delivery = umschlaege[0]?.transportOrderInbound?.executionTime?.toString(),
            onHold = umschlaege[0]?.transportOrderInbound?.detailedData?.toString(),
            emptyFull = umschlaege[0]?.publicData?.goodsEnclosed.toString(),
            isoCode = umschlaege[0]?.publicData?.isoCode,
            containerCode = umschlaege[0]?.id.toString()
        )
    } else {
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
}
fun ContainerDTO.toPartialContainer(): PartialContainer{
    if (umschlaege?.get(0)?.type == "om3"){
        return PartialContainer(
            containerId = containerid.uppercase(),
            terminal = umschlaege[0]?.facility.toString(),
            carrierCode = umschlaege[0]?.containerOperatorId.toString(),
            carrierNameEN = umschlaege[0]?.containerOperatorName?.en.toString(),
            carrierNameDE = umschlaege[0]?.containerOperatorName?.de.toString(),
            vesselCode = umschlaege[0]?.transportOrderInbound?.carrierOrder?.vesselId.toString(),
            vesselName = umschlaege[0]?.transportOrderInbound?.carrierOrder?.vesselName.toString(),
            status = umschlaege[0]?.transportOrderInbound?.state,
            delivery = umschlaege[0]?.transportOrderInbound?.executionTime?.toString(),
            onHold = umschlaege[0]?.transportOrderInbound?.detailedData?.toString(),
            emptyFull = umschlaege[0]?.publicData?.goodsEnclosed.toString(),
            isoCode = umschlaege[0]?.publicData?.isoCode,
            containerCode = umschlaege[0]?.id.toString()
        )
    }
    else
    {
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
}