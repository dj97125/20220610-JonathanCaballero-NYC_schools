package com.example.nyc_schools_test.model.local.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.nyc_schools_test.model.local.entities.SchoolSatEntity

@Dao
interface SatDao {

    @Query("SELECT * FROM school_sat_table")
    suspend fun getSchoolsSat(): List<SchoolSatEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSchoolsSat(schools: List<SchoolSatEntity>)

    @Query("DELETE FROM school_sat_table")
    suspend fun deleteAllSchoolSat()
}