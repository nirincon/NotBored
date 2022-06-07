package com.example.notbored.domain.repository

import com.example.notbored.data.model.ActivitiesList
import com.example.notbored.data.request.ActivitiesDataSource

class ActivityRepositoryImpl(private val dataSource: ActivitiesDataSource): ActivityRepository {
    override suspend fun getActivities(participants: String): ActivitiesList = dataSource.getActivities(participants)
}