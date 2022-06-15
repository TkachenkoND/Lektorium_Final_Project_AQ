package com.example.autoquest.data.locate.repository_impl

import android.content.Context
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import android.util.Log
import com.example.autoquest.domain.repository.locate_repository.LocateRepository
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import kotlin.math.pow

class LocateRepositoryImpl(
    private val context: Context,
) : LocateRepository, OnMapReadyCallback {

    var locationManager: LocationManager? = null

    private lateinit var mMap: GoogleMap

    private var latitudeRepo: Double? = null
    private var longitudeRepo: Double? = null

    override fun getLocationListener(
        latitude: Double,
        longitude: Double,
        mapFragment: SupportMapFragment,
        locationListenerCallback: (result: Boolean?) -> Unit
    ) {
        latitudeRepo = latitude
        longitudeRepo = longitude

        val locationListener = object : LocationListener {

            override fun onLocationChanged(location: Location) {
                if ((location.latitude - latitude).pow(2.0) + (location.longitude - longitude).pow(
                        2.0
                    ) == 0.0000001
                    || (location.latitude - latitude).pow(2.0) + (location.longitude - longitude).pow(
                        2.0
                    ) < 0.0000001
                ) {
                    locationListenerCallback.invoke(true)
                    mMap.clear()
                } else {
                    locationListenerCallback.invoke(false)
                }
            }

            override fun onStatusChanged(provider: String, status: Int, extras: Bundle) {}
            override fun onProviderEnabled(provider: String) {}
            override fun onProviderDisabled(provider: String) {}
        }

        mapFragment.getMapAsync(this@LocateRepositoryImpl)

        locationManager = context.getSystemService(Context.LOCATION_SERVICE) as LocationManager

        try {
            locationManager!!.requestLocationUpdates(
                LocationManager.NETWORK_PROVIDER,
                0L,
                0f,
                locationListener
            )
        } catch (ex: SecurityException) {
            Log.d("myTag", ex.toString())
        }
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        val locate = LatLng(latitudeRepo!!, longitudeRepo!!)
        mMap.addMarker(
            MarkerOptions()
                .position(locate)
                .title("Marker in Locate")
        )
        mMap.moveCamera(CameraUpdateFactory.newLatLng(locate))
    }

}