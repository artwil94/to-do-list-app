package com.example.todolistapp.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


enum class TaskPriority {
    LOW,
    MEDIUM,
    HIGH
}

@Entity(tableName = "task_table")
data class Task(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo("title")
    val title: String,
    @ColumnInfo("description")
    val description: String,
    @ColumnInfo("due_date")
    val dueDate: String,
    @ColumnInfo("category")
    val category: String,
    @ColumnInfo("priority")
    val priority: TaskPriority
)