package com.example.todolistapp.data.repository

import com.example.todolistapp.data.Task
import com.example.todolistapp.data.TasksDataBase
import com.example.todolistapp.domain.model.TaskModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class TaskRepositoryImpl @Inject constructor(private val tasksDataBase: TasksDataBase) :
    TaskRepository {
    override suspend fun addTask(taskModel: TaskModel) {
        val task = Task(
            title = taskModel.title,
            description = taskModel.description,
            dueDate = taskModel.dueDate,
            priority = taskModel.priority,
            category = taskModel.category
        )
        tasksDataBase.taskDao.addTask(task)
    }

    override suspend fun deleteTask(id: Int) {
        TODO("Not yet implemented")
    }

    override fun getTask(id: Int): Flow<Task> {
        TODO("Not yet implemented")
    }

    override fun getAllTasks(): Flow<List<Task>>  =
        tasksDataBase.taskDao.getAllTasks()


}