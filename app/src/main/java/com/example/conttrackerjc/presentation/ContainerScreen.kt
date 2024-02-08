package com.example.conttrackerjc.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.conttrackerjc.R
import com.example.conttrackerjc.data.Container
import com.example.conttrackerjc.presentation.components.Reeder
import com.example.conttrackerjc.presentation.components.Ship
import com.example.conttrackerjc.presentation.components.Terminal
import com.example.conttrackerjc.presentation.components.TimeOfDelivery
import com.example.conttrackerjc.presentation.components.Title

@Composable
fun ContainerScreen(
    container: Container
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(15.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Title(id = container.containerId, status = container.status)
        Terminal(terminal = container.terminal)
        Ship(name = container.vesselName, callSign = container.vesselCode)
        Reeder(reederCode = container.carrierCode, reederEN = container.carrierNameEN, reederDE = container.carrierNameDE)
        TimeOfDelivery(time = container.delivery)

    }
}

@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
fun PrevContainerScreen(
    
) {
    ContainerScreen(container = Container(containerId="YMLU8786435", terminal="CTB", carrierCode="YML", carrierNameDE =null,carrierNameEN =null, vesselCode="5LKX7", vesselName="ONE INNOVATION", status="GELOESCHT", delivery="2024-01-23T08:13:00+01:00", onHold="false", emptyFull="4", isoCode="45G1", containerCode="4N96"))
}