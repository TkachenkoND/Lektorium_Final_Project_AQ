package com.example.autoquest.domain.repository.fireBase_repository

import com.example.autoquest.domain.models.QuestsItemList
import kotlinx.coroutines.flow.Flow

interface FetchFavoriteQuestItemFromFbRepository {
    fun fetchFavoriteQuestItemFromFb(): Flow<QuestsItemList>
}