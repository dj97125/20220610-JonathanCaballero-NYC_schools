package com.example.nyc_schools_test.common

import android.content.Context
import android.widget.Toast
import com.example.nyc_schools_test.model.local.entities.SchoolEntity
import com.example.nyc_schools_test.model.local.entities.SchoolSatEntity
import com.example.nyc_schools_test.model.remote.SchoolListResponse
import com.example.nyc_schools_test.model.remote.SchoolSatResponse

fun Context.toast(message: String, lenght: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this, message, lenght).show()
}

fun SchoolSatResponse.toDomain() =
    SchoolSatResponse(dbn, satTestTakers, readingAvg, mathAvg, writingAvg)

fun SchoolSatEntity.toDomain() =
    SchoolSatResponse(dbn, satTestTakers, readingAvg, mathAvg, writingAvg)

fun SchoolSatResponse.toDatabase() =
    SchoolSatEntity(
        dbn = dbn,
        satTestTakers = satTestTakers,
        readingAvg = readingAvg,
        mathAvg = mathAvg,
        writingAvg = writingAvg
    )

fun SchoolListResponse.toDomain() = SchoolListResponse(
    dbn,
    school_name,
    overview_paragraph,
    neighborhood,
    location,
    phone_number,
    school_email,
    website
)

fun SchoolEntity.toDomain() = SchoolListResponse(
    dbn,
    school_name,
    overview_paragraph,
    neighborhood,
    location,
    phone_number,
    school_email,
    website
)

fun SchoolListResponse.toDatabase() =
    SchoolEntity(
        dbn = dbn,
        school_name = school_name,
        overview_paragraph = overview_paragraph,
        neighborhood = neighborhood,
        location = location,
        phone_number = phone_number,
        school_email = school_email,
        website = website
    )

