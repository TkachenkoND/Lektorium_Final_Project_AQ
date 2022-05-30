package com.example.autoquest.data.services

import com.example.autoquest.domain.models.QuestsItemList
import retrofit2.http.GET

interface QuestsDataServices {
    @GET("/guests_json.json")
    suspend fun getQuestsData(): QuestsItemList
}