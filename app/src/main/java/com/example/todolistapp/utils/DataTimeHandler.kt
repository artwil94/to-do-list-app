package com.example.todolistapp.utils

import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId

object DataTimeHandler {
    fun LocalDateTime.toMillis() = this.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli()

    fun Long.toLocalDateTime(): LocalDateTime {
        return LocalDateTime.ofInstant(Instant.ofEpochMilli(this), ZoneId.systemDefault())
    }
}