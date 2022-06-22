package com.example.autoquest.domain.usecases

import com.example.autoquest.domain.repository.fireBase_repository.AddQuestToFavouritesRepository

class AddQuestToFavouritesUseCase(
    private val addQuestToFavouritesRepository: AddQuestToFavouritesRepository
) {
    fun execute(userId: String, questId: Int, changeCallback: (result: Boolean) -> Unit) {
        addQuestToFavouritesRepository.addQuestToFavourites(userId, questId, changeCallback)
    }
}