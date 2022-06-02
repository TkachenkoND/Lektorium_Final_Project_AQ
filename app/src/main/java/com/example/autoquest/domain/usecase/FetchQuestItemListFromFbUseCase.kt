package com.example.autoquest.domain.usecase

import com.example.autoquest.domain.repository.FetchQuestItemListRepository

class FetchQuestItemListFromFbUseCase(private val fetchQuestItemListRepository: FetchQuestItemListRepository) {
    fun execute() = fetchQuestItemListRepository.fetchQuestItemList()
}