package com.example.autoquest.domain.usecase

import com.example.autoquest.domain.repository.FetchQuestItemListRepository
import com.example.autoquest.domain.repository.UpdateQuestItemInDbRepository

class UpdateQuestItemInDataBaseUseCase(
    private val updateQuestItemInDbRepository: UpdateQuestItemInDbRepository
) {
    fun execute() = updateQuestItemInDbRepository.updateQuestItemInDb()
}