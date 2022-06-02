package com.example.autoquest.domain.usecase

import com.example.autoquest.domain.repository.FetchQuestTaskListRepository

class FetchQuestTaskListFromFbUseCase(private val fetchQuestTaskListRepository: FetchQuestTaskListRepository) {
    fun execute() = fetchQuestTaskListRepository.fetchQuestTaskList()

}