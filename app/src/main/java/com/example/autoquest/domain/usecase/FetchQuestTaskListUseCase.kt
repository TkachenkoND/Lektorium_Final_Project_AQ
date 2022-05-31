package com.example.autoquest.domain.usecase

import com.example.autoquest.domain.repository.FetchQuestTaskListRepository

class FetchQuestTaskListUseCase (private val fetchQuestTaskListRepository: FetchQuestTaskListRepository){
    suspend fun execute() = fetchQuestTaskListRepository.fetchQuestTaskList()

}