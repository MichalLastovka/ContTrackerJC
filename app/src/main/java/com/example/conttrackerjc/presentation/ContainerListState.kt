package com.example.conttrackerjc.presentation

import androidx.compose.runtime.snapshots.SnapshotStateList
import com.example.conttrackerjc.data.Container

data class ContainerListState(
    var showDialog: Boolean = false,
    var enterIDText: String = "",
    var containerList: List<Container> = emptyList<Container>(),
    var wantedCont: Container = Container(containerId="Kontejner nenalezen", terminal="", carrierCode="", carrierName=null, vesselCode="", vesselName="", status="", delivery="", onHold="", emptyFull="", isoCode="", containerCode="")
)