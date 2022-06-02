package com.example.autoquest.domain.repository.database_repository

interface FetchDataQuestItemFromDbRepository {
    suspend fun fetchDataQuestItemFromDb(questId: Int): Any
}