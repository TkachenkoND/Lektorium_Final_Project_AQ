package com.example.autoquest.data.repository_impl

import com.example.autoquest.data.services.QuestsTasksServices
import com.example.autoquest.domain.repository.QuestsTasksRepository

class QuestsTasksRepositoryImpl(
    private val questsTasksServices: QuestsTasksServices
): QuestsTasksRepository {
    override suspend fun getQuestsTasksFromApi() = questsTasksServices.getQuestsTasks()
}