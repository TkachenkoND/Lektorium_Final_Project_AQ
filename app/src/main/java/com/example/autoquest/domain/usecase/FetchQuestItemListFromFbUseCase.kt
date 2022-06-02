package com.example.autoquest.domain.usecase

import com.example.autoquest.domain.repository.fireBase_repository.FetchQuestItemListFromFbRepository

class FetchQuestItemListFromFbUseCase(private val fetchQuestItemListFromFbRepository: FetchQuestItemListFromFbRepository) {
    fun execute() = fetchQuestItemListFromFbRepository.fetchQuestItemList()
}