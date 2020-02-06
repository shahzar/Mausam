package com.shzlabs.mausam.ui.search

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.shzlabs.mausam.data.DataManager
import com.shzlabs.mausam.data.model.WeatherModel
import com.shzlabs.mausam.data.repository.ApiService
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.MockitoAnnotations

@RunWith(JUnit4::class)
class SearchViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Mock
    lateinit var apiService: ApiService

    @Mock
    lateinit var observer: Observer<WeatherModel>

    lateinit var dataManager: DataManager

    lateinit var searchViewModel: SearchViewModel

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        dataManager = DataManager(apiService)
        searchViewModel = SearchViewModel(dataManager)
        searchViewModel.weatherData.observeForever(observer)
    }

    @Test
    fun testNull(){
        assertNotNull(searchViewModel.weatherData)
        assertTrue(searchViewModel.weatherData.hasActiveObservers())
    }


}