package com.felipe.test.inloco.features.map

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.felipe.test.inloco.model.Position
import com.felipe.test.inloco.repository.WeatherRepository
import com.google.android.gms.maps.model.LatLng
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

class MapViewModel @Inject constructor(
    private val repository: WeatherRepository
) : ViewModel() {

    fun searchSelectedNearbyPlaces(pos: LatLng) = liveData(Dispatchers.IO) {
        val result = repository.getCityList(Position(pos.latitude, pos.longitude))
        emit(result)
    }
}
