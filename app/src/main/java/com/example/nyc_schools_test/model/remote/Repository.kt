package com.example.nyc_schools_test.model.remote

import android.util.Log
import com.example.nyc_schools_test.common.FailedResponseException
import com.example.nyc_schools_test.common.StateAction
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

interface Repository {
    fun NYCSchoolCatched(): Flow<StateAction>
    fun NYCSatCatched(schoolDbn: String): Flow<StateAction>
}

class RepositoryImpl @Inject constructor(
    private val service: NycApi
) : Repository {

    override fun NYCSchoolCatched() = flow {
        emit(StateAction.LOADING)
        try {
            val respose = service.getSchoolList()
            if (respose.isSuccessful) {
                respose.body()?.let {
                    emit(StateAction.SUCCESS(it))
                    Log.i("Repository", "NYCSchoolCatched: $it ")
                } ?: throw NullPointerException()
            } else {
                throw FailedResponseException()
            }
        } catch (e: Exception) {
            emit(StateAction.ERROR(e))
        }
    }

    override fun NYCSatCatched(schoolDbn: String) = flow {
        emit(StateAction.LOADING)
        try {
            val respose = service.getSchoolSat(schoolDbn)
            if (respose.isSuccessful) {
                respose.body()?.let {
                    emit(StateAction.SUCCESS(it))
                    Log.i("Repository", "NYCSatCatched: $it ")
                } ?: throw NullPointerException()
            } else {
                throw FailedResponseException()
            }
        } catch (e: Exception) {
            emit(StateAction.ERROR(e))
        }
    }
}