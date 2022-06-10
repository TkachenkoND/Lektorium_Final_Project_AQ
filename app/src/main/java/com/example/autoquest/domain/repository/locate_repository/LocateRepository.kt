package com.example.autoquest.domain.repository.locate_repository

import com.google.android.gms.maps.SupportMapFragment

interface LocateRepository {
    fun getLocationListener(latitude: Double, longitude: Double, mapFragment: SupportMapFragment): Boolean
}