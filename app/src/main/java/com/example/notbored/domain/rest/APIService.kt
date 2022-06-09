package com.example.notbored.domain.rest

import com.example.notbored.data.model.Activity
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Interface, for the retrofit calls, where are the endpoints for this calls.
 */
interface APIService {

    /**
     * Endpoint who return an activity, this endpoint uses 4 optionals params.
     */
    @GET("/api/activity")
    suspend fun getActivity(
        @Query("participants") participants: String,
        @Query("type") type: String,
        @Query("minprice") minprice: String,
        @Query("maxprice") maxprice: String
    ): Activity
}

/**
 * Object who create an instance of retrofit.
 */
object RetrofitClient {
    val apiservice by lazy {
        Retrofit.Builder()
            .baseUrl("http://www.boredapi.com/")
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build().create(APIService::class.java)
    }
}