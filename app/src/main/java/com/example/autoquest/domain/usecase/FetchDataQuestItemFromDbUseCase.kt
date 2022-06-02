package com.example.autoquest.domain.usecase

import com.example.autoquest.domain.repository.FetchDataQuestItemFromDbRepository

class FetchDataQuestItemFromDbUseCase(
    private val fetchDataQuestItemFromDbRepository: FetchDataQuestItemFromDbRepository
) {
    fun execute() = fetchDataQuestItemFromDbRepository.fetchDataQuestItemFromDb()
}