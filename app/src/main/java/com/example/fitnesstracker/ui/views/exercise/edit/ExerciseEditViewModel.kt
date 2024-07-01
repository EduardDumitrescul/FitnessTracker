package com.example.fitnesstracker.ui.views.exercise.edit

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fitnesstracker.data.dto.ExerciseWithMuscles
import com.example.fitnesstracker.data.models.Exercise
import com.example.fitnesstracker.data.repositories.ExerciseRepository
import com.example.fitnesstracker.data.repositories.MuscleRepository
import com.example.fitnesstracker.services.ExerciseService
import com.example.fitnesstracker.services.MuscleService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class ExerciseEditViewModel @Inject constructor(
    val exerciseId: Int,
    private val exerciseService: ExerciseService,
    private val muscleService: MuscleService,
): ViewModel() {

    private var _exercise: MutableStateFlow<ExerciseWithMuscles> = MutableStateFlow(ExerciseWithMuscles(
        exerciseId = 0,
        exerciseName = "",
        exerciseDescription = "",
        equipment = "",
        primaryMuscle = "",
        secondaryMuscles = listOf()
    ))
    val exercise: StateFlow<ExerciseWithMuscles>
        get() = MutableStateFlow(_exercise.value)

    val muscleNames: StateFlow<List<String>> 
        get() = muscleService
            .getMuscleNames()
            .stateIn(viewModelScope, SharingStarted.WhileSubscribed(0), listOf())

    fun updateExerciseName(it: String) {
        _exercise.value.exerciseName = it
    }

    fun updateExerciseDescription(it: String) {
        _exercise.value.exerciseDescription = it
    }

    fun updatePrimaryMuscle(it: String) {
        _exercise.value.primaryMuscle = it
    }

    fun updateSecondaryMuscles(it: Set<String>) {
        _exercise.value.secondaryMuscles = it.toList()
    }

    fun save() {
        if(_exercise.value.exerciseId == 0) {
            exerciseService.add(_exercise.value)
        }

    }

    fun updateEquipment(it: String) {
        _exercise.value.equipment = it
    }

}