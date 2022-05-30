package com.example.autoquest.data.repository_impl

import com.example.autoquest.data.services.QuestsDataServices
import com.example.autoquest.domain.repository.QuestsDataRepository

class QuestsDataRepositoryImpl(
    private val questsDataServices: QuestsDataServices
): QuestsDataRepository {
    override suspend fun getQuestsDataFromApi() = questsDataServices.getQuestsData()
}