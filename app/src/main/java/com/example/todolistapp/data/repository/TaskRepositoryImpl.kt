package com.example.todolistapp.data.repository

import com.example.todolistapp.data.Task
import com.example.todolistapp.data.TasksDataBase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class TaskRepositoryImpl @Inject constructor(private val tasksDataBase: TasksDataBase) :
    TaskRepository {
    override suspend fun addTask(task: Task) {
        tasksDataBase.taskDao.addTask(task = task)
    }

    override suspend fun deleteTask(taskId: Int) {
        withContext(Dispatchers.IO) {
            val task = tasksDataBase.taskDao.getTaskObject(taskId)
            tasksDataBase.taskDao.deleteTask(task = task)
        }
    }

    override suspend fun updateTask(task: Task) {
        tasksDataBase.taskDao.updateTask(task = task)
    }

    override fun getTask(id: Int): Flow<Task> = tasksDataBase.taskDao.getSelectedTask(id = id)

    override suspend fun getTaskDetails(id: String): Task =
        withContext(Dispatchers.IO) {
            tasksDataBase.taskDao.getTaskObject(id.toInt())
        }

    override fun getAllTasks(): Flow<List<Task>> =
        tasksDataBase.taskDao.getAllTasks()


}