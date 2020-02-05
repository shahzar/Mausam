package com.shzlabs.mausam.ui.forecast

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.shzlabs.mausam.data.DataManager
import com.shzlabs.mausam.data.model.ForecastModel
import com.shzlabs.mausam.data.repository.ApiService
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.MockitoAnnotations

@RunWith(JUnit4::class)
class ForecastViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Mock
    lateinit var apiService: ApiService

    @Mock
    lateinit var observer: Observer<ForecastModel>

    lateinit var dataManager: DataManager

    lateinit var forecastViewModel: ForecastViewModel

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        dataManager = DataManager(apiService)
        forecastViewModel = ForecastViewModel(dataManager)
        forecastViewModel.forecastData.observeForever(observer)
    }

    @Test
    fun testNull(){

//        runBlockingTest {
//            when(dataManager.getWeather())
//
//        }
        assertNotNull(forecastViewModel.forecastData)
        assertTrue(forecastViewModel.forecastData.hasActiveObservers())
    }
}