package com.shzlabs.mausam.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.shzlabs.mausam.data.DataManager
import com.shzlabs.mausam.data.model.WeatherModel
import com.shzlabs.mausam.di.components.AppComponent
import com.shzlabs.mausam.ui.base.BaseViewModel
import javax.inject.Inject

class HomeViewModel @Inject constructor (val dataManager: DataManager) : BaseViewModel(){

    private val _weatherData = MutableLiveData<WeatherModel>()

    val sampleData: LiveData<WeatherModel>
        get() = _weatherData

    fun inject(appComponent: AppComponent) {
        appComponent.inject(this)
    }

    fun getWeatherData() {

        ioLaunch(
            block = {
                dataManager.getWeather("London")
            },
            onSuccess = {
                _weatherData.value = it
            }
        )

    }

}
