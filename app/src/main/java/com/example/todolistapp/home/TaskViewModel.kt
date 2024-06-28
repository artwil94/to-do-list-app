package com.example.todolistapp.home

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todolistapp.data.Task
import com.example.todolistapp.data.repository.TaskRepository
import com.example.todolistapp.domain.model.Priority
import com.example.todolistapp.domain.model.TaskModel
import com.example.todolistapp.utils.Constants.TASK_TITLE_LENGTH_LIMIT
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TaskViewModel @Inject constructor(private val taskRepository: TaskRepository) :
    ViewModel() {

    val id: MutableState<Int> = mutableIntStateOf(0)
    val title: MutableState<String> = mutableStateOf("")
    val description: MutableState<String> = mutableStateOf("")
    val priority: MutableState<Priority> = mutableStateOf(Priority.LOW)

    private val _allTasks = MutableStateFlow<List<Task>>(emptyList())
    val allTasks: StateFlow<List<Task>> = _allTasks

    fun addTask() {
        viewModelScope.launch(Dispatchers.IO) {
            val task = Task(
                title = title.value,
                description = description.value,
                priority = priority.value
            )
            taskRepository.addTask(task = task)
        }
    }

    fun updateTask() {
        viewModelScope.launch(Dispatchers.IO) {
            val task = Task(
                id = id.value,
                title = title.value,
                description = description.value,
                priority = priority.value
            )
            taskRepository.updateTask(task = task)
        }
    }

    fun deleteTask(task: Task) {
        viewModelScope.launch(Dispatchers.IO) {
            taskRepository.deleteTask(taskId = task.id)
        }
    }

    fun getAllTasks() {
        viewModelScope.launch {
            taskRepository.getAllTasks().collect { tasks ->
                _allTasks.value = tasks
            }
        }
    }

    private val _selectedTask: MutableStateFlow<Task?> = MutableStateFlow(null)
    val selectedTask: StateFlow<Task?> = _selectedTask
    fun getTaskDetails(taskId: Int) =
        viewModelScope.launch {
            taskRepository.getTask(id = taskId).collect { task ->
                _selectedTask.value = task
            }
        }

    fun updateTitle(newTitle: String) {
        if (newTitle.length <= TASK_TITLE_LENGTH_LIMIT) {
            title.value = newTitle
        }
    }
}

data class TaskUIState(
    val isLoading: Boolean = false,
    val tasks: StateFlow<List<TaskModel>>? = null,
    val isDone: Boolean = false
)