package com.example.nyc_schools_test.model.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.nyc_schools_test.model.local.entities.SchoolEntity

@Dao
interface SchoolsDao {

    @Query("SELECT * FROM school_table order by dbn")
    suspend fun getSchools(): List<SchoolEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSchools(schools: List<SchoolEntity>)

    @Query("DELETE FROM school_table")
    suspend fun deleteAllSchool()


}