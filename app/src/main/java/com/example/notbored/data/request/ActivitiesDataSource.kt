package com.example.notbored.data.request

import com.example.notbored.data.model.Activity
import com.example.notbored.domain.rest.APIService

class ActivitiesDataSource(private val apiService: APIService) {
    //Search Movies trough the WEB/API.
    suspend fun getActivity(
        participants: String,
        type: String,
        minprice: String,
        maxprice: String
    ): Activity {
        return apiService.getActivity(participants, type, minprice, maxprice)
    }
}