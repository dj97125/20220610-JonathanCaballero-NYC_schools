package com.example.nyc_schools_test.model.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.nyc_schools_test.model.local.daos.SatDao
import com.example.nyc_schools_test.model.local.entities.SchoolEntity
import com.example.nyc_schools_test.model.local.entities.SchoolSatEntity

@Database(
    entities = [
        SchoolEntity::class,
        SchoolSatEntity::class
    ],
    version = 1
)
abstract class SchoolDatabase : RoomDatabase() {
    abstract fun getSchoolsDao(): SchoolsDao
    abstract fun getSatsDao(): SatDao
}