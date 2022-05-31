package com.example.autoquest.domain.repository

interface AddQuestInFavoriteRepository {
    suspend fun addQuestInFavorite(questId: Int)
}