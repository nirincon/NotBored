package com.example.notbored.data.request

import com.example.notbored.data.model.Activity
import com.example.notbored.domain.rest.APIService

class ActivitiesDataSource(private val apiService: APIService) {
    //Search Movies trough the WEB/API.
    suspend fun getActivity(participants: String, type: String): Activity {
         return apiService.getActivity(participants, type)
    }
}