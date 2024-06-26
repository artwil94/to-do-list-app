package com.example.todolistapp.data.repository

import com.example.todolistapp.data.Task
import com.example.todolistapp.domain.model.TaskModel
import kotlinx.coroutines.flow.Flow

interface TaskRepository {
    suspend fun addTask(taskModel: TaskModel)

    suspend fun deleteTask(id: Int)

    fun getTask(id: Int): Flow<Task>

    fun getAllTasks(): Flow<List<Task>>

}