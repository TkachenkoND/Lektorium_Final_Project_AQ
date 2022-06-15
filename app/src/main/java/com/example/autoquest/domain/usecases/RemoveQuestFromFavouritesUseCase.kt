package com.example.autoquest.domain.usecases

import com.example.autoquest.domain.repository.fireBase_repository.RemoveQuestFromFavouritesRepository

class RemoveQuestFromFavouritesUseCase(
    private val removeQuestFromFavouritesRepository: RemoveQuestFromFavouritesRepository
) {
    suspend fun execute(questId: String, userId: String) {
        removeQuestFromFavouritesRepository.removeQuestFromFavourites(questId, userId)
    }
}