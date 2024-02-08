package com.example.conttrackerjc.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.UUID


@Entity(tableName = "containers")
data class Container(
    @PrimaryKey(autoGenerate = false)
    val containerId: String,
    val notifyOn: Boolean = false,
    var note: String? = "",
    val uuid: UUID = UUID.randomUUID(),
    val terminal: String?,
    val carrierCode: String?,
    val carrierNameEN: String?,
    val carrierNameDE: String?,
    val vesselCode: String?,
    val vesselName: String?,
    val status: String?,
    val delivery: String?,
    val onHold: String?,
    val emptyFull: String?,
    val isoCode: String?,
    val containerCode: String?
)

//PartialContainer serves for updating local database without overriding the "notify" and "note" data field
@Entity
data class PartialContainer(
    @PrimaryKey(autoGenerate = false)
    val containerId: String,
    val terminal: String?,
    val carrierCode: String?,
    val carrierNameEN: String?,
    val carrierNameDE: String?,
    val vesselCode: String?,
    val vesselName: String?,
    val status: String?,
    val delivery: String?,
    val onHold: String?,
    val emptyFull: String?,
    val isoCode: String?,
    val containerCode: String?
)
