package com.example.autoquest.domain.repository.database_repository

import com.example.autoquest.data.database.entity.QuestItemEntity

interface UpdateQuestItemInDbRepository {
    fun updateQuestItemInDb(listQuests: List<QuestItemEntity>)
}