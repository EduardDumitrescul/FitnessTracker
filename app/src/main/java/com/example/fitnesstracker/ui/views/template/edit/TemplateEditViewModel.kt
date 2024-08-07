package com.example.fitnesstracker.ui.views.template.edit

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fitnesstracker.data.dto.TemplateDetailed
import com.example.fitnesstracker.data.models.ExerciseSet
import com.example.fitnesstracker.services.TemplateService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

private const val TAG = "TemplateEditViewModel"

@HiltViewModel
class TemplateEditViewModel @Inject constructor(
    private var templateId: Int,
    private val templateService: TemplateService,
): ViewModel() {
    private val _templateDetailed = MutableStateFlow(TemplateDetailed.default())
    val templateDetailed: StateFlow<TemplateDetailed> get() = _templateDetailed

    init {
        if(templateId == 0) {
            createNewTemplate()
        }
        else {
            getTemplateData()
        }

    }

    private fun createNewTemplate() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                templateId = templateService.createNewTemplate()
                val flow = templateService.getTemplateWithExercisesById(templateId)

                flow.collect { templateDetailed ->
                    _templateDetailed.value = templateDetailed
                }
            }

        }
    }

    private fun getTemplateData() {
        viewModelScope.launch {
            templateService.getTemplateWithExercisesById(templateId)
                .collect { newTemplateDetailed ->
                    _templateDetailed.value = newTemplateDetailed
                }
        }
    }

    fun updateSet(set: ExerciseSet) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                templateService.updateSet(set)
            }
        }
    }

    fun addSet(templateExerciseCrossRefId: Int) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                templateService.addSetToTemplateExercise(templateExerciseCrossRefId)
            }
        }
    }

    fun removeSet(templateExerciseCrossRefId: Int, setId: Int) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                templateService.removeSetFromTemplateExercise(templateExerciseCrossRefId, setId)
            }
        }
    }

    fun addExercise(exerciseId: Int) {
        viewModelScope.launch {
            templateService.addExerciseToTemplate(templateId, exerciseId)
        }
    }

    fun updateTemplateName(templateName: String) {
        viewModelScope.launch {
            templateService.updateTemplateName(templateId, templateName)
        }
    }

    fun removeExerciseFromTemplate(templateExerciseCrossRefId: Int) {
        viewModelScope.launch {
            templateService.removeExerciseFromTemplate(templateExerciseCrossRefId)
        }
    }
}