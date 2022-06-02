package com.example.autoquest.data.database.repository_impl

import com.example.autoquest.data.database.dao.QuestItemDao
import com.example.autoquest.domain.repository.database_repository.FetchAllQuestItemFromDbRepository

class FetchAllQuestItemFromDbRepositoryImpl(
    private val questItemDao: QuestItemDao
) : FetchAllQuestItemFromDbRepository {
    override suspend fun fetchAllQuestItemFromDb() = questItemDao.fetchAllQuestItemFromDb()
}