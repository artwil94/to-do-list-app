package com.example.todolistapp

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class ToDoApp : Application() {
    override fun onCreate() {
        super.onCreate()
    }
}