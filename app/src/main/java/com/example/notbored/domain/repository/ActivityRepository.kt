package com.example.notbored.domain.repository

import com.example.notbored.data.model.ActivitiesList

interface ActivityRepository {

    suspend fun getActivities(participants: String): ActivitiesList
}