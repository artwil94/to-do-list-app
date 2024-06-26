package com.example.todolistapp.data.di

import android.content.Context
import androidx.room.Room
import com.example.todolistapp.data.TasksDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object TimberTransportModule {

    @Singleton
    @Provides
    fun provideTaskDataBase(@ApplicationContext context: Context): TasksDataBase {
        return Room.databaseBuilder(context, TasksDataBase::class.java, "tasks_database")
            .build()
    }
}