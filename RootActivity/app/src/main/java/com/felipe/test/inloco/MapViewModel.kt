package com.felipe.test.inloco

import androidx.lifecycle.ViewModel
import com.google.android.gms.maps.model.LatLng
import javax.inject.Inject

class MapViewModel @Inject constructor() : ViewModel() {

    private var pos: LatLng? = null

    fun searchSelectedNearbyPlaces() {

    }

    fun onMapClicked(pos: LatLng) {
        this.pos = pos
    }
}
