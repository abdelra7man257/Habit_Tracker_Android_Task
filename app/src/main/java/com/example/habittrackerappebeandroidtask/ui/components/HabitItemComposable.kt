package com.example.habittrackerappebeandroidtask.ui.components

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.DirectionsRun
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.domain.model.Habit
import com.example.habittrackerappebeandroidtask.ui.model.HabitUI

@Composable
fun HabitItem(modifier: Modifier = Modifier, habit: HabitUI, onCheckedChange:(Boolean)->Unit={}) {

    ElevatedCard(
        modifier = modifier
            .fillMaxWidth()
            .background(Color.Transparent)

        ,
        elevation = CardDefaults.elevatedCardElevation(defaultElevation = 10.dp),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.elevatedCardColors(containerColor = MaterialTheme.colorScheme.surface , )

    ) {
        Row(
            Modifier
                .fillMaxWidth()
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Checkbox(onCheckedChange = {
                onCheckedChange(it)
            }, checked =habit.habitProgress)
            Icon(
                modifier = Modifier.size(65.dp),
                imageVector = Icons.Default.CalendarMonth,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.primary
            )

            Column(Modifier.weight(1f)) {
                Text(text = habit.habitName, style = MaterialTheme.typography.titleMedium , maxLines = 1 , overflow = TextOverflow.Ellipsis , )
                Text(
                    modifier = Modifier
                        .padding(top = 4.dp)
                        .fillMaxWidth(),
                    overflow = TextOverflow.Ellipsis,
                    text = habit.habitDescription,
                    maxLines = 1,
                    style = MaterialTheme.typography.bodySmall
                )
            }

            HabitItemStatus(habitStatus = habit.habitProgress)

        }
    }

}


