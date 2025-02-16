package com.example.habittrackerappebeandroidtask.ui.screens

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.History
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.domain.model.Habit
import com.example.habittrackerappebeandroidtask.navigation.Route
import com.example.habittrackerappebeandroidtask.ui.components.HabitItem
import com.example.habittrackerappebeandroidtask.ui.components.MainHeaderComposable
import com.example.habittrackerappebeandroidtask.ui.model.HabitUI
import com.example.habittrackerappebeandroidtask.ui.viewmodel.SharedViewModel
import com.example.habittrackerappebeandroidtask.ui.viewmodel.SharedViewModelContract
import com.google.gson.Gson
import kotlinx.coroutines.flow.collect
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun HabitsMainScreen(modifier: Modifier = Modifier, setSelectedHabit:(HabitUI)->Unit, state: SharedViewModelContract.State, toggleHabitStatus:(Int, Boolean)->Unit, navigate:(Route)->Unit ) {
    var currentDate by remember { mutableStateOf(LocalDate.now()) }

    Box(modifier.fillMaxSize()) {
        Column(Modifier.fillMaxSize()) {
            Text(
                text = currentDate.format(DateTimeFormatter.ofPattern("dd MMMM yyyy")),
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            Spacer(Modifier.height(10.dp))
            MainHeaderComposable(progress = state.progress , completedHabits = state.completedHabits , totalHabits = state.totalHabits)

            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(8.dp),
                contentPadding = PaddingValues(horizontal = 10.dp, vertical = 10.dp)
            ) {
                itemsIndexed(state.listHabitUI , key = { _, item -> item.habitId }) { index, item->
                    HabitItem(Modifier.clickable {
                        setSelectedHabit(item)
                        navigate(Route.AddEditScreen(Gson().toJson(item)))
                    } ,habit = item){
                        toggleHabitStatus(index,it)
                    }
                }
            }


        }
        Column( modifier = Modifier.align(Alignment.BottomEnd).padding(18.dp) , verticalArrangement = Arrangement.spacedBy(12.dp)) {
            FloatingActionButton(onClick = {
                navigate(Route.HabitHistoryScreen)
            } ) {
                Icon(imageVector = Icons.Default.History , contentDescription = "" )
            }

            FloatingActionButton(onClick = {
                navigate(Route.AddEditScreen())
            } ,) {
                Icon(imageVector = Icons.Default.Add , contentDescription = "" )
            }

        }
    }

}