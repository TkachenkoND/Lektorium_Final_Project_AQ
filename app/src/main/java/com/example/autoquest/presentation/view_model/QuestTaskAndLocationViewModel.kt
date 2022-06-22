package com.example.autoquest.presentation.view_model

import androidx.lifecycle.ViewModel
import com.example.autoquest.domain.usecases.LocateUseCase
import com.google.android.gms.maps.SupportMapFragment

class QuestTaskAndLocationViewModel(
    private val locateUseCase: LocateUseCase
) : ViewModel() {

    fun getLocate(
        latitude: Double,
        longitude: Double,
        mapFragment: SupportMapFragment,
        locationListenerCallback: (result: Boolean?) -> Unit
    ) = locateUseCase.execute(latitude, longitude, mapFragment, locationListenerCallback)
}