package com.example.conttrackerjc.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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

@Composable
fun Title(
    id: String,
    status: String?
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = id, fontSize = 30.sp, fontWeight = FontWeight.Bold)
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                modifier = Modifier
                    .width(35.dp),
                painter = painterResource(
                    id =
                    when (status){
                        "GELOESCHT" -> R.drawable.container_crane
                        "ARRIVED" -> R.drawable.container_crane
                        "CONTAINER_NICHT_GELOESCHT" -> R.drawable.container_ship2
                        "ANNOUNCED" -> R.drawable.container_ship2
                        "GESPERRT" -> R.drawable.pause_button
                        else -> {R.drawable.delivered}
                    }
                ),
                contentDescription = "status"
            )
            Text(
                modifier = Modifier.padding(start = 10.dp),
                text =
                when (status){
                    "GELOESCHT" -> "Složeno"
                    "ARRIVED" -> "Složeno"
                    "CONTAINER_NICHT_GELOESCHT" -> "Na cestě"
                    "ANNOUNCED" -> "Na cestě"
                    "GESPERRT" -> "Pozastaveno"
                    else -> status.toString()
                },
                color = if (status == "GELOESCHT" || status == "ARRIVED") Color.Green else Color.Red,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Composable
fun Terminal(
    terminal: String?
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier,
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                modifier = Modifier.width(40.dp),
                painter = painterResource(id = R.drawable.terminal),
                contentDescription = "terminal"
            )
            Text(text = "Terminál")
        }

        Text(
            modifier = Modifier.padding(start = 10.dp),
            text = if (terminal.isNullOrBlank()) "Nedostupné" else terminal,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
fun Ship(
    name: String?,
    callSign: String?
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier,
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                modifier = Modifier.width(40.dp),
                painter = painterResource(id = R.drawable.container_ship),
                contentDescription = "ship"
            )
            Text(text = "Plavidlo")
        }

        Text(
            modifier = Modifier.padding(start = 10.dp),
            text = if (name == "null" && callSign == "null") "Nedostupné" else "$name ($callSign)",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
fun Reeder(
    reederCode: String?,
    reederEN: String?,
    reederDE: String?

) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 5.dp),
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier,
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                modifier = Modifier.width(40.dp),
                painter = painterResource(id = R.drawable.captain),
                contentDescription = "captain"
            )
            Text(text = "Rejdař")
        }

        Text(
            modifier = Modifier.padding(start = 10.dp),
            text =
            if (reederDE == "" && reederEN == ""){
                "Nedostupné"
            }else
                when(reederDE){
                    "" -> "$reederEN($reederCode)"
                    else -> "$reederDE($reederCode)"
                    },
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
fun TimeOfDelivery(
    time: String?
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 5.dp),
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier,
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                modifier = Modifier.width(40.dp),
                painter = painterResource(id = R.drawable.clock),
                contentDescription = "time"
            )
            Text(text = "Složeno")
        }

        Text(
            modifier = Modifier.padding(start = 10.dp),
            text = if (time == "") "Nedostupné" else "$time",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
    }
}



@Preview(
    showSystemUi = true,
    showBackground = true
)
@Composable
fun PrevTitle(

) {
    Title("YMLU8786435", "GELOESCHT")
}

@Preview
@Composable
fun PrevTerminal(

) {
    Terminal(terminal = "YMCA")
}

@Preview
@Composable
fun PrevShip(

) {
    Ship("MayDay", "AC132")
}