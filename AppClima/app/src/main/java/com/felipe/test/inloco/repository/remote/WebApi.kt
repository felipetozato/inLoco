package com.felipe.test.inloco.repository.remote

import com.felipe.test.inloco.BuildConfig
import com.felipe.test.inloco.model.WeatherResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface WebApi {

    //Private keys like WEATHER_APP_ID these would be better to stay in a local.properties file not commited in the repository, for security reasons.
    // But for the sake of simplicity and easy setup i'm hardcoding them.
    @GET("find?cnt=15&APPID=${BuildConfig.WEATHER_APP_ID}")
    suspend fun getCityList(@Query("lat") lat: Double, @Query("lon") lon: Double): WeatherResponse
}