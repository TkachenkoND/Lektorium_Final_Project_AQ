package com.example.autoquest.domain.usecases

import com.example.autoquest.domain.repository.fireBase_repository.UpdateQuestIsFavoriteInFbRepository

class UpdateIsFavoriteInFbUseCase(
    private val updateQuestIsFavoriteInFbRepository: UpdateQuestIsFavoriteInFbRepository
) {
    suspend fun execute(isFavorite: Boolean, questId: Int) =
        updateQuestIsFavoriteInFbRepository.updateQuestIsFavoriteInFb(isFavorite, questId)
}