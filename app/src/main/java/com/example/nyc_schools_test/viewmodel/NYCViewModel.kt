package com.example.nyc_schools_test.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.nyc_schools_test.common.StateAction
import com.example.nyc_schools_test.model.remote.Repository
import com.example.nyc_schools_test.model.remote.RepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

@HiltViewModel
class NYCViewModel @Inject constructor(
    private val repository: Repository,
    private val coroutineScope: CoroutineScope
) : ViewModel() {

    private val _schoolResponse = MutableLiveData<StateAction>()
    val schoolResponse: MutableLiveData<StateAction>
        get() = _schoolResponse

    private val _schoolSatResponse = MutableLiveData<StateAction>()
    val schoolSatResponse: MutableLiveData<StateAction>
        get() = _schoolSatResponse

    fun getSchoolList() {
        coroutineScope.launch {
            repository.NYCSchoolCatched().collect {
                _schoolResponse.postValue(it)
            }
        }
    }

    fun getSatList() {
        coroutineScope.launch {
            repository.NYCSatCatched().collect {
                _schoolSatResponse.postValue(it)
            }
        }
    }
}