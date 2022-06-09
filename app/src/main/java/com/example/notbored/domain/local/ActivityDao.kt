package com.example.notbored.domain.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.notbored.data.model.Activity

@Dao
interface ActivityDao {
    /**
     *Queries for saving the data obtained in the api to be saved locally
     */
    @Query("SELECT * FROM Activity")
    suspend fun getLocalActivityList(): List<Activity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveLocalActivityList(activity: Activity)
}