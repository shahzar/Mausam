package com.shzlabs.mausam.data.repository

import com.shzlabs.mausam.data.model.ForecastModel
import com.shzlabs.mausam.data.model.WeatherModel
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("weather?units=metric&appid=73bdfae75f5fb972121c86bbdd7588eb")
    suspend fun getWeather(@Query("q") city: String): WeatherModel

    @GET("forecast?units=metric&appid=73bdfae75f5fb972121c86bbdd7588eb")
    suspend fun getForecast(@Query("q") city: String): ForecastModel

    @GET("forecast?units=metric&appid=73bdfae75f5fb972121c86bbdd7588eb")
    suspend fun getForecast(@Query("lat") lat: Int, @Query("lon") lon: Int): ForecastModel


}