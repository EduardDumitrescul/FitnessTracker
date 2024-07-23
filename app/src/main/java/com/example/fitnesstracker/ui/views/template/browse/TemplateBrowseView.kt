package com.example.fitnesstracker.ui.views.template.browse

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.fitnesstracker.data.dto.TemplateSummary
import com.example.fitnesstracker.ui.components.appbar.LargeAppBar
import com.example.fitnesstracker.ui.components.button.Fab
import com.example.fitnesstracker.ui.components.button.FilledButton
import com.example.fitnesstracker.ui.theme.AppTheme

private const val TAG = "TemplateBrowseView"

@Composable
fun TemplateBrowseView(
    viewModel: TemplateBrowseViewModel = hiltViewModel<TemplateBrowseViewModel>(),
    navigateToTemplateDetailedView: (Int) -> Unit,
) {
    val templateSummaries by viewModel.templateSummaries.collectAsState()
    Log.d(TAG, templateSummaries.toString())
    
    StatelessTemplateBrowseView(
        templates = templateSummaries,
        onCardClicked = navigateToTemplateDetailedView
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun StatelessTemplateBrowseView(
    templates: List<TemplateSummary>,
    onCardClicked: (Int) -> Unit = {},
) {
    Scaffold(
        topBar = {
            LargeAppBar(
                title = "Templates",
                showNavigationIcon = false,
                actions = {},
            )
        },
        floatingActionButton = {
            Fab(Icons.Rounded.Add, "more options", onClick = { /*TODO*/ })
        },
        containerColor = AppTheme.colors.background
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .padding(innerPadding)
                .padding(horizontal = AppTheme.dimensions.paddingLarge),
            verticalArrangement = Arrangement.spacedBy(AppTheme.dimensions.spacingLarge)
        ) {
            item {
                FilledButton(
                    text = "start new workout",
                    onClick = { /*TODO*/ },
                    modifier = Modifier.fillMaxWidth()
                )
            }

            items(templates) {template ->
                TemplateCard(
                    template = template,
                    onClick = {
                        onCardClicked(template.templateId)
                    }
                )
            }

        }


    }
}

@Preview
@Composable
fun PreviewTemplateBrowseView() {
    val template = TemplateSummary(
        templateId = 0,
        templateName = "Push Workout",
        workedMuscles = listOf("chest", "shoulders", "triceps"),
        exerciseList = listOf(
            "3 x bench press",
            "4 x shoulder press",
            "3 x triceps pushdown",
            "2 x incline press",
            "5 x lateral raise")
    )
    AppTheme {
        StatelessTemplateBrowseView(
            templates = listOf(template, template, template, template)
        )
    }
}