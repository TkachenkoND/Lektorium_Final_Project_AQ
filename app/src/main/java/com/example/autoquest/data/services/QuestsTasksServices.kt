package com.example.autoquest.data.services

import com.example.autoquest.domain.models.QuestsTasksList
import retrofit2.http.GET

interface QuestsTasksServices {
    @GET("/guests_json.json")
    suspend fun getQuestsTasks(): QuestsTasksList
}