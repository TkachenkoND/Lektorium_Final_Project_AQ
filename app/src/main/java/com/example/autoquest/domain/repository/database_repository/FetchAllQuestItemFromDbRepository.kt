package com.example.autoquest.domain.repository.database_repository

interface FetchAllQuestItemFromDbRepository {
    suspend fun fetchAllQuestItemFromDb(): Any
}