package com.example.autoquest.domain.usecase

import com.example.autoquest.domain.repository.database_repository.UpdateQuestItemInDbRepository

class UpdateQuestItemInDataBaseUseCase(
    private val updateQuestItemInDbRepository: UpdateQuestItemInDbRepository
) {
    fun execute() = updateQuestItemInDbRepository.updateQuestItemInDb()
}