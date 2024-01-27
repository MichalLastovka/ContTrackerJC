package com.example.conttrackerjc.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.outlined.CheckCircle
import androidx.compose.material.icons.outlined.Clear
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material.icons.outlined.Refresh
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.conttrackerjc.R
import com.example.conttrackerjc.data.Container
import com.example.conttrackerjc.ui.theme.GoldenBell

@Composable
fun ContainerItem(
    container: Container,
    onContainerClicked: () -> Unit,
    onDeleteClicked: () -> Unit,
    onRefreshClicked: () -> Unit,
    onNotifyClicked: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {

        Text(modifier = Modifier
                .clickable { onContainerClicked() },
            text = container.containerId,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
        Image(
            modifier = Modifier
                .width(35.dp),
            painter = painterResource(id =
                when (container.status){
                    "GELOESCHT" -> R.drawable.container_crane
                    "CONTAINER_NICHT_GELOESCHT" -> R.drawable.container_ship2
                    else -> {R.drawable.delivered}
                }
            ),
            contentDescription = "")

        Icon(
            modifier = Modifier.clickable { onNotifyClicked() },
            imageVector = if(!container.notifyOn)Icons.Outlined.Notifications else Icons.Filled.Notifications,
            contentDescription = "notify me",
            tint = if(!container.notifyOn)Color.Black else GoldenBell)
        Icon(modifier = Modifier.clickable { onRefreshClicked() }, imageVector = Icons.Outlined.Refresh, contentDescription = "refresh", tint = Color.Blue)
        Icon(
            modifier = Modifier
                .scale(1.2f)
                .clickable { onDeleteClicked() },
            imageVector = Icons.Outlined.Delete,
            contentDescription = "Delete Container",
            tint = Color.Red
        )

    }
}