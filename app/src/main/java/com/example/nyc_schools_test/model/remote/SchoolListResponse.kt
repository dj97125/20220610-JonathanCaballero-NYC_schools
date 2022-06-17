package com.example.nyc_schools_test.model.remote

import com.google.gson.annotations.SerializedName


data class SchoolListResponse(
    val dbn: String,
    val school_name: String?,
    val overview_paragraph: String,
    val neighborhood: String,
    val location: String?,
    val phone_number: String?,
    val school_email: String?,
    val website: String?,
)


data class SchoolSatResponse(
    val dbn: String,
    @SerializedName("num_of_sat_test_takers")
    val satTestTakers: String,
    @SerializedName("sat_critical_reading_avg_score")
    val readingAvg: String,
    @SerializedName("sat_math_avg_score")
    val mathAvg: String,
    @SerializedName("sat_writing_avg_score")
    val writingAvg: String
)

