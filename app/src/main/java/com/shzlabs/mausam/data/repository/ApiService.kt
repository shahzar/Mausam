package com.shzlabs.mausam.data.repository

import com.shzlabs.mausam.data.model.SampleDataModel
import com.shzlabs.mausam.data.model.Weather
import com.shzlabs.mausam.data.model.WeatherModel
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("weather?appid=73bdfae75f5fb972121c86bbdd7588eb")
    suspend fun getWeather(@Query("q") city: String): WeatherModel

}