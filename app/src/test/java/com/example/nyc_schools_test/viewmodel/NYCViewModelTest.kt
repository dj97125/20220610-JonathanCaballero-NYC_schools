package com.example.nyc_schools_test.viewmodel
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.nyc_schools_test.common.StateAction
import com.example.nyc_schools_test.model.remote.Repository
import com.example.nyc_schools_test.model.remote.SchoolListResponse
import com.example.nyc_schools_test.model.remote.SchoolSatResponse
import com.google.common.truth.Truth.assertThat
import io.mockk.clearAllMocks
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.lang.Exception


@ExperimentalCoroutinesApi
class NYCViewModelTest{

    @get:Rule var rule = InstantTaskExecutorRule()
    lateinit var subject:NYCViewModel
    lateinit var repository: Repository
    private val testDispatcher = UnconfinedTestDispatcher()
    private val testScopeCoroutine = TestScope(testDispatcher)

    @Before
    fun setUpTest() {
        repository = mockk()
        subject = NYCViewModel(repository,testScopeCoroutine)
    }

    @Test
    fun `get schools list when fetching data from server returns SUCCESS response`(){
        every {
            repository.NYCSchoolCatched()
        } returns flowOf(StateAction.SUCCESS(listOf(
            mockk<SchoolListResponse>())))

        var StateActionTestList = mutableListOf<StateAction>()
        subject.schoolResponse.observeForever{
            StateActionTestList.add(it)
        }

        subject.getSchoolList()
        assertEquals(StateActionTestList.size, 1)
        val successTest = StateActionTestList.get(0) as StateAction.SUCCESS<List<SchoolListResponse>>
        //assertEquals(successTest.response.size, 1)
        assertEquals(successTest.response.size, StateActionTestList.size)

    }
    @Test
    fun `get sat list when fetching data from server returns SUCCESS response`(){
        every {
            repository.NYCSatCatched()
        } returns flowOf(StateAction.SUCCESS(listOf(
            mockk<SchoolSatResponse>())))

        var StateActionTestList = mutableListOf<StateAction>()
        subject.schoolSatResponse.observeForever{
            StateActionTestList.add(it)
        }

        subject.getSatList()
        assertEquals(StateActionTestList.size, 1)
        val successTest = StateActionTestList.get(0) as StateAction.SUCCESS<List<SchoolSatResponse>>
        //assertEquals(successTest.response.size, 1)
        assertEquals(successTest.response.size, StateActionTestList.size)

    }
    @Test
    fun `get schools list when fetching data from server returns LOADING response`(){
        every {
            repository.NYCSchoolCatched()
        } returns flowOf(StateAction.LOADING)

        var StateActionTestList = mutableListOf<StateAction>()
        subject.schoolResponse.observeForever{
            StateActionTestList.add(it)
        }

        subject.getSchoolList()
        val loadingTest = StateActionTestList.get(0) as StateAction.LOADING
        assertThat(loadingTest).isInstanceOf(StateAction.LOADING::class.java)

    }
    @Test
    fun `get sat list when fetching data from server returns LOADING response`(){
        every {
            repository.NYCSatCatched()
        } returns flowOf(StateAction.LOADING)

        var StateActionTestList = mutableListOf<StateAction>()
        subject.schoolSatResponse.observeForever{
            StateActionTestList.add(it)
        }

        subject.getSatList()
        val loadingTest = StateActionTestList.get(0) as StateAction.LOADING
        assertThat(loadingTest).isInstanceOf(StateAction.LOADING::class.java)

    }
    @Test
    fun `get schools list when fetching data from server returns ERROR response`(){
        every {
            repository.NYCSchoolCatched()
        } returns flowOf(StateAction.ERROR(Exception("Error fetching data from server")))

        var StateActionTestList = mutableListOf<StateAction>()
        subject.schoolResponse.observeForever{
            StateActionTestList.add(it)
        }

        subject.getSchoolList()
        val errorTest = StateActionTestList.get(0) as StateAction.ERROR
        assertThat(errorTest).isInstanceOf(StateAction.ERROR::class.java)

    }
    @Test
    fun `get sat list when fetching data from server returns ERROR response`(){
        every {
            repository.NYCSatCatched()
        } returns flowOf(StateAction.ERROR(Exception("Error fetching data from server")))

        var StateActionTestList = mutableListOf<StateAction>()
        subject.schoolSatResponse.observeForever{
            StateActionTestList.add(it)
        }

        subject.getSatList()
        val errorTest = StateActionTestList.get(0) as StateAction.ERROR
        assertThat(errorTest).isInstanceOf(StateAction.ERROR::class.java)

    }

    @After
    fun shutdownTest() {
        clearAllMocks()
        Dispatchers.resetMain()
    }

}