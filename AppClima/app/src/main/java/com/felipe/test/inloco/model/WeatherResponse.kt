package com.felipe.test.inloco.model

class WeatherResponse(
    val message: String? = null,
    val cod: String? = null,
    val count: Int = 0,
    val list: List<WeatherCityInfo>? = null
)

