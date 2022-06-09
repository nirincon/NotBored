package com.example.notbored.domain.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.notbored.data.model.Activity

/**
 *Database versioning is defined
 */
@Database(
    entities = [Activity::class],
    version = 1
)
abstract class ActivityDb:RoomDatabase() {

    abstract fun activityDao(): ActivityDao
}