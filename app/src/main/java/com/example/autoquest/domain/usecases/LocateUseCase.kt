package com.example.autoquest.domain.usecases

import com.example.autoquest.domain.repository.locate_repository.LocateRepository
import com.google.android.gms.maps.SupportMapFragment

class LocateUseCase(
    private val locateRepository: LocateRepository
) {
    fun execute(latitude: Double, longitude: Double, mapFragment: SupportMapFragment) =
        locateRepository.getLocationListener(latitude, longitude, mapFragment)
}