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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.conttrackerjc.ui.theme.CancelButtonRed
import com.example.conttrackerjc.ui.theme.ConfirmButtonGreen

@Composable
fun AddContainerDialog(
    contText: String,
    noteText: String,
    onContValueChange: (String) -> Unit,
    onNoteValueChange: (String) -> Unit,
    onDismiss: () -> Unit,
    onConfirm: (List<String>) -> Unit
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
                    value = contText,
                    maxLines = 1,
                    onValueChange = {
                        onContValueChange(it)
                    },
                    placeholder = {
                        Row {
                            Text(text = "Číslo kontejneru")
                        }
                    }
                )
                OutlinedTextField(
                    modifier = Modifier.padding(horizontal = 25.dp),
                    value = noteText,
                    maxLines = 1,
                    onValueChange = {
                        onNoteValueChange(it)
                    },
                    placeholder = {
                        Row {
                            Text(text = "Poznámka (volitelné)", fontSize = 10.sp)
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
                        colors = ButtonDefaults.buttonColors(containerColor = CancelButtonRed)
                    ) {
                        Text(text = "Zrušit")
                    }
                    Button(
                        enabled = contText.isNotBlank(),
                        onClick = { onConfirm((listOf(contText, noteText))) },
                        colors = ButtonDefaults.buttonColors(containerColor = ConfirmButtonGreen)
                    ) {
                        Text(text = "Uložit")
                    }
                }
            }
        }
    }
}