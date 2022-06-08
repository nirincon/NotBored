package com.example.notbored.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Activity(
    val activity: String,
    val accessibility: Float,
    val type: String,
    val participants: String,
    val price: Float,
    @PrimaryKey
    val key: String,
    val error: String? = null
)
