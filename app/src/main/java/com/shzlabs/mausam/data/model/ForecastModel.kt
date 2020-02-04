package com.shzlabs.mausam.data.model
import com.google.gson.annotations.SerializedName


data class ForecastModel(
    @SerializedName("city")
    var city: City = City(),
    @SerializedName("cnt")
    var cnt: Int = 0,
    @SerializedName("cod")
    var cod: String = "",
    @SerializedName("list")
    var list: List<ForecastItem> = listOf(),
    @SerializedName("message")
    var message: Int = 0
)

data class City(
    @SerializedName("coord")
    var coord: ForecastCoord = ForecastCoord(),
    @SerializedName("country")
    var country: String = "",
    @SerializedName("id")
    var id: Int = 0,
    @SerializedName("name")
    var name: String = "",
    @SerializedName("population")
    var population: Int = 0,
    @SerializedName("sunrise")
    var sunrise: Int = 0,
    @SerializedName("sunset")
    var sunset: Int = 0,
    @SerializedName("timezone")
    var timezone: Int = 0
)

data class ForecastCoord(
    @SerializedName("lat")
    var lat: Double = 0.0,
    @SerializedName("lon")
    var lon: Double = 0.0
)

data class ForecastItem(
    @SerializedName("clouds")
    var clouds: ForecastClouds = ForecastClouds(),
    @SerializedName("dt")
    var dt: Int = 0,
    @SerializedName("dt_txt")
    var dtTxt: String = "",
    @SerializedName("main")
    var main: ForecastMain = ForecastMain(),
    @SerializedName("rain")
    var rain: Rain = Rain(),
    @SerializedName("snow")
    var snow: Snow = Snow(),
    @SerializedName("sys")
    var sys: ForecastSys = ForecastSys(),
    @SerializedName("weather")
    var weather: List<ForecastWeather> = listOf(),
    @SerializedName("wind")
    var wind: ForecastWind = ForecastWind()
)

data class ForecastClouds(
    @SerializedName("all")
    var all: Int = 0
)

data class ForecastMain(
    @SerializedName("feels_like")
    var feelsLike: Double = 0.0,
    @SerializedName("grnd_level")
    var grndLevel: Int = 0,
    @SerializedName("humidity")
    var humidity: Int = 0,
    @SerializedName("pressure")
    var pressure: Int = 0,
    @SerializedName("sea_level")
    var seaLevel: Int = 0,
    @SerializedName("temp")
    var temp: Double = 0.0,
    @SerializedName("temp_kf")
    var tempKf: Double = 0.0,
    @SerializedName("temp_max")
    var tempMax: Double = 0.0,
    @SerializedName("temp_min")
    var tempMin: Double = 0.0
)

data class Rain(
    @SerializedName("3h")
    var h: Double = 0.0
)

data class Snow(
    @SerializedName("3h")
    var h: Double = 0.0
)

data class ForecastSys(
    @SerializedName("pod")
    var pod: String = ""
)

data class ForecastWeather(
    @SerializedName("description")
    var description: String = "",
    @SerializedName("icon")
    var icon: String = "",
    @SerializedName("id")
    var id: Int = 0,
    @SerializedName("main")
    var main: String = ""
)

data class ForecastWind(
    @SerializedName("deg")
    var deg: Int = 0,
    @SerializedName("speed")
    var speed: Double = 0.0
)