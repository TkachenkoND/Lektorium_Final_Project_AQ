package com.example.autoquest.domain.usecase

import com.example.autoquest.domain.repository.database_repository.FetchAllQuestItemFromDbRepository

class FetchAllQuestItemFromDbUseCase(
    private val fetchAllQuestItemFromDbRepository: FetchAllQuestItemFromDbRepository
) {
    fun execute() = fetchAllQuestItemFromDbRepository.fetchAllQuestItemFromDb()
}