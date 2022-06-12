package com.example.autoquest.domain.usecases

import com.example.autoquest.domain.repository.database_repository.FetchAllQuestItemFromDbRepository

class FetchAllItemFromDbUseCase(
    private val fetchAllQuestItemFromDbRepository: FetchAllQuestItemFromDbRepository
) {
    suspend fun execute() = fetchAllQuestItemFromDbRepository.fetchAllQuestItemFromDb()
}