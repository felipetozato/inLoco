package com.felipe.test.inloco.features.map

import android.Manifest
import android.content.pm.PackageManager
import android.location.LocationManager
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.getSystemService
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.felipe.test.inloco.R
import com.felipe.test.inloco.di.injector
import com.google.android.gms.maps.*
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.map_fragment.*
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
                    loadingView.visibility = View.VISIBLE
                    viewModel.searchSelectedNearbyPlaces(it).observe(this, Observer {
                        loadingView.visibility = View.GONE
                        it?.let { list ->
                            val direc = MapFragmentDirections.actionMapFragmentToCityListFragment(
                                list.toTypedArray()
                            )
                            findNavController().navigate(direc)
                        }
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

    override fun onMapReady(map: GoogleMap) {
        this.map = map

        map.setOnMapClickListener {
            map.clear()
            map.addMarker(MarkerOptions().position(it))
            point = it
            fab.show()
        }

        centerMap()
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if (grantResults.all { granted -> granted == PackageManager.PERMISSION_GRANTED }) {
            centerMap()
        }
    }

    private fun checkPermission() =
        ContextCompat.checkSelfPermission(this.requireContext(), Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED

    private fun centerMap() {
        if(checkPermission()) {
            val locationManager = getSystemService(requireContext(), LocationManager::class.java) as LocationManager
            val location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER)

            map?.moveCamera(CameraUpdateFactory.newLatLngZoom(
                LatLng(location.latitude, location.longitude), 15f))
        } else {
            requestPermissions(
                REQUIRED_PERMISSION,
                REQUEST_CODE
            )
        }
    }

    companion object {
        private val REQUIRED_PERMISSION = arrayOf(Manifest.permission.ACCESS_FINE_LOCATION)
        private const val REQUEST_CODE = 100
    }
}
