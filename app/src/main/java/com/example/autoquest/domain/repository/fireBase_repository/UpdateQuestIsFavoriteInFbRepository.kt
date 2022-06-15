package com.example.autoquest.domain.repository.fireBase_repository

interface UpdateQuestIsFavoriteInFbRepository {
    suspend fun updateQuestIsFavoriteInFb(isFavorite: Boolean, questId: Int)
}