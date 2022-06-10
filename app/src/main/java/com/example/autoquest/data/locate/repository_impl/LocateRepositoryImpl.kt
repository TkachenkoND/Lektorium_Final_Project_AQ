package com.example.autoquest.data.locate.repository_impl

import android.content.Context
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.autoquest.domain.repository.locate_repository.LocateRepository
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class LocateRepositoryImpl(private val context: Context) : LocateRepository, OnMapReadyCallback {

    var locationManager: LocationManager? = null

    private lateinit var mMap: GoogleMap

    private var latitudeRepo: Double? = null
    private var longitudeRepo: Double? = null

    override fun getLocationListener(
        latitude: Double,
        longitude: Double,
        mapFragment: SupportMapFragment
    ) : Boolean {
        var _isAtAppointedPlace: Boolean? = null

        val locationListener = object : LocationListener {
            override fun onLocationChanged(location: Location) {
                if (longitude == location.longitude && latitude == location.latitude) {
                        Toast.makeText(context, "Ви в назначеному місці", Toast.LENGTH_SHORT)
                            .show()
                    _isAtAppointedPlace = true
                }
                else{
                    _isAtAppointedPlace = false
                    Toast.makeText(
                        context,
                        "Просувайтеся на вказану точку!",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }

            override fun onStatusChanged(provider: String, status: Int, extras: Bundle) {}
            override fun onProviderEnabled(provider: String) {}
            override fun onProviderDisabled(provider: String) {}
        }

        mapFragment.getMapAsync(this)

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

        return _isAtAppointedPlace!!
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