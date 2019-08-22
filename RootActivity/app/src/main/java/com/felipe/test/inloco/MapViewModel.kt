package com.felipe.test.inloco

import androidx.lifecycle.ViewModel
import com.google.android.gms.maps.model.LatLng

class MapViewModel : ViewModel() {

    private var pos: LatLng? = null

    fun searchSelectedNearbyPlaces() {

    }

    fun onMapClicked(pos: LatLng) {
        this.pos = pos
    }
}
