package com.example.autoquest.domain.repository.fireBase_repository

interface AddQuestToFavouritesRepository {
    fun addQuestToFavourites(userId: String, questId: Int)
}