package com.example.todolistapp.domain.model

import com.example.todolistapp.R

enum class Category(val icon: Int) {
    PERSONAL(icon =  R.drawable.ic_personal),
    WORK(icon =R.drawable.ic_work),
    HOME(icon= R.drawable.ic_home),
    SPORT(icon=R.drawable.ic_sport )
}