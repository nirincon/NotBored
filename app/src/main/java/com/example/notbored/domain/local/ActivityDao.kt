package com.example.notbored.domain.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.notbored.data.model.Activity

@Dao
interface ActivityDao {

    @Query("SELECT * FROM Activity")
    suspend fun getLocalActivityList(): ArrayList<Activity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveLocalActivityList(activity: Activity)
}