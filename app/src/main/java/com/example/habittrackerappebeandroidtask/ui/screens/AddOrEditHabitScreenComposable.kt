package com.example.habittrackerappebeandroidtask.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.habittrackerappebeandroidtask.ui.model.HabitUI
import com.example.habittrackerappebeandroidtask.ui.viewmodel.SharedViewModelContract

@Composable
fun AddOrEditHabitScreen(
    modifier: Modifier = Modifier,
    state: SharedViewModelContract.State,
    onNameChange: (String) -> Unit,
    onDescription: (String) -> Unit,
    onCancel: () -> Unit,
    onConfirm: () -> Unit,
) {

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.background)
            .padding(10.dp),
        verticalArrangement = androidx.compose.foundation.layout.Arrangement.spacedBy(10.dp)
    ) {
        OutlinedTextField(
            value = state.selectedHabit?.habitName ?: "",
            onValueChange = {
                onNameChange(it)
            },
            label = {
                Text("Habit Name")
            },
            maxLines = 1,
            modifier = Modifier
                .background(color = Color.Transparent)
                .fillMaxWidth(),
        )

        OutlinedTextField(
            value = state.selectedHabit?.habitDescription ?: "",
            onValueChange = {
                onDescription(it)
            },
            maxLines = 5,
            minLines = 5,
            label = {
                Text("Habit Description")
            },
            modifier = Modifier
                .background(color = Color.Transparent)
                .fillMaxWidth(),
        )
        Row(
            Modifier.fillMaxWidth(),
            horizontalArrangement = androidx.compose.foundation.layout.Arrangement.spacedBy(10.dp)
        ) {
            Button(modifier = Modifier.weight(1f), onClick = {
                onConfirm()
            }) {
                Text("Confirm")
            }

            Button(
                modifier = Modifier.weight(1f),
                onClick = {
                    onCancel()
                },
                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.error)) {
                Text("Cancel")
            }
        }

    }

}