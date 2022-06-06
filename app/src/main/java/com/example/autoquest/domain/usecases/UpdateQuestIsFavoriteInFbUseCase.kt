package com.example.autoquest.domain.usecases

import com.example.autoquest.domain.repository.fireBase_repository.UpdateQuestIsFavoriteInFbRepository

class UpdateQuestIsFavoriteInFbUseCase(
    private val updateQuestIsFavoriteInFbRepository: UpdateQuestIsFavoriteInFbRepository
) {
    fun execute(isFavorite: Boolean) =
        updateQuestIsFavoriteInFbRepository.updateQuestIsFavoriteInFb(isFavorite)
}