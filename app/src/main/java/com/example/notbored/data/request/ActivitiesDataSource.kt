package com.example.notbored.data.request

import com.example.notbored.data.model.ActivitiesList
import com.example.notbored.domain.rest.APIService

class ActivitiesDataSource(private val apiService: APIService) {
    //Search Movies trough the WEB/API.
    suspend fun getActivities(participants: String): ActivitiesList {
         return apiService.getActivities(participants)
    }
}