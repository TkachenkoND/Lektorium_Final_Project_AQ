package com.example.autoquest.domain.usecase

import com.example.autoquest.domain.repository.QuestsDataRepository

class LoadQuestsDataUseCase(private val questsDataRepository: QuestsDataRepository) {
    suspend fun execute() = questsDataRepository.getQuestsDataFromApi()
}