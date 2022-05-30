package com.example.autoquest.domain.usecase

import com.example.autoquest.domain.repository.QuestsTasksRepository


class LoadQuestsTasksUseCase(private val questsTasksRepository: QuestsTasksRepository) {
    suspend fun execute() = questsTasksRepository.getQuestsTasksFromApi()
}