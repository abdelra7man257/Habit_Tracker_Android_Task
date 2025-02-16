package com.example.habittrackerappebeandroidtask.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.material.icons.filled.TaskAlt
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import com.example.habittrackerappebeandroidtask.R

@Composable
fun HabitItemStatus(
    modifier: Modifier = Modifier,
    habitStatus: Boolean
) {

 val (statusIcon,tint) =   when(habitStatus)
    {
        true -> {
            Icons.Default.TaskAlt to MaterialTheme.colorScheme.primary
        }
        false -> {
            Icons.Default.Schedule to MaterialTheme.colorScheme.error
        }

    }

    Box(modifier.fillMaxHeight() , contentAlignment = androidx.compose.ui.Alignment.BottomEnd) {
        Icon(
            modifier = Modifier
                .size(30.dp).align(Alignment.BottomEnd),
            imageVector = statusIcon,
            contentDescription = null,
            tint = tint
        )
    }
}