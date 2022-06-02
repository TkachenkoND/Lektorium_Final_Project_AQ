package com.example.autoquest.domain.repository.database_repository

import com.example.autoquest.data.database.entity.QuestItemEntity

interface InsertQuestItemInDbRepository {
    suspend fun insertQuestItemInDb(questItem: QuestItemEntity): Any
}