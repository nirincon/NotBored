package com.example.notbored.data.model

import androidx.room.Entity

@Entity
data class Activity(
    val activity: String,
    val accessibility: Float,
    val type: String,
    val participants: String,
    val price: Float,
    val key: String
    )
