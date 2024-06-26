package com.example.todolistapp.task

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todolistapp.data.repository.TaskRepository
import com.example.todolistapp.domain.model.TaskModel
import com.example.todolistapp.home.TaskUIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddNewTaskViewModel @Inject constructor(
    private val taskRepository: TaskRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(TaskUIState())
    val uiState = _uiState.asStateFlow()

    fun addTask(taskModel: TaskModel) {
        viewModelScope.launch {
            taskRepository.addTask(taskModel)
        }
    }
}