package com.example.todolistapp.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todolistapp.data.repository.TaskRepository
import com.example.todolistapp.domain.model.TaskModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val taskRepository: TaskRepository) :
    ViewModel() {

    private val _uiState = MutableStateFlow(TaskUIState())
    val uiState = _uiState.asStateFlow()

    val taskState: StateFlow<List<TaskModel>> = taskRepository.getAllTasks().map { tasks ->
        tasks.map { task ->
            TaskModel(
                id = task.id,
                title = task.title,
                description = task.description
            )
        }
    }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    fun getAllTasks() {
        _uiState.update { it.copy(isLoading = true) }
        viewModelScope.launch {
            val newTasks = taskRepository.getAllTasks().map { tasks ->
                tasks.map { task ->
                    TaskModel(
                        id = task.id,
                        title = task.title,
                        description = task.description,
                    )
                }
            }
                .stateIn(
                    viewModelScope,
                    SharingStarted.WhileSubscribed(5000),
                    emptyList()
                )
            _uiState.update {
                it.copy(
                    isLoading = false,
                    tasks = newTasks
                )
            }
        }
    }

    fun onDoneClick() {
        _uiState.update {
            it.copy(
                isDone = true
            )
        }
    }

    fun onUncheck() {
        _uiState.update {
            it.copy(
                isDone = false
            )
        }
    }
}

data class TaskUIState(
    val isLoading: Boolean = false,
    val tasks: StateFlow<List<TaskModel>>? = null,
    val isDone: Boolean = false
)