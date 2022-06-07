package com.example.notbored.data.model

data class Activity(
    val activity: String,
    val accessibility: Float,
    val type: String,
    val participants: String,
    val price: Float,
    val key: String
    )

data class ActivitiesList(val results: List<Activity> = listOf())