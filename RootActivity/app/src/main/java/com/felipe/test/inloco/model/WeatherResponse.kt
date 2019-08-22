package com.felipe.test.inloco.model

import com.google.gson.annotations.SerializedName

class WeatherResponse(
    val message: String? = null,
    val cod: String? = null,
    val count: Int = 0,
    val list: List<WeatherCityInfo>? = null
)

class WeatherCityInfo(
    val name: String? = null,
    @SerializedName("main") val tempInfo: TempInfo? = null,
    @SerializedName("weather") val weatherCityInfo: List<WeatherInfo>? = null
)

class TempInfo(
    val temp: Double? = null,
    @SerializedName("temp_min") val tempMin: Double,
    @SerializedName("temp_max") val tempMax: Double
)

class WeatherInfo(
    val main: String? = null,
    val description: String? = null
)