package com.example.autoquest.domain.repository

import com.example.autoquest.domain.models.QuestsItemList

interface QuestsDataRepository {
    suspend fun getQuestsDataFromApi(): QuestsItemList
}