package com.felipe.test.inloco

import com.felipe.test.inloco.model.WeatherResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface WebApi {

    @GET("find?cnt=15&APPID=997ddbe2890ce356d97871a57ce8622c")
    suspend fun getCityList(@Query("lat") lat: Double, @Query("lon") lon: Double): WeatherResponse
}