package com.example.notbored.domain.rest

import com.example.notbored.data.model.ActivitiesList
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

//RETROFIT AND CALL TO THE API SERVICE
interface APIService {
    @GET("/api/activity?participants=")
    suspend fun getActivities(@Query("participants") participants: String): ActivitiesList
}

object RetrofitClient {
    val apiservice by lazy {
        Retrofit.Builder()
            .baseUrl("http://www.boredapi.com/")
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build().create(APIService::class.java)
    }
}