package com.example.autoquest.domain.usecases

import com.example.autoquest.domain.repository.fireBase_repository.FetchQuestTaskListFromFbRepository

class FetchQuestTaskListFromFbUseCase(private val fetchQuestTaskListFromFbRepository: FetchQuestTaskListFromFbRepository) {
    fun execute() = fetchQuestTaskListFromFbRepository.fetchQuestTaskList()

}