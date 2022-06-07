package com.example.notbored.domain.rest

import com.example.notbored.data.model.Activity
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

//RETROFIT AND CALL TO THE API SERVICE
interface APIService {
    @GET("/api/activity")
    suspend fun getActivity(@Query("participants") participants: String, @Query("type") type: String): Activity
}

object RetrofitClient {
    val apiservice by lazy {
        Retrofit.Builder()
            .baseUrl("http://www.boredapi.com/")
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build().create(APIService::class.java)
    }
}