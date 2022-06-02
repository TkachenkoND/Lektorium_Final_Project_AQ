package com.example.autoquest.domain.usecase

import com.example.autoquest.domain.repository.FetchAllQuestItemFromDbRepository

class FetchAllQuestItemFromDbUseCase(
    private val fetchAllQuestItemFromDbRepository: FetchAllQuestItemFromDbRepository
) {
    fun execute() = fetchAllQuestItemFromDbRepository.fetchAllQuestItemFromDb()
}