package com.example.todolistapp.data.repository

import com.example.todolistapp.data.Task
import kotlinx.coroutines.flow.Flow

interface TaskRepository {
    suspend fun addTask(task: Task)

    suspend fun deleteTask(taskId: Int)

    suspend fun updateTask(task: Task)

    fun getTask(id: Int): Flow<Task>

    suspend fun getTaskDetails(id: String): Task

    fun getAllTasks(): Flow<List<Task>>

    fun getTasksSortedByDate(): Flow<List<Task>>
}