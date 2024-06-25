package com.example.fitnesstracker.view.exercise

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.fitnesstracker.view.components.appbar.LargeAppBar
import com.example.fitnesstracker.view.components.textfield.FilledTextField
import com.example.fitnesstracker.view.theme.AppTheme
import androidx.hilt.navigation.compose.hiltViewModel


@Composable
fun ExerciseListView(
    navigateBack: () -> Unit,
    viewModel: ExerciseListViewModel = hiltViewModel<ExerciseListViewModel>(),
) {
    val exerciseSummaries by viewModel.filteredExerciseSummaries.collectAsState()

    LazyColumn(
        Modifier.padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item {
            LargeAppBar(
                title = "Exercises",
                onNavigationIconClick = navigateBack
            )
        }
        item {
            FilledTextField(
                onValueChange = {viewModel.updateFilter(it)},
                placeholderText = "exercise name"
            )
        }


        items(exerciseSummaries) {
            ExerciseRow(it)
        }
    }
}


@Composable
@Preview(showBackground = true)
fun Preview() {
    AppTheme {
        ExerciseListView(
            navigateBack = {}
        )
    }

}