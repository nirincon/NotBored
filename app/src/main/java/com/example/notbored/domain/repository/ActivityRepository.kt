package com.example.notbored.domain.repository

import com.example.notbored.data.model.Activity

interface ActivityRepository {

    /**
     * Repository who search a activity, and return an activity.
     */
    suspend fun getActivity(
        participants: String,
        type: String,
        minprice: String,
        maxprice: String
    ): Activity
}