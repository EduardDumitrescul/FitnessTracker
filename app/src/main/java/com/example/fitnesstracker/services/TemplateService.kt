package com.example.fitnesstracker.services

import android.util.Log
import com.example.fitnesstracker.data.dto.ExerciseWithSets
import com.example.fitnesstracker.data.dto.TemplateSummary
import com.example.fitnesstracker.data.models.Exercise
import com.example.fitnesstracker.data.models.Muscle
import com.example.fitnesstracker.data.models.Template
import com.example.fitnesstracker.data.repositories.ExerciseRepository
import com.example.fitnesstracker.data.repositories.MuscleRepository
import com.example.fitnesstracker.data.repositories.TemplateRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import javax.inject.Inject

private const val TAG = "TemplateService"

class TemplateService @Inject constructor(
    private val templateRepository: TemplateRepository,
    private val muscleRepository: MuscleRepository,
    private val exerciseRepository: ExerciseRepository
) {
    @OptIn(ExperimentalCoroutinesApi::class)
    fun getTemplateSummaries(): Flow<List<TemplateSummary>> {
        return templateRepository.getBaseTemplates().flatMapLatest { templates ->
            if (templates.isEmpty()) {
                Log.d(TAG, "No templates found.")
                flowOf(emptyList())
            } else {
                val templateSummaryFlows = templates.map { template ->
                    val musclesFlow = muscleRepository.getMusclesByTemplateId(template.id)
                    val exercisesFlow = exerciseRepository.getExercisesWithSetsByTemplateId(template.id)

                    combine(musclesFlow, exercisesFlow) { muscles, exercises ->
                        Log.d(TAG, "Muscles: $muscles")
                        Log.d(TAG, "Exercises: $exercises")

                        TemplateSummary(
                            templateId = template.id,
                            templateName = template.name,
                            workedMuscles = muscles.map { it.name },
                            exerciseList = exercises.map { "${it.sets.size} x ${it.exercise.name}" }
                        )
                    }
                }

                Log.d(TAG, templateSummaryFlows.toString())

                combine(templateSummaryFlows) {
                    val summaries = it.toList()
                    Log.d(TAG, "Combined summaries: $summaries")
                    summaries
                }
            }
        }
    }

}