package com.example.todolistapp.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.todolistapp.domain.model.Priority

@Entity(tableName = "task_table")
data class Task(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo("title")
    val title: String,
    @ColumnInfo("description")
    val description: String,
    @ColumnInfo("due_date")
    val dueDate: String? = null,
    @ColumnInfo("category")
    val category: String? = null,
    @ColumnInfo("priority")
    val priority: Priority? = null
)