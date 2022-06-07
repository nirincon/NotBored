package com.example.notbored.domain.repository

import com.example.notbored.data.model.Activity

interface ActivityRepository {

    suspend fun getActivity(participants: String): Activity
}