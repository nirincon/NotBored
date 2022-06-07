package com.example.notbored.domain.repository

import com.example.notbored.data.model.Activity
import com.example.notbored.data.request.ActivitiesDataSource

class ActivityRepositoryImpl(private val dataSource: ActivitiesDataSource): ActivityRepository {
    override suspend fun getActivity(participants: String): Activity = dataSource.getActivity(participants)
}