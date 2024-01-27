package com.example.conttrackerjc.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.conttrackerjc.data.Container

@Composable
fun ContainerScreen(
    container: Container
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(5.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = container.containerId,
            fontSize = 25.sp,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = when(container.status){
                "GELOESCHT" -> "(Složeno)"
                "CONTAINER_NICHT_GELOESCHT" -> "(Nesloženo)"
                else -> container.status.toString()},
            color = if(container.status == "GELOESCHT") Color.Green else Color.Red,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )

        Row (
            modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Start
        ){
            Text(text = "Kód: ", fontSize = 15.sp, fontWeight = FontWeight.Bold)
            Text(text = container.containerCode.toString())
        }
        Row (
            modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Start
        ){
            Text(text = "Kód rejdaře: ", fontSize = 15.sp, fontWeight = FontWeight.Bold)
            Text(text = container.carrierCode.toString())
        }
        Row (
            modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Start
        ){
            Text(text = "Doručeno: ", fontSize = 15.sp, fontWeight = FontWeight.Bold)
            Text(text = container.delivery.toString())
        }
    }
}

@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
fun PrevContainerScreen(
    
) {
    ContainerScreen(container = Container(containerId="YMLU8786435", terminal="CTB", carrierCode="YML", carrierName=null, vesselCode="5LKX7", vesselName="ONE INNOVATION", status="GELOESCHT", delivery="2024-01-23T08:13:00+01:00", onHold="false", emptyFull="4", isoCode="45G1", containerCode="4N96"))
}