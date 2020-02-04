package com.shzlabs.mausam.ui.forecast

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.shzlabs.mausam.data.DataManager
import com.shzlabs.mausam.data.model.ForecastModel
import com.shzlabs.mausam.ui.base.BaseViewModel
import javax.inject.Inject

class ForecastViewModel @Inject constructor(private val dataManager: DataManager) : BaseViewModel() {

    private val _forecastData = MutableLiveData<ForecastModel>()

    val forecastData: LiveData<ForecastModel>
        get() = _forecastData

    fun getForecastData() {

        ioLaunch(
            block = {
                dataManager.getForecast("Dubai")
            },
            onSuccess = {
                _forecastData.value = it
            }
        )

    }

}
