package com.example.nyc_schools_test.model.remote


import android.content.ContentValues
import android.util.Log
import com.example.nyc_schools_test.common.*
import com.example.nyc_schools_test.model.local.Dao
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import com.example.nyc_schools_test.common.StateAction
import kotlinx.coroutines.flow.Flow


interface Repository {
    suspend fun NYCSchoolCatched(): Flow<StateAction>
    suspend fun NYCSatCatched(schoolDbn: String): Flow<StateAction>
}

class RepositoryImpl @Inject constructor(
    private val service: NycApi,
    private val dao: Dao
) : Repository {


    override suspend fun NYCSatCatched(dbn: String) = flow {
        emit(StateAction.LOADING)
        try {
            val connected = InternetCheck()
            if (connected.isConnected()) {
                emit(StateAction.MESSAGE("Loading from network"))
                val response = service.getSchoolSat()
                if (response.isSuccessful) {
                    response.body()?.let { result ->
                        if (result.isNotEmpty()) {
                            dao.deleteAllSatLocalItem()
                            dao.insertLocalSat(result.map { it.toDatabase() })
                            result
                        } else {
                            dao.getAllCachedSat()
                        }
                        emit(StateAction.SUCCESS(result.map {
                            it.toDomain()
                        }.filter { x ->
                            x.dbn == dbn
                        }))
                    } ?: throw Exception("Response null")
                }
            } else {
                emit(StateAction.MESSAGE("Loading from cache"))
                val cache = dao.getAllCachedSat()
                if (!cache.isNullOrEmpty()) {
                    emit(StateAction.SUCCESS(cache.map {
                        it.toDomain()
                    }.filter { x ->
                        x.dbn == dbn
                    }))
                } else {
                    throw Exception("Network call failed")
                }
            }
        } catch (e: Exception) {
            emit(StateAction.ERROR(e))
        }
    }

    override suspend fun NYCSchoolCatched() = flow {
        emit(StateAction.LOADING)
        try {
            val connected = InternetCheck()
            if (connected.isConnected()) {
                emit(StateAction.MESSAGE("Loading from network"))
                val response = service.getSchoolList()
                if (response.isSuccessful) {
                    response.body()?.let { result ->
                        dao.deleteAllSchoolLocalItem()
                        dao.insertLocalSchool(result.map { it.toDatabase() })
                        emit(StateAction.SUCCESS(result.map { it.toDomain() }))
                    } ?: throw Exception("Response null")
                } else {
                    throw Exception("Network call failed")
                }
            } else {
                emit(StateAction.MESSAGE("Loading from cache"))
                val cache = dao.getAllCachedSchools()
                if (!cache.isNullOrEmpty()) {
                    emit(StateAction.SUCCESS(cache.map { it.toDomain() }))
                } else {
                    throw Exception("Cache failed")
                }
            }
        } catch (e: Exception) {
            emit(StateAction.ERROR(e))
        }
    }
//override fun NYCSchoolCatched() = flow {
//    emit(StateAction.LOADING)
//    try {
//        val respose = service.getSchoolList()
//        if (respose.isSuccessful) {
//            respose.body()?.let {
//                emit(StateAction.SUCCESS(it))
//                Log.i("Repository", "NYCSatCatched: $it ")
//            } ?: throw NullPointerException()
//        } else {
//            throw FailedResponseException()
//        }
//    } catch (e: Exception) {
//        emit(StateAction.ERROR(e))
//    }
//}


//    override fun NYCSatCatched(schoolDbn: String) = flow {
//        emit(StateAction.LOADING)
//        try {
//            val respose = service.getSchoolSat(schoolDbn)
//            if (respose.isSuccessful) {
//                respose.body()?.let {
//                    emit(StateAction.SUCCESS(it))
//                    Log.i("Repository", "NYCSatCatched: $it ")
//                } ?: throw NullPointerException()
//            } else {
//                throw FailedResponseException()
//            }
//        } catch (e: Exception) {
//            emit(StateAction.ERROR(e))
//        }
//    }
}