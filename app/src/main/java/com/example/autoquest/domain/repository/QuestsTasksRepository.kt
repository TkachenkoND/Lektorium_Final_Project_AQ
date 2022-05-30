package com.example.autoquest.domain.repository

import com.example.autoquest.domain.models.QuestsTasksList

interface QuestsTasksRepository {
    suspend fun getQuestsTasksFromApi(): QuestsTasksList
}