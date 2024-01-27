package com.example.conttrackerjc.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog

@Composable
fun AddContainerDialog(
    text: String,
    onValueChange: (String) -> Unit,
    onDismiss: () -> Unit,
    onConfirm: (String) -> Unit
) {
    Dialog(
        onDismissRequest = { }
    ) {
        Card(
            modifier = Modifier.padding(10.dp),
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                OutlinedTextField(
                    modifier = Modifier.padding(25.dp),
                    value = text,
                    onValueChange = {
                                    onValueChange(it)
                    },
                    placeholder = {
                        Row {
                            Text(text = "Zadej ID kontejneru")
                        }
                    }
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 10.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    Button(
                        onClick = { onDismiss() },
                        colors = ButtonDefaults.buttonColors(containerColor = Color.Red)
                    ) {
                        Text(text = "Zrušit")
                    }
                    Button(
                        onClick = { onConfirm(text) },
                        colors = ButtonDefaults.buttonColors(containerColor = Color.Green)
                    ) {
                        Text(text = "Uložit")
                    }
                }
            }
        }
    }
}

@Preview(
    showBackground = true,
)
@Composable
fun PrevAddContainerDialog(

) {
    AddContainerDialog(onConfirm = {}, onDismiss = {}, onValueChange = {}, text = "")
}