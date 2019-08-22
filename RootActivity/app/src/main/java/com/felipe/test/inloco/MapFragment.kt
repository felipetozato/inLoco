package com.felipe.test.inloco

import android.annotation.SuppressLint
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.felipe.test.inloco.di.injector
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.map_fragment.*
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions




class MapFragment : Fragment(), OnMapReadyCallback {

    private val viewModel by lazy {
        ViewModelProviders.of(this, injector.mapViewModelFactory()).get(MapViewModel::class.java)
    }

    private var map: GoogleMap? = null

    private var point: LatLng? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.map_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fab.setOnClickListener { view ->
            map?.let {_ ->
                point?.let {
                    viewModel.searchSelectedNearbyPlaces(it).observe(this, Observer {
                        Log.d("TAG", "HOLAAA")
                    })
                } ?: run {
                    Snackbar.make(view,getString(R.string.error_no_selected_point), Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show()
                }
            } ?: run {
                Snackbar.make(view, getString(R.string.error_map_loading), Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()

            }
        }
    }

    override fun onStart() {
        super.onStart()

        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    @SuppressLint("MissingPermission")
    override fun onMapReady(map: GoogleMap) {
        this.map = map

        map.setOnMapClickListener {
            map.clear()
            map.addMarker(MarkerOptions().position(it))
            point = it
        }

        // Acquire a reference to the system Location Manager
//        val locationManager = getSystemService(requireContext(), LocationManager::class.java) as LocationManager
//        val location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER)
//
//        map.moveCamera(CameraUpdateFactory.newLatLngZoom(
//            LatLng(location.latitude, location.longitude), 15f))
    }
}
