package com.example.habittrackerappebeandroidtask.ui.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.StrokeCap.Companion.Round
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.habittrackerappebeandroidtask.R

@Composable
fun MainHeaderComposable(modifier: Modifier = Modifier , progress:Float , completedHabits:Int , totalHabits:Int) {
    val animatedProgress by animateFloatAsState(
        targetValue = progress,
        animationSpec = tween(durationMillis = 800)
    )
    Box(modifier = modifier.padding(horizontal = 10.dp) ,contentAlignment = Alignment.Center ) {
        Image(
            imageVector = ImageVector.vectorResource(R.drawable.mainsvg)
            ,modifier = Modifier.fillMaxWidth().padding(top = 5.dp)
            , contentDescription = "",
            contentScale = ContentScale.FillBounds
        )
        Row(modifier = Modifier.fillMaxWidth() , verticalAlignment = Alignment.CenterVertically , horizontalArrangement = Arrangement.spacedBy(16.dp , Alignment.CenterHorizontally)) {
            Box (Modifier.size(100.dp) , contentAlignment = Alignment.Center){
                CircularProgressIndicator(
                    progress = { animatedProgress },
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background,
                    strokeWidth = 10.dp,
                    trackColor = MaterialTheme.colorScheme.surfaceVariant,
                    strokeCap = Round,
                )
                Text(text = "${progress.times(100).toInt()}%" , color = MaterialTheme.colorScheme.background , fontWeight = FontWeight.Bold)

            }
            Text("$completedHabits of $totalHabits habits completed" , color = MaterialTheme.colorScheme.background , fontWeight = FontWeight.Bold )

        }

    }

}