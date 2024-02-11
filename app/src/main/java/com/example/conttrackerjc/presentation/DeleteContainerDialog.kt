package com.example.conttrackerjc.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.conttrackerjc.R
import com.example.conttrackerjc.data.Container
import com.example.conttrackerjc.ui.theme.CancelButtonRed
import com.example.conttrackerjc.ui.theme.ConfirmButtonGreen

@Composable
fun DeleteContainerDialog(
    onNoClicked: () -> Unit,
    onYesClicked: () -> Unit
) {
    Dialog(
        onDismissRequest = {}
    ) {
        Card(
            modifier = Modifier.padding(5.dp)
            ,
        ) {
            Column (
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                Text(text = stringResource(R.string.delete_container_dialog_title), fontSize = 20.sp)
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 10.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    Button(
                        onClick = { onNoClicked() },
                        colors = ButtonDefaults.buttonColors(containerColor = CancelButtonRed)
                    ) {
                        Text(text = stringResource(R.string.answer_no))
                    }
                    Button(
                        onClick = { onYesClicked() },
                        colors = ButtonDefaults.buttonColors(containerColor = ConfirmButtonGreen)
                    ) {
                        Text(text = stringResource(R.string.answer_yes))
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun PrevDelete(
    
) {
    DeleteContainerDialog(onNoClicked = { /*TODO*/ }, onYesClicked = {})
}