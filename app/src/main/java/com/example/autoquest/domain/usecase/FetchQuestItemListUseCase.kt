package com.example.autoquest.domain.usecase

import com.example.autoquest.domain.repository.FetchQuestItemListRepository

class FetchQuestItemListUseCase(private val fetchQuestItemListRepository: FetchQuestItemListRepository) {
    fun execute() = fetchQuestItemListRepository.fetchQuestItemList()

}