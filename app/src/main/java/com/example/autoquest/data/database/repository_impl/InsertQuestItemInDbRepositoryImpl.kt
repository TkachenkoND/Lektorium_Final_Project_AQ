package com.example.autoquest.data.database.repository_impl

import com.example.autoquest.data.database.dao.QuestItemDao
import com.example.autoquest.data.database.entity.QuestItemEntity
import com.example.autoquest.domain.repository.database_repository.InsertQuestItemInDbRepository

class InsertQuestItemInDbRepositoryImpl(
    private val questItemDao: QuestItemDao
) : InsertQuestItemInDbRepository {
    override suspend fun insertQuestItemInDb(questItem: QuestItemEntity) =
        questItemDao.insertQuestItemInDb(questItem)
}