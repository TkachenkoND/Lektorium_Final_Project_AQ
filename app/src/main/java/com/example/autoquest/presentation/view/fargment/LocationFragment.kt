package com.example.autoquest.presentation.view.fargment

import android.content.Context.LOCATION_SERVICE
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.autoquest.R
import com.example.autoquest.databinding.LocationFragmentBinding
import com.example.autoquest.presentation.view.fargment.BaseFragment
import com.example.autoquest.presentation.view_model.QuestSharedViewModel
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class LocationFragment : BaseFragment<LocationFragmentBinding>(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap

    var latitude: Double? = null
    var longitude: Double? = null

    private val sharedVm by sharedViewModel<QuestSharedViewModel>()
    private var locationManager: LocationManager? = null

    override fun initBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = LocationFragmentBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        locationManager = requireContext().getSystemService(LOCATION_SERVICE) as LocationManager

        initObserveQuestTaskData()

        val mapFragment = childFragmentManager.findFragmentById(
            R.id.map_fragment
        ) as SupportMapFragment

        mapFragment.getMapAsync(this@LocationFragment)

        try {
            locationManager?.requestLocationUpdates(
                LocationManager.NETWORK_PROVIDER,
                0L,
                0f,
                locationListener
            )
        } catch (ex: SecurityException) {
            Log.d("myTag", ex.toString())
        }
    }

    private fun initObserveQuestTaskData() {
        sharedVm.questId.observe(viewLifecycleOwner) {
            if (it != null) {
                latitude = sharedVm.listTasksQuests.value!!.questTaskList[it].latitude
                longitude = sharedVm.listTasksQuests.value!!.questTaskList[it].longitude
            } else {
                latitude = 50.450238
                longitude = 30.523348
            }
        }
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        val locate = LatLng(latitude!!, longitude!!)
        mMap.addMarker(
            MarkerOptions()
                .position(locate)
                .title("Marker in Locate")
        )
        mMap.moveCamera(CameraUpdateFactory.newLatLng(locate))
    }

    private val locationListener: LocationListener = object : LocationListener {
        override fun onLocationChanged(location: Location) {
            if (longitude == location.longitude && latitude == location.latitude)
                Toast.makeText(requireContext(), "Ви в назначеному місці", Toast.LENGTH_SHORT)
                    .show()
            else
                Toast.makeText(
                    requireContext(),
                    "Просувайтеся на вказану точку!",
                    Toast.LENGTH_SHORT
                ).show()
        }

        override fun onStatusChanged(provider: String, status: Int, extras: Bundle) {}
        override fun onProviderEnabled(provider: String) {}
        override fun onProviderDisabled(provider: String) {}
    }

}