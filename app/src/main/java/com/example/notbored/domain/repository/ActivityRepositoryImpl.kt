package com.example.notbored.domain.repository

import com.example.notbored.data.model.Activity
import com.example.notbored.data.request.ActivitiesDataSource

class ActivityRepositoryImpl(private val dataSource: ActivitiesDataSource) : ActivityRepository {
    override suspend fun getActivity(
        participants: String,
        type: String,
        minprice: String,
        maxprice: String
    ): Activity = dataSource.getActivity(participants, type, minprice, maxprice)
}