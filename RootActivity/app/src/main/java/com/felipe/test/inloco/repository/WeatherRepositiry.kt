package com.felipe.test.inloco.repository

import com.felipe.test.inloco.WebApi
import com.felipe.test.inloco.model.Position
import dagger.Reusable
import java.lang.Exception
import javax.inject.Inject

@Reusable
class WeatherRepositiry @Inject constructor(private val api: WebApi) {

    suspend fun getCityList(position: Position) {
        try {return api.getCityList(position.lat, position.lon)
            return api.getCityList(position.lat, position.lon)
        } catch (ex: Exception) {
            //return error
        }
    }
}