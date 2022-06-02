package com.example.autoquest.domain.usecases

import com.example.autoquest.data.database.entity.QuestItemEntity
import com.example.autoquest.domain.repository.database_repository.InsertQuestItemInDbRepository

class InsertQuestItemInDbUseCase(
    private val insertQuestItemInDbRepository: InsertQuestItemInDbRepository
) {
    suspend fun execute(questItem: QuestItemEntity) = insertQuestItemInDbRepository.insertQuestItemInDb(questItem)
}