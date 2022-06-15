package com.example.autoquest.domain.repository.fireBase_repository

interface RemoveQuestFromFavouritesRepository {
    suspend fun removeQuestFromFavourites(questId: String, userId: String)
}