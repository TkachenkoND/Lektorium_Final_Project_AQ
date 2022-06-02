package com.example.autoquest.domain.usecases

import com.example.autoquest.data.database.entity.QuestItemEntity
import com.example.autoquest.domain.repository.database_repository.UpdateQuestItemInDbRepository

class UpdateQuestItemInDataBaseUseCase(
    private val updateQuestItemInDbRepository: UpdateQuestItemInDbRepository
) {
    fun execute(listQuests: List<QuestItemEntity>) =
        updateQuestItemInDbRepository.updateQuestItemInDb(listQuests)
}