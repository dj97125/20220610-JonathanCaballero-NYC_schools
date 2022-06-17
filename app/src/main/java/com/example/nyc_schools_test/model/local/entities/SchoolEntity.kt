package com.example.nyc_schools_test.model.local.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "school_table")
data class SchoolEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int = 0,
    @ColumnInfo(name = "dbn")
    val dbn: String,
    @ColumnInfo(name = "school_name")
    val school_name: String?,
    @ColumnInfo(name = "overview_paragraph")
    val overview_paragraph: String,
    @ColumnInfo(name = "neighborhood")
    val neighborhood : String,
    @ColumnInfo(name = "location")
    val location: String?,
    @ColumnInfo(name = "phone_number")
    val phone_number: String?,
    @ColumnInfo(name = "school_email")
    val school_email: String?,
    @ColumnInfo(name = "website")
    val website: String?,
)