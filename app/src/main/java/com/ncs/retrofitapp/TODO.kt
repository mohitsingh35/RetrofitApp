package com.ncs.retrofitapp

data class TODO(
    val completed: Boolean,
    val id: Int,
    val title: String,
    val userId: Int
)