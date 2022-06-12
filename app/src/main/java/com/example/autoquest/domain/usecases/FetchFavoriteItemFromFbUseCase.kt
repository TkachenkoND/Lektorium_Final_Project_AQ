package com.example.autoquest.domain.usecases

import com.example.autoquest.domain.repository.fireBase_repository.FetchFavoriteQuestItemFromFbRepository

class FetchFavoriteItemFromFbUseCase(
    private val fetchFavoriteQuestItemFromFbRepository: FetchFavoriteQuestItemFromFbRepository
) {
    fun execute() = fetchFavoriteQuestItemFromFbRepository.fetchFavoriteQuestItemFromFb()
}