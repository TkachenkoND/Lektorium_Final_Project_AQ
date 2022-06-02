package com.example.autoquest.data.database.repository_impl

import com.example.autoquest.data.database.dao.QuestItemDao
import com.example.autoquest.data.database.entity.QuestItemEntity
import com.example.autoquest.domain.repository.database_repository.UpdateQuestItemInDbRepository

class UpdateQuestItemInDbRepositoryImpl(
    private val questItemDao: QuestItemDao
) : UpdateQuestItemInDbRepository {
    override fun updateQuestItemInDb(listQuests: List<QuestItemEntity>) {
        questItemDao.updateQuestItemInDb(listQuests)
    }
}