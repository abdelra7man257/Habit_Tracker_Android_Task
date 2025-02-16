import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.habittrackerappebeandroidtask.ui.components.HabitItem
import com.example.habittrackerappebeandroidtask.ui.components.MainHeaderComposable
import com.example.habittrackerappebeandroidtask.ui.viewmodel.SharedViewModelContract
import com.kizitonwose.calendar.compose.WeekCalendar
import com.kizitonwose.calendar.compose.weekcalendar.rememberWeekCalendarState
import com.kizitonwose.calendar.core.WeekDay
import com.kizitonwose.calendar.core.daysOfWeek
import com.kizitonwose.calendar.core.firstDayOfWeekFromLocale
import com.kizitonwose.calendar.core.yearMonth
import java.time.DayOfWeek
import java.time.Instant
import java.time.LocalDate
import java.time.YearMonth
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.time.format.TextStyle
import java.util.Locale

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun HistoryScreenComposable(
    modifier: Modifier = Modifier,
    habitsState: SharedViewModelContract.State,
    onDateSelected: (Long) -> Unit = {},
    ) {
    var currentDate by remember { mutableStateOf(LocalDate.now()) }
    val firstDayOfWeek = remember { firstDayOfWeekFromLocale() }

    val state = rememberWeekCalendarState(
        startDate = YearMonth.now().minusMonths(100).atDay(1),
        endDate = YearMonth.now().plusMonths(100).atEndOfMonth(),
        firstVisibleWeekDate = currentDate,
        firstDayOfWeek = firstDayOfWeek
    )
    val currentMonth by remember {
        derivedStateOf { state.firstVisibleWeek.days.first().date.yearMonth }
    }

    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = currentMonth.format(DateTimeFormatter.ofPattern("MMMM yyyy")),
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        WeekCalendar(
            state = state,
            modifier = Modifier.background(Color.White),
            weekHeader = { DaysOfWeekTitle(daysOfWeek()) },
            dayContent = { day ->
                Day(day, isSelected = day.date == currentDate) {
                    currentDate = it.date
                    onDateSelected(it.date.atStartOfDay().toEpochSecond(ZoneId.systemDefault().rules.getOffset(Instant.now())))
                    Log.d("HistoryScreenComposable", "Date selected: ${it.date.atStartOfDay().toEpochSecond(ZoneId.systemDefault().rules.getOffset(Instant.now()))}")
                }
            }
        )

        Spacer(modifier = Modifier.height(16.dp))
        MainHeaderComposable(progress = habitsState.progress, completedHabits = habitsState.completedHabits, totalHabits = habitsState.totalHabits)

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            contentPadding = PaddingValues(horizontal = 10.dp, vertical = 10.dp)
        ) {
            itemsIndexed(habitsState.listHabitUI , key = { _, item -> item.habitId }) { index, item->
                HabitItem(habit = item)
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun Day(day: WeekDay, isSelected: Boolean, onClick: (WeekDay) -> Unit) {
    Column(
        modifier = Modifier
            .aspectRatio(1f)
            .fillMaxHeight()
            .clickable { onClick(day) },
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = day.date.dayOfMonth.toString(),
            color = if (isSelected) Color(0xFFFFAA00) else Color.Black
        )
        if (isSelected) {
            Box(
                modifier = Modifier
                    .width(30.dp)
                    .height(5.dp)
                    .background(Color(0xFFFFAA00), shape = RoundedCornerShape(10.dp))
            )
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun DaysOfWeekTitle(daysOfWeek: List<DayOfWeek>) {
    Row(modifier = Modifier.fillMaxWidth()) {
        for (dayOfWeek in daysOfWeek) {
            Text(
                modifier = Modifier.weight(1f),
                textAlign = TextAlign.Center,
                text = dayOfWeek.getDisplayName(TextStyle.SHORT, Locale.getDefault()),
            )
        }
    }
}
