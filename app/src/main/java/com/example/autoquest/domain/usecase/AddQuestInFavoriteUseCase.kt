package com.example.autoquest.domain.usecase

import com.example.autoquest.domain.repository.AddQuestInFavoriteRepository

class AddQuestInFavoriteUseCase(private val addQuestInFavoriteRepository: AddQuestInFavoriteRepository) {
    suspend fun execute(questId: Int) = addQuestInFavoriteRepository.addQuestInFavorite(questId)
}