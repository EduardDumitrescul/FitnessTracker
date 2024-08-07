package com.example.fitnesstracker.ui.views.exercise.browse

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.fitnesstracker.data.dto.ExerciseWithMuscles
import com.example.fitnesstracker.data.models.Exercise
import com.example.fitnesstracker.ui.components.MuscleChipRow
import com.example.fitnesstracker.ui.theme.AppTheme

@Composable
fun ExerciseRow(
    model: ExerciseWithMuscles,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        EquipmentIcon(model.exercise.equipment)
        Column {
            Text(
                text = model.exercise.name,
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
            ExerciseWithMuscles(
                exercise = Exercise(
                    id = 1,
                    description = "!231",
                    equipment = "barbell",
                    name = "Bench Press"),
                primaryMuscle = "chest",
                secondaryMuscles = listOf("shoulders", "triceps"),
            )
        )
    }
}