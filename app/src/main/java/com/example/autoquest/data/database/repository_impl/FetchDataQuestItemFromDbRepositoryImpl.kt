package com.example.autoquest.data.database.repository_impl

import com.example.autoquest.data.database.dao.QuestItemDao
import com.example.autoquest.domain.repository.database_repository.FetchDataQuestItemFromDbRepository

class FetchDataQuestItemFromDbRepositoryImpl(
    private val questItemDao: QuestItemDao
) : FetchDataQuestItemFromDbRepository {
    override suspend fun fetchDataQuestItemFromDb(questId: Int) = questItemDao.fetchDataQuestItemFromDb(questId)
}