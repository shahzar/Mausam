package com.shzlabs.mausam.data

import com.shzlabs.mausam.data.repository.ApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class DataManager @Inject constructor(private val remoteDataSrc: ApiService) {

    private suspend fun <T> safeApiCall(apiCall: suspend () -> T) = withContext(Dispatchers.IO) {
        try {
            apiCall.invoke()
        } catch (throwable: Throwable) {
            when(throwable) {
                is IOException -> throw IOException("Network Error. Please check your internet.")
                is HttpException -> throw Exception("Error: ${throwable.message()}")
                else -> throw Exception("Some error occured")
            }
        }
    }

    suspend fun getWeather(city: String) = safeApiCall {
        remoteDataSrc.getWeather(city)
    }

    suspend fun getForecast(lat: Int, lon: Int) = safeApiCall {
        remoteDataSrc.getForecast(lat, lon)
    }
}