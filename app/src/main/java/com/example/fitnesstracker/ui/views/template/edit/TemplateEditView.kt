package com.example.fitnesstracker.ui.views.template.edit

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.Edit
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.fitnesstracker.ui.components.appbar.LargeAppBar
import com.example.fitnesstracker.ui.components.button.TextButton
import com.example.fitnesstracker.ui.components.dialog.StringInputDialog
import com.example.fitnesstracker.ui.theme.AppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TemplateEditView(
    previouslySelectedExerciseId: Int = 0,
    viewModel: TemplateEditViewModel = hiltViewModel(),
    onNewExerciseButtonClick: () -> Unit,
    navigateBack: () -> Unit,
) {
    LaunchedEffect(previouslySelectedExerciseId) {
        if(previouslySelectedExerciseId > 0) {
            viewModel.addExercise(previouslySelectedExerciseId)
        }
    }

    val templateWithExercises by viewModel.templateDetailed.collectAsState()

    var shouldShowNameChangeDialog by remember {
        mutableStateOf(false)
    }

    Scaffold(
        topBar = {
            LargeAppBar(
                title = "Edit - ${templateWithExercises.template.name}",
                onNavigationIconClick = navigateBack
            )
        },
        containerColor = AppTheme.colors.background
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(horizontal = AppTheme.dimensions.paddingLarge),
        ) {
            TextButton(
                text = "change name",
                imageVector = Icons.Rounded.Edit,
                onClick = { shouldShowNameChangeDialog = true },
                modifier = Modifier.padding(bottom = 32.dp)
            )


            LazyColumn(
                horizontalAlignment = Alignment.CenterHorizontally ,
                verticalArrangement = Arrangement.spacedBy(16.dp),
            ) {
                items(templateWithExercises.exercisesWithSetsAndMuscles) { exerciseDetailed ->
                    EditableExerciseCard(
                        exerciseDetailed = exerciseDetailed,
                        onClick = { /*TODO*/ },
                        onRemoveClick = {
                            viewModel.removeExerciseFromTemplate(exerciseDetailed.templateExerciseCrossRefId)
                        },
                        updateSet = { set ->
                            viewModel.updateSet(set)
                        },
                        addSet = {
                            viewModel.addSet(exerciseDetailed.templateExerciseCrossRefId)
                        },
                        removeSet = {
                            viewModel.removeSet(exerciseDetailed.templateExerciseCrossRefId, it)
                        },
                        modifier = Modifier.fillMaxWidth()
                    )
                }

                item {
                    TextButton(
                        text = "new exercise",
                        imageVector = Icons.Rounded.Add,
                        onClick = onNewExerciseButtonClick
                    )
                }
            }
        }
    }

    if(shouldShowNameChangeDialog) {
        StringInputDialog(
            title = "Choose a name",
            initialValue = templateWithExercises.template.name,
            onDismissRequest = { shouldShowNameChangeDialog = false },
            onSave = {
                viewModel.updateTemplateName(it)
            })
    }

}

@Preview
@Composable
fun PreviewTemplateEditView() {
    AppTheme {
        TemplateEditView(
            onNewExerciseButtonClick = {},
            navigateBack = {}
        )
    }
}