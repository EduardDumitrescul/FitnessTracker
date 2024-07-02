package com.example.fitnesstracker.ui.views.exercise.list

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.fitnesstracker.data.dto.ExerciseSummary
import com.example.fitnesstracker.ui.theme.AppTheme

@Composable
fun ExerciseRow(
    model: ExerciseSummary,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        EquipmentIcon(model.equipmentType)
        Column {
            Text(
                text = model.exerciseName,
                color = AppTheme.colors.onBackground,
                style = AppTheme.typography.headline
            )

            MuscleChipRow(model = model.getMuscleChipRowModel())
        }
    }
}


@Composable
@Preview(showBackground = true)
private fun PreviewExerciseRow() {
    AppTheme {
        ExerciseRow(
            ExerciseSummary(
                exerciseId = 1,
                equipmentType = "barbell",
                exerciseName = "Bench Press",
                primaryMuscle = "chest",
                secondaryMuscles = listOf("shoulders", "triceps"),
            )
        )
    }
}