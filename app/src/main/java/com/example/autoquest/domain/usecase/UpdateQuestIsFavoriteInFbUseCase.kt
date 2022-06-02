package com.example.autoquest.domain.usecase

import com.example.autoquest.domain.repository.fireBase_repository.UpdateQuestIsFavoriteInFbRepository

class UpdateQuestIsFavoriteInFbUseCase(
    private val updateQuestIsFavoriteInFbRepository: UpdateQuestIsFavoriteInFbRepository
) {
    fun execute(questId: Int) =
        updateQuestIsFavoriteInFbRepository.updateQuestIsFavoriteInFb(questId)
}