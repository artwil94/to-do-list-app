package com.example.todolistapp.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [Task::class], version = 1
)
abstract class TasksDataBase : RoomDatabase() {

    abstract val task: Task
}