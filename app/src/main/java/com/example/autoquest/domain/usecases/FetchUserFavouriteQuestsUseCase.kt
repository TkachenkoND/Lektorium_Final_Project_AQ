package com.example.autoquest.domain.usecases

import com.example.autoquest.domain.repository.fireBase_repository.FetchUserFavouriteQuestsRepository

class FetchUserFavouriteQuestsUseCase(
    private val fetchUserFavouriteQuestsRepository: FetchUserFavouriteQuestsRepository
) {
    fun execute(userId: String) = fetchUserFavouriteQuestsRepository.getUserFavouriteQuests(userId)
}