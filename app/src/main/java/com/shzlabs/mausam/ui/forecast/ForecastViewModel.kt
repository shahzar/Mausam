package com.shzlabs.mausam.ui.forecast

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.shzlabs.mausam.data.DataManager
import com.shzlabs.mausam.data.model.ForecastModel
import com.shzlabs.mausam.ui.base.BaseViewModel
import javax.inject.Inject

class ForecastViewModel @Inject constructor(private val dataManager: DataManager) : BaseViewModel() {

    private val _forecastData = MutableLiveData<ForecastModel>()
    private val _showProgress = MutableLiveData<Boolean>()

    val forecastData: LiveData<ForecastModel>
        get() = _forecastData

    val showProgress: LiveData<Boolean>
        get() = _showProgress


    fun getForecastData(lat: Int, lon: Int) {

        _showProgress.value = true

        ioLaunch(
            block = {
                dataManager.getForecast(lat, lon)
            },
            onSuccess = {
                _forecastData.value = it
                _showProgress.value = false
            }
        )

    }

}
