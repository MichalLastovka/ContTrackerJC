package com.example.conttrackerjc.presentation

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.core.content.ContextCompat
import com.example.conttrackerjc.ConTrackerApp
import com.example.conttrackerjc.data.Container

data class ContainerListState(
    var showDialog: Boolean = false,
    var enterIDText: String = "",
    var containerList: List<Container> = emptyList<Container>(),
    var wantedCont: Container = Container(containerId="Kontejner nenalezen", terminal="", carrierCode="", carrierNameEN=null, carrierNameDE=null, vesselCode="", vesselName="", status="", delivery="", onHold="", emptyFull="", isoCode="", containerCode=""),
    var hasNotificationPermission: Boolean = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU){
        ContextCompat.checkSelfPermission(ConTrackerApp.getAppContext(), Manifest.permission.POST_NOTIFICATIONS) == PackageManager.PERMISSION_GRANTED
    }else{
        true
    },
)