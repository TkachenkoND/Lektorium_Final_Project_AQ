package com.example.autoquest.domain.usecases

import com.example.autoquest.domain.repository.fireBase_repository.FetchQuestItemListFromFbRepository

class FetchItemListFromFbUseCase(private val fetchQuestItemListFromFbRepository: FetchQuestItemListFromFbRepository) {
    fun execute() = fetchQuestItemListFromFbRepository.fetchQuestItemList()
}