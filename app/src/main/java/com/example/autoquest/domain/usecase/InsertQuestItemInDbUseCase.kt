package com.example.autoquest.domain.usecase

import com.example.autoquest.domain.repository.database_repository.InsertQuestItemInDbRepository

class InsertQuestItemInDbUseCase(
    private val insertQuestItemInDbRepository: InsertQuestItemInDbRepository
) {
    fun execute() = insertQuestItemInDbRepository.insertQuestItemInDb()
}