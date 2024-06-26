package com.example.todolistapp.domain.model

data class TaskModel(
    val id: Int = 0,
    val title: String,
    val description: String,
    val dueDate: String? = null,
    val priority: Priority? = null,
    val category: String? = null
)