package com.example.habittrackerappebeandroidtask.ui.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ProgressIndicator(
    progress: Float,
    modifier: Modifier = Modifier,
    size: Int = 100,
    strokeWidth: Float = 12f,
    primaryColor: Color = MaterialTheme.colorScheme.primary,
    secondaryColor: Color = MaterialTheme.colorScheme.surfaceVariant,
) {
    val animatedProgress by animateFloatAsState(
        targetValue = progress,
        animationSpec = tween(durationMillis = 800)
    )

    Box(
        modifier = modifier.size(size.dp),
        contentAlignment = Alignment.Center
    ) {
        // Background Circle
        Canvas(modifier = Modifier.size(size.dp)) {
            drawCircle(
                color = secondaryColor,
                radius = size.dp.toPx() / 2,
                center = Offset(size.dp.toPx() / 2, size.dp.toPx() / 2)
            )
        }

        // Progress Arc
        Canvas(modifier = Modifier.size(size.dp)) {
            drawArc(
                brush = Brush.linearGradient(
                    colors = listOf(primaryColor, Color.Cyan)
                ),
                startAngle = -90f,
                sweepAngle = animatedProgress * 360f,
                useCenter = false,
                style = Stroke(width = strokeWidth, cap = StrokeCap.Round),
                size = Size(size.dp.toPx(), size.dp.toPx()),
                topLeft = Offset.Zero
            )
        }

        // Percentage Text
        Text(
            text = "${(animatedProgress * 100).toInt()}%",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = primaryColor
        )
    }
}
