package com.example.autoquest.domain.usecases

import com.example.autoquest.domain.repository.database_repository.FetchDataQuestItemFromDbRepository

class FetchDataQuestItemFromDbUseCase(
    private val fetchDataQuestItemFromDbRepository: FetchDataQuestItemFromDbRepository
) {
    suspend fun execute(questId: Int) = fetchDataQuestItemFromDbRepository.fetchDataQuestItemFromDb(questId)
}