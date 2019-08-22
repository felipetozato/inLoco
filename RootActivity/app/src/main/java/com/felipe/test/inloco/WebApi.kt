package com.felipe.test.inloco

import retrofit2.http.GET
import retrofit2.http.Path

interface WebApi {

    @GET("find?lat={lat}&lon={lon}&cnt=15&APPID={APP_ID}")
    fun getCityList(@Path("lat") lat: Double, @Path("lon") lon: Double)
}