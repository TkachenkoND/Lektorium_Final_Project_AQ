package com.example.autoquest.domain.usecase

import com.example.autoquest.domain.repository.fireBase_repository.FetchFavoriteQuestItemFromFbRepository

class FetchFavoriteQuestItemFromFbUseCase(
    private val fetchFavoriteQuestItemFromFbRepository: FetchFavoriteQuestItemFromFbRepository
) {
    fun execute() = fetchFavoriteQuestItemFromFbRepository.fetchFavoriteQuestItemFromFb()
}