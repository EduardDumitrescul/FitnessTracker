package com.example.fitnesstracker.ui.views.template.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fitnesstracker.data.dto.TemplateDetailed
import com.example.fitnesstracker.services.TemplateService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class TemplateDetailedViewModel @Inject constructor(
    val templateId: Int,
    templateService: TemplateService
): ViewModel() {
    val templateDetailed: StateFlow<TemplateDetailed> = templateService
        .getTemplateWithExercisesById(templateId)
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), TemplateDetailed.default())
}