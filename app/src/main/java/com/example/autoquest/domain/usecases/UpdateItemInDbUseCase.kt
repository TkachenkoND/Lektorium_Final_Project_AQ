package com.example.autoquest.domain.usecases

import com.example.autoquest.data.database.entity.QuestItemEntity
import com.example.autoquest.domain.repository.database_repository.UpdateQuestItemInDbRepository

class UpdateItemInDbUseCase(
    private val updateQuestItemInDbRepository: UpdateQuestItemInDbRepository
) {
    fun execute(questItem: QuestItemEntity) =
        updateQuestItemInDbRepository.updateQuestItemInDb(questItem)
}