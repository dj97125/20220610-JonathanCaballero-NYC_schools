package com.example.nyc_schools_test.model.local.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "school_sat_table")
data class SchoolSatEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int = 0,
    @ColumnInfo(name = "dbn")
    val dbn: String,
    @ColumnInfo(name = "sat_test_takers")
    val satTestTakers: String,
    @ColumnInfo(name = "reading_avg")
    val readingAvg: String,
    @ColumnInfo(name = "math_avg")
    val mathAvg: String,
    @ColumnInfo(name = "writing_avg")
    val writingAvg: String
)
