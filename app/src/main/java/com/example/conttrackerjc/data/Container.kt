package com.example.conttrackerjc.data

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "containers")
data class Container(
    @PrimaryKey(autoGenerate = false)
    val containerId: String,
    val notifyOn: Boolean = false,
    val terminal: String?,
    val carrierCode: String?,
    val carrierName: String?,
    val vesselCode: String?,
    val vesselName: String?,
    val status: String?,
    val delivery: String?,
    val onHold: String?,
    val emptyFull: String?,
    val isoCode: String?,
    val containerCode: String?
)