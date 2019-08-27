package com.felipe.test.inloco.repository

import android.util.Log
import com.felipe.test.inloco.model.Position
import com.felipe.test.inloco.model.WeatherCityInfo
import com.felipe.test.inloco.repository.remote.WebApi
import dagger.Reusable
import java.lang.Exception
import javax.inject.Inject

@Reusable
class WeatherRepository @Inject constructor(private val api: WebApi) {

    suspend fun getCityList(position: Position): List<WeatherCityInfo>? {
        return try {
            val result = api.getCityList(position.lat, position.lon)
            if (result.cod == "200") result.list
            else null
        } catch (ex: Exception) {
            Log.d("Repository", ex.message)
            null
        }
    }
}