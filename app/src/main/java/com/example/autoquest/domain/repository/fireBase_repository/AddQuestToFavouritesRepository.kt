package com.example.autoquest.domain.repository.fireBase_repository

interface AddQuestToFavouritesRepository {
    fun addQuestToFavourites(userId: Int, questId: Int)
}