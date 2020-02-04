package com.shzlabs.mausam.ui.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.shzlabs.mausam.data.DataManager
import com.shzlabs.mausam.data.model.WeatherModel
import com.shzlabs.mausam.di.components.AppComponent
import com.shzlabs.mausam.ui.base.BaseViewModel
import javax.inject.Inject

class SearchViewModel @Inject constructor (val dataManager: DataManager) : BaseViewModel(){

    private val _weatherData = MutableLiveData<WeatherModel>()
    private val _showProgress = MutableLiveData<Boolean>()

    val weatherData: LiveData<WeatherModel>
        get() = _weatherData

    val showProgress: LiveData<Boolean>
        get() = _showProgress


    fun inject(appComponent: AppComponent) {
        appComponent.inject(this)
    }

    fun getWeatherData(cities: List<String>) {

        cities.forEach {
            getWeatherData(it)
        }

    }

    private fun getWeatherData(city: String) {
        _showProgress.value = true

        ioLaunch(
            block = {
                dataManager.getWeather(city)
            },
            onSuccess = {
                _showProgress.value = false
                _weatherData.value = it
            }
        )
    }

}
